package main.collection.generic;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by yudong on 17/1/7.
 * 比较器排序是推荐方法，去排序自定义对象。其扩展性也比较强。
 * 这里讲字符串按照长度来排序。
 *
 */
public class ComparatorDemo {

    public static void main(String[] args){
        TreeSet ts = new TreeSet(new StringComparator());
        ts.add("00");
        ts.add("c++");
        ts.add("java");
        ts.add("compare");
        ts.add("98");
        ts.add("100");

        Iterator iter = ts.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

    }
}

class StringComparator implements Comparator<String>{
    public int compare(String o1, String o2){
        int result = o1.length() - o2.length();
        if(result == 0){
            result = o1.compareTo(o2);
        }

        return result;
    }
}
