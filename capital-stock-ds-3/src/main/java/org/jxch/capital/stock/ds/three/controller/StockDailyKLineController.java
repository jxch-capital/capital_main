package org.jxch.capital.stock.ds.three.controller;

import org.jxch.capital.stock.ds.api.StockDailyKLineAPI;
import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.jxch.capital.stock.ds.three.service.StockDailyKLine3Service;
import org.jxch.capital.stock.ds.three.service.StockDailyKLineService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock/3/daily/")
public class StockDailyKLineController implements StockDailyKLineAPI {

    private final StockDailyKLineService stockDailyKLineService;

    public StockDailyKLineController(@Qualifier("stockDailyKLineService") StockDailyKLineService stockDailyKLineService) {
        this.stockDailyKLineService = stockDailyKLineService;
    }

    @PostMapping("/search-singleton")
    @ResponseBody
    @Override
    public List<KLineVO> search(@RequestBody SearchDailyKLineDTO param) {
        return stockDailyKLineService.searchSingleton(param);
    }

    @PostMapping("/search-all")
    @ResponseBody
    @Override
    public List<StockKLineVO> search(@RequestBody SearchAllDailyKLineDTO param) {
        return stockDailyKLineService.searchAll(param);
    }
}
