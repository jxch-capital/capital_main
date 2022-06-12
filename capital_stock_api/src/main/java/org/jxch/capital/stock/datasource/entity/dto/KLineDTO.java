package org.jxch.capital.stock.datasource.entity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Accessors(chain = true)
public class KLineDTO {
    private Date date;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal vol;
}
