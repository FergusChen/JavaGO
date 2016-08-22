package main.pattern;

/**
 * Created by Administrator on 2016/8/15.
 * 模板方法模式
 * 在一个类的成员方法中，一部分代码是确定的，而一部分是不确定的。
 * 可以把不确定的那部分代码封装成另一个方法，子类若有其它实现，可以覆盖此方法。
 */

abstract class GetTime{
    //设置成final是为了避免重写此方法。
    public final void getTime(){
        long beginTime = System.currentTimeMillis();
        runCode();//不确定的部分
        long endTime = System.currentTimeMillis();
        System.out.println("runTime:" + (endTime - beginTime));
    }

    //不确定的代码，需要被重写。当然，也可以在这里有一些默认实现，这样就不需要abstract了。
    public abstract void runCode();

}

class SubTime extends GetTime{
    long sum = 0;
    public void runCode(){
        for(int i = 0; i < 10000; i++){
            sum = sum + i;
        }
        System.out.println("sum:" + sum);
    }
}
public class modelMethodDemo {
    public static void main(String[] args){
        SubTime sub1 = new SubTime();
        sub1.getTime();
    }
}
