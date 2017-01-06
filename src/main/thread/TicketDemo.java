package main.thread;

/**
 * Created by Administrator on 2016/12/14.
 */
public class TicketDemo {
    public static void main(String[] args){
//        Ticket1 t1 = new Ticket1();
//        Ticket1 t2 = new Ticket1();
//        Ticket1 t3 = new Ticket1();
//        Ticket1 t4 = new Ticket1();
//
//        //问题：多个线程同时运行，同一张票被卖了4次。可以把tick变量变为static，但用Ticket2的方法更好
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();

        //Ticket2方法
        //创建一个Ticket对象，由多个Thread执行。t中的成员变量只有1份，都是共享变量。
        Ticket2 t = new Ticket2();
        Thread th1 = new Thread(t);
        Thread th2 = new Thread(t);
        Thread th3 = new Thread(t);
        Thread th4 = new Thread(t);
        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
}

class Ticket1 extends Thread{
    private int tick = 1000; //票总量
    public void run(){
        while(true){
            if(tick > 0){
                System.out.println(Thread.currentThread().getName() + "...sale:" + tick--); //Thread.currentThread()相当于this
            }
        }
    }

}

/**
 * Ticket2实现Runnable接口，重写run方法，这样也可以避免java单继承的问题。
 */
class Ticket2 implements Runnable{
    private int tick = 1000;
    Object obj = new Object();
    public void run(){
        while(true){
            synchronized (obj) { //同步代码块
                if (tick > 0) {
                    System.out.println(Thread.currentThread().getName() + "...sale:" + tick--);
                }
            }
        }
    }
}
