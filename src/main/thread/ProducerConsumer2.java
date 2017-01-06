package main.thread;

import java.util.concurrent.locks.*;

/**
 * Created by Administrator on 2017/1/5.
 *
 * 使用Lock和Condition是推荐的做法。这样可以只唤醒需要唤醒的线程
 * lock负责锁定一个多线程代码块，相当于synchronized
 * condition对象是lock对象创建的，用于标识不同的线程，这样生产者和消费者的情形可以完美地解决
 * */
public class ProducerConsumer2 {
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

class Commodity2 {
    private String name;
    private int num;
    private boolean flag; //标记商品状态，true：已生产，待消费； false：已消费，待生产。

    private Lock lock = new ReentrantLock(); //1个lock管理1个范围，其可以对应多个condition
    private Condition conditionProducer = lock.newCondition();  //标记生产者的condition
    private Condition conditionConsumer = lock.newCondition(); //标记消费者的condition

    /**
     * 这段代码需要先上锁，然后生产者产生1个商品，接下来生产者线程wait，然后唤醒消费者线程，最后解锁
     * @param name 商品名称
     * @throws InterruptedException
     */
    public void set(String name) throws InterruptedException{
        lock.lock();
        while (this.flag) {
            try {
                conditionProducer.await();  //t1和t2这两个生产者线程挂起
                this.name = name + "-" + num++;
                System.out.println(Thread.currentThread().getName() + " 生产了:" + this.name);
                this.flag = true;
                conditionConsumer.signal();  //唤醒t3和t4这两个消费者线程。
            } finally {
                lock.unlock();
            }
        }

    }

    /**
     * 消费者消费商品
     */
    public void out() throws InterruptedException{
        lock.lock();
        while (!this.flag) {
            try {
                conditionConsumer.await(); ////t3和t4这两个生产者线程挂起
                System.out.println(Thread.currentThread().getName() + "........消费了:" + this.name);
                this.flag = false;
                conditionProducer.signal();//唤醒t1和t2这两个生产者线程。
            } finally {
                lock.unlock();
            }
        }

    }
}

class Producer2 implements Runnable {
    private Commodity2 c;

    public Producer2(Commodity2 commodity) {
        this.c = commodity;
    }

    public void run() {
        while (true) {
            try {
                c.set("商品");
            }catch (InterruptedException e){

            }
        }
    }

}

class Consumer2 implements Runnable {
    private Commodity2 c;

    public Consumer2(Commodity2 commodity) {
        this.c = commodity;
    }

    public void run() {

        while (true) {
            try{
                c.out();
            }catch (InterruptedException e){

            }

        }
    }
}
