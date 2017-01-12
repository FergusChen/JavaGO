package main.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yudong on 17/1/8.
 */
public class TreeMapDemo {
    public static void main(String[] args){
        TreeMap<Friend, String> friends = new TreeMap<>();
        friends.put(new Friend("zhangsan", 21), "beijing");
        friends.put(new Friend("lisi", 23), "shanghai");
        friends.put(new Friend("wangwu", 22), "tianjin");
        friends.put(new Friend("zhaoliu", 22), "hangzhou");

        //遍历方法1
        Iterator<Friend> iter1 = friends.keySet().iterator();
        while(iter1.hasNext()){
            Friend friend = iter1.next();
            System.out.println(friend.getName() + "..." + friend.getAge() + "......" + friends.get(friend));
        }
        System.out.println();

        //遍历方法2
        Iterator<Map.Entry<Friend, String>> iter2 = friends.entrySet().iterator();
        while(iter2.hasNext()){
            Map.Entry<Friend, String> entry = iter2.next();
            Friend friend = entry.getKey();
            String city = entry.getValue();
            System.out.println(friend.getName() + "..." + friend.getAge() + "......" + city);

        }

    }
}
