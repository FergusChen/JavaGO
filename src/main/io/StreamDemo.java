package main.io;

import java.io.*;

/**
 * Created by yudong on 17/1/17.
 * 字节流
 * 包括图片, 视频, 文本等, 都可以作为字节流来处理
 */
public class StreamDemo {
    public static void main(String[] args) throws IOException{
//        writeFile();
//        readFile1();
//        readFile2();
//        readFile3();

        keyboardInput();
    }

    /**写字节流的方法*/
    public static void writeFile() throws IOException {
        FileOutputStream stream = new FileOutputStream("newFile.txt");
        stream.write("hello stream".getBytes());  //处理字节的操作, 无需flush, 直接针对源文件进行处理了.
        stream.close();
    }

    /**读字节流的方法*/
    public static void readFile1() throws  IOException{
        FileInputStream stream = new FileInputStream("demo.txt");
        int ch = 0;
        while((ch = stream.read()) != -1){
            System.out.print((char)ch);
        }
        stream.close();
    }

    /**
     * 缓冲区方法, 推荐
     * */
    public static void readFile2() throws IOException{
        FileInputStream stream = new FileInputStream("demo.txt");
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = stream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }

        stream.close();
    }

    /**
     * 对于小文件,可以这样处理, 一次性读出来. 但是如果文件过大的话, 可能会溢出.
     * */
    public static void readFile3() throws IOException{
        FileInputStream stream  = new FileInputStream("demo.txt");
        byte[] buf = new byte[stream.available()]; //available方法返回字节大小.
        stream.read(buf);
        System.out.println(new String(buf));
        stream.close();
    }

    /**
     * 键盘输入方法
     * 也演示了字符流和字节流之间的转换. InputStreamReader和OutputStreamWriter两个类. 作为桥梁
     * InputStreamReader 传入字符流对象, 将其转换为字节流.
     *
     * */
    public static void keyboardInput() throws IOException{

        //键盘的输入
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));

//        BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream("demo.txt")));

        //定义输出流
//        BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("console.txt")));

        String line = null;
        while((line = bufReader.readLine()) != null){
            if("over".equals(line)){
                break;
            }
            bufWriter.write(line);
            bufWriter.newLine();
            bufWriter.flush();
        }
        bufWriter.close();
    }
}
