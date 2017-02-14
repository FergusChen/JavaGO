package main.net;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yudong on 17/2/11.
 */
public class TcpTransfer {
}

class TransClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.1.113", 6722);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); //自动刷新的Writer
        //定义Socket读取服务器返回数据
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //向服务器写数据
        String line = null;
        while ((line = reader.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
//            writer.write(line);//写到缓冲区
//            writer.newLine();
//            writer.flush(); //需要flush才会把数据发送给服务端

            writer.println(line);
            String reply = serverIn.readLine();
            System.out.println("server:" + reply);
        }

        reader.close();
        socket.close();

    }
}

class TransServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(6722);
        Socket clientSocket = socket.accept();
        String ip = clientSocket.getInetAddress().getHostAddress();
        System.out.println(ip + " connected...");

        //读取客户端数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //目的, socket输出流, 将大写数据写入到socket输出流
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

        String line = null;
        while ((line = reader.readLine()) != null) {//BufferedReader: readLine只有收到回车符的时候才会返回数据, 所以客户端的数据需要添加newLine才能读到. 同样的,服务器端的返回数据也是如此
//            writer.write(line.toUpperCase());
//            writer.newLine();
//            writer.flush();

            writer.println(line.toUpperCase()); //PrintWriter
        }

        reader.close();
        socket.close();
    }
}
