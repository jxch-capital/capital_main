package org.jxch.capital.stock.ds.entity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jxch.capital.stock.ds.entity.StockType;

import java.util.Date;

@Data
@Builder
@Accessors(chain = true)
public class SearchDailyKLineDTO {
    private Date start;
    private Date end;
    private String code;
    private StockType stockType;

    public boolean hasStockType() {
        return stockType != null;
    }
}
