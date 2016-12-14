package main.oo.inner;

/**
 * Created by Administrator on 2016/12/8.
 * 非静态内部类可以直接访问其所在类的成员，但是其所在的成员若要访问内部类成员，要新建内部类对象。
 * 静态内部类可以直接用类名访问，当然，其访问成员变量的方法和静态方法等一样。
 * 内部类什么时候用？
 * 简单来说，就是类嵌套类的时候。当一个类包含的属性不能用1个简单类型描述，而且要用到该类的其它属性。
 * 而该属性又不需要在其它地方用的时候，就用内部类来定义该属性。 如人体和心脏的关系，人体里有心脏，而心脏需要用到人体的其它属性。若把心脏定义在外部，
 * 则需要在心脏内定义人体的属性，而这样就不合逻辑了。
 *
 * 另外，局部的内部类，里面要么访问类的成员变量，如果是局部变量，则默认添加final修饰。
 *
 */

class Outer{
    private int x = 5;

    private class Inner{
        private int x = 7;
        void function(){
            System.out.println("inner.x " + x);
            System.out.println("outer.x " + Outer.this.x);
        }
    }

    void method(){
        Inner in = new Inner();
        in.function();

        int y = 3;
        class Inner2{
            void function(){
                System.out.println("method y=" + y); //不能出现y++等修改y的内容，因为y是final
            }
        }

        new Inner2().function();
    }
}
public class InnerClassDemo {
    public static void main(String[] args){
        Outer out = new Outer();
        out.method();
    }
}
