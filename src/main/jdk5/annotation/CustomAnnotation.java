package main.jdk5.annotation;

import main.jdk5.Enum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yudong on 17/2/20.
 * 自定义注解,
 *
 * 这里也提到, Java中有很多与Class平级的东西, 像是Interface, Enum, Annotation等, 他们都有一个父级的称呼, 就是TYPE.
 * ElementType是一个枚举.
 * 自定义注解的关键就是可以添加属性. 在使用的时候可以设置.
 */
@Retention(RetentionPolicy.RUNTIME) //元注解, 为注解添加的注解.
//Retention标注注解的生命周期, 标记这个注解要保存到哪个阶段. 注解的生命周期有3个阶段: SOURCE, CLASS, RUNTIME,
// 分别是源文件, 编译期和运行期. 默认值是CLASS阶段. 上面RUNTIME标记注解一直保存在运行期,
//其它的: Override在SOURCE, SuppressWarnings在SOURCE, 编译时就会被抛弃, Deprecated在RUNTIME, 一直被虚拟机保存到运行时.

@Target({ElementType.METHOD, ElementType.TYPE}) //标记注解用在哪里. 可以是METHOD, 也可以在Class, Interface等TYPE
public @interface CustomAnnotation {
    String color() default "red"; //定义属性是用方法的形式来定义,
    //如果只有String value()属性, 则不需要写属性名, 直接传入即可.
    String value();
    int[] customArr();

    Enum.TrafficLamp lamp() default Enum.TrafficLamp.RED;  //枚举类型的属性

    MetaAnnotation annotationAttr() default @MetaAnnotation("v"); //以注解为属性
}
