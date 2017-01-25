package main.io.file;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by yudong on 17/1/20.
 * 演示File类的基本操作,
 */
public class FileDemo {
    public static final String TestFilesPath = "/Users/yudong/Desktop/gitRepo/JavaGO/testFiles";
    public static final String BasicPath = "/Users/yudong/Desktop/gitRepo/JavaGO";
    public static final String TmpPath = "/Users/yudong/Desktop/gitRepo/JavaGO/tempDirectory";

    public static void main(String[] args) throws IOException{
//        basicDemo();
//        fileList();
//        filterFile();

        File dir = new File(BasicPath);
//        showDir(dir);
        showDir(dir, 0);

//        File tempDir = new File(TmpPath);
//        removeDir(tempDir);
    }

    /**
     * File类的基本操作
     * */
    public static void basicDemo() throws IOException{
        File f1 = new File("testFiles/demo.txt");
        File demo1 = new File("testFiles/demo1.txt");
        File f2 = new File(TestFilesPath, "fileDemo.txt");
        System.out.println("创建fileDemo.txt:" + f2.createNewFile()); //创建新文件, 返回Boolean值. File类方法createTempFile创建临时文件. 放在系统临时文件目录.
        System.out.println("删除fileDemo.txt:" + f2.delete());
        System.out.println("重命名/移动demo.txt" + demo1.renameTo(f2));

        File dir = new File(BasicPath);
        File f3 = new File("tempDir");
        File f4 = new File("tempDir" + File.separator + "aa" + File.separator + "bb" + File.separator + "cc");
        System.out.println("创建目录:" + f3.mkdir());
        System.out.println("创建多级目录:" + f4.mkdirs());
        System.out.println("isFile:" + dir.isFile() + "\tisDirectory:" + dir.isDirectory() + "\tisHidden" + dir.isHidden());

        System.out.println("demo.txt的路径: " + f1.getPath());
        System.out.println("demo.txt的绝对路径: " + f1.getAbsolutePath());
        System.out.println("fileDemo.txt的父目录: " + f1.getParent()); //只能针对绝对路径的File对象
    }

    /***
     * 列出指定目录下的文件:
     * listRoots: 根目录
     * list: 指定路径下的目录或文件名(String[])
     * listFiles: 指定路径下的目录或文件对象(File[])
     */
    public static void fileList(){
        File[] roots = File.listRoots(); //列出根目录(windows下是各个分区目录, Mac下只有/)
        System.out.println("系统根目录: ");
        for(File file: roots){
            System.out.println(file);
        }
        System.out.println("当前目录下的文件和文件夹: ");
        File dir = new File(BasicPath);
        String[] filenames = dir.list();
        for(String name:filenames){
            System.out.println(name);
        }
        System.out.println();

        System.out.println("当前目录下的文件和文件夹: ");
        File[] files = dir.listFiles();
        for(File file:files){
            System.out.println(file + "---" + file.length() + "B");
        }



    }
    /**
     * 文件筛选
     * 主要用到list方法传入匿名内部类
     * */
    public static void filterFile(){
        File dir = new File(BasicPath);
        String[] files = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        System.out.println("共筛选出"+ files.length + "个文件");
        for(String file: files){
            System.out.println(file);
        }
    }

    /**
     * 递归遍历目录
     * */
    public static void showDir(File dir){
        System.out.println(dir);
        File[] files = dir.listFiles();
        for(int i = 0; i < files.length; i++){
            if(files[i].isDirectory()){
                showDir(files[i]);
            }else{
                System.out.println(files[i]);
            }

        }
    }

    /**
     * 打印目录树
     * */
    public static String getLevel(int level){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < level; i++){
            sb.append("|--");
        }

        return sb.toString();
    }

    public static void showDir(File dir, int level){
        System.out.println(getLevel(level) + dir.getName());
        File[] files = dir.listFiles();
        level++;
        for(int i = 0; i < files.length; i++){
            if(files[i].isDirectory()){
                showDir(files[i], level);
            }else{
                System.out.println(getLevel(level) + files[i].getName());
            }
        }
    }

    /**
     * 删除目录(递归), 该方法谨慎操作,因为不经过回收站,直接删除
     * */
    public static void removeDir(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    removeDir(files[i]);
                } else {
                    System.out.println(files[i] + "::file::" + files[i].delete());
                }

            }
            System.out.println(dir + "::dir::" + dir.delete());
        }
    }

}
