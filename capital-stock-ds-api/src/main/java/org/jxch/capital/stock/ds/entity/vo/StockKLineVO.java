package org.jxch.capital.stock.ds.entity.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class StockKLineVO {
    private String code;
    private List<KLineVO> kLineVOS;
}
