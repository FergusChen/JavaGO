package main.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yudong on 17/2/11.
 */
public class TcpCopy {
}

class CopyClient{
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("192.168.1.113", 2349);
        BufferedReader reader = new BufferedReader(new BufferedReader(new FileReader("demo.txt")));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        String line = null;
        while((line = reader.readLine()) != null){
            writer.println(line);
        }

        socket.shutdownOutput(); //相当于给予结束标记, 标记已经结束.

        //读取服务器的反馈数据
        BufferedReader serverReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = serverReader.readLine();
        System.out.println(str);

        serverReader.close();
        socket.close();
    }
}

class CopyServer{
    public static void main(String[] args) throws IOException{
        ServerSocket socket = new ServerSocket(2349);
        Socket clientSocket = socket.accept();
        String ip = clientSocket.getInetAddress().getHostAddress();
        System.out.println(ip + " connected!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //创建文件并写入文件中
        PrintWriter writer =new PrintWriter(new FileWriter("server.txt"), true);
        String line = null;
        while((line = reader.readLine()) != null){
            writer.println(line);
        }

        PrintWriter out  = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("上传成功");

        out.close();
        clientSocket.close();
        socket.close();
    }
}