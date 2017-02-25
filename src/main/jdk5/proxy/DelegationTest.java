package main.jdk5.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by yudong on 17/2/23.
 * 【代理】
 * 对于已经完成的类, 想要给类增加一些功能(如:添加计时器, 异常处理,日志收集, 事务管理等), 就可以用代理实现, 而不需要修改原类的代码。
 * <p>
 * 原理:
 * 原来的调用方式: client直接调用目标类(目标类实现方法:m1(), m2()……)的方法。
 * 使用代理: 代理类(proxy)调用目标类的方法, 对外提供与目标类相同或更多的接口(m1(), m2()……), 并对需要修改的方法添加处理逻辑(如添加计时器)。
 * 见图delegation.jpeg
 * 这样, 代理类调用目标类的方法, 往往需要添加其他的功能代码, 添加位置如下:
 * 1. 在调用目标方法之前
 * 2. 在调用目标方法之后
 * 3. 在调用目标方法之前后
 * 4. 在处理目标方法异常的catch块中。
 * <p>
 * <p>
 * AOP也是基于代理的
 * 有很多模块都需要具有相同的功能, 这些相同功能就是交叉业务。如安全、事物、日志等, 在StudentManager、CourseManager、TeacherManager等模块都需要。
 * 注:JVM可以在运行期动态生成类的字节码, 这种动态生成的类往往被用作代理类, 即动态代理类。 JVM生成的动态类必须实现1个或多个接口,
 * 所以, 动态代理类只能用作具有相同接口的目标类的代理, 如果要动态添加放啊, 则可以使用CGLIB库。(待了解)
 * <p>
 * Proxy有 getProxyClass(ClassLoader loader, Class<?>... interfaces)获取指定类的代理类的二进制对象。需要传入类加载器和指定类的接口
 */
public class DelegationTest {
    public static void main(String[] args) throws Exception {
        Class clazzproxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class); //获取Collection的代理类
        System.out.println(clazzproxy1.getName());

        //代理类的对象
        System.out.println("$Proxy0的构造方法:");
        Constructor[] constructors = clazzproxy1.getConstructors();
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            StringBuilder sb = new StringBuilder(name);
            sb.append("("); //单线程用StringBuilder,效率高。 多线程用StringBuffer,更安全。
            Class[] clazzParams = constructor.getParameterTypes();
            for (Class clazzParam : clazzParams) {
                sb.append(clazzParam.getName()).append(",");
            }
            if (clazzParams != null && clazzParams.length != 0) {
                sb.deleteCharAt(sb.length() - 1); //删除最后一个逗号
            }
            sb.append(")");
            System.out.println(sb.toString());
        }

        System.out.println("\n$Proxy0的方法:");
        Method[] methods = clazzproxy1.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            StringBuilder sb = new StringBuilder(name);
            sb.append("("); //单线程用StringBuilder,效率高。 多线程用StringBuffer,更安全。
            Class[] clazzParams = method.getParameterTypes();
            for (Class clazzParam : clazzParams) {
                sb.append(clazzParam.getName()).append(",");
            }
            if (clazzParams != null && clazzParams.length != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            System.out.println(sb.toString());
        }

        //创建实例对象
        //方法1: 先实现内部类
        System.out.println("创建实例对象");
        Constructor constructor = clazzproxy1.getConstructor(InvocationHandler.class);//其只有1个有参的构造方法
        class MyInvocationHandler1 implements InvocationHandler {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        }
        Collection proxy1 = (Collection) constructor.newInstance(new MyInvocationHandler1());
        System.out.println(proxy1);// proxy1的toString()方法返回的是null, 不是对象为null
        proxy1.clear();
//        proxy1.size();//会报异常, 这里调用invoke会返回null, 会报空指针异常

        //方法2: 匿名内部类的常用方法
        Collection proxy2 = (Collection) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        //方法3: 用
        Collection proxy3 = (Collection) Proxy.newProxyInstance(
                Collection.class.getClassLoader(),
                new Class[]{Collection.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        ArrayList target = new ArrayList();
                        //这里添加的方法是硬编码添加, 也不好修改。 下面尝试动态插入代码, 方法就是把代码封装成1个对象, 把对象传进来, 执行对象
                        long beginTime = System.currentTimeMillis();
                        Object  result = method.invoke(target, args);//看下面的注释, 这里如果不传入target, 而传入代理对象, 就会造成死循环
                        long endTime = System.currentTimeMillis();
                        System.out.println(method.getName() + ": runtime: " + (endTime - beginTime));
                        return result; //这里是目标类返回什么类型的值, 就返回什么类型。
                    }
                }
        );

        proxy3.add("aaa");
        proxy3.add("bbb");
        proxy3.add("ccc");

        System.out.println(proxy3.size());//之所以size是0, 是每次都调用invoke, new出新的ArrayList, 可以把ArrayList放外面,作成员变量
        /**
         * 可以大概猜出各个方法内部的实现, 用传入的handler来invoke相应方法。传入的参数: 当前对象(代理对象), 对象的方法名, 接收的参数
         * int size(){
         *      return handler.invoke(this, this.getClass().getMethod("size"), null)
         * }
         */
        System.out.println("将功能调用封装, 添加动态代码的代理模式。。。");
        ArrayList target = new ArrayList();
        Collection proxy4 = (Collection)getProxy(target, new MyAdvise());
        proxy4.add("aaa");
        proxy4.add("bbb");
        proxy4.size();
    }

    /**
     * 此方法实现动态插入代码, 传入通用的目标对象, 执行目标对象的方法。
     * 这里不知道目标对象, 所以要定义一个契约(Interface), 说明目标对象一定实现了这个方法
     * @param target  执行目标
     * @param advise 传入系统建议的方法
     * @return
     */
    private static Object getProxy(final Object target, final Advise advise){
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
