package main.thread;

/**
 * Created by Administrator on 2017/1/5.
 *
 * join方法可以让该线程立刻执行，直到执行结束，其它线程才能执行。
 * main线程在执行到t1.join()之后，main就挂起，直到t1执行结束才能执行main线程。
 *
 * join可以让某线程临时加入
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException{
        DemoThread d = new DemoThread();
        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);

        t1.start();
        t2.start();
        t1.join();

        for(int i = 0; i < 60; i++){
            System.out.println(Thread.currentThread().getName() + "...." + i);
        }

    }
}

class DemoThread implements Runnable{
    public void run(){
        for(int i = 0; i < 60; i++){
            System.out.println(Thread.currentThread().getName() + "...." + i);
        }
    }
}
