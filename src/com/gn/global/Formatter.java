package com.gn.global;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Formatter {
    public static LocalDate toLocalDate(Date date) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return LocalDate.of(year, month, day);
    }
}
