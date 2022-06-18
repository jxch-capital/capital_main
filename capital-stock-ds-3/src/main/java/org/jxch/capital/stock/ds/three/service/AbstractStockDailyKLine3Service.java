package org.jxch.capital.stock.ds.three.service;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractStockDailyKLine3Service implements StockDailyKLine3Service {
    private final AtomicInteger order = new AtomicInteger(Integer.MAX_VALUE);

    @Override
    public int getOrder() {
        return order.get();
    }

    protected void decrementOrder() {
        this.order.decrementAndGet();
    }

}
