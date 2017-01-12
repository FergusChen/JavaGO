package main.collection.utils;

import main.basic.IntegerDemo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yudong on 17/1/9.
 * 对数组进行处理的工具类, float[], double[], int[]...
 * 包括排序, 查找, 反转等.
 *
 */
public class ArraysDemo {
    public static void main(String[] args){
        int[] arr = {2, 3, 4, 6};
        String str = Arrays.toString(arr);
        System.out.println("array: " + str);

        //Array to List
        Integer[] arr1 = {2, 3, 4, 5};
        List<Integer> list = Arrays.asList(arr1);  //返回不可变长度的list

        System.out.println("list: " + list);

    }
}
