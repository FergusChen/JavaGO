package main.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yudong on 17/2/13.
 */
public class URLConnectionDemo {
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("http://192.168.0.103/myweb/demo.html?name=fergus&age=30");
        System.out.println("getProtocol(): " + url.getProtocol());
        System.out.println("getHost(): " + url.getHost());
        System.out.println("getPort(): " + url.getPort());
        System.out.println("getPath(): " + url.getPath());
        System.out.println("getFile(): " + url.getFile());
        System.out.println("getQuery(): " + url.getQuery());

        int port = url.getPort();
        if (port == -1) {
            port = 80;
        }

        System.out.println();

        URL demoURL =  new URL("http://localhost:8080/myweb/demo.html");
        URLConnection conn = demoURL.openConnection(); //获取远程连接对象
        System.out.println("URLConnection: " + conn);

        InputStream input = conn.getInputStream();
        byte[] buf = new byte[1024];
        int len = input.read(buf);
        System.out.println(new String(buf, 0, len));
    }
}
