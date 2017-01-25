package main.io;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yudong on 17/1/20.
 */
public class ExceptionLog {

    public static void main(String[] args) {
        try {
            int[] arr = new int[2];
            System.out.println(arr[2]); //制造一个异常
        } catch (Exception e) {
            printLog();
            e.printStackTrace(System.out);
        }
    }

    public static void printLog() {

        try {
            Date d = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormatter.format(d);

            PrintStream out = new PrintStream("testFiles/exception.log");
            out.println(date);
            System.setOut(out);
        } catch (IOException ex) {
            throw new RuntimeException("日志文件创建失败...");
        }

    }

}
