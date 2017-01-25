package main.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by yudong on 17/1/23.
 * RandomAccessFile类直接继承自Object, 同时具备文件的读和写功能. 其内部封装了byte数组对数据进行访问. 用文件指针来进行操作.
 * 文件指针用getFilePointer来获取文件指针位置, 通过seek方法来改变指针位置
 *
 * 其实, 其读写的方法就是内部封装了字节输入流和输出流
 *
 * 该类只能操作文件, 并且可以定义读取的模式(只读, 读写)
 *
 * 字节操作. 这个如果只是字节数组, 断点续传之类的, 都可以用此方法.
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException{
        writeFile();

        readFile();
    }

    public static void writeFile() throws IOException{
        RandomAccessFile accessFile = new RandomAccessFile("testFiles/random.txt", "rw");
        accessFile.write("李四".getBytes());
        accessFile.writeInt(98);

        accessFile.write("王五".getBytes());
        accessFile.writeInt(116);
        accessFile.close();
    }

    public static void readFile() throws IOException{
        RandomAccessFile accessFile = new RandomAccessFile("testFiles/random.txt", "r");
        byte[] buf = new byte[6];  //utf-8编码, 每个汉字是3个字节
        accessFile.read(buf);
        String name = new String(buf);
        int age = accessFile.readInt();
        System.out.println(name + "..." + age);

        //可以用循环来继续读取, 也可以用seek来改变指针
        accessFile.seek(9);


    }
}
