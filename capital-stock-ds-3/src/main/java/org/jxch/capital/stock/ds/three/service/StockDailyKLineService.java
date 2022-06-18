package org.jxch.capital.stock.ds.three.service;

import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;

import java.util.List;

public interface StockDailyKLineService {
    List<KLineVO> searchSingleton(SearchDailyKLineDTO dto);

    List<StockKLineVO> searchAll(SearchAllDailyKLineDTO dto);
}
