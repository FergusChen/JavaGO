package main.collection.generic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by qnchen on 17/1/7.
 * 泛型限定。用泛型可以限定个别类（有继承关系）可以有上限（父类）也可以有下限（子类）
 * ? extends E  类E的所有子类都可以， 即确定上限
 * ? super E  类E的所有父类都可以，即确定下限
 */
public class GenericLimit {
    public static void main(String[] args){


        ArrayList<GenericPerson> persons = new ArrayList<>();
        persons.add(new GenericPerson("zhangsan"));
        persons.add(new GenericPerson("lisi"));
        persons.add(new GenericPerson("wangwu"));
        persons.add(new GenericPerson("zhaoliu"));
        printCollection(persons);

        ArrayList<GenericStudent> students = new ArrayList<>();
        students.add(new GenericStudent("aa"));
        students.add(new GenericStudent("bb"));
        students.add(new GenericStudent("cc"));
        students.add(new GenericStudent("dd"));
        students.add(new GenericStudent("ee"));
        printCollection(students);
    }

    /**
     * 打印集合，打印GenericPerson的子类
     * 实际上，collection的add方法都可以添加该类以及该类的子类
     * */
    public static void printCollection(ArrayList<? extends GenericPerson> p){
        Iterator<? extends GenericPerson> iter = p.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next().getName());
        }
    }
}

class GenericPerson{
    private String name;
    public GenericPerson(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}

class GenericStudent extends GenericPerson{
    public GenericStudent(String name) {
        super(name);
    }

}
