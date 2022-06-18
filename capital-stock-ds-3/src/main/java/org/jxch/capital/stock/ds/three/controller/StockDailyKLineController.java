package org.jxch.capital.stock.ds.three.controller;

import org.jxch.capital.stock.ds.api.StockDailyKLineAPI;
import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.jxch.capital.stock.ds.three.service.StockDailyKLine3Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock/3/daily/")
public class StockDailyKLineController implements StockDailyKLineAPI {

    private final StockDailyKLine3Service stockDailyKLine3Service;

    public StockDailyKLineController(StockDailyKLine3Service stockDailyKLine3Service) {
        this.stockDailyKLine3Service = stockDailyKLine3Service;
    }

    @PostMapping("/search-singleton")
    @ResponseBody
    @Override
    public List<KLineVO> search(@RequestBody SearchDailyKLineDTO dto) {
        return stockDailyKLine3Service.searchSingleton(dto);
    }

    @PostMapping("/search-all")
    @ResponseBody
    @Override
    public List<StockKLineVO> search(@RequestBody SearchAllDailyKLineDTO dto) {
        return stockDailyKLine3Service.searchAll(dto);
    }
}
