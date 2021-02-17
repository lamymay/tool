package com.arc.test.topic;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 叶超
 * @since 2019/8/25 22:38
 */
public class TestDate {


    public static void test(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        int duration = 1;
        cl.add(Calendar.MONTH, duration);
        DateTimeFormatter temp = DateTimeFormatter.ISO_DATE;
        //LocalDateTime parse = LocalDateTime.parse(cl.getTime().toString(), temp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("YYMMdd HH:mm:ss");
        String format = simpleDateFormat.format(cl.getTime());
        System.out.println(format);
    }


/*
    public static long nextMonthStartTimeMilli(long timestamp) {
        Clock clock = Clock.fixed(Instant.ofEpochMilli(timestamp), DEFAULT_ZONE_ID);
        Month nextMonth = MonthDay.now(clock).getMonth().plus(1);
        return LocalDate.now(clock).with(MonthDay.of(nextMonth, 1)).atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
    }

    public static long nextWeekStartTimeMilli(long timestamp) {
        return LocalDate.now(Clock.fixed(Instant.ofEpochMilli(timestamp), DEFAULT_ZONE_ID)).plusWeeks(1).with(DayOfWeek.MONDAY).atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
    }

    public static long tomorrowStartTimeMilli(long timestamp) {
        return LocalDate.now(Clock.fixed(Instant.ofEpochMilli(timestamp), DEFAULT_ZONE_ID)).plusDays(1).atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
    }

    */
/*** 距离下个自然月剩余的毫秒数*@return*//*

    public static long nextMonthLeftTimeMilli() {
        long now = System.currentTimeMillis();
        return nextMonthStartTimeMilli(now) - now;
    }

    */
/*** 距离下个周一剩余的毫秒数*@return*//*

    public static long nextWeekLeftTimeMilli() {
        long now = System.currentTimeMillis();
        return nextWeekStartTimeMilli(now) - now;
    }

    */
/*** 距离明天剩余的毫秒数*@return*//*

    public static long tomorrowLeftTimeMilli() {
        long now = System.currentTimeMillis();
        return tomorrowStartTimeMilli(now) - now;
    }
*/
}
