package org.jxch.capital.stock.ds.entity;

import lombok.Getter;

public enum StockType {
    SH("sh"), SZ("sz");

    @Getter
    private final String type;

    StockType(String type) {
        this.type = type;
    }
}
