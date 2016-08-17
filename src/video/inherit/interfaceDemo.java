package video.inherit;

/**
 * Created by Administrator on 2016/8/16.
 */
interface A {
    public void showA();
}
interface B extends A{
    public void showB();
}
class C implements B{
    public void showA(){}
    public void showB(){}
}

public interface interfaceDemo {
}
