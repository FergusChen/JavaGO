package main.regex;

import java.util.TreeSet;

/**
 * Created by yudong on 17/2/15.
 */
public class RegexExec {
    public static void main(String[] args){
        sortIP();
    }

    /**
     * 把IP地址按照各段进行排序, 涉及对IP字符串的处理.
     */
    public static void sortIP(){
        String ip = "192.168.1.113 10.10.11.9 102.49.23.44 2.2.1.3 8.102.33.4";

        //将各个数字前面补两个0
        ip = ip.replaceAll("(\\d+)", "00$1");
        System.out.println("ip: " + ip);

        //各段取末3位数字
        ip = ip.replaceAll("0*(\\d{3})", "$1");
        System.out.println("ip: " + ip);

        //放入TreeSet中进行排序

        String[] arr= ip.split(" +"); //以空格分割
        TreeSet<String> set = new TreeSet<>();
        for(String s: arr){
            set.add(s);
        }

        for(String s: set){
            System.out.println(s.replaceAll("0*(\\d+)", "$1"));
        }
    }
}
