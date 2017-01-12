package main.collection.generic;


/**
 * Created by qnchen on 17/1/7.
 * 泛型有泛型类、泛型方法、泛型接口等。
 * 泛型类通常用T来指代任意类型，这样，类中所有出现T的地方，都是相同的类型。
 */
public class GenericMethodDemo {
    public static void main(String[] args){
        //演示泛型类
        GenericClass<String> g1 = new GenericClass<String>();
        g1.print("abc");
        g1.show("def");

        GenericClass<Integer> g2 = new GenericClass<>();
        g2.print(32);
        g2.show(100);

        //演示泛型方法

        GenericMethod gm1 = new GenericMethod();
        gm1.print(1100);
        gm1.show("method");
    }
}

/**
 * 泛型类， 用T指定类型
 * */
class GenericClass<T>{

    //这里的T和初始化类时候的T必须一致
    public void print(T t){
        System.out.println("print..." + t);
    }

    public void show(T t){
        System.out.println("show..." + t);
    }
}

/**
 * 泛型方法
 * */
class GenericMethod{

    //不同的类用不同的泛型
    public <T> void print(T t){
        System.out.println("print..." + t);
    }

    public <E> void show(E e){
        System.out.println("show..." + e);
    }
}


