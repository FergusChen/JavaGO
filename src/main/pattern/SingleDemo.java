package main.pattern;

/**
 * Created by Administrator on 2016/8/14.
 */
/** 懒汉式加载*/
/**
 * 该类演示单例类，包括懒汉式和饿汉式，最常用的还是饿汉式
 * @author qnchen
 * @version v1.0
 * 创建文档的命令（注：只有public的类才能生成文档，而且只有private的方法才会加入文档）
 * javadoc -d SingleHelp -author -version SingleDemo.java
 * */

/**
 * 懒汉式， 加锁前后两次判断，线程安全。
 * */
class Singleton1 {
    private static Singleton1 s = null;
    private Singleton1(){}
    /**
     * @param
     * @return
     * */
    public static Singleton1 getInstance(){
//        if(s == null){
//            s = new Singl
// eton1(); //懒汉式在这里容易出现多线程同步的问题，可能new出多个对象。解决方案就是把方法加synchronized，但这样效率就低了。
//        }
        //下面这个是即解决多线程问题，又解决低效问题的方法。
        if(s == null){
            synchronized (Singleton1.class){
                if(s== null){
                    s = new Singleton1();
                }
            }
        }
        return s;
    }
}
/**懒汉式：静态内部类实现单例模式。*/
class Singleton2{
    //静态内部类
    private static class LazyHolder{
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    private Singleton2(){}
    public static Singleton2 getInstance(){
        return LazyHolder.INSTANCE;
    }
}

/** 饿汉式单例，类一旦加载就会初始化。
 * 不管用不用都会占据内存。天生线程安全。
 * 因为简单，所以也很常用*/
class Single2{
    private static Single2 s = new Single2();
    private Single2(){}
    public static Single2 getInstance(){
        return s;
    }
}

public class SingleDemo {

}
