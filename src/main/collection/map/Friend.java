package main.collection.map;


import main.basic.IntegerDemo;

import java.util.Comparator;

/**
 * Created by yudong on 17/1/8.
 */
public class Friend implements Comparable<Friend>{
    private String name;
    private Integer age;

    public String getName(){
        return this.name;
    }
    public Integer getAge(){
        return this.age;
    }
    public Friend(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public int hashCode(){
        return name.hashCode() + age * 34;
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Friend)){
            throw new ClassCastException("类型不匹配, 期待 Friend");
        }

        Friend friend = (Friend)obj;

        return this.name.equals(friend.name) && this.age == friend.age;
    }

    public int compareTo(Friend friend){
        int result = this.age.compareTo(friend.age);
        if(result == 0){
            result = this.name.compareTo(friend.name);
        }
        return result;
    }
}
