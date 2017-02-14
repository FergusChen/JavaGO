package main.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yudong on 17/2/9.
 */
public class IPdemo {
    public static void main(String[] args) throws UnknownHostException{
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println("ip:" + ip);
        System.out.println("name:" + ip.getHostName());
        System.out.println("address:" + ip.getHostAddress());

        InetAddress ipnet = InetAddress.getByName("www.baidu.com");
        System.out.println("ip:" + ipnet);
        System.out.println("name:" + ipnet.getHostName());
        System.out.println("address:" + ipnet.getHostAddress());

        InetAddress[] ipList = InetAddress.getAllByName("www.sina.com");
        System.out.println("size:" + ipList.length);
        for(InetAddress p: ipList){
            System.out.println(p);
        }


    }
}
