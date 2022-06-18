package org.jxch.capital.stock.ds.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jxch.capital.stock.ds.entity.StockType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@Accessors(chain = true)
public class SearchDailyKLineDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date end;
    private String code;
    private StockType stockType;
}
