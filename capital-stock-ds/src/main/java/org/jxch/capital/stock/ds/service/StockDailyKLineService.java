package org.jxch.capital.stock.ds.service;

import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;

import java.util.List;

public interface StockDailyKLineService {
    List<KLineVO> search(SearchDailyKLineDTO dto);
    List<StockKLineVO> search(SearchAllDailyKLineDTO dto);
}
