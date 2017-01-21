package main.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by yudong on 17/1/17.
 * 装饰设计模式:
 * 当想要对已有的对象的功能进行加强时, 可以自定义1个类, 将该对象传入, 基于已有功能, 提供加强功能.
 * 那么这样的自定义类称为装饰类.
 *
 * 这里思考: 装饰类是否可以用继承来代替?
 * 从实现上说, 完全可以用继承来实现, 但是, 装饰类比继承子类更加灵活. 为了几个功能去建立继承关系, 不如用装饰类, 将父类传入构造函数中.
 * 总之, 用装饰类建立组合关系, 处理更加灵活
 *
 */
public class DecorateDesign {
public static void main(String[] args) throws  IOException{
    FileReader reader = new FileReader("demo.txt");
    MyLineNumberReader mylinereader = new MyLineNumberReader(reader);
    String line = null;
    while((line = mylinereader.myReadLine()) != null){
        System.out.println(mylinereader.getLineNumber() + ": " + line);
    }
    mylinereader.close();
}
}


/** 装饰类的Demo */
class Man{
    public void run(){
        System.out.println("5公里/小时");
    }
}

class SuperMan{
    private Man p;
    SuperMan(Man p){
        this.p = p;
    }

    public void run(){
        p.run();
        System.out.println("起飞...");
        System.out.println("1000公里/小时");
    }
}


/**
 * 装饰类实现LineNumberReader
 * 这里,装饰类配合继承, 可以省去大量代码(直接使用BufferedReader的ReadLine方法).
 * */
class MyLineNumberReader extends BufferedReader{
    private Reader r;
    private int lineNumber;
    MyLineNumberReader(Reader r){
        super(r);
        this.r = r;
    }

    public String myReadLine() throws IOException{
        lineNumber++; //读一行,计数器就自增一次.
        return super.readLine();
        /*
        StringBuilder sb = new StringBuilder();
        int ch = 0;
        while((ch = r.read()) != -1) {
            if ((ch == '\r')) {
                continue;
            }if((ch == '\n')){
                return sb.toString();
            }else{
                sb.append((char)ch);
            }
        }
        if(sb.length() != 0){
            return sb.toString();
        }
        return null;*/
    }

    public void setLineNumber(int lineNumber){
        this.lineNumber = lineNumber;
    }

    public int getLineNumber(){
        return this.lineNumber;
    }

//    public void myClose() throws IOException{
//        r.close();
//    }
}