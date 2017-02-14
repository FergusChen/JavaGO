package main.net;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yudong on 17/2/12.
 */
public class PicUpload {
}

class PicClient{
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("192.168.0.103", 6338);
        FileInputStream inputStream = new FileInputStream("/Users/yudong/Desktop/run.png");
        OutputStream out = socket.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = inputStream.read(buf)) != -1){
            out.write(buf, 0, len);
        }

        socket.shutdownOutput();// 结束标记

        InputStream in = socket.getInputStream();
        byte[] bufIn = new byte[1024];
        int num = in.read(bufIn);
        System.out.println(new String(bufIn, 0, num));

        socket.close();
        inputStream.close();

    }
}

class PicServer{
    /**
     * 为了让不同的客户端能同时连接服务端, 需要为每一个连接进来的客户端开辟一个线程
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        ServerSocket socket = new ServerSocket(6338);
        while(true) {
            Socket clientSocket = socket.accept();
            new Thread(new PicThread(clientSocket)).start();
        }

    }
}

class PicThread implements Runnable{
    private Socket socket; //连接成功的客户端, 连接哪个就传进来哪个

    public PicThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        int num = 1;
        String ip = socket.getInetAddress().getHostAddress();
        try{
            System.out.println(ip + " connected!");

            InputStream in = socket.getInputStream();

            File file = new File("server.png");
            while(file.exists()){
                file = new File("server(" + (num++) + ").png");
            }
            FileOutputStream outputStream = new FileOutputStream(file);

            byte[] buf = new byte[1024];

            int len = 0;
            while ((len = in.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }

            OutputStream out = socket.getOutputStream();
            out.write("上传成功".getBytes());

            outputStream.close();
            socket.close();
        } catch (IOException e){
            throw new RuntimeException(ip + "连接失败");
        }
    }
}