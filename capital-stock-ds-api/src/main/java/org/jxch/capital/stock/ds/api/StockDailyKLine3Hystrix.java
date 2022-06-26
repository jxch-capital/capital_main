package org.jxch.capital.stock.ds.api;

import lombok.extern.slf4j.Slf4j;
import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("stockDailyKLineHystrix")
public class StockDailyKLine3Hystrix implements StockDailyKLine3API {
    @Value("${app.stock.ds.retry:3}")
    private int retry;

    @Value("${app.stock.ds.sleep-second:1000}")
    private int sleepSecond;

    private final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    private final StockDailyKLine3API stockDailyKLine3API;

    public StockDailyKLine3Hystrix(StockDailyKLine3API stockDailyKLine3API) {
        this.stockDailyKLine3API = stockDailyKLine3API;
    }

    @Nullable
    @Override
    public List<KLineVO> searchSingleton(SearchDailyKLineDTO dto) {
        retry();
        return stockDailyKLine3API.searchSingleton(dto);
    }

    @Nullable
    @Override
    public List<StockKLineVO> searchAll(SearchAllDailyKLineDTO dto) {
        retry();
        return stockDailyKLine3API.searchAll(dto);
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
