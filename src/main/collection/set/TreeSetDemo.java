package main.collection.set;

/**
 * Created by Administrator on 2016/8/24.
 *
 * TreeSet存入的元素会进行排序，其底层采用二叉排序树。
 * This implementation provides guaranteed log(n) time cost for the basic operations (add, remove and contains).
 * 如果存储的是自定义元素，则元素要具有可比较性，即实现Comparable接口，然后根据该接口的compareTo方法进行排序或去重。
 *
 * 如果TreeSet存储的元素本身并不具有可比较性，则可以让TreeSet具有可比较性。即元素不具有比较性，则让集合具有比较性。具体实现： 用比较器对象来初始化TreeSet。
 * 注：当存储的元素具有可比较性，同时TreeSet传入比较器，则以比较器为主。建议开发的时候，使用比较器。
 */


import main.collection.Person;

import java.util.Comparator;
import java.util.Iterator;
import  java.util.TreeSet;
public class TreeSetDemo {
    public static void main(String[] args){
        TreeSet set = new TreeSet<String>();
        set.add("ddkf");
        set.add("aa");
        set.add("bb");

        Iterator iter = set.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }

        //存储实现了Comparable接口的对象
        Student stu = new Student("SA11","Fergus",20);
        Student stu1 = new Student("SA22","Jenny",21);
        Student stu2 = new Student("SA13","Tony",23);

        TreeSet stuSet = new TreeSet();
        stuSet.add(stu);
        stuSet.add(stu1);
        stuSet.add(stu2);

        Iterator iterStu = stuSet.iterator();
        while (iterStu.hasNext()){
            Student s = (Student)iterStu.next();
            System.out.println(s.getSno() + "..." + s.getName() + "..." + s.getAge());
        }

        //用比较器初始化TreeSet
        TreeSet perSet = new TreeSet(new myComparator());
        Person p1 =new Person("Fergus",20);
        Person p2 = new Person("Jenney", 22);
        Person p3 = new Person("Tony", 21);
        perSet.add(p1);
        perSet.add(p2);
        perSet.add(p3);

        Iterator iterPer = perSet.iterator();
        while (iterPer.hasNext()){
            Person p = (Person)iterPer.next();
            System.out.println(p.getName() + "..." + p.getAge());
        }

    }
}

class Student implements Comparable{
    private String sno;
    private String name;
    private Integer age;

    public Student(String sno, String name, int age) {
        this.sno = sno;
        this.name = name;
        this.age = age;
    }

    public String getSno(){
        return this.sno;
    }

    public String getName(){
        return this.name;
    }

    public Integer getAge(){
        return this.age;
    }

    public int compareTo(Object obj){
        if(!(obj instanceof Student)){
            throw new RuntimeException("not a Student.");
        }
        Student stu = (Student)obj;
        int snoResult = this.sno.compareTo(stu.sno);
        if(snoResult != 0){
            return snoResult;
        }else{
            int nameResult = this.name.compareTo(stu.name);
            return nameResult;
        }
    }
}

/**推荐排序方法，比较器方法*/
class myComparator implements Comparator {
    public int compare(Object o1, Object o2){
        Person p1 = (Person)o1;
        Person p2 = (Person)o2;

        int result = p1.getName().compareTo(p2.getName());

        if(result == 0){
            result = p1.getAge().compareTo(p2.getAge());
        }
        return result;
    }
}

