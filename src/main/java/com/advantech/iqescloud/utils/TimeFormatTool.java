package com.advantech.iqescloud.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huqili.tp
 */
public class TimeFormatTool {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SIM = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat YEAR_MONTH_DAY = new SimpleDateFormat("yyyy-MM-dd");

    public static String getHoursAndMinutes(String date) throws ParseException {
        String HoursAndMinutes = SIM.format(SIMPLE_DATE_FORMAT.parse(date));
        return HoursAndMinutes;
    }

    public static String getNow() {
        String now = SIMPLE_DATE_FORMAT.format(new Date());
        return now;
    }

    public static String getTenMinutesAfter() {
        long tenMinutesAfter = System.currentTimeMillis() + 10 * 60 * 1000;
        String ten = SIMPLE_DATE_FORMAT.format(new Date(tenMinutesAfter));
        return ten;
    }

    public static String getMinutesAfterFromNow(int n) {
        long minutesAfterFromNow = System.currentTimeMillis() + n * 60 * 1000;
        String date = SIMPLE_DATE_FORMAT.format(new Date(minutesAfterFromNow));
        return date;
    }

    public static String getMinutesAfter(String date, int n) throws ParseException {
        long minutesAfter = SIMPLE_DATE_FORMAT.parse(date).getTime() + n * 60 * 1000;
        String dateTime = SIMPLE_DATE_FORMAT.format(new Date(minutesAfter));
        return dateTime;
    }

    public static List<String> getNextWeek(String date) throws ParseException {
        List<String> nextWeek = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            long time = YEAR_MONTH_DAY.parse(date).getTime() + i * 24 * 60 * 60 * 1000;
            String day = YEAR_MONTH_DAY.format(new Date(time));
            nextWeek.add(day);
        }
        return nextWeek;
    }

    public static String getDaysAfterFromNow(int day, int minutes) {
        long AfterFromNow = System.currentTimeMillis() + day * 24 * 60 * 60 * 1000 + minutes * 60 * 1000;
        String date = SIMPLE_DATE_FORMAT.format(new Date(AfterFromNow));
        return date;
    }
}
