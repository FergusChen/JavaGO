package main.inherit;

/**
 * Created by Administrator on 2016/8/14.
 * 继承的优点：
 * 提高代码的复用性
 * 让类之间的关系更加清晰。
 * 类之间的继承关系是在于is a关系，一定不要为了复用而随便继承。
 *
 * 下面是变量、函数、构造函数在继承中的特性
 */

abstract class Person{
    String name;
    abstract void eat();
    void speak(){
        System.out.println("my name:" + name);
    }
}

class Student extends Person{
    String university;
    //覆盖父类的成员方法
    void speak(){
        super.speak();
        System.out.println("my university:"+ this.university);
    }
    void eat(){
        System.out.println("student eat");
    }
    void study(){
        System.out.println("I should study hard, because it's my duty");
    }
}

class Worker extends Person{
    void eat(){
        System.out.println("worker eat");
    }
    void work(){
        System.out.println("I should work hard, because it's my duty");
    }
}

public class SimpleDemo {
    public static void main(String[] args){
        Student stu1 = new Student();
        stu1.speak();
    }
}
