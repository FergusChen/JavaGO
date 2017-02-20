package main.jdk5;

import main.collection.Person;
import main.io.PropertiesDemo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by yudong on 17/2/16.
 * 要理解反射, 先要了解Class关键字. Class用于描述所有的Java类, 就好像Person代表所有的人一样.
 * 通过Class可以得到可以获得Java类中应有的东西(属性, 方法等), 如类名, 包名, 方法列表, 实现的接口等,
 * 类的字节码就是类编译后得到的二进制文件(class文件).
 * 8种基本数据类型(int, boolean, float等), 用int.class 或对应包装类的TYPE方法。
 * 只要在源程序中出现的类型(int,void,int[]等)都有自己对一个的Class对象.
 *
 * [反射]就是将java类的各种成分(成员变量,成员方法,构造方法,包等)映射成相应的java类(如Field对象, Method对象,Contructor对象,Package对象等等).
 * 获取相应的对象之后, 就可以使用对象的属性和方法, 设置修改源对象的属性和方法.
 * 反射是比较耗性能的, 不要经常用.
 *
 */
public class Reflect {
    public static void main(String[] args) throws Exception{

//        ClassTest();

//        constructorTest();

//        fieldTest();

//        ReflectPoint point = new ReflectPoint(2, 3);
//        changeFieldValue(point);
//        System.out.println(point);

//        methodTest();

//        TestArguments.main(new String[]{"aa", "bb", "cc"});
        //若不知道类名, 只知道其包含main方法, 如何调用呢?
//        String className = "main.jdk5.TestArguments";
//        Method mainMethod = Class.forName(className).getMethod("main", String[].class);
        //虽然的确是1个字符串数组作为参数, 但为了兼容jdk1.4. jdk1.4没有可变参数, 需要将多个参数封装成数组, 运行时会把数组拆开. 这里当成3个参数
//        mainMethod.invoke(null, new String[]{"aa", "bb", "cc"});
//        mainMethod.invoke(null, new Object[]{new String[]{"aa", "bb", "cc"}});//解决方法是将其再封装1层, 或者强转成Object类型

//        arrayTest();

        reflectObj();


    }

    public static void ClassTest() throws ClassNotFoundException{
        //获取类的字节码有3种方式, 直接从虚拟机中获取: 1. 类名.class; 2. 对象.getClass(); 类加载 3. Class的load方法: forName()
        Class c1 = Math.class; //获取Math类的字节码

        Person p = new Person();
        Class c2 = p.getClass();
        System.out.println("内存中是否只有1份相同类型的字节码:" + (c1 == Math.class));//字节码都是1份, 可以用等号比较

        Class c3 = Class.forName("java.lang.String");//反射时常用.
        System.out.println(c3.isPrimitive() + "..." + int.class.isPrimitive()); //isPrimitive判断是否是原始类型(基本数据类型)字节码。
        System.out.println(Integer.TYPE.isPrimitive());
        System.out.println("数组:" + int[].class.isArray());
    }

    /**
     * 获取类的构造方法对象
     */
    public static void constructorTest() throws Exception{

        //getConstructor()方法获取指定的构造方法 , 通过指定参数类型来获取相应的构造方法对象. Constructor可以获取相应的修饰符
        //获取所属类, 更重要的是newInstance新建实例.
        Constructor constructor0 = String.class.getConstructor(StringBuffer.class);
        String str = (String)constructor0.newInstance(new StringBuffer("abc")); //newInstance返回泛型对象,需要强制转换.
        System.out.println(str.charAt(1));
    }

    /**
     * 获取类的成员变量对象
     */
    public static void fieldTest() throws Exception{
        ReflectPoint point = new ReflectPoint(3, 5);
        //对公有字段
        Field y = point.getClass().getField("y");
        System.out.println(y.getInt(point)); //在这个对象中的值

        //对私有字段
        Field x = point.getClass().getDeclaredField("x"); //获取声明的字段
        x.setAccessible(true); // 暴力反射, 强制获取数据类型
        System.out.println(x.getInt(point));
    }

    public static void changeFieldValue(Object obj) throws Exception{
        Field[] fields = obj.getClass().getFields();
        for(Field field:fields){
            if(field.getType() == String.class){//内存中只有1份字节码
                String value = (String)field.get(obj);
                String newValue = value.replace('b', 'a');
                field.set(obj, newValue);
            }
        }
    }

    /**
     * 反射获取类的方法, 并调用方法
     */
    public static void methodTest() throws Exception{
        Method methodChatAt = String.class.getMethod("charAt", int.class); //getMethod传入方法名和重载参数列表
        System.out.println(methodChatAt.invoke("hello", 4));
    }

    /**
     * 数组的字节码
     */
    public static void arrayTest() throws Exception{
        int[] a1 = new int[3];
        int[] a2 = new int[4];
        int[][] a3 = new int[2][3];
        String[] a4 = new String[3];
        System.out.println(a1.getClass() == a2.getClass());
//        System.out.println(a1.getClass() == a4.getClass());  //数组编译成字节码, 相同类型和维度是共享同一份字节码,
//        System.out.println(a1.getClass() == a3.getClass());
        System.out.println(a1.getClass().getName()); //看文档, 中括号 + 字母, [I 代表整型. /*.getSuperclass()*/即Object类


        int[] a5 = new int[]{1, 2, 4};
        String[] a6 = new String[]{"aa", "bb", "cc"};

        printObject(a5);
        System.out.println();
        printObject(a6);
        System.out.println();
        printObject("abc");
    }

    public static void printObject(Object obj){
        Class c = obj.getClass();

        if(c.isArray()){
            int len = Array.getLength(obj);
            for(int i = 0; i < len; i++){
                System.out.println(Array.get(obj, i));
            }
        }else{
            System.out.println(obj);
        }
    }

    public static void reflectObj() throws Exception{
        //
        InputStream inputStream = new FileInputStream("resources/config.properties");
        Properties props = new Properties();
        props.load(inputStream);
        inputStream.close();

        String className = props.getProperty("className");

        Collection collections = (Collection)Class.forName(className).newInstance();

        ReflectPoint p1 = new ReflectPoint(3, 3);
        ReflectPoint p2 = new ReflectPoint(5, 5);
        ReflectPoint p3 = new ReflectPoint(3, 3);

        collections.add(p1);
        collections.add(p2);
        collections.add(p3);
        collections.add(p1);

        System.out.println("集合元素个数: " + collections.size());
    }

}

/**
 * 测试类
 */
class ReflectPoint{
    private int x;
    public int y;
    public String str1 = "ball";
    public String str2 = "baby";
    public String str3 = "case";

    public ReflectPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "str1: " + str1 + "\t str2: " + str2 + "\t str3: " + str3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReflectPoint point = (ReflectPoint) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}


class TestArguments{
    public static void main(String[] args){
        for(String arg : args){
            System.out.println(arg);
        }
    }
}