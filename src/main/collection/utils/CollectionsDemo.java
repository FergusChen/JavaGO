package main.collection.utils;

import java.util.*;

/**
 * Created by yudong on 17/1/9.
 * Collections存在很多工具方法, 包括排序, 最大值, 反转等.
 * 另外, list等都是线程不安全的, Collections有相应的方法, 传入一般的list,返回线程安全的list
 */
public class CollectionsDemo {

    public static void main(String[] args){

//        sortDemo();

//        binarySearchDemo();

//        fillDemo();

        reverseOrderDemo();
    }

    public static void sortDemo(){
        List<String> list = new ArrayList<String>();

        list.add("abc");
        list.add("kk");
        list.add("baaa");
        list.add("java");
        list.add("kk");

        //-----sort------
//        Collections.sort(list); //默认排序
        System.out.println(list);

        Collections.sort(list, new StringLengthComparator());
        System.out.println(list); //自定义比较器进行排序

        //-----max------
        String max = Collections.max(list);
        System.out.println("max: " + max);

        String max1 = Collections.max(list, new StringLengthComparator());
        System.out.println("Length Max: " + max1);

    }

    /**
     * 对有序列表进行二分查找
     * 其返回值很特别. 找到的话,会返回其所以. 如果没有找到,则返回 -(插入位置) - 1
     * */
    public static void binarySearchDemo(){
        List<String> list = new ArrayList<String>();

        list.add("abc");
        list.add("kk");
        list.add("baaa");
        list.add("java");
        list.add("kk");
        Collections.sort(list);

        System.out.println(list);

        int index = Collections.binarySearch(list, "baaa");
        System.out.println(index);
    }

    //fill方法演示
    public static void fillDemo() {
        List<String> list = new ArrayList<String>(10);
        list.add("abc");
        list.add("kk");
        list.add("baaa");
        list.add("java");
        list.add("kk");

        Collections.replaceAll(list, "baaa", "scala");
        System.out.println(list);

        Collections.fill(list, "abc");

        System.out.println(list);

    }
    /**Collections类有个方法返回逆向比较器*/
    public static void reverseOrderDemo(){
        TreeSet<String> ts = new TreeSet<>(Collections.reverseOrder());
        ts.add("aa");
        ts.add("ddkf");
        ts.add("scala");
        ts.add("java");
        ts.add("converse");

        System.out.println("reverse:" + ts);

        TreeSet<String> ts1 = new TreeSet<>(Collections.reverseOrder(new StringLengthComparator()));
        ts1.add("aa");
        ts1.add("ddkf");
        ts1.add("scala");
        ts1.add("java");
        ts1.add("converse");

        System.out.println("custom reverse: " + ts1);
    }


}


class StringLengthComparator implements Comparator<String>{
    public int compare(String s1, String s2){
        int result =  s1.length() - s2.length();
        if(result == 0){
            result = s1.compareTo(s2);
        }

        return result;
    }
}
