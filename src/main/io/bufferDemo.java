package main.io;

import java.io.*;

/**
 * Created by yudong on 17/1/17.
 * 字符流
 * BufferedWriter 和BufferedReader是为了提高流的读写效率的,
 */
public class bufferDemo {
    public static void main(String[] args){
//        bufferedWriterDemo();
//        bufferedReaderDemo();
        lineNumberReaderDemo();

    }

    public static void bufferedWriterDemo(){
        FileWriter writer  = null;
        BufferedWriter bufWriter = null;
        try{
            writer = new FileWriter("demo.txt");
            bufWriter = new BufferedWriter(writer);//BufferedWriter目的是提高流的执行速度,所以必须先有流

            bufWriter.write("new bufferedWriter");
            bufWriter.newLine();
            bufWriter.write("hello");
            bufWriter.flush();
        }catch (IOException e){
            throw  new RuntimeException("读写出错");
        }finally {
            if(bufWriter != null){
                try{
                    bufWriter.close();   //关闭缓冲区就是关闭流
                }catch (IOException e){
                    throw new RuntimeException("关闭缓冲区错误");
                }
            }
        }
    }
    public static void bufferedReaderDemo(){
        FileReader  reader = null;
        BufferedReader bufReader = null;
        try{
            reader = new FileReader("demo.txt");
            bufReader = new BufferedReader(reader);
            String line = null;
            while((line = bufReader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            throw new RuntimeException("读取数据错误");
        }finally {
            if(bufReader != null){
                try{
                    bufReader.close();
                }catch (IOException e){
                    throw new RuntimeException("关闭流失败");
                }
            }
        }
    }
    /**LineNumberReader是BufferedReader的子类, 可以附带对行号进行处理*/
    public static void lineNumberReaderDemo(){
        FileReader  reader = null;
        LineNumberReader lineReader = null;
        try{
            reader = new FileReader("demo.txt");
            lineReader = new LineNumberReader(reader);
            String line = null;
            lineReader.setLineNumber(100); //设置行号
            while((line = lineReader.readLine()) != null){
                System.out.println(lineReader.getLineNumber() + ": " + line); //获取行号
            }
        }catch (IOException e){
            throw new RuntimeException("读取数据错误");
        }finally {
            if(lineReader != null){
                try{
                    lineReader.close();
                }catch (IOException e){
                    throw new RuntimeException("关闭流失败");
                }
            }
        }
    }
}
