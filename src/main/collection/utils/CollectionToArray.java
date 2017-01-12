package main.collection.utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by yudong on 17/1/9.
 * 把集合变成数组, 目的是为了 [限定对集合的操作].
 * 返回一个list,则可以有增删. 但是如果不可以增删, 则可以返回1个数组.
 */
public class CollectionToArray {
    public static void main(String[] args){
        ArrayList<String> a1 = new ArrayList<>();
        a1.add("aaa");
        a1.add("bbb");
        a1.add("ccc");

        String[] strArr = a1.toArray(new String[a1.size()]);
        System.out.println(Arrays.toString(strArr));

        String[] strArr1 = a1.toArray(new String[5]); //这里使用传递进来的数组,多余位置用null填充
        System.out.println(Arrays.toString(strArr1));
    }
}
