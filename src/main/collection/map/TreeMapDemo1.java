package main.collection.map;

import main.basic.IntegerDemo;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by yudong on 17/1/8.
 */
public class TreeMapDemo1 {
    public static void main(String[] args){
        String s = "comfortable---sunshine---haha";
        System.out.println(charCount(s));
    }

    public static String charCount(String str){
        char[] chs = str.toCharArray();
        //不能是基本类型, 可以是基本类型的包装器类型
        TreeMap<Character, Integer> tm = new TreeMap<>();
        for(int i = 0; i < chs.length; i++){
            Integer count = tm.getOrDefault(chs[i], 0);
            count++;
            tm.put(chs[i],count);

        }

        StringBuilder sb = new StringBuilder();
        Iterator<Character> iter = tm.keySet().iterator();
        while(iter.hasNext()){
            Character key = iter.next();
            sb.append(key + "(" + tm.get(key) + ") ");
        }
        return sb.toString();
    }
}
