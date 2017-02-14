package main.net;

import javax.print.attribute.standard.PrinterLocation;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yudong on 17/2/12.
 */
public class TcpLogin {
}

class LoginClient{
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("192.168.0.103", 5825);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); //键盘输入用户名

        //发送数据到服务端
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        //从服务端获取数据
        BufferedReader bufferedIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //校验3次
        for(int i = 0; i < 3; i++){
            String line = bufferedReader.readLine();
            if(line == null){
                break;
            }
            out.println(line);

            //获取服务端返回数据
            String info = bufferedIn.readLine();
            System.out.println("server: " + info);
            if(info.contains("欢迎")){
                break;
            }
        }
        bufferedReader.close();
        socket.close();
    }
}

class LoginServer{
    public static void main(String[] args) throws IOException{
        ServerSocket socket = new ServerSocket(5825);

        while(true){
            Socket clientSocket = socket.accept();
            new Thread(new UserThread(clientSocket)).start();
        }
    }
}

class UserThread implements Runnable{
    private Socket socket;

    public UserThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        String ip = socket.getInetAddress().getHostAddress();
        System.out.println(ip + " connected!");
        try{
            for(int i = 0; i < 3; i++){
                BufferedReader bufIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = bufIn.readLine();

                if(name == null){
                    break;
                }
                BufferedReader bufReader = new BufferedReader(new FileReader("userDB.txt"));

                String line = null;

                //返回客户端数据
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                boolean flag = false;
                while((line = bufReader.readLine()) != null){
                    if(line.equals(name)){
                        flag = true;
                        break;
                    }
                }

                if(flag){
                    System.out.println(name + "已登录");
                    out.println(name + ", 欢迎光临");
                    break;
                }else{
                    System.out.println(name + "尝试登录");
                    out.println(name + ", 用户名不存在");
                }

            }

            socket.close();
        }catch (IOException e){
            throw new RuntimeException(ip + "校验失败");
        }
    }
}
