package main.jdk5.classLoader;

/**
 * Created by yudong on 17/2/22.
 * java虚拟机可以安装多个类加载器, 系统默认有3个: BootStrap, ExtClassLoader, AppClassLoader
 * 每个类加载器有是一个类, 但BootStrap除外, 其是java虚拟机内核的一部分, 虚拟机启动时, 自动加载到内存
 *
 * jre目录下有很多特别的文件夹:
 * jre/lib/ext: 外部jar包所在目录, 扩展类的使用
 * jre/lib还存放许多java运行和部署的类, 如:rt.jar
 * AppClassLoader用于加载CLASSPATH指定的所有jar或目录
 *
 * 当类在ext目录下能找到时, 就用ExtClassLoader来加载, 否则用AppClassLoader来加载.
 * 这是java类加载器调度的策略之一.
 * java首先派当前线程的类加载器来加载,
 * 如果类A引用了类B, 则用加载类A的加载器来加载类B...
 * 也可以手动用 loadClass()方法来手动加载.
 *
 * 上面3个类加载器有继承关系, 在实际使用时, 是用的委托机制, 子类发起请求, 会先委托父类去找, 找不到的话再委托父类的父类, 直到找到BootStrap.
 * 但如果还找不到, 就会倒回来委托子类, 直到请求发起的类. 如果还找不到, 就报异常. 所以, 要手动写一个java.lang.System类, 通常也是没有意义的,
 * 因为一定会找到BootStrap来调用系统的System类. 除非自定义类加载器.
 */
public class ClassLoaderTest {
    public static void main(String[] args){
        System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getName()); // 类加载器也是一个类

        System.out.println(System.class.getClassLoader());//这里是BootStrap, 加载jre/lib/rt.jar里面的类, 如System

        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        System.out.println();
        while(loader != null){
            System.out.println("层级关系: " + loader.getClass().getName());
            loader = loader.getParent();
        }
        System.out.println(loader);
    }
}
