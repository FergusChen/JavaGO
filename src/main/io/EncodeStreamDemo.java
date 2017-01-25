package main.io;

import java.io.*;

/**
 * Created by yudong on 17/1/25.
 * java中的编码问题, GBK, UTF8...
 *
 */
public class EncodeStreamDemo {

    public static void main(String[] args) throws IOException{
        writeText();
        readText();
    }

    public static void writeText() throws IOException{
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("testFiles/gbk.txt"), "GBK");
        writer.write("你好,GBK");
        writer.close();

        writer = new OutputStreamWriter(new FileOutputStream("testFiles/utf8.txt"), "UTF-8");
        writer.write("你好,utf8");
        writer.close();
    }

    public static void readText() throws IOException{
        InputStreamReader reader = new InputStreamReader(new FileInputStream("testFiles/gbk.txt"),"GBK"); //这里找UTF-8则会找错码表
        char[] buf= new char[20];
        int len = reader.read(buf);
        String str = new String(buf, 0, len);
        System.out.println(str);
        reader.close();
    }
}
