package main.other;

import java.util.Random;

/**
 * Created by yudong on 17/1/11.
 */
public class MathDemo {
    public static void main(String[] args){
        double d = 8.3942;
        System.out.println("floor:" + Math.floor(d) + "\nceil:" + Math.ceil(d) + "\nround:" + Math.round(d));

        //生成随机数
        for(int i = 0; i < 10; i++){
            int r = (int)(Math.random() * 10 - 1);
            System.out.println(r);
        }

        System.out.println("Random对象生成的随机数:");
        Random r = new Random();
        for(int i = 0; i < 10; i++){
            int x = r.nextInt(10);
            System.out.println(x);
        }
    }
}
