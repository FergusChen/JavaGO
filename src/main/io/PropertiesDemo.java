package main.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * Created by yudong on 17/1/21.
 * Properties类 是java.util中的一个类, 相当于OC的属性列表, 专门处理键值对的配置数据
 */
public class PropertiesDemo {
    public static void main(String[] args) throws IOException{
//        setAndGet();

        countProperties();
    }

    public static void setAndGet() {
        Properties prop = new Properties();
        prop.setProperty("河南", "400");
        prop.setProperty("北京", "600");

        String value = prop.getProperty("北京");
        System.out.println("北京的pm2.5:" + value);
        Set<String> keys = prop.stringPropertyNames();
        for (String key : keys) {
            System.out.println(key + ":" + prop.getProperty(key));
        }

    }

    /**
     * Properties来进行配置文件读写
     * */
    public static void countProperties() throws IOException{
        Properties prop = new Properties();
        File file = new File("testFiles/count.properties");
        if(!file.exists()){
            file.createNewFile();
        }

        FileInputStream inputStream = new FileInputStream(file); //文件读取流, 默认编码. 若指定编码, 参考SystemDemo.java
        prop.load(inputStream);
        int count = 0;
        String value = prop.getProperty("times");
        if(value != null){
            count = Integer.parseInt(value);
            if(count >= 5){
                System.out.println("已超过使用限制");
            }
        }
        count++;
        prop.setProperty("time", count + "");
        FileOutputStream outputStream = new FileOutputStream(file);
        prop.store(outputStream, "使用次数");

        inputStream.close();
        outputStream.close();
    }
}
