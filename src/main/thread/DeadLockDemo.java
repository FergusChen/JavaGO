package main.thread;

/**
 * Created by Administrator on 2016/12/16.
 */
public class DeadLockDemo {
    public static void main(String[] args){
        Thread t1 = new Thread(new DeadLock(true));
        Thread t2 = new Thread(new DeadLock(false));
        t1.start();
        t2.start();
    }

}

class DeadLock implements Runnable{
    private boolean flag;
    public DeadLock(){}
    public DeadLock(boolean flag){
        this.flag = flag;
    }

    public void run(){
        if(flag){
            synchronized (LockUtil.lock1){
                System.out.println("if lock1");
                synchronized (LockUtil.lock2){
                    System.out.println("if lock2");
                }
            }
        }else{
            synchronized (LockUtil.lock2){
                System.out.println("else lock2");
                synchronized (LockUtil.lock1){
                    System.out.println("else lock1");
                }
            }
        }
    }
}

class LockUtil {
    static Object lock1 = new Object();
    static Object lock2 = new Object();
}