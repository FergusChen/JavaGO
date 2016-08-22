package main.CollectionDemo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ArrayListDemo {
    public static void main(String[] args){

        ArrayList arr1 = new ArrayList();
        arr1.add("a1");
        arr1.add("a2");
        arr1.add("a1");
        arr1.add("a3");
        arr1.add("a2");

        ArrayList arr2 = getNonRepeatArrayList(arr1);
        System.out.println(arr2);

        ArrayList personArr = new ArrayList();
        personArr.add(new Person("zhangsan",15));
        personArr.add(new Person("lisi",15));
        personArr.add(new Person("sansan",15));
        personArr.add(new Person("lisi",15));
        personArr.add(new Person("zhangsan",15));
        personArr = getNonRepeatArrayList(personArr);
        Iterator iter = personArr.iterator();
        while (iter.hasNext()){
            Person p1 = (Person)iter.next();
            System.out.println(p1.getName() +"..." + p1.getAge());
        }
        System.out.println(personArr);

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

class Person{
    private String name;
    private int age;
    Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    //重写equals方法
    public boolean equals(Object obj){
        if(!(obj instanceof Object)) {
            return false;
        }
        Person p1 = (Person)obj;
        return this.name.equals(p1.name) && this.age == p1.age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
}
