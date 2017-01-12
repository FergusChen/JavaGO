package main.collection.array;

import main.collection.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/8/22.
 * 数组的遍历, 排序
 * java的可变参数
 */
public class ArrayListDemo {
    public static void main(String[] args){

        ArrayList<String> arr1 = new ArrayList<>();
        arr1.add("a1");
        arr1.add("a2");
        arr1.add("a1");
        arr1.add("a3");
        arr1.add("a2");


        ArrayList arr2 = getNonRepeatArrayList(arr1);
        System.out.println(arr2);

        ArrayList<Person> personArr = new ArrayList<>();
        personArr.add(new Person("zhangsan",15));
        personArr.add(new Person("lisi",15));
        personArr.add(new Person("sansan",15));
        personArr.add(new Person("lisi",15));
        personArr.add(new Person("zhangsan",15));
        personArr = getNonRepeatArrayList(personArr);
        System.out.println("Iterator迭代器:");
        Iterator iter = personArr.iterator();
        while (iter.hasNext()){
            Person p1 = (Person)iter.next();
            System.out.println(p1.getName() +"..." + p1.getAge());
        }

        //for循环遍历, 对于列表和基本数组都可以
        System.out.println("\nfor循环:");
        for(Person p : personArr){
            System.out.println(p.getName() +"..." + p.getAge());
        }
        System.out.println(personArr);

        /** 可变参数 */
        show(1);
        show(2,3,4,5);
        show(5,3,4,2,51,3,2);
        show();
        show("note" , 4, 5, 6, 3);

    }

    public static void show(int... arr){
        System.out.println(Arrays.toString(arr));
    }

    public static void show(String str, int... arr){
        System.out.println(str + ": " + Arrays.toString(arr));
    }
    public static  ArrayList getNonRepeatArrayList(ArrayList arr){
        ArrayList nonRepeatArr = new ArrayList();
        Iterator iter = arr.iterator();
        while(iter.hasNext()){
            Object obj = iter.next();
            if(!nonRepeatArr.contains(obj)){
                nonRepeatArr.add(obj);
            }

        }
        return nonRepeatArr;
    }
}


