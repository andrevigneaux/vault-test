package com.andre.vaulttest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final int firstFortnight = 14;

    public static Date getDate(String stringDate) {
        try {
            return sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getNow() {
        LocalDate localDate = LocalDate.now();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static boolean isDateTheFirstFortnight(Date date) {
        return getDayOfDate(date) <= firstFortnight;
    }

    public static Integer getDayOfDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();
    }
}
