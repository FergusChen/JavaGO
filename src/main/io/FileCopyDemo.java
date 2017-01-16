package main.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yudong on 17/1/16.
 * 1 创建目标文件
 * 2 读取源文件
 * 3 写入到目标文件
 * 4 关闭资源
 */
public class FileCopyDemo {
    public static void main(String[] args) {
//        copy1();
        copy2();
    }

    public static void copy1() {
        FileWriter writer = null;
        FileReader reader = null;
        try {
            writer = new FileWriter("demo1.txt");
            reader = new FileReader("demo.txt");
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
            writer = new FileWriter("demo1.txt");
            reader = new FileReader("demo.txt");
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
}
