package main.jdk5;

/**
 * Created by yudong on 17/2/16.
 * jdk1.5的新特性
 * 03 增强for循环
 * 用 element: elements的方法来遍历集合.
 * for(VariableModifiers Type Identifier: Expression) Statement
 * 其中VariableModifiers是可选的, 即额外修饰符, final等. Expression必须实现Iterable
 */
public class EnhancedFor {
    public static void main(String[] args){

        int[] arr1 = {1, 4, 42, 5};
        for(int a:arr1){
            System.out.println(a);
        }

    }
}
