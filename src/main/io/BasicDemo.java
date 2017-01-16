package main.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yudong on 17/1/15.
 * 字符流和字节流
 *
 * 字符流的两个基类:Writer和Reader, 这两个抽象类有一些常用的直接子类,如BufferedWriter, StringWriter, BufferReader等
 * 这里演示IO流的写操作和异常处理. IO异常最好不要抛
 */
public class BasicDemo {
    public static void main(String[] args) {
        FileWriter writer = null; //建立FileWriter引用
        try{
            writer = new FileWriter("/Users/yudong/Desktop/demo.txt");//打开文件, 处理异常
            writer.write("demo append something.");//将数据写入到缓存中.
            writer.flush();
        }catch(IOException e){
            System.out.println("catch:" + e.toString());
        }finally {
            try {
                if(writer != null) {  //有几个IO流,就用几个if进行判断, 然后分别close.
                    writer.close();
                }
            }catch (IOException e){
                System.out.println("catch:" + e.toString());
            }
        }

    }
}
