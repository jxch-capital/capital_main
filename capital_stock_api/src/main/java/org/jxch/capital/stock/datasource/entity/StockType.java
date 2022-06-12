package org.jxch.capital.stock.datasource.entity;

public enum StockType {
    SH("sh"), SZ("sz");

    private String type;

    StockType(String type) {
        this.type = type;
    }
}
