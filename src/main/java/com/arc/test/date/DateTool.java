package com.arc.test.date;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author yechao
 * @since 2020/5/26 9:38 上午
 */
@Slf4j
public class DateTool {


    private static void testGetRefreshAwardTime() {
        //testWhile();
        Date date1 = stringToDate("2019-05-26 10:58:00");
        Date date2 = stringToDate("2019-09-12 21:10:00");
        Date date3 = stringToDate("2020-09-12 21:10:00");
        Date date4 = stringToDate("2020-09-12 21:10:00");
        Date date5 = stringToDate("2020-09-12 21:10:00");


        List<Date> dateList = new ArrayList<>();
        dateList.add(date1);
        dateList.add(date2);
        dateList.add(date3);
        dateList.add(date4);
        dateList.add(date5);

        getRefreshAwardTime(dateList);
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

    private static void testWhile() {
        int a = 0;
        while (a < 10) {
            try {
                Thread.sleep(500);
                a++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (a == 7) {
                //continue;
                break; //的话 a 到7为止
            }

            System.out.println(a);
        }
        System.out.println(a);
    }

    private static Date dateAddDay(Date now, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();

    }

    Date cache = null;


    public static void getRefreshAwardTime(List<Date> dateList) {
        for (Date originAwardTime : dateList) {
            //上线 --> 进行中 --> 开奖中 --> 开奖成功(时间是对的) //非开奖状态的时间需要加一天 并维护db
            Date trueAwardTime = originAwardTimeAddOneDay(originAwardTime);
            System.out.println(trueAwardTime.toLocaleString());

        }
    }

    private static Date originAwardTimeAddOneDay(Date originAwardTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originAwardTime);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }


//    /**
//     * 比较两个时间是否相同 仅比较 小时+分钟
//     * //static final int awardScheduleStep = 5 * 60 * 1000;
//     *
//     * @param past 时间一
//     * @param now  时间二
//     * @return boolean
//     */
//    @Deprecated
//    public static boolean isSameTime(Date past, Date now) {
//        SimpleDateFormat monthAndDayFormat = new SimpleDateFormat("HH:mm");
//        String pastString = monthAndDayFormat.format(past);
//        String nowString = monthAndDayFormat.format(now);
//        try {
//            Date parse1 = monthAndDayFormat.parse(pastString);
//            Date parse2 = monthAndDayFormat.parse(nowString);
//            long time1 = parse1.getTime();
//            long time2 = parse2.getTime();
//
//            long between = Math.abs(time2 - time1);
//            //两个时间5min以内算一样
//            if (between <= 3000) {
//                return true;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

//    /**
//     * 当前时间的小时分钟时间和 开奖时间小时分钟 比较
//     *
//     * @param awardTime 开奖时间
//     * @return boolean
//     */
//    public static boolean isNowAfterAwardTime(Date awardTime) {
//        SimpleDateFormat monthAndDayFormat = new SimpleDateFormat("HH:mm");
//        String pastString = monthAndDayFormat.format(awardTime);
//        String nowString = monthAndDayFormat.format(new Date());
//        try {
//            Date parse1 = monthAndDayFormat.parse(pastString);
//            Date parse2 = monthAndDayFormat.parse(nowString);
//            //今天在 开奖时间后(小时+分钟)
//            return parse2.after(parse1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    private static void fun2() {
        System.out.println(5 * 60 * 1000);
        Date date = fixTodayTime(stringToDate("1999-01-01 00:00:00"));
        printLocalDate(date);

    }

    private static void fun0() {
        //测试取出时间的小时和分钟
        Date day1 = stringToDate("2020-05-29 09:00:00");
        Date day2 = stringToDate("2020-05-29 10:00:00");
        //Date day3 = stringToDate("2020-05-29 16:10:00");
        Date now = new Date();

        long time1 = day1.getTime();
        long time2 = day2.getTime();
        long nowTime = now.getTime();

        long betweenSeconds = time2 - time1;//3600000 单位ms
        long between2 = nowTime - time1;//32644580 单位ms


        System.out.println(time1);
        System.out.println(time2);
        System.out.println(nowTime);
        System.out.println(betweenSeconds);
        System.out.println(between2);

        //忽略

    }

    private static void printLocalDate(Date date) {
        System.out.println(date.toLocaleString());
    }

    private static void printLocalDate(long date) {
        System.out.println(new Date(date).toLocaleString());
    }

    private static Date fixTodayTime(Date date1) {
        //只去到时间的小时&分钟 然后给出自然时间
        Date now = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime=" + localDateTime);
        System.out.println("now=" + now);
        System.out.println("date1=" + date1);

        long nowTime = now.getTime();
        long tempTime = date1.getTime();

        System.out.println(nowTime);
        System.out.println(tempTime);
        System.out.println(tempTime - nowTime);

        System.out.println("////");
        Date day1 = stringToDate("2020-01-01 00:00:00");
        Date day2 = stringToDate("2020-01-01 23:59:59");
        System.out.println(day1);
        System.out.println(day2);
        System.out.println(day1.getTime());
        System.out.println(day2.getTime());

        long sec = day2.getTime() - day2.getTime();
        System.out.println("一天的毫秒数=" + (-day2.getTime()));
        System.out.println("一天的秒数是sec=" + sec);
        long time1 = day1.getTime();
        long time2 = day2.getTime();

        long startUnix = time1 - sec;
        long endUnix = time2 - sec;
        System.out.println("一天开始的时刻,时间戳是=" + startUnix);
        System.out.println("一天结束的时刻,时间戳是=" + endUnix);

        return new Date(startUnix);
    }


