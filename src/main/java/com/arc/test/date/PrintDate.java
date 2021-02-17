package com.arc.test.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yechao
 * @since 2020/8/3 10:03 上午
 */
public class PrintDate {

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


    public static void main(String[] args) {
        Date now = new Date();
        System.out.println("此时时间戳="+now.getTime()+" 时间="+now.toLocaleString());

//        Date zeroToday = DateUtils.truncate(now, Calendar.DATE);
//        Date latestToday = DateUtils.truncate(now, Calendar.HOUR);
//        Date latestToday = DateUtils.truncate(DateUtils.addDays(now, 1),Calendar.HOUR);
//        System.out.println("当第一秒时间戳="+zeroToday.getTime()+" 时间="+zeroToday.toLocaleString());
//        System.out.println("当最后一秒时间戳="+latestToday.getTime()+" 时间="+latestToday.toLocaleString());

        Date date1 = stringToDate("2020-08-07 00:00:00");
        Date date2 = stringToDate("2020-08-07 23:59:59");
        System.out.println("---------------------------------------------------");
        System.out.println("当第一秒时间戳="+date1.getTime()+" 时间="+date1.toLocaleString());
        System.out.println("当最后一秒时间戳="+date2.getTime()+" 时间="+date2.toLocaleString());


        Date day1 = new Date(1596194622000L);
        System.out.println(day1.toLocaleString());
        System.out.println(new Date(1596194816000L).toLocaleString());

        System.out.println(new Date().getTime());

    }

}
