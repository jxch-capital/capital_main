package org.jxch.capital.stock.ds.three.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jxch.capital.stock.ds.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SinaKLineVO {
    @DateTimeFormat(pattern = DateUtil.D_PATTERN)
    @JsonFormat(pattern = DateUtil.D_PATTERN, timezone = DateUtil.D_TIMEZONE)
    private Date day;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
}
