package com.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    public static long calculateDateDifference(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            LocalDate d2 = LocalDate.parse(date2, formatter);
            return ChronoUnit.DAYS.between(d1, d2);
        } catch (Exception e) {
            System.out.println("Error parsing dates: " + e.getMessage());
            return -1;
        }
    }
}
