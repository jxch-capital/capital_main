package org.jxch.capital.stock.datasource.entity.vo;

import lombok.*;
import lombok.experimental.Accessors;

@Builder
@Data
@Accessors(chain = true)
public class ResponseVO<T> {
    private Integer code;
    private String msg;
    private long timestamp;
    private T data;
}
