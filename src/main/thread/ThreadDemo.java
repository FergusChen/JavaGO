package main.thread;

/**
 * Created by Administrator on 2016/12/14.
 */
public class ThreadDemo {
    public static void main(String[] args){

        Demo d0 = new Demo();
        Demo d1 = new Demo();
        d1.start();
        d0.start();

        for(int i = 0; i < 100; i++){
            System.out.println("main thread..." + i);
        }
    }
}

class Demo extends Thread{
    public void run(){
        for(int i = 0; i < 100; i++){
            System.out.println("demo thread run..." + i);
        }
    }
}