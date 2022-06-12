package org.jxch.capital.stock.datasource.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jxch.capital.stock.datasource.api.StockDailyKLineAPI;
import org.jxch.capital.stock.datasource.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.datasource.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.datasource.entity.vo.KLineVO;
import org.jxch.capital.stock.datasource.entity.vo.StockKLineVO;
import org.jxch.capital.stock.datasource.service.StockDailyKLineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StockDailyKLineServiceImpl implements StockDailyKLineService {
    private final StockDailyKLineAPI stockDailyKLineAPI;

    public StockDailyKLineServiceImpl(StockDailyKLineAPI stockDailyKLineAPI) {
        this.stockDailyKLineAPI = stockDailyKLineAPI;
    }


    @Override
    public List<KLineVO> search(SearchDailyKLineDTO dto) {

        List<KLineVO> search = stockDailyKLineAPI.search(dto);

        return null;
    }

    @Override
    public List<StockKLineVO> search(SearchAllDailyKLineDTO dto) {
        return null;
    }
}
