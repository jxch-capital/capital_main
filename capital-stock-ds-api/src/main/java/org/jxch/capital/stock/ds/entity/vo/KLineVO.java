package org.jxch.capital.stock.ds.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jxch.capital.stock.ds.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Accessors(chain = true)
@Builder
public class KLineVO {
    @DateTimeFormat(pattern = DateUtil.D_PATTERN)
    @JsonFormat(pattern = DateUtil.D_PATTERN, timezone = DateUtil.D_TIMEZONE)
    private String date;
    private String open;
    private String close;
    private String high;
    private String low;
    private String vol;
}
