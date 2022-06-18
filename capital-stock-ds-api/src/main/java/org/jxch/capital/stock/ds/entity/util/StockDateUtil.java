package org.jxch.capital.stock.ds.entity.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StockDateUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String date2string(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DATE_FORMATTER);
    }

    public static Date string2Date(String date) {
        return Date.from(LocalDateTime.parse(date, DATE_FORMATTER).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String nowString() {
        return date2string(new Date());
    }

}
