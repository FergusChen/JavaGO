package main.other;

/**
 * Created by yudong on 17/1/10.
 * java.lang.Runtime
 * Runtime提供了很多非静态方法, 需要调用getRuntime()方法来获取单例
 *
 * Runtime可以启动系统的软件, 也可以调用Process的destroy()杀掉该进程启动的进程.(注:不能杀掉非该进程启动的进程)
 */
public class RuntimeDemo {
    public static void main(String[] args) throws Exception{
        Runtime r = Runtime.getRuntime();
        Process p = r.exec("/Applications/TextEdit.app");  //Permission denied
        p.destroy();
    }
}
