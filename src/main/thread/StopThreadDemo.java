package main.thread;

/**
 * Created by Administrator on 2017/1/5.
 * 停止线程的方法，之前的stop()和suspend()已经过时。目前停止线程只有1种策略，就是让线程执行代码执行完。
 * 一般情况下，线程执行代码都在循环中，一旦循环结束，线程也就结束了。
 * 所以，可以用flag控制循环，也可以直接break跳出循环。
 *
 * 但是，有一种特殊情况，就是使用flag也可能出现多个线程同时wait。 即下面的synchronized + wait。这时候需要用interrupt方法处理。
 * interrupt是用于中断线程的，当线程调用wait时，interrupt能够将强制线程清除中断状态。
 * 如果线程在调用 Object 类的 wait()、wait(long) 或 wait(long, int) 方法，
 * 或者该类的 join()、join(long)、join(long, int)、sleep(long) 或 sleep(long, int) 方法过程中受阻，
 * 则其中断状态将被清除，它还将收到一个 InterruptedException。
 */
public class StopThreadDemo {
    public static void main(String[] args){
        Thread1 th = new Thread1();
        Thread t1 = new Thread(th);
        Thread t2 = new Thread(th);

        t1.setDaemon(true);  //setDaemon 将线程设置为守护线程。即后台执行，当剩余的线程都是守护线程时，虚拟机将退出。
        t2.setDaemon(true);

        t1.start();
        t2.start();

        int num = 0;
        while(true){
            if(num++ == 600){
//                th.changeFlag();
                t1.interrupt();
                t2.interrupt();
                break;
            }
            System.out.println(Thread.currentThread().getName() + "....run...." + num);
        }
    }

}

class Thread1 implements Runnable{
    private boolean flag = true;

    public synchronized void run(){
        while(flag){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName() + "catch exception....InterruptedException");
                flag = false;
            }
            System.out.println(Thread.currentThread().getName() + "....run");
        }
    }

    public void changeFlag(){
        flag = !flag;
    }
}
