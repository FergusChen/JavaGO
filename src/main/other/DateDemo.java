package main.other;

/**
 * Created by yudong on 17/1/10.
 * java.util.Data
 */

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateDemo {
    public static void main(String[] args) {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss E");
        System.out.println(dateFormat.format(d));

        //Calendar对象
        Calendar c = Calendar.getInstance();
        System.out.println("year:" + c.get(Calendar.YEAR));

       c.set(2014, 10, 21);  //注意月份是索引
        printCalendar(c);

        c.add(Calendar.MONTH, 2);
        printCalendar(c);

        c.add(Calendar.DAY_OF_MONTH, -36);
        printCalendar(c);


    }

    public static void printCalendar(Calendar c){
        //java对月份的处理还是会用查表法
        String[] months = {"一月", "二月", "三月", "四月",
                "五月", "六月", "七月", "八月",
                "九月", "十月", "十一月", "十二月"};
        String[] weeks = {"", "礼拜日", "礼拜一", "礼拜二", "礼拜三", "礼拜四", "礼拜五", "礼拜六"};

        int monthIndex = c.get(Calendar.MONTH);
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(c.get(Calendar.YEAR) + "年" + months[monthIndex]
                + c.get(Calendar.DAY_OF_MONTH) + "日\t" + weeks[weekIndex]);
    }
}
