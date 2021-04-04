package com.kevzz.stockcalc.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormattingUtility {

    public static String process(String originalDate, String originalFormat, String targetFormat) {

        try {
            DateFormat originalDateFormat = new SimpleDateFormat(originalFormat);
            DateFormat targetDateFormat = new SimpleDateFormat(targetFormat);
            Date date = originalDateFormat.parse(originalDate);
            return targetDateFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String process(Calendar originalDate, String targetFormat) {

        try {
            DateFormat targetDateFormat = new SimpleDateFormat(targetFormat);
            return targetDateFormat.format(originalDate.getTime());
        } catch (Exception ex) {
            return null;
        }
    }

    public static Calendar revProcess(String originalDate, String originalFormat) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(originalFormat);

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(simpleDateFormat.parse(originalDate));

            return calendar;
        } catch (Exception ex) {
            return null;
        }
    }
}
