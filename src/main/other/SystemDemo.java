package main.other;

/**
 * Created by yudong on 17/1/10.
 * java.lang包
 *
 * System中的方法和成员都是静态的,
 * out:标准输出
 * in:标准输入
 *
 */
import java.util.*;

public class SystemDemo {
    public static void main(String[] args){
        //Properties是Hashtable的子类, 可以用key来获取value
        Properties properties = System.getProperties();

        //设置自定义key
        System.setProperty("zzke","自定义设置");

        for(Object obj:properties.keySet()){
            String value = (String)properties.get(obj);
            System.out.println(obj + "->" + value);
        }

        String osName = System.getProperty("os.name");
        System.out.println("os name: " + osName);


    }
}
