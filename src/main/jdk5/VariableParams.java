package main.jdk5;

/**
 * Created by yudong on 17/2/16.
 * jdk1.5的新特性
 * 02 [可变参数]
 * 当一个方法的参数个数不确定, 可以在定义时使用可变参数. 在java中, 可变参数有如下特性:
 * 1 只能出现在参数列表的最后;
 * 2 ...位于变量类型和变量名之间
 * 3 在调用此类型的方法时, 编译器为该可变参数创建一个数组, 来保存参数
 */
public class VariableParams {
    public static void main(String[] args){
        sum(2, 4);
        sum(3, 5, 2, 4);
    }
    public static void sum(int a, int... args){
        int sum = a;
        for(int i = 0; i < args.length; i++){
            sum += args[i];
        }

        System.out.println("sum: " + sum);
    }
}

