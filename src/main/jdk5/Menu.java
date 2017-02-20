package main.jdk5;

/**
 * Created by yudong on 17/2/16.
 * jdk1.5的新特性
 * 05 [枚举]
 * java之前把C语言的枚举去掉了, 现在因为需要, 又将枚举加入进来.
 *
 * 枚举也是一个类, 可以编译成Class文件, 每个元素的匿名类也会编译成匿名的class对象.
 * 枚举的定义和常用方法;
 * 枚举也可以定义构造方法, 但必须在枚举元素之后.
 * 构造方法必须是私有的, 在枚举元素定义时调用.
 */
public class Menu {
    public enum Week{
        SUN(), MON(1), TUES(2), WED, THURS, FRI, SAT;

        private Week(){
            System.out.println("调用无参构造方法...");  //演示构造方法. 枚举相当于静态成员, 加载到内存中就创建对象.
        }

        private Week(int day){
            System.out.println("调用有参构造方法!!!");

        }
    }

    //定义枚举: 交通灯, 演示抽象方法
    public enum TrafficLamp{
        RED(30){
            @Override
            public TrafficLamp nextLamp() {
                return GREEN;
            }
        },

        GREEN(50){
            @Override
            public TrafficLamp nextLamp() {
                return YELLOW;
            }
        },

        YELLOW(5){
            @Override
            public TrafficLamp nextLamp() {
                return RED;
            }
        };

        public abstract TrafficLamp nextLamp();
        private int time;
        private TrafficLamp(int time) {this.time = time;}
    }
    public static void main(String[] args){
        WeekDay weekDay = WeekDay.SUN;  //用类来定义枚举, 就可以限定了只能赋给这些值.
        System.out.println(weekDay.nextDay());

        Week week = Week.SUN;
//        System.out.println(week); //自己实现了toString方法.
//        System.out.println(week.name());  //
//        System.out.println(week.ordinal());  //枚举索引
//        System.out.println(Week.valueOf("SUN")); //字符串变成对应的枚举元素
//        System.out.println(Week.values().length);  //values()获取所有的枚举元素
    }
}



abstract class WeekDay{
    private WeekDay(){}
    public final static WeekDay SUN = new WeekDay(){
        @Override
        public WeekDay nextDay() {
            return MON;
        }

        @Override
        public String toString() {
            return "Sunday";
        }
    };
    public final static WeekDay MON = new WeekDay(){
        @Override
        public WeekDay nextDay() {
            return TUES;
        }

        @Override
        public String toString() {
            return "Monday";
        }
    };
    public final static WeekDay TUES = new WeekDay(){
        @Override
        public WeekDay nextDay() {
            return WED;
        }

        @Override
        public String toString() {
            return "Tuesday";
        }
    };
    public final static WeekDay WED = new WeekDay(){
        @Override
        public WeekDay nextDay() {
            return THURS;
        }

        @Override
        public String toString() {
            return "Wednesday";
        }
    };
    public final static WeekDay THURS = new WeekDay(){
        @Override
        public WeekDay nextDay() {
            return FRI;
        }

        @Override
        public String toString() {
            return "Thursday";
        }
    };
    public final static WeekDay FRI = new WeekDay(){
        @Override
        public WeekDay nextDay() {
            return SAT;
        }

        @Override
        public String toString() {
            return "Friday";
        }
    };
    public final static WeekDay SAT = new WeekDay(){
        @Override
        public WeekDay nextDay() {
            return SUN;
        }

        @Override
        public String toString() {
            return "Saturday";
        }
    };

    public abstract WeekDay nextDay();
}