package org.jxch.capital.stock.datasource.api;

import lombok.extern.slf4j.Slf4j;
import org.jxch.capital.stock.datasource.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.datasource.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.datasource.entity.vo.KLineVO;
import org.jxch.capital.stock.datasource.entity.vo.StockKLineVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("stockDailyKLineHystrix")
public class StockDailyKLineHystrix implements StockDailyKLineAPI {
    @Value("${capital.stock-datasource.retry}")
    private int retry;

    @Value("${capital.stock-datasource.sleep-second}")
    private int sleepSecond;

    private final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    private final StockDailyKLineAPI stockDailyKLineAPI;

    public StockDailyKLineHystrix(@Qualifier("stockDailyKLineAPI") StockDailyKLineAPI stockDailyKLineAPI) {
        this.stockDailyKLineAPI = stockDailyKLineAPI;
    }

    @Nullable
    @Override
    public List<KLineVO> search(SearchDailyKLineDTO dto) {
        retry();
        return stockDailyKLineAPI.search(dto);
    }

    @Nullable
    @Override
    public List<StockKLineVO> search(SearchAllDailyKLineDTO dto) {
        retry();
        return stockDailyKLineAPI.search(dto);
    }

    private void retry() {
        if (threadLocal.get() == null) {
            threadLocal.set(retry);
        }

        threadLocal.set(threadLocal.get() - 1);

        if (threadLocal.get() < 0)
            throw new RuntimeException("被限流");

        try {
            TimeUnit.SECONDS.sleep(sleepSecond);
        } catch (InterruptedException ignored) {
        }
    }
}