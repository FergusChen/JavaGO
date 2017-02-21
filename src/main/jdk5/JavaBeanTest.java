package main.jdk5;

import main.jdk5.test.ReflectPoint;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Created by yudong on 17/2/20.
 * javaBean是一种特殊的类, 用于传递数据. 使用javaBean的类必须有getter和setter方法(约定命名).
 * javaBean可以通过属性名获取属性值
 *
 * apache实现了BeanUtils, 可以对java对象的属性更方便地进行set和get
 * BeanUtils也实现了map和JavaBean的相互转化,
 */
public class JavaBeanTest {
    public static void main(String[] args) throws Exception{
        ReflectPoint point = new ReflectPoint(3, 6);
        String propertyName = "x"; //通过属性名获取属性的值. 可以用属性名获取开头字母大写的字符串, 再添加get,得到"getX", 再用反射调用此方法即可.
        //javaBean提供了接口来通过属性名get和set属性值
        PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, point.getClass());//属性描述对象. (属性名, 对象名)
        Method methodGetX = descriptor.getReadMethod();
        Object x = methodGetX.invoke(point);
        System.out.println(x);

        Method methodSetX = descriptor.getWriteMethod();
        methodSetX.invoke(point, 7);
        System.out.println(point.getX());

        /***上面是直接用JavaBean来获取属性描述符, 再进行get和set, 下面可以用Apache封装好的BeanUtils来get和set(注意引入BeanUtils和logging)
         * 注: 要获取的类必须是public class*/
        System.out.println("beanUtils获取:" + BeanUtils.getProperty(point, propertyName));

        BeanUtils.setProperty(point, propertyName, "12"); //beanUtils设置, 这里传入的是字符串, 会自动转换成整型
        System.out.println("BeanUtils设置:" + point.getX());

        BeanUtils.setProperty(point, "initDate.time", "10000");//设置属性可以级联设置. 因为Date类型有setTime方法, 就有time属性.若有空指针异常,是没有new
        System.out.println(BeanUtils.getProperty(point, "initDate.time"));

        PropertyUtils.setProperty(point, propertyName, 30); //用PropertyUtils也很方便, 类型不需要转换
        System.out.println("propertyUtils: " + PropertyUtils.getProperty(point, propertyName));
    }
}
