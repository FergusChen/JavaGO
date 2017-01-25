package main.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yudong on 17/1/16.
 */
public class FileWriterAppendDemo {
    public static void main(String[] args){
        FileWriter writer = null;
        try{
            writer = new FileWriter("testFiles/demo.txt", true); //第2个参数指定是否append
            writer.write("\nhello~");
            writer.flush();
        }catch (IOException e){
            System.out.println(e.toString());
        }finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }catch (IOException e){
                System.out.println(e.toString());
            }
        }
    }
}
