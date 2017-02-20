package main.jdk5;

/**
 * Created by yudong on 17/2/16.
 * jdk1.5的特性
 * 01 [静态导入]
 * import导入的时候通常导入一个类, 如果类里面有静态方法, 则可以用"类名."的方式调用. 例如Math里面的类(注: java.lang包是默认导入的)
 * 而静态导入使用import static关键字, 可以导入单个静态方法, 也可以导入该类的所有方法. 具体如下.
 */
//import static java.lang.Math.max;

import static java.lang.Math.*;

public class StaticImport {
    public static void main(String[] args) {

        System.out.println(max(3, 5));
        System.out.println(abs(3 - 9));
    }
}
