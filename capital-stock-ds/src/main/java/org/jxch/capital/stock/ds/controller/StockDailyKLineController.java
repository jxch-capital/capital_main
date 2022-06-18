package org.jxch.capital.stock.ds.controller;

import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daily")
public class StockDailyKLineController {

    

    @PostMapping("/search-all")
    @ResponseBody
    public List<StockKLineVO> search(@RequestParam("search-all") @RequestBody SearchAllDailyKLineDTO param) {
        return null;
    }

    @PostMapping("/search-single")
    @ResponseBody
    public List<KLineVO> search(@RequestParam("search-single") @RequestBody SearchDailyKLineDTO param) {
        return null;
    }

}
