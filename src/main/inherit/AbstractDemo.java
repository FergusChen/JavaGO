package main.inherit;

/**
 * Created by Administrator on 2016/8/14.
 */
abstract class Employee{
    private String name;
    private String id;
    private double pay;

    Employee(String name, String id, double pay){
        this.name = name;
        this.id = id;
        this.pay = pay;
    }

    public abstract void work();
}

class Manager extends Employee {
    private double bounds;
    Manager(String name, String id, double pay, double bounds){
        super(name, id, pay);
        this.bounds = bounds;
    }
    public void work(){
        System.out.println("manager work");
    }

}

class NormalEmp extends Employee{
    NormalEmp(String name, String id, double pay){
        super(name, id, pay);
    }
    public void work(){
        System.out.println("normal employee work");
    }
}
