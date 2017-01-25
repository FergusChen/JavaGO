package main.io;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yudong on 17/1/25.
 * 学生列表, 按照成绩排序, 并且保存到文件中, 以便下次查阅
 */
public class IOexercise {

    public static void main(String[] args) throws IOException{
        Comparator<Student> cmp = Collections.reverseOrder(); //获取反向比较器
        Set<Student> students = StudentUtil.getStudents(cmp);

        StudentUtil.write2File(students);

    }
}

class Student implements Comparable<Student>{
    private String name;
    private int math, cn, en;
    private int sum;
    public Student(String name, int math, int cn, int en){
        this.name = name;
        this.math = math;
        this.cn = cn;
        this.en = en;
        this.sum = math + cn + en;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getSum(){
        return this.sum;
    }

    public int hashCode(){
        return name.hashCode() + sum * 75;
    }
    public boolean equals(Object obj){
        if(!(obj instanceof Student)){
            throw new ClassCastException("类型不匹配");
        }
        Student s = (Student)obj;
        return this.name.equals(s.name) && this.sum == s.sum;

    }

    public int compareTo(Student s){
        int num = new Integer(this.sum).compareTo(new Integer(s.sum));
        if(num == 0){
            num = this.name.compareTo(s.name);
        }

        return num;
    }

    public String toString(){
        return this.name + "[" + math + ", " + cn + ", " + en  + "]";
    }

}

class StudentUtil{
    /**
     * 从键盘输入, 获取学生对象. 这个是默认比较器方法
     * @return 键盘输入的学生集合
     * @throws IOException
     */
    public static Set<Student> getStudents() throws IOException{
        return getStudents(null);
    }

    /**
     * 从键盘输入, 获取学生对象. 此方法可以传入一个比较器
     * @param cmp
     * @return
     * @throws IOException
     */
    public static Set<Student> getStudents(Comparator<Student> cmp) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        Set<Student> students = null;
        if(cmp == null) {
            students = new TreeSet<>();
        }else{
            students = new TreeSet<>(cmp);
        }
        while((line = bufferedReader.readLine()) != null){
            if("over".equals(line)){
                break;
            }
            String[] info = line.split(",");
            Student stu = new Student(info[0], Integer.parseInt(info[1]),
                    Integer.parseInt(info[2]),
                    Integer.parseInt(info[3]));
            students.add(stu);
        }

        bufferedReader.close();
        return students;
    }

    public static void write2File(Set<Student> students) throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("testFiles/students.txt"));
        for(Student stu: students){
            bufferedWriter.write(stu.toString() + "\t");
            bufferedWriter.write(stu.getSum() + "");
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }
}