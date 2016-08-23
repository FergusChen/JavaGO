package main.collection;

import java.util.*;
/**
 * Created by Administrator on 2016/8/22.
 */
public class collectionDemo {
    public static void main(String[] args){
        ArrayList arr1 = new ArrayList();
        arr1.add("a1");
        arr1.add("b1");
        arr1.add("c1");
        ArrayList arr2 = new ArrayList();
        arr2.add("a1");
        arr2.add("b1");
        arr2.add("c2");
        System.out.println("size:" + arr1.size());
        //只保留arr1中与arr2相同的元素。
        arr1.retainAll(arr2);
        ListIterator iter = arr1.listIterator();
        while(iter.hasNext()){
            Object item = iter.next();
            if(item.equals("b1")){
                iter.add("has b1");
            }
        }

        System.out.println(arr1);

    }

}
