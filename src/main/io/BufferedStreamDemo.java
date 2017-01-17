package main.io;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yudong on 17/1/17.
 * 带有缓存的字节流
 */
public class BufferedStreamDemo {
    public static void main(String[] args) {
       long start = System.currentTimeMillis();
        copyMusic();
        long end = System.currentTimeMillis();
        System.out.println("copy时间:" + (end - start) + "ms");
    }

    public static void copyMusic(){
        FileOutputStream outputStream = null;
        FileInputStream input = null;
        MyBufferedInputStream inputStream = null;
        try {
            input = new FileInputStream("信乐团-海阔天空.mp3");
            outputStream = new FileOutputStream("海阔天空.mp3");
            inputStream = new MyBufferedInputStream(input);
            int byteData = 0;

            while ((byteData = inputStream.myRead()) != -1) {
                outputStream.write(byteData);
            }
        } catch (IOException e) {
            throw new RuntimeException("读写异常");
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("关闭流失败");
            }

            try {
                if (inputStream != null) {
                    inputStream.myClose();
                }
            } catch (IOException e) {
                throw new RuntimeException("关闭流失败");
            }
        }
    }
}


class MyBufferedInputStream {
    private InputStream in = null;
    private byte[] buf = new byte[1024];
    private int pos = 0, count = 0;  //pos为读取的指针. count为每次读取的字节长度

    MyBufferedInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * 用缓冲区的方法来进行字节的读取(注意陷阱)
     * 陷阱: 字节流每次读取1个字节, 但判断结束条件是 -1, 读到的1个字节是有可能是11111111 (即-1), 有可能刚开始读,然后就结束.
     * 解决办法: 每次读1个字节,都将其提成为4个字节的整数, 前面3个字节补 0;
     * myRead方法的思路, 当count == 0时, 一次读1024字节, 然后, 一个字节一个字节地返回, 只要count != 0, 就返回buf中相应位置的字节.
     */
    public int myRead() throws IOException {
        if (count == 0) {
            count = in.read(buf); //读一次, 1024字节
            if (count < 0) {
                return -1;
            }

            pos = 0;
            byte b = buf[pos];

            count--;
            pos++;
            return b&0xff;
        } else if (count > 0) {
            byte b = buf[pos];
            count--;
            pos++;
            return b&0xff;
        }
        return -1;
    }

    public void myClose() throws IOException {
        in.close();
    }
}
