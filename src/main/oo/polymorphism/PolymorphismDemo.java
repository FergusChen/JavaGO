package main.oo.polymorphism;

/**
 * Created by Administrator on 2016/12/6.
 *
 * 多态的优点在于提高了代码的复用性和扩展性。
 * 以后还可以结合反射来进行进一步的抽象
 * */
public class PolymorphismDemo {
    public static void main(String[] args){
        func(new Cat());
        func(new Dog());
        func(new Pig());

    }

    public static void func(Animal a){
        a.eat();
    }
}

abstract class Animal{
    public abstract void eat();
}

class Cat extends Animal{
    public void eat(){
        System.out.println("吃鱼...");
    }
}

class Dog extends Animal{
    public void eat(){
        System.out.println("吃骨头");
    }
}

class Pig extends  Animal{
    public void eat(){
        System.out.println("吃饲料");
    }
}
