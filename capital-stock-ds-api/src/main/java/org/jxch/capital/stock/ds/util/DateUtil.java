package org.jxch.capital.stock.ds.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static final String D_PATTERN = "yyyy-MM-dd";
    public static final String D_TIMEZONE = "GMT+8";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(D_PATTERN);

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

    public static Date string2Date(String pattern) {
        return Date.from(LocalDate.from(FORMATTER.parse(pattern)).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