    /**
     * 当前时间的小时分钟时间和 开奖时间小时分钟 比较
     *
     * @param awardTime 开奖时间
     * @return boolean
     */
    public static boolean isNowAfterAwardTime(Date awardTime) {
        Date now = new Date();
        SimpleDateFormat monthAndDayFormat = new SimpleDateFormat("HH:mm");
        String pastString = monthAndDayFormat.format(awardTime);
        String nowString = monthAndDayFormat.format(now);
        try {
            Date parse1 = monthAndDayFormat.parse(pastString);
            Date parse2 = monthAndDayFormat.parse(nowString);
            //今天在 开奖时间后(小时+分钟)
            boolean after = parse2.after(parse1);
            if (after) {
                //如果当前时间在开奖时间之后 不一定要开奖, 两分钟内则开奖
                boolean sameTime = isSameTime(awardTime, now);
                return after & sameTime;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean isSameTime(Date past, Date now) {
        SimpleDateFormat monthAndDayFormat = new SimpleDateFormat("HH:mm");
        String pastString = monthAndDayFormat.format(past);
        String nowString = monthAndDayFormat.format(now);
        try {
            Date parse1 = monthAndDayFormat.parse(pastString);
            Date parse2 = monthAndDayFormat.parse(nowString);
            long time1 = parse1.getTime();
            long time2 = parse2.getTime();

            long between = Math.abs(time2 - time1);
            //两个时间2min以内算一样
            long awardTimeDeviation = 180000L;
            if (between <= awardTimeDeviation) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        //fun10();
        //fun1();

        System.out.println(new Date(1591810199000L).toLocaleString());

        fun3();
    }

    private static void fun3() {
        Date date1 = stringToDate("2020-06-02 10:10:00");
        Date date2 = stringToDate("2020-06-02 10:12:00");
        Date date3 = stringToDate("2020-06-02 10:14:00");
        Date date4 = stringToDate("2020-06-02 10:14:02");

        boolean sameMinutes = isSameMinutes(date1, date2);
        System.out.println(sameMinutes);
        sameMinutes = isSameMinutes(date3, date4);
        System.out.println(sameMinutes);
    }

    private static void fun1() {
        // 1 数据准备 假定 定义开奖是是A
        Date date1 = stringToDate("2020-06-02 10:10:00");
        Date date2 = stringToDate("2020-06-02 10:12:00");
        Date date3 = stringToDate("2020-06-02 10:14:00");
        Date date4 = stringToDate("2020-06-02 10:20:00");


        List<Date> dateList = new ArrayList<>();
        dateList.add(date1);
        dateList.add(date2);
        dateList.add(date3);
        dateList.add(date4);

        // 2 调试
        for (Date date : dateList) {
            boolean canAward = canAwardNow(date);
            System.out.println("定义时间" + canAward);
        }


    }

    private static boolean canAwardNow(Date awardTime) {
        //期望 开奖时间到了就开奖,但是触发时机的问题存在,不能保证正好对应时刻能执行开奖,则比对是否能开奖是一个区间
        // 1 如果 now 在定义开奖时间前, 时间没有到一定不能开奖
        // 2 如果 now 在定义开奖时间后,可能可以开奖 也可能不能开奖

        //
        // 注意 now 是精确时间  awardTime 是不精确的时间
//        Date now = stringToDate("2020-06-02 10:10:00");

        //如何判断 now 在awardTime 前或者后?    drawing状态的
        // 开奖时间是 2020-06-02 10:10:00   假定now=2020-06-02 06:10:00  ---> 开奖吗? 不开奖
        // 开奖时间是 2020-06-03 10:10:00   假定now=2020-06-02 06:11:00  ---> 开奖吗? 不开奖

        SimpleDateFormat monthAndDayFormat = new SimpleDateFormat("HH:mm");
        String pastString = monthAndDayFormat.format(awardTime);
        String nowString = monthAndDayFormat.format(stringToDate("2020-06-02 10:10:00"));
        try {
            Date past = monthAndDayFormat.parse(pastString);
            Date now = monthAndDayFormat.parse(nowString);
            long pastTime = past.getTime();
            long nowTime = now.getTime();

            long after = nowTime - pastTime;

            //正数 意味着 now 晚于 pastTime,
            if (after < 0) {

            }
//            long between = Math.abs(time2 - time1);
            //两个时间2min以内算一样
            long awardTimeDeviation = 180000L;
//            if (between <= awardTimeDeviation) {
//                return true;
//            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isSameMinutes(Date now, Date past) {
        SimpleDateFormat yearMonthDayHourMinutesFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String pastString = yearMonthDayHourMinutesFormat.format(past);
        String nowString = yearMonthDayHourMinutesFormat.format(now);
        try {
            Date parse1 = yearMonthDayHourMinutesFormat.parse(pastString);
            Date parse2 = yearMonthDayHourMinutesFormat.parse(nowString);
            long time1 = parse1.getTime();
            long time2 = parse2.getTime();
            System.out.println(time1);
            System.out.println(time2);
            return time1 == time2;
            //两个时间2min以内算一样
        } catch (Exception e) {
            log.error("时间格式化转换异常啦,返回false=两个时间不一样", e);
            return false;
        }
    }


    private static void fun10() {
        Date now = new Date();
        Date date = dateAddDay(now, 1);
        System.out.println(date);
        testGetRefreshAwardTime();
        //fun1();
        //fun2();
        Date past = stringToDate("2020-05-29 18:30:00");
        System.out.println(isNowAfterAwardTime(past));
    }
}
