package org.jxch.capital.stock.ds.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String Date2String(Date date) {
        return FORMATTER.format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public static String Date2String(Date date, DateTimeFormatter formatter) {
        return formatter.format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public static String Date2String(Date date, String pattern) {
        return DateTimeFormatter.ofPattern(pattern)
                .format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

}
