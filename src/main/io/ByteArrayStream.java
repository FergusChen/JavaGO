package main.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by yudong on 17/1/25.
 * ByteArrayInputStream和ByteArrayOutputStream 主要用字节数组来操作数据,
 * 两个没有操作底层资源, 都不会跑出IO异常, 也无需关闭. 其都涉及byte[]缓存数组
 * ByteArrayInputStream是传入数据字节数组
 * ByteArrayOutputStream是内置字节数组, 数组长度可以自动增长.
 *
 * 类似的对象, 还有CharArrayReader和CharArrayWriter, StringReader和StringWriter
 */
public class ByteArrayStream {
    public static void main(String[] args){
        //输入流需要传入byte数组作为输入源, 这个可以有其它任何输入源
        ByteArrayInputStream inputStream = new ByteArrayInputStream("hello world".getBytes());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); //输出流对象

        int b = 0;
        while((b = inputStream.read())!= -1 ){
            outputStream.write(b);
        }

        System.out.println("缓冲区大小: " + outputStream.size());
        System.out.println(outputStream.toString());
    }
}
