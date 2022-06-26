package org.jxch.capital.stock.ds.three.controller;

import org.jxch.capital.stock.ds.api.StockDailyKLine3API;
import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.jxch.capital.stock.ds.three.service.StockDailyKLineService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock/3/daily/")
public class StockDailyKLine3Controller implements StockDailyKLine3API {

    private final StockDailyKLineService stockDailyKLineService;

    public StockDailyKLine3Controller(@Qualifier("stockDailyKLineService") StockDailyKLineService stockDailyKLineService) {
        this.stockDailyKLineService = stockDailyKLineService;
    }

    @PostMapping("/search-singleton")
    @ResponseBody
    @Override
    public List<KLineVO> searchSingleton(@RequestBody SearchDailyKLineDTO param) {
        return stockDailyKLineService.searchSingleton(param);
    }

    @PostMapping("/search-all")
    @ResponseBody
    @Override
    public List<StockKLineVO> searchAll(@RequestBody SearchAllDailyKLineDTO param) {
        return stockDailyKLineService.searchAll(param);
    }
}
