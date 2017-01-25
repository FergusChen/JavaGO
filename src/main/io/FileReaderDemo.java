package main.io;


import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yudong on 17/1/16.
 * 字符流 FileReader
 */
public class FileReaderDemo {
    public static void main(String[] args){
        FileReader reader = null;

        //读取方法1
        try{
            reader = new FileReader("testFiles/demo.txt");
            int ch = 0;
            while((ch = reader.read()) != -1){
                System.out.println("ch = " + (char) ch);
            }
        }catch (IOException e){
            System.out.println(e.toString());
        }finally {
            try{
                if(reader != null){
                    reader.close();
                }
            }catch (IOException e){
                System.out.println(e.toString());
            }
        }

        //读取方法2(推荐)
        try{
            reader = new FileReader("testFiles/demo.txt");
            char[] buf = new char[1024]; //通常定义缓存数组为1024的整数倍
            int num = 0;
            while((num = reader.read(buf)) != -1){ //read(char[] ch)返回读取的字符个数
                System.out.println("num = "  + num);
                System.out.println(new String(buf, 0, num));
            }
        }catch (IOException e){
            System.out.println(e.toString());
        }finally {
            try{
                if(reader != null){
                    reader.close();
                }
            }catch (IOException e){
                System.out.println(e.toString());
            }
        }
    }
}
