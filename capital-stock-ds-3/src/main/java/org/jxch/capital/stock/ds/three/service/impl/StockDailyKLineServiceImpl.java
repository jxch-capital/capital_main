package org.jxch.capital.stock.ds.three.service.impl;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.jxch.capital.stock.ds.three.service.StockDailyKLine3Service;
import org.jxch.capital.stock.ds.three.service.StockDailyKLineService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("stockDailyKLineService")
public class StockDailyKLineServiceImpl implements StockDailyKLineService, ApplicationContextAware {
    private final static int SEARCH_ALL_PARTITION = 100;
    private ApplicationContext context;
    private List<StockDailyKLine3Service> stock3Services;

    @PostConstruct
    public void getStockDailyKLine3Services() {
        stock3Services = context.getBeansOfType(StockDailyKLine3Service.class).values().stream().toList();
    }

    private List<StockDailyKLine3Service> sort3Services() {
        return this.stock3Services = this.stock3Services.stream()
                .sorted(Comparator.comparingInt(StockDailyKLine3Service::getOrder)).toList();
    }

    @Override
    public List<KLineVO> searchSingleton(SearchDailyKLineDTO dto) {
        sort3Services();

        for (StockDailyKLine3Service service : this.stock3Services) {
            if (service.support(dto.getStart(), dto.getEnd(), dto.getStockType()) &&
                    service.supportSearchSingleton(dto)) {
                try {
                    log.info("3?????????:[{}]", service.getClass());
                    return service.searchSingleton(dto);
                } catch (UnsupportedOperationException ignored) {
                } catch (Throwable e) {
                    e.printStackTrace();
                    log.warn("????????????, ???????????????1?????? :[{}]", service.getClass());
                    service.sleep(1, TimeUnit.HOURS);
                }
            }
        }

        throw new RuntimeException("????????????????????????");
    }

    @Override
    public List<StockKLineVO> searchAll(SearchAllDailyKLineDTO dto) {
        LinkedBlockingQueue<StockDailyKLine3Service> services = new LinkedBlockingQueue<>(sort3Services());
        Queue<StockKLineVO> vos = new ConcurrentLinkedQueue<>();
        Lists.partition(dto.getCodes(), SEARCH_ALL_PARTITION).parallelStream().forEach(codes -> {
            SearchAllDailyKLineDTO dtoP = SearchAllDailyKLineDTO.builder()
                    .start(dto.getStart()).end(dto.getEnd()).stockType(dto.getStockType()).codes(codes).build();

            for (int i = 0; i < this.stock3Services.size() * 2; i++) {
                try {
                    StockDailyKLine3Service service = services.poll(10, TimeUnit.MINUTES);
                    if (service != null &&
                            service.support(dtoP.getStart(), dtoP.getEnd(), dtoP.getStockType()) &&
                            service.supportSearchAll(dtoP)) {
                        try {
                            log.info("3?????????:[{}]", service.getClass());
                            vos.addAll(service.searchAll(dtoP));
                            services.offer(service);
                            break;
                        } catch (UnsupportedOperationException ignored) {
                        } catch (Throwable e) {
                            e.printStackTrace();
                            log.warn("????????????, ???????????????1?????? :[{}]", service.getClass());
                            service.sleep(2, TimeUnit.HOURS);
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return vos.stream().toList();
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
