package main.oo.polymorphism;

/**
 * Created by Administrator on 2016/12/7.
 */
interface PCI {
    void open();

    void close();
}

class MainBoard {
    public void run(){
        System.out.println("mainboard run");

    }

    public void usePCI(PCI p){
        if(p != null) {
            p.open();
            p.close();
        }
    }

}

class NetCard implements PCI{
    public void open(){
        System.out.println("netcard run");
    }

    public void close(){
        System.out.println("newcard close");
    }
}

public class MainBoardDemo {
    public static void main(String[] args){
        MainBoard mb = new MainBoard();
        mb.run();
//        mb.usePCI(null);
        mb.usePCI(new NetCard());
    }
}


