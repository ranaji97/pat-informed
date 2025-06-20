package com.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    public static long calculateDateDifference(String filing, String publication) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate filingDate = LocalDate.parse(filing, formatter);
            LocalDate publicationDate = LocalDate.parse(publication, formatter);
            return ChronoUnit.DAYS.between(filingDate, publicationDate);
        } catch (Exception e) {
            System.out.println("Error parsing dates: " + e.getMessage());
            return -1;
        }
    }
}