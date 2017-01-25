package main.io;

import java.io.*;

/**
 * Created by yudong on 17/1/25.
 * DataInputStream和DataOutputStream用于进行基本数据类型的存取
 */
public class DataStreamDemo {
    public static void main(String[] args) throws IOException{
//        writeData();
//        readData();
        writeUTF8();
        readUTF8();
    }

    /***
     * 写入基本数据类型
     * @throws IOException
     */
    public static void writeData() throws IOException{
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("testFiles/data.txt"));
        dataOutputStream.writeInt(348);
        dataOutputStream.writeBoolean(true);
        dataOutputStream.writeDouble(3.14159);

        dataOutputStream.close();
    }

    /**
     * 读取基本数据类型, 只能按照写入的次序来读取
     * @throws IOException
     */
    public static void readData() throws IOException{
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("testFiles/data.txt"));
        int i = dataInputStream.readInt();
        boolean b = dataInputStream.readBoolean();
        double d = dataInputStream.readDouble();

        System.out.println("int: " + i + "\t boolean:" + b + "\t double: " + d);
        dataInputStream.close();
    }

    public static void writeUTF8()throws IOException{
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("testFiles/utf8data.txt"));
        dataOutputStream.writeUTF("你好, 中文字符, UTF8编码");
        dataOutputStream.close();
    }

    public static void readUTF8() throws IOException{
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("testFiles/utf8data.txt"));
        String s = dataInputStream.readUTF();
        System.out.println(s);
        dataInputStream.close();
    }
}
