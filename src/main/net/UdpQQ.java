package main.net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by yudong on 17/2/11.
 */
public class UdpQQ {
    public static void main(String[] args) throws Exception{
        DatagramSocket sendSocket = new DatagramSocket();
        DatagramSocket receiveSocket = new DatagramSocket(6772);
        new Thread(new QQSender(sendSocket)).start();
        new Thread(new QQReceiver(receiveSocket)).start();
    }
}

//class QQSender {
//    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
//        DatagramSocket socket = new DatagramSocket();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            if ("886".equals(line)) {
//                break;
//            }
//            byte[] buf = line.getBytes();
//            DatagramPacket packet = new DatagramPacket(line.getBytes(), buf.length, InetAddress.getByName("192.168.1.113"), 8839);
//            socket.send(packet);
//        }
//        //此网段的广播地址: 192.168.1.255
//
//        socket.close();
//    }
//}
//
//class QQReceiver {
//    public static void main(String[] args) throws SocketException, IOException {
//        DatagramSocket socket = new DatagramSocket(8839);
//        while (true) {
//            byte[] buf = new byte[1024];
//            DatagramPacket packet = new DatagramPacket(buf, buf.length);
//
//            socket.receive(packet);
//            String ip = packet.getAddress().getHostAddress();
//            String data = new String(packet.getData(), 0, packet.getLength());
//
//            System.out.println(ip + "::" + data);
//        }
//    }
//}


/**
 *
 * */
class QQSender implements Runnable {
    DatagramSocket socket;

    public QQSender(DatagramSocket socket) {
        this.socket = socket;

    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if ("886".equals(line)) {
                    break;
                }

                byte[] buf = line.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, 0, buf.length, InetAddress.getByName("192.168.1.113"), 6772);
                socket.send(packet);
            }
        } catch (Exception e) {
            throw new RuntimeException("发送失败");
        }
    }
}

class QQReceiver implements Runnable{
    private DatagramSocket socket;
    public QQReceiver(DatagramSocket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            while(true){
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);

                String ip = packet.getAddress().getHostAddress();
                String data = new String(packet.getData(),0, packet.getLength());
                System.out.println(ip + "::" + data);
            }
        }catch (Exception e){
            throw new RuntimeException("接收失败");
        }
    }


}