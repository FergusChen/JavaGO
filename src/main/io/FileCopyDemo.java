package main.io;

import java.io.*;

/**
 * Created by yudong on 17/1/16.
 * 1 创建目标文件
 * 2 读取源文件
 * 3 写入到目标文件
 * 4 关闭资源
 */
public class FileCopyDemo {
    public static void main(String[] args) {
        copy1();
        copy2();
        copy3();
        /**以上都是拷贝文件, 字符流*/
        copyImage();


    }

    /**最简单粗暴的方法, 不推荐*/
    public static void copy1() {
        FileWriter writer = null;
        FileReader reader = null;
        try {
            writer = new FileWriter("testFiles/demo1.txt");
            reader = new FileReader("testFiles/demo.txt");
            int ch = 0;
            while ((ch = reader.read()) != -1) {
                writer.write(ch);
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("读写失败");

        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("读写失败");

            }
        }
    }

    public static void copy2() {
        FileWriter writer = null;
        FileReader reader = null;
        try {
            writer = new FileWriter("testFiles/demo1.txt");
            reader = new FileReader("testFiles/demo.txt");
            char[] buf = new char[1024];
            int len = 0;
            while ((len = reader.read(buf)) != -1) {
                writer.write(buf, 0, len);
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("读写失败");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("读写失败");
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            throw new RuntimeException("读写失败");
                        }
                    }
                }

            }
        }
    }

    /**用缓冲读写对象处理, 速度更快*/
    public static void copy3(){
        BufferedReader bufReader = null;
        BufferedWriter bufWriter = null;
        try{
            bufReader = new BufferedReader(new FileReader("testFiles/demo.txt"));
            bufWriter = new BufferedWriter(new FileWriter("testFiles/demoBuf.txt"));
            String line = null;
            while((line = bufReader.readLine()) != null){
                bufWriter.write(line);
                bufWriter.newLine();
                bufWriter.flush();
            }

        }catch (IOException e){
            throw new RuntimeException("读写错误");
        }finally {
            try{
                if(bufReader != null){
                    bufReader.close();
                }
            }catch(IOException e){
                throw new RuntimeException("关闭流失败");
            }

            try{
                if(bufWriter != null){
                    bufWriter.close();
                }
            }catch (IOException e){
                throw new RuntimeException("关闭流失败");
            }
        }
    }

    /**复制图片   这里用到字节流*/
    public static void copyImage(){
        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        try{
            outputStream = new FileOutputStream("testFiles/copy.png");
            inputStream = new FileInputStream("testFiles/run.png");
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buf)) != -1){
                outputStream.write(buf, 0, len);
            }
        }catch (IOException e){
            throw new RuntimeException("读写异常");
        }finally {
            try{
                if(outputStream != null){
                    outputStream.close();
                }
            }catch (IOException e){
                throw new RuntimeException("关闭流失败");
            }

            try{
                if(inputStream != null){
                    inputStream.close();
                }
            }catch (IOException e){
                throw new RuntimeException("关闭流失败");
            }
        }
    }



}
