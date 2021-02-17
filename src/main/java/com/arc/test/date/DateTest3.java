package com.arc.test.date;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yechao
 * @since 2020/6/1 7:09 下午
 */
public class DateTest3 {

    public static void main(String[] args) throws Exception {

        fun2();
    }


    private static void fun2() throws Exception {
        String pattern = "MMdd";
        DateFormat monthDayDateFormat = new SimpleDateFormat(pattern);
        Date now = new Date();
        now=PrintDate.stringToDate("2020-08-01 00:00:00");
        String format = monthDayDateFormat.format(now);
        System.out.println(now.toLocaleString() + "今天=" + format);

        Date yesterday = DateUtils.addDays(now, -1);
        String dateName = monthDayDateFormat.format(yesterday);
        System.out.println(yesterday.toLocaleString() + "昨天=" + dateName);

        //Date tempDate = dateFormat.parse("2020-06-01");
        //Date yesterdayDte = DateUtils.addDays(tempDate, -1);

    }

}
