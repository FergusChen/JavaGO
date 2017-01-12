package main.collection.generic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by qnchen on 17/1/7.
 * 泛型，
 */
public class GenericDemo {
    public static void main(String[] args){
        ArrayList<String> array = new ArrayList<String>();
        array.add("generic");
        array.add("generic");
//        array.add(100); //集合本身只能存储基本数据类型，jdk1.5之后的自动装箱

        Iterator iter = array.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}
