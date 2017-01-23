package main.collection;

import java.io.Serializable;

/**
 * Created by yudong on 17/1/8.
 *
 * Person实现序列化, 也保证后面io包里面可以持久化.
 * 另外, 这还使用自定义UID, 以便已经持久化的对象, 就算修改了字段的访问修饰符, 也能重新读取出来.
 * 但是,要注意,静态字段是不能序列化的, 序列化只能序列化堆内存中的数据. 如果非静态字段也不需要序列化, 则用transient修饰.
 */
public class Person implements Serializable{
    public static final long serialVersionUID = 42L;
    private String name;
    transient private Integer age;
    public Person(){}
    public Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    //重写hashCode方法，在HashSet中，判断元素相同（add、remove、contains等）先依赖hashCode，后依赖equals。
    public int hashCode(){
        return name.hashCode() + age;
    }
    //重写equals方法。在ArrayList等容器中判断元素相同（remove、contains等）依赖equals方法
    public boolean equals(Object obj){
        if(!(obj instanceof Object)) {
            return false;
        }
        Person p1 = (Person)obj;
        return this.name.equals(p1.name) && this.age == p1.age;
    }
    public String getName(){
        return name;
    }
    public Integer getAge(){
        return age;
    }
}
