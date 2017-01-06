package main.thread;

/**
 * Created by Administrator on 2016/12/19.
 */
public class ThreadSignaling {
    public static void main(String[] args) {
        ResourceOpt r = new ResourceOpt();
        new Thread(new InputOpt(r)).start();
        new Thread(new OutputOpt(r)).start();

    }
}

class ResourceOpt {
    private String name;
    private String gender;
    private boolean flag;  //标记资源状态，true：已写，待输出； false：已输出，待重写。

    public synchronized void set(String name, String gender) {
        if (this.flag) {
            try {
                this.wait();  //当前线程等待。必须有锁（对象监视器），能够确定到时候哪个线程可以notify
            } catch (InterruptedException e) {
            }
        }
        this.name = name;
        this.gender = gender;
        this.flag = true;
        this.notify();  //唤醒线程池中，此对象监视器上等待的线程。
    }

    public synchronized void out() {
        if (!this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println(name + "......" + gender);
        this.flag = false;
        this.notify();
    }
}

class InputOpt implements Runnable {
    private ResourceOpt r;

    public InputOpt(ResourceOpt resource) {
        this.r = resource;
    }

    public void run() {
        int x = 0;
        while (true) {
            if (x == 0) {
                r.set("Mike", "male");
            } else {
                r.set("Lily", "female");
            }
            x = (x + 1) % 2;

        }
    }
}

class OutputOpt implements Runnable {
    private ResourceOpt r;

    public OutputOpt(ResourceOpt resource) {
        this.r = resource;
    }

    public void run() {
        while (true) {
            r.out();
        }
    }
}