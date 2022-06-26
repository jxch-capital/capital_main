package org.jxch.capital.stock.ds.three.service.impl;

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
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("stockDailyKLineService")
public class StockDailyKLineServiceImpl implements StockDailyKLineService, ApplicationContextAware {
    private ApplicationContext context;
    private List<StockDailyKLine3Service> stock3Services;

    @PostConstruct
    public void getStockDailyKLine3Services() {
        stock3Services = context.getBeansOfType(StockDailyKLine3Service.class).values().stream().toList();
    }

    private void sort3Services() {
        this.stock3Services = this.stock3Services.stream()
                .sorted(Comparator.comparingInt(StockDailyKLine3Service::getOrder)).toList();
    }

    @Override
    public List<KLineVO> searchSingleton(SearchDailyKLineDTO dto) {
        sort3Services();

        for (StockDailyKLine3Service service : this.stock3Services) {
            if (service.support(dto.getStart(), dto.getEnd(), dto.getStockType()) &&
                    service.supportSearchSingleton(dto)) {
                try {
                    log.info("3方查询:[{}]", service.getClass());
                    return service.searchSingleton(dto);
                } catch (UnsupportedOperationException ignored) {
                } catch (Throwable e) {
                    e.printStackTrace();
                    log.warn("查询失败, 该接口休眠1小时 :[{}]", service.getClass());
                    service.sleep(1, TimeUnit.HOURS);
                }
            }
        }

        throw new RuntimeException("接口可能均被限流");
    }

    @Override
    public List<StockKLineVO> searchAll(SearchAllDailyKLineDTO dto) {
        sort3Services();

        for (StockDailyKLine3Service service : this.stock3Services) {
            if (service.support(dto.getStart(), dto.getEnd(), dto.getStockType()) &&
                    service.supportSearchAll(dto)) {
                try {
                    log.info("3方查询:[{}]", service.getClass());
                    return service.searchAll(dto);
                } catch (UnsupportedOperationException ignored) {
                } catch (Throwable e) {
                    e.printStackTrace();
                    log.warn("查询失败, 该接口休眠1小时 :[{}]", service.getClass());
                    service.sleep(2, TimeUnit.HOURS);
                }
            }
        }

        throw new RuntimeException("接口可能均被限流");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
