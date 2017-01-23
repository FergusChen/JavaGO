package main.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by yudong on 17/1/23.
 * 管道流是连接输入流和输出流的. 通常情况下, 先有输出流将数据输出到文件, 然后由输入流来进行读取.
 * 管道流在直接从输出流进行读取. 而且 管道流不建议单线程操作. 因为涉及到类似(生产者和消费者的问题)
 * 使用多线程, 读取的管道流只有在有数据写入时才会执行读取操作.
 */
public class PipeStreamDemo{
    public static void main(String[] args) throws IOException{
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        in.connect(out);

        Reader reader = new Reader(in);
        Writer writer = new Writer(out);
        new Thread(reader).start();
        new Thread(writer).start();

    }
}

class Reader implements Runnable{
    private PipedInputStream in;
    Reader(PipedInputStream in){
        this.in = in;
    }

    public void run(){
        try{
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            String s = new String(buf, 0, len);
            System.out.println(s);
        }catch (IOException e){
            throw new RuntimeException("管道读取流失败");
        }finally {
            try{
                if(in != null){
                    in.close();
                }
            }catch (IOException e){
                throw new RuntimeException("管道流关闭失败");
            }
        }
    }
}

class Writer implements Runnable{
    private PipedOutputStream out;
    Writer(PipedOutputStream out){
        this.out = out;
    }

    public void run(){
        try{
            out.write("写入管道流数据".getBytes());

        }catch (IOException e){
            throw new RuntimeException("管道写入流写入失败");
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
            }catch (IOException e){
                throw new RuntimeException("管道写入流关闭失败");
            }
        }
    }
}