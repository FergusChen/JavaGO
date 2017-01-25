package main.io;

import main.collection.Person;
import main.other.SystemDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by yudong on 17/1/23.
 * 演示对象的持久化
 * 用ObjectOutputStream将对象持久化到文件, ObjectInputStream读取持久化的对象
 */
public class SerializableDemo {
    public static void main(String[] args) throws Exception{
        writePerson();
        readPerson();
    }

    public static void writePerson() throws Exception{
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("testFiles/person.object"));

        outputStream.writeObject(new Person("fergus", 25));

        outputStream.close();
    }

    public static void readPerson() throws Exception{
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("testFiles/person.object"));
        Person p = (Person)inputStream.readObject();
        System.out.println("姓名:" + p.getName() + "\t 年龄:" + p.getAge());
        inputStream.close();
    }
}
