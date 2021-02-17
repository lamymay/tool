package com.arc.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yechao
 * @since 2020/5/23 11:44 下午
 */
public class TestDate {

    public static void main(String[] args) throws ParseException {

        fun2();


    }

    private static void fun2() throws ParseException {
        Date past = stringToDate("2020-09-12 21:15:00");
        Date now = stringToDate("2020-09-12 21:10:00");
        long pastTime = past.getTime();
        long nowTime = now.getTime();
        System.out.println(pastTime);
        System.out.println(nowTime);
        //1599916320_000
        //1599916200_000
        SimpleDateFormat monthAndDayFormat = new SimpleDateFormat("HH:mm");
        String pastString = monthAndDayFormat.format(past);
        String nowString = monthAndDayFormat.format(now);
        Date parse1 = monthAndDayFormat.parse(pastString);
        Date parse2 = monthAndDayFormat.parse(nowString);

        System.out.println(parse1);
        System.out.println(parse2);

        long time = parse1.getTime();
        long time2 = parse2.getTime();
        System.out.println();
        System.out.println(time);
        System.out.println(time2);

        //1000*60
        System.out.println(time / (1000 * 6));
        System.out.println(time2 / (1000 * 6));

        System.out.println(time2 - time);

        int step = 5 * 60 * 1000;
        System.out.println("5min=s " + step);

        long between = time2 - time;

        if (between <= step) {
            System.out.println(true);
        }

        boolean isSame = isSameDate2(past, now);
        System.out.println(isSame);
        System.out.println(isSameDate2(now, past));
        System.out.println(isSameDate2(stringToDate("2020-09-12 21:10:00"), stringToDate("2020-09-12 21:05:55")));
        Calendar calendar = Calendar.getInstance();

    }

    private static boolean isSameDate2(Date past, Date now) {
        SimpleDateFormat monthAndDayFormat = new SimpleDateFormat("HH:mm");
        String pastString = monthAndDayFormat.format(past);
        String nowString = monthAndDayFormat.format(now);
        try {
            Date parse1 = monthAndDayFormat.parse(pastString);
            Date parse2 = monthAndDayFormat.parse(nowString);
            long time1 = parse1.getTime();
            long time2 = parse2.getTime();

            //1000*60
            int step = 5 * 60 * 1000;

            long between = Math.abs(time2 - time1);
            //两个时间5min以内算一样
            if (between <= step) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void fun3() {
        LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
        System.out.println(localDateTime);

        LocalDate now = LocalDate.now();
        String today = now.getYear() + now.getMonth().getValue() + now.getDayOfMonth() + "";
        System.out.println(today);
        Integer date = Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        String userId = "uid";
        String instanceCode = "insCode";
        String key = "duobao_" + userId + instanceCode;
        System.out.println(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        System.out.println(key);
        System.out.println(key + date);


    }

    /**
     * 比较两个时间是否相同 仅比较 小时+分钟
     *
     * @param date1 时间一
     * @param date2 时间二
     * @return boolean
     */
    private static boolean isSameTime(Date date1, Date date2) {
        SimpleDateFormat monthAndDayFormat = new SimpleDateFormat("HH:mm");
        String pastString = monthAndDayFormat.format(date1);
        String nowString = monthAndDayFormat.format(date2);
        //todo
        return pastString.equals(nowString);
    }

    public static void fun1(String[] args) {
        //21:00  2020-05-20T12:54:37.000Z  Thu May 21 19:42:24 CST 2020
        int awardTotal = 3;
        for (int i = 0; i < awardTotal; i++) {
            System.out.println(i);
        }

        //Date now = new Date();
        Date past = stringToDate("2019-09-12 21:10:00");
        Date now = stringToDate("2020-09-12 21:10:00");


        SimpleDateFormat monthAndDayFormat = new SimpleDateFormat("MM-dd");

        String pastString = monthAndDayFormat.format(past);
        String nowString = monthAndDayFormat.format(now);

        System.out.println("************** 测试时间  ****************");
        System.out.println(past);
        System.out.println(now);
        System.out.println(isSameDate(past, now));
        System.out.println(pastString.equals(nowString));
        System.out.println(isSameTime(past, now));
        System.out.println("******************************");
    }

    private static boolean isSameDate(Date d1, Date d2) {
        LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return localDate1.isEqual(localDate2);
    }


    public static Date stringToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

}
