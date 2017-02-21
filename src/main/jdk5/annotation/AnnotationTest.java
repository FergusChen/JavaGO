package main.jdk5.annotation;

/**
 * Created by yudong on 17/2/20.
 * jdk1.5的新特性
 * [注解]
 *  注解相当于标记, 加了注解就等于为程序打上某种标记. 编译器, IDE等可以用反射读取这些标记, 以采取进一步操作.
 *  1个注解也是1个类,使用注解时就在创建此注解的实例.
 *  注解可以加在 类, 成员方法, 成员属性, 局部变量等
 *
 *  注解的应用, 除了jdk实现的注解之外, 通常需要自己实现注解.
 */
@CustomAnnotation(color="red", value = "custom", customArr = {1, 2, 3}, annotationAttr = @MetaAnnotation("modify")) //仅仅给value设值, 就不用写属性名value了,
public class AnnotationTest {

    @SuppressWarnings("deprecation") //jdk内部注解: 不再提示警告. 去掉删除线.
    public static void main(String[] args){
        System.runFinalizersOnExit(true);

        AnnotationTest.sayHi();//这就会出现过时删除线
        //查看该类是否存在CustomAnnotation注解, 存在的话就获取
        if(AnnotationTest.class.isAnnotationPresent(CustomAnnotation.class)){
            CustomAnnotation annotation = (CustomAnnotation)AnnotationTest.class.getAnnotation(CustomAnnotation.class);
            System.out.println(annotation);
            System.out.println("value: " + annotation.value());
            System.out.println("arrayLength: " + annotation.customArr().length);
            System.out.println("menu: " + annotation.lamp().nextLamp().name());
            System.out.println("annotation: " + annotation.annotationAttr().value());
        }
    }

    @Deprecated  //标记某方法过时
    public static void sayHi(){
        System.out.println("Hi");
    }
}
