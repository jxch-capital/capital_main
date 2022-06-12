package org.jxch.capital.stock.datasource.entity.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class KLineVO {
    private String date;
    private String open;
    private String close;
    private String high;
    private String low;
    private String vol;
}
