package main.jdk5.proxy.aopframework;

import main.jdk5.proxy.Advise;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yudong on 17/2/25.
 */
public class BeanFactory {
    Properties props = new Properties();
    public BeanFactory(InputStream confStream){
        try{
            props.load(confStream);
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public Object getBean(String name){
        String className = props.getProperty(name);
        Object bean = null;
        Object proxy = null;
        try {
            Class clazz = Class.forName(className);
            bean = clazz.newInstance(); //调用不带参数的构造方法
        }catch (Exception e){
            e.printStackTrace();
        }
        if(bean instanceof ProxyFactoryBean){
            ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean)bean;
            try {
                Advise advise = (Advise)Class.forName(props.getProperty(name + ".advise")).newInstance();
                Object target = Class.forName(props.getProperty(name + ".target")).newInstance();
                proxyFactoryBean.setAdvise(advise);
                proxyFactoryBean.setTarget(target);
                proxy = proxyFactoryBean.getProxy();
            }catch (Exception e){
                e.printStackTrace();
            }
            return proxy;
        }
        return bean;
    }
}
