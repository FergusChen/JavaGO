package main.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

/**
 * Created by yudong on 17/1/8.
 * Map:
 *      HashTable:Hash表数据结构, 线程同步的
 *      HashMap: Hash表数据结构, 线程非同步, 允许使用null作为key和value。效率高。
 *      TreeMap: 底层是二叉排序树, 有排序.
 *
 */
public class MapDemo {
    public static void main(String[] args){
        Map<String, String> map = new HashMap<String, String>();

        //添加元素, put相同的键时,可以覆盖原有的值
        map.put("01", "oo01");
        map.put("02", "oo02");
        map.put("03", "oo03");

        System.out.println("containKey: " + map.containsKey("001")); //true or false

        System.out.println("get:" + map.get("023"));

        //遍历方法1---keySet
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()){
            String key = iter.next();
            System.out.println(key + "->" + map.getOrDefault(key, "null"));
        }
        //遍历方法2---entrySet  entrySet保存键值对的映射关系
        Set<Map.Entry<String, String>> entryKeys = map.entrySet();
        Iterator<Map.Entry<String, String>> iterEntry = entryKeys.iterator();
        while(iterEntry.hasNext()){
            Map.Entry<String, String> entry = iterEntry.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "->" + value);
        }

    }
}
