package main.io.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by yudong on 17/1/21.
 * [合并流]  [切割流]
 *
 * SequenceStream可以将多个文件输入流合并到1个文件输入流, 在实际开发中很常用.
 * 下面是一个Demo, 演示1个构造方法的(Enumeration参数)
 */
public class MergeSplitFileDemo {
    public static void main(String[] args) throws IOException{

//        mergeFile();
//        splitFile();
        mergePicture();
    }

    /**
     * SequenceInputStream 进行流合并
     * @throws IOException
     */
    public static void mergeFile() throws  IOException{
        Vector<FileInputStream> vector = new Vector<>();
        vector.add(new FileInputStream("demo.txt"));
        vector.add(new FileInputStream("demoBuf.txt"));
        vector.add(new FileInputStream("fileDemo.txt"));

        Enumeration<FileInputStream> enumeration = vector.elements();

        SequenceInputStream inputStream = new SequenceInputStream(enumeration);
        FileOutputStream outputStream = new FileOutputStream("mergeDemo.txt");
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = inputStream.read(buf)) != -1){
            outputStream.write(buf, 0, len);
        }

        inputStream.close();
        outputStream.close();
    }

    /**
     * 切割图片
     * @throws IOException
     */
    public static void splitFile() throws IOException{
        FileInputStream inputStream = new FileInputStream("run.png");
        FileOutputStream outputStream = null;

        byte[] buf = new byte[1024];
        int len = 0;
        int count = 0;
        while((len = inputStream.read(buf)) != -1){
            outputStream = new FileOutputStream("split/" + (count++) + ".part");
            outputStream.write(buf, 0, len);
            outputStream.close();
        }

        inputStream.close();
    }

    public static void mergePicture() throws IOException{
        ArrayList<FileInputStream> list = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            list.add(new FileInputStream("split/" + i + ".part"));
        }

        Iterator<FileInputStream> iter = list.iterator();
        Enumeration<FileInputStream> enumeration = new Enumeration<FileInputStream>() {
            @Override
            public boolean hasMoreElements() {
                return iter.hasNext();
            }

            @Override
            public FileInputStream nextElement() {
                return iter.next();
            }
        };

        SequenceInputStream inputStream = new SequenceInputStream(enumeration);
        FileOutputStream outputStream = new FileOutputStream("split/merge.png");
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = inputStream.read(buf)) != -1){
            outputStream.write(buf, 0, len);
            outputStream.flush();
        }

        outputStream.close();
        inputStream.close();
    }
}
