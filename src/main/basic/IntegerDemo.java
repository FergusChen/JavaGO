package main.basic;

/**
 * Created by Administrator on 2016/8/23.
 */
public class IntegerDemo {
    public static void main(String[] args){
        integerStore();
    }

    public static void integerStore(){
        /**JVM中一个字节以下的整型数据 [-128,127]，会在JVM启动的时候加载进内存。
         * i01和i03都是调用valueOf方法。在valueOf的具体实现中，如果在[-128,127]之间，且IntegerCache中有相应对象，则返回该对象引用。
         * 除非用new Integer()显式的创建对象，或者修改变量值时创建新的对象。

         * 下面例子中，只有i04是一个新对象，其他都是同一个对象 */
        Integer i01 = 59;
        int i02 = 59;  //存储在栈区
        Integer i03 = Integer.valueOf(59);
        Integer i04 = new Integer(59);

        System.out.println(i01== i02); //i01 是 Integer 对象， i02 是 int ，这里比较的不是地址，而是值。 Integer 会自动拆箱成 int ，然后进行值的比较。所以，为真。
        System.out.println(i01== i03);
        System.out.println(i03== i04);
        System.out.println(i02== i04);
        i01 = 90;
        System.out.println(i01 + "..." + i02 + "..." + i03 + "..." + i04);
    }
}
