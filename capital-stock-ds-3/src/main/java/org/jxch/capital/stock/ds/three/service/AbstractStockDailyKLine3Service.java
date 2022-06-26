package org.jxch.capital.stock.ds.three.service;

import lombok.NonNull;
import org.jxch.capital.stock.ds.entity.StockType;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractStockDailyKLine3Service implements StockDailyKLine3Service {
    private final AtomicInteger order = new AtomicInteger(Integer.MAX_VALUE);
    private final Date wakeUpDate = new Date();
    private final Calendar calendar = Calendar.getInstance();

    @Override
    public boolean support(Date start, Date end, StockType stockType) {
        return calendar.getTime().compareTo(wakeUpDate) > 0;
    }

    @Override
    public void sleep(long time,@NonNull TimeUnit unit) {
        wakeUpDate.setTime(calendar.getTime().getTime() + unit.toMillis(time));
    }

    @Override
    public int getOrder() {
        return order.get();
    }

    protected void decrementOrder() {
        this.order.decrementAndGet();
    }

}
