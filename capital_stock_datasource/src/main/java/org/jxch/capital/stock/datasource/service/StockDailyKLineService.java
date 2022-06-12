package org.jxch.capital.stock.datasource.service;

import org.jxch.capital.stock.datasource.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.datasource.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.datasource.entity.vo.KLineVO;
import org.jxch.capital.stock.datasource.entity.vo.StockKLineVO;

import java.util.List;

public interface StockDailyKLineService {
    List<KLineVO> search(SearchDailyKLineDTO dto);
    List<StockKLineVO> search(SearchAllDailyKLineDTO dto);
}
