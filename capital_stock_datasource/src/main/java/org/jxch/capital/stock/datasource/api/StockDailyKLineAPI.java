package org.jxch.capital.stock.datasource.api;

import org.jxch.capital.stock.datasource.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.datasource.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.datasource.entity.vo.KLineVO;
import org.jxch.capital.stock.datasource.entity.vo.StockKLineVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component("stockDailyKLineAPI")
@FeignClient(name = "${capital.stock-datasource.service-name}", fallback = StockDailyKLineHystrix.class
        , contextId = "stockDailyKLineAPI")
public interface StockDailyKLineAPI {

    @PostMapping("/daily/search-single")
    List<KLineVO> search(@RequestBody SearchDailyKLineDTO dto);

    @PostMapping("/daily/search-all")
    List<StockKLineVO> search(@RequestBody SearchAllDailyKLineDTO dto);

}
