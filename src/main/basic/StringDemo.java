package main.basic;

/**
 * Created by Administrator on 2016/8/23.
 */
public class StringDemo {
    public static void main(String[] args){
        stringStore();
    }

    public static void stringStore(){
        /**String a = "1234"这种产生的字面字符串,如果内存中存在字符串“1234”则会直接引用,如果没有则分配内存。new String是显式开辟内存 */
        String a="1234";
        String b="1234";
        String c = new String("1234");
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a.equals(c));
    }
}
