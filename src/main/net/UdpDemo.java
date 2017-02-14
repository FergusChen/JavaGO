package main.net;

import java.io.IOException;
import java.net.*;

/**
 * Created by yudong on 17/2/11.
 */
public class UdpDemo {


}

class UdpSend{
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException{
        //1 创建udp Socket服务, DatagramSocket对象, SocketException
        DatagramSocket socket = new DatagramSocket(7777); //不能是5430, 因为接收端先运行, 已经是5430了

        //2 封装数据
        byte[] data = "udp data".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.1.113"),5430);//UnknownHostException

        //3 通过socket服务,将数据发送出去, IOException
        socket.send(packet);

        //4 关闭资源
        socket.close();
    }
}


class UdpReceive{
    public static void main(String[] args) throws SocketException, IOException{
        //1 创建udp Socket服务, 即DatagramSocket. 作为接收端, 可以指定一个端口.
        DatagramSocket socket = new DatagramSocket(5430);
        while (true) {
            //2 创建接收数组.
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            //3 接收数据
            socket.receive(packet);

            //4 解析数据
            String ip = packet.getAddress().getHostAddress();
            String data = new String(packet.getData(), 0, packet.getLength());
            int port = packet.getPort(); //系统随机为发送端分配的端口
            System.out.println(ip + ":: " + port + "::" + data);
        }
        //5 关闭资源
//        socket.close();
    }
}