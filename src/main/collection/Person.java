package main.collection;

/**
 * Created by yudong on 17/1/8.
 */
public class Person{
    private String name;
    private Integer age;
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
