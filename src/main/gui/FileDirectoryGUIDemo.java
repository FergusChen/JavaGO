package main.gui;

/**
 * Created by yudong on 17/2/6.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FileDirectoryGUIDemo {
    private Frame f;
    private TextField tf;
    private Button but;
    private TextArea ta;

    FileDirectoryGUIDemo(){
        init();
    }

    public void init(){
        f = new Frame("my directory window");
        f.setBounds(300, 100, 600, 600);
        f.setLayout(new FlowLayout());

        tf = new TextField(60); //60列

        but = new Button("转到");

        ta = new TextArea(25, 70);

        f.add(tf);
        f.add(but);
        f.add(ta);

        myEvent();
        f.setVisible(true);

    }

    private void myEvent(){
        //给按钮添加监听器
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gotoDir();
            }
        });

        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    gotoDir();
                }
            }
        });

        //给窗体添加监听器
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private  void gotoDir(){
        ta.setText("");
        String dirPath = tf.getText();
        File dir = new File(dirPath);
        if(dir.exists() && dir.isDirectory()){
            String[] names = dir.list();
            for(String name: names){
                ta.append(name + "\r\n");
            }
        }else{
            Dialog d = new Dialog(f, "提示:", true); //第3个参数是模式,确定此对话框未消失以前, f能否操作.
            d.setBounds(400, 200, 300, 150);
            d.setLayout(new FlowLayout());
            Label lab = new Label("路径错误,请重新输入");
            Button button = new Button("确定");

            d.add(lab);
            d.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d.setVisible(false);
                }
            });

            d.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    d.setVisible(false);
                }
            });
            d.setVisible(true);

        }
    }
    public static void main(String[] args){
        new FileDirectoryGUIDemo();
    }
}
