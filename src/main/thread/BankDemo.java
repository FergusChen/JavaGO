package main.thread;


/**
 * Created by Administrator on 2016/12/16.
 * 一个银行：有两个客户分别存300元，每次存100；
 * 同步函数，
 * 同步函数使用的锁是this。但如果同步函数被静态修饰，就不是this，而是该类的字节码文件对象，即Bank.class
 * 用synchronized时，可以用任意对象，如Object对象，类的字节码文件对象等。注意，同一个共享资源被多个线程操作，必须用同一个锁
 */
public class BankDemo {
    public static void main(String[] args) {
        Customer c = new Customer();
        Thread th1 = new Thread(c);
        Thread th2 = new Thread(c);
        th1.start();
        th2.start();
    }
}

class Bank {
    private int sum;

    public synchronized void add(int n) {
        //分析：这两句话可能出现多线程异常，可能sum加了，但是没有打印出来就切换到别的线程。【共享变量sum】
        //这里用同步函数来处理，不用同步代码块
        sum = sum + n;
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
        System.out.println("sum=" + sum);
    }
}

class Customer implements Runnable {
    private Bank b = new Bank();

    public void run() {
        for (int i = 0; i < 3; i++) {
            b.add(100);
        }
    }
}
