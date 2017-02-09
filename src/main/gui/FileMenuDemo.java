package main.gui;

/**
 * Created by yudong on 17/2/7.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileMenuDemo {
    private Frame f;
    private TextArea ta;
    private MenuBar bar;
    private Menu fileMenu;
    private MenuItem openItem, saveItem, exitItem;
    private FileDialog openDia, saveDia;

    private File file;

    FileMenuDemo() {
        init();
    }

    public void init() {
        f = new Frame("my window");
        f.setBounds(300, 100, 500, 600);
//        f.setLayout(new FlowLayout());

        ta = new TextArea();
        f.add(ta);

        bar = new MenuBar();
        fileMenu = new Menu("文件");

        openItem = new MenuItem("打开");
        saveItem = new MenuItem("保存");
        exitItem = new MenuItem("退出");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        bar.add(fileMenu);
        f.setMenuBar(bar);

        openDia = new FileDialog(f, "打开文件", FileDialog.LOAD);
        saveDia = new FileDialog(f, "保存文件", FileDialog.SAVE);


        myEvent();
        f.setVisible(true);
    }

    public void myEvent() {
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDia.setVisible(true);
                String dirPath = openDia.getDirectory();
                String fileName = openDia.getFile();
                if (dirPath == null || fileName == null) {
                    return;
                }

                ta.setText("");
                file = new File(dirPath, fileName);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        ta.append(line + "\r\n");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException("读取失败");
                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException("关闭文件流失败");
                    }
                }
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file == null) {
                    saveDia.setVisible(true);
                    String dirPath = saveDia.getDirectory();
                    String fileName = saveDia.getFile();

                    if (dirPath == null || fileName == null) {
                        return;
                    }

                    file = new File(dirPath, fileName);
                }
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file));
                    String text = ta.getText();
                    writer.write(text);

                } catch (IOException ex) {
                    throw new RuntimeException("保存出错");
                } finally {
                    try {
                        if (writer != null) {
                            writer.close();
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException("关闭流错误");
                    }
                }

            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new FileMenuDemo();
    }
}
