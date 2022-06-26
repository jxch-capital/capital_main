package org.jxch.capital.stock.ds.api;

import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component("stockDailyKLine3API")
@FeignClient(name = "${capital.ds3.service-name}", fallback = StockDailyKLine3Hystrix.class
        , contextId = "stockDailyKLine3API")
public interface StockDailyKLine3API {

    @PostMapping("/daily/search-single")
    List<KLineVO> searchSingleton(@RequestBody SearchDailyKLineDTO dto);

    @PostMapping("/daily/search-all")
    List<StockKLineVO> searchAll(@RequestBody SearchAllDailyKLineDTO dto);

}
