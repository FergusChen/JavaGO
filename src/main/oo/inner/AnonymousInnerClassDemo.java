package main.oo.inner;

/**
 * Created by Administrator on 2016/12/8.
 *
 * 匿名内部类是对内部类的简化形式，但要有一个前提，就是内部类必须继承1个父类，或者实现1个接口
 * 在定义的时候，直接new 父类或接口，并且在后面用花括号来初始化。
 * 匿名类常常只定义1次，调用1个方法。不然的话，匿名内部类就太胖了。
 */

abstract class AbsClass{
    abstract void show();
}

class Outer2{
    int x = 3;
    public void function(){
        new AbsClass(){
            void show(){
                System.out.println("匿名内部类，直接用父类或接口来new x:" + x);
            }
        }.show();
    }
}
public class AnonymousInnerClassDemo {
    public static void main(String[] args){
        Outer2 out = new Outer2();
        out.function();
    }
}
