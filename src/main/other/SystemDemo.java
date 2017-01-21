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
import java.io.*;
import java.util.*;

public class SystemDemo {
    public static void main(String[] args) throws IOException{
        //Properties是Hashtable的子类, 可以用key来获取value
        Properties properties = System.getProperties();

        //设置自定义key
        System.setProperty("zzke", "自定义设置");

//        for(Object obj:properties.keySet()){
//            String value = (String)properties.get(obj);
//            System.out.println(obj + "->" + value);
//        }

//        String osName = System.getProperty("os.name");
//        System.out.println("os name: " + osName);
//
        //properties的保存, list 或store
        properties.list(new PrintStream("sysInfo.txt")); //list可以将properties直接保存到输出流
        properties.setProperty("zzke", "中文设置");
        OutputStreamWriter outputStream =new OutputStreamWriter( new FileOutputStream("sysInfo2.txt"),"utf-8");
        properties.store(outputStream, "注释信息:修改zzke属性");
        outputStream.close();

        //load将properties都加载到内存, 指定输入流
        InputStreamReader reader = new InputStreamReader(new FileInputStream("sysInfo.txt"), "utf-8");
        Properties prop = new Properties();
        prop.load(reader); //InputStreamReader指定编码类型
        System.out.println(prop);
        reader.close();


    }
}
