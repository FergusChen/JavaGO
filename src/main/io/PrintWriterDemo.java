package main.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by yudong on 17/1/21.
 * PrintWriter对象非常常用, 其扩展性也很强, 可以用字符串路径, File对象, 字符流, 字节流等进行初始化, 也可以指定字符集等.
 */
public class PrintWriterDemo {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter out = new PrintWriter(System.out, true); //第2个参数可以自动刷新.

        String line = null;
        while ((line = reader.readLine()) != null){
            if("over".equals(line)){
                break;
            }
            out.println(line);
        }
        out.close();
        reader.close();
    }
}
