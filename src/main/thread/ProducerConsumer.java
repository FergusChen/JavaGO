package main.thread;

/**
 * Created by Administrator on 2016/12/19.
 * 多线程就不仅仅是两个线程来回切换的事情。比如，多个生产者，多个消费者在同时工作时，应该如何控制，这里就要注意两点：
 * 1、flag要循环判断，因为唤醒的时候可能是判断过flag，然后直接执行下面的语句
 * 2、要使用notifyAll()，不然的话while判断flag可能会导致死锁。
 *
 * 此方案是jdk1.5之前的做法，采用wait和notifyAll来处理，但是每一次notifyAll都会唤醒所有的线程。
 * 但实际上，生产者只需要唤醒消费者，消费者也只需要唤醒生产者，这是就需要用jdk1.5之后的lock和condition对象。
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        Commodity c = new Commodity();
        Thread t1 = new Thread(new Producer(c));
        Thread t2 = new Thread(new Producer(c));
        Thread t3 = new Thread(new Consumer(c));
        Thread t4 = new Thread(new Consumer(c));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Commodity {
    private String name;
    private int num;
    private boolean flag; //标记商品状态，true：已生产，待消费； false：已消费，待生产。

    public synchronized void set(String name) {
        while (this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {

            }
        }

        this.name = name +"-" + num++;
        System.out.println(Thread.currentThread().getName() + " 生产了:" + this.name);
        this.flag = true;
        this.notifyAll();
    }


    public synchronized void out() {
        while (!this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {

            }
        }

        System.out.println(Thread.currentThread().getName() + "........消费了:" + this.name);
        this.flag = false;
        this.notifyAll();
    }

}

class Producer implements Runnable {
    private Commodity c;

    public Producer(Commodity commodity) {
        this.c = commodity;
    }

    public void run() {
        while (true) {
            c.set("商品");
        }
    }

}

class Consumer implements Runnable {
    private Commodity c;

    public Consumer(Commodity commodity) {
        this.c = commodity;
    }

    public void run() {
        while (true) {
            c.out();
        }
    }
}
