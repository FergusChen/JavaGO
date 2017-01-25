package main.io.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yudong on 17/1/21.
 * 筛选中某个路径下所有的java文件,并将文件名保存到指定文件中
 */
public class FilterFile {
    public static void main(String[] args){
        File dir = new File(FileDemo.BasicPath);
        List files = new ArrayList<File>();
        fileListRecursion(dir, files);
        System.out.println(files.size());
        File javaFile = new File(dir, "testFiles/javaFile.txt");
        writeToFile(files, javaFile.toString());
    }

    /**
     * 递归查找指定目录下的所有java文件, 并存储到List中.
     * @param dir  指定目录
     * @param list  结果列表, 查找结果存储到此列表
     */
    public static void fileListRecursion(File dir, List<File> list){
        File[] files = dir.listFiles();
        for(int i = 0; i < files.length; i++){
            if(files[i].isDirectory()){
                fileListRecursion(files[i], list);
            }else{
                if(files[i].getName().endsWith(".java")){
                    list.add(files[i]);
                }
            }
        }
    }

    public static void writeToFile(List<File> files, String javaListFile){
        BufferedWriter bufWriter = null;
        try{
            bufWriter = new BufferedWriter(new FileWriter(javaListFile));
            for(File file: files){
                String path = file.getAbsolutePath();
                bufWriter.write(path);
                bufWriter.newLine();
                bufWriter.flush();
            }
        }catch (IOException e){
            throw new RuntimeException("IO异常" + e.toString());
        }finally {
            try{
                if(bufWriter != null){
                    bufWriter.close();
                }
            }catch (IOException e){
                throw new RuntimeException("关闭IO流失败");
            }
        }
    }

}
