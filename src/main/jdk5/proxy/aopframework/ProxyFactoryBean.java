package main.jdk5.proxy.aopframework;

import main.jdk5.proxy.Advise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yudong on 17/2/25.
 */
public class ProxyFactoryBean {

    private Advise advise;
    private Object target;

    public Advise getAdvise() {
        return advise;
    }

    public void setAdvise(Advise advise) {
        this.advise = advise;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy(){
        Object proxy =  Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //这里添加的方法是硬编码添加, 也不好修改。 下面尝试动态插入代码, 方法就是把代码封装成1个对象, 把对象传进来, 执行对象
                        /*long beginTime = System.currentTimeMillis();
                        Object  result = method.invoke(target, args);//看下面的注释, 这里如果不传入target, 而传入代理对象, 就会造成死循环
                        long endTime = System.currentTimeMillis();
                        System.out.println(method.getName() + ": runtime: " + (endTime - beginTime));
                        return result; //这里是目标类返回什么类型的值, 就返回什么类型。
                        */

                        advise.beforeMethod();
                        Object result = method.invoke(target, args);

                        advise.afterMethod(method);
                        return result;
                    }
                }
        );
        return proxy;
    }
}
