package main.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yudong on 17/2/11.
 *
 * TCP连接分客户端和服务端, 客户端是Socket, 服务端是ServerSocket
 * Socket对象在建立时,就可以指定连接主机. 在连接成功后, 才能进行数据传输.
 */
public class TcpDemo {
}

class TcpClient{
    public static void main(String[] args) throws IOException{
        //1 创建TCP通路
        Socket socket = new Socket("192.168.1.113", 5638);

        //2 发送数据,
        OutputStream out = socket.getOutputStream();

        out.write("tcp client data".getBytes());

        //接受服务端的数据
        InputStream input = socket.getInputStream();
        byte[] data = new byte[1024];
        int len = input.read(data);
        System.out.println(new String(data, 0, len));

        // 关闭服务
        socket.close();
    }
}


class TcpServer{
    public static void main(String[] args) throws IOException{
        //1 建立服务器socket服务
        ServerSocket socket = new ServerSocket(5638);

        //2 通过accept方法获取链接过来的客户端对象
        Socket clientSocket = socket.accept();

        //3 解析客户端发送过来的数据

        String ip = clientSocket.getInetAddress().getHostAddress();
        System.out.println(ip + " connected");
        InputStream in = clientSocket.getInputStream();

        byte[] buf = new byte[1024];
        int len = in.read(buf);

        System.out.println(new String(buf, 0, len));

        //返回数据
        OutputStream output = clientSocket.getOutputStream();
        output.write("服务器收到了...".getBytes());

        clientSocket.close();
    }
}