package org.jxch.capital.stock.ds.three.service;

import org.jxch.capital.stock.ds.entity.StockType;
import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.springframework.core.Ordered;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public interface StockDailyKLine3Service extends StockDailyKLineService, Ordered {

    default boolean support(Date start, Date end, StockType stockType) {
        return true;
    }

    default void sleep(long time, TimeUnit unit) {
    }

    default boolean supportSearchSingleton(SearchDailyKLineDTO dto) {
        return true;
    }

    default boolean supportSearchAll(SearchAllDailyKLineDTO dto) {
        return true;
    }
}
