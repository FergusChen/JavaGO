package main.thread;

/**
 * Created by Administrator on 2016/12/19.
 * 就说1个公共资源，有输入线程，有输出线程，如何做到同步。
 * 这里用资源对象作为锁，来进行同步，但是，输入进行如果得到执行权，就一直赋值，输出线程若得到执行权，就一直输出。
 * 而真正合适的是：输入完成后就输出，输出完成后再重新输入。这样，需要线程间通信：wait和notify。见ThreadSignaling
 */
public class ResourceSync {
    public static void main(String[] args){
        Resource r = new Resource();
        Input input = new Input(r);
        Output output = new Output(r);

        Thread t1 = new Thread(input);
        Thread t2 = new Thread(output);
        t1.start();
        t2.start();
    }
}

class Resource{
    String name;
    String gender;
}

class Input implements Runnable{
    private Resource r;
    public Input(Resource resource){
        this.r = resource;
    }
    public void run(){
        int x = 0;
        while(true){
            synchronized (r) {  //这里如果不同步，就可能在没有完成r的赋值时，被输出线程抢去，出现人妖的输出。
                if (x == 0) {    //但是同步要同步所有线程代码，包括输出代码。而且因为资源是相同的，用r即可。
                    r.name = "mike";
                    r.gender = "male";
                } else {
                    r.name = "lily";
                    r.gender = "female";
                }
                x = (x + 1) % 2;
            }
        }
    }
}

class Output implements Runnable{
    private Resource r;
    public Output(Resource resource){
        this.r = resource;
    }

    public void run(){
        while(true){
            synchronized (r) {
                System.out.println(r.name + "......" + r.gender);
            }
        }
    }
}
