package main.jdk5.proxy.aopframework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yudong on 17/2/25.
 * 工厂类BeanFactory负责创建指定目标类或代理类的实例对象, 并通过配置文件实现切换。BeanFactory的getBean方法,可以返回对应类的实例对象,或代理对象
 */
public class AOPdemo {
    private Object bean;

    public static void main(String[] args) throws IOException{
        InputStream inputStream  = new FileInputStream("resources/config.properties");
        Object bean = new BeanFactory(inputStream).getBean("xxx");
        System.out.println(bean.getClass().getName());
    }
}
