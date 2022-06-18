package org.jxch.capital.stock.ds.entity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jxch.capital.stock.ds.entity.StockType;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class SearchAllDailyKLineDTO {
    private Date start;
    private Date end;
    private List<String> codes;
    private StockType stockType;

    public boolean hasStockType() {
        return stockType != null;
    }
}
