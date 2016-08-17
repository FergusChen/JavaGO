package video.pattern;

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
 * 懒汉式
 * */
class Single1{
    private static Single1 s = null;
    private Single1(){}
    /**
     * @param
     * @return
     * */
    private static Single1 getInstance(){
//        if(s == null){
//            s = new Single1(); //懒汉式在这里容易出现多线程同步的问题，可能new出多个对象。解决方案就是把方法加synchronized，但这样效率就低了。
//        }
        //下面这个是即解决多线程问题，又解决低效问题的方法。需要时再加锁。
        if(s == null){
            synchronized (Single1.class){
                if(s== null){
                    s = new Single1();
                }
            }
        }
        return s;
    }
}

/** 饿汉式加载， 开发时常用*/
class Single2{
    private static Single2 s = new Single2();
    private Single2(){}
    private static Single2 getInstance(){
        return s;
    }
}

public class SingleDemo {

}
