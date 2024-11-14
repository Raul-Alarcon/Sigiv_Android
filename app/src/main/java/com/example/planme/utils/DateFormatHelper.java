package com.example.planme.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatHelper {
    public static String format(Date date, String template){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template, new Locale("es", "ES"));
        return  simpleDateFormat.format(date);
    }
}
