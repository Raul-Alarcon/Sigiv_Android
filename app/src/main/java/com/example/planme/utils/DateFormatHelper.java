package com.example.planme.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatHelper {

    private static final SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    public static String format(String stringDate, String template) {

        try {
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = formatter.parse(stringDate);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template, Locale.US);
            return  simpleDateFormat.format(date);
        } catch (ParseException e){
            throw new RuntimeException(e);
        }
    }

    public static Date stringToDate(String dateString) {
        try {
            formatter.setTimeZone(TimeZone.getTimeZone("CST"));
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCurrentDateTime(){
        return formatter.format(new Date());
    }
}
