package com.example.planme.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatHelper {
    public static String format(Date date, String template){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template, Locale.getDefault());
        return  simpleDateFormat.format(date);
    }

    public static Date stringToDate(String dateString) {
        dateString = dateString.replace("CST", "America/Chicago");
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
