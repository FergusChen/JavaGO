package main.jdk5.proxy;

import java.lang.reflect.Method;

/**
 * Created by yudong on 17/2/24.
 */
public interface Advise {
    void beforeMethod(); //这里也可以接受参数
    void afterMethod(Method method);
}
