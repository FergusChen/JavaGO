package main.net;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yudong on 17/2/13.
 * 浏览器作为客户端, 就可以获取返回的数据.
 */
public class TcpServerDemo {
    public static void main(String[] args) throws IOException{
        ServerSocket socket = new ServerSocket(8829);
        Socket clientSocket = socket.accept();
        System.out.println(clientSocket.getInetAddress().getHostAddress());

        InputStream input = clientSocket.getInputStream();
        byte[] buf = new byte[1024];
        int len = input.read(buf);
        System.out.println(new String(buf, 0, len));

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        out.println("您好, 欢迎"); //<font color='red' size='7'> </font>

        socket.close();
        clientSocket.close();
    }
}

/**
 * 模拟浏览器 发送数据
 */
class WebRequestDemo{
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("192.168.0.103", 8080);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GET /myweb/demo.html HTTP/1.1");
        out.println("Accept: */*");
        out.println("Accept-Language: zh-cn");
        out.println("Host: 192.168.0.103:8829");
        out.println("Connection: closed"); //Keep-Alive

        out.println();
        out.println();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = null;
        while((line = reader.readLine()) != null){
            System.out.println(line);
        }
        socket.close();
    }
}