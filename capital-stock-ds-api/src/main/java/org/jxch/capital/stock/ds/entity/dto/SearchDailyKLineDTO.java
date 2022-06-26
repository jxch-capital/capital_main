package org.jxch.capital.stock.ds.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jxch.capital.stock.ds.entity.StockType;
import org.jxch.capital.stock.ds.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@Accessors(chain = true)
public class SearchDailyKLineDTO {
    @DateTimeFormat(pattern = DateUtil.D_PATTERN)
    @JsonFormat(pattern = DateUtil.D_PATTERN, timezone = DateUtil.D_TIMEZONE)
    private Date start;
    @DateTimeFormat(pattern = DateUtil.D_PATTERN)
    @JsonFormat(pattern = DateUtil.D_PATTERN, timezone = DateUtil.D_TIMEZONE)
    private Date end;
    private String code;
    private StockType stockType;
}
