package main.jdk5.proxy;

import java.lang.reflect.Method;

/**
 * Created by yudong on 17/2/24.
 */
public class MyAdvise implements Advise{
    long beginTime;
    @Override
    public void beforeMethod() {
        beginTime = System.currentTimeMillis();
    }

    @Override
    public void afterMethod(Method method) {
        long endTime = System.currentTimeMillis();
        System.out.println(method.getName() + " time:" + (endTime - beginTime));
    }
}
