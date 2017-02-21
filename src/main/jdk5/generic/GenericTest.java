package main.jdk5.generic;

import main.jdk5.test.ReflectPoint;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

/**
 * Created by yudong on 17/2/20.
 * jdk1.5的特性
 * [泛型]
 * 集合类型可以添加任意元素, 而泛型的基本用法就是告诉编译器, 这个集合只能放什么类型的元素.
 * 编译器会根据泛型的设置, 挡住一些非法类型的输入, 在编译时, 则会去掉类型.
 * java的泛型类似于C++中的template, java语言的泛型基本上完全是在编译器中实现的.
 * 泛型中的?通配符:
 */
public class GenericTest {
    public static void main(String[] args) throws Exception{
        //无泛型
        ArrayList arr1 = new ArrayList();
        arr1.add(1);
        arr1.add(7L);
        arr1.add("abc");
//        int i = (Integer)arr1.get(1); Long cannot be cast to Integer

        //用泛型, 挡住非法类型数字的输入
        ArrayList<String> arr2 = new ArrayList<>();
//        arr2.add(1);
//        arr2.add(7L);
        arr2.add("abc");
        String str = arr2.get(0);

        ArrayList<Integer> arr3 = new ArrayList<>();
        System.out.println(arr2.getClass() == arr3.getClass());//编译时, 会去掉类型信息, 这两个字节码是相同的. 所以, 用反射去给字节码添加不同类型也是可以的.
        //用反射来给arr3添加String类型数据
        arr3.getClass().getMethod("add", Object.class).invoke(arr3, "abc");
        System.out.println("反射添加的字符串: " + arr3.get(0));

        Integer a = add(3, 5);
        Number b = add(3, 1.3); //这里有类型推断
//        Object c = add("a", 5);  //定义时限定了类型, 就不能用字符串了.

        Integer[] array = new Integer[]{1, 3, 5, 6}; //
        swap(array, 2, 3);  //这里不能使用int[], 因为T只能标志引用类型. 数组已经是对象了, 不能进行自动装箱.
        System.out.println("交换后: " + array[2]);


        Object obj = "abc";
        String str1 = autoConvert(obj);


        copy1(new Vector<String>(), new String[10]);
        copy2(new Date[10], new String[10]);
//        copy1(new Vector<Date>(), new String[10]); //类型推断有传播性, 这样会报错.


        GenericDao dao = new GenericDao();
        dao.add(new ReflectPoint(3, 5));

        Method applyMethod = GenericTest.class.getMethod("applyVector", Vector.class);
        Type[] types = applyMethod.getGenericParameterTypes();
        ParameterizedType pType = (ParameterizedType) types[0];
        System.out.println("rawType: " + pType.getRawType());
        System.out.println("actualTypeArguments: " + pType.getActualTypeArguments()[0]); //返回数组(因为有Map(K, V), 这里第1个元素)
    }

    /**
     * 无法获取参数的类型, 可以通过反射获取到Vector的类型是Date
     */
    public static void applyVector(Vector<Date> v1){

    }

    public static void printCollection(Collection<?> collection){ //泛型是确定类型的, Integer和Object也是不同. ?指定任意类型
//        collection.add("abc");  //然而, 设定?通配符, 就无法调用类型相关的方法.可以调用类型无关的方法(如:size())
        System.out.println(collection.size());
        for(Object obj: collection){
            System.out.println(obj);
        }

        Vector<? extends Number> v1 = new Vector<>(); //Number的子类, 如Integer, Double
        Vector<? super Integer> v2 = new Vector<>(); //Integer的父类. 如Number, Object

    }

    public static <T extends Number> T add(T a, T b){ //泛型, 返回值前面的类型是自定义的, 任意类型变量的标识符T, 也可以限定上下线
        return  b;
    }

    public static <T> void swap(T[] a, int i, int j){
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 练习 -- 将Object传进来, 转换成任意类型.
     */
    private static <T> T autoConvert(Object obj){
        return (T)obj;
    }

    /**
     * 练习 -- 将任意类型的数据填充到数组某个位置
     */
    private static <T> void fillArray(T[] arr, T obj, int index){
        arr[index] = obj;
    }

    /**
     * 练习 -- 采用任意参数的方式, 打印任意类型的集合
     */
    private static <T> void printCollection1(Collection<T> collection){
        for(Object obj:collection){
            System.out.println(obj);
        }
    }

    /**
     * 练习 -- 数组到数组的拷贝. 不作检查
     */

    private static <T> void copy1(Collection<T> src, T[] dest){
        int i = 0;
        for(T obj: src){
            dest[i] = obj;
            i++;
        }
    }

    private static <T> void copy2(T[] src, T[] dest){
        for(int i = 0; i < src.length; i++){
            dest[i] = src[i];
        }
    }



}
