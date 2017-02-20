package main.jdk5;

import main.basic.IntegerDemo;

/**
 * Created by yudong on 17/2/16.
 * jdk1.5的新特性
 * 04 自动装箱和拆箱
 * List, Set, Map等不能存基本数据类型, 有时候可以自动装箱.
 * 在平时运算或赋值中, 也可以进行基本数据类型和类类型的自动转换.
 * 装箱: 基本数据类型到类类型
 * int -> Integer
 * double -> Double
 * ...
 * 拆箱: 类类型到基本数据类型
 * Integer -> int
 * Double -> double
 * ...
 * */
public class AutoBox {
    public static void main(String[] args){
        //自动装箱
        Integer i = 3;

        //自动拆箱
        System.out.println( i + 5); //i是Integer类型, 拆箱后才能进行加法运算

        //面试, -128 - 127之间的Integer是同一个对象(1个字节, 直接放到缓存池中) 这是一个设计模式, 叫享元模式(iflyweight).
        // 即小对象若出现频率过高, new出很多对象, 也会占用很多空间, 可将小对象缓存起来, 共享此对象.
        // 一个大对象里也可以包含部分共享的小对象,  其它变化的部分作为外部属性再进行封装.
        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1==i2);// 注: 自动装箱和Integer.valueOf()手动封装对象都是一样的, 但equals方法是比较大小, 无论多大都是true

    }
}
