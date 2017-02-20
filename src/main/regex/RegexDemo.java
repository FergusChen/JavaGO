package main.regex;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yudong on 17/2/14.
 * 正则表达式的作用:
 * 一:  [匹配字符串]
 * 校验QQ号:
 *  5-15位的数字
 *  开头不能是0
 *
 * 校验手机号:
 *  11位数字
 *  第1位为1
 *  第2位为3或5或8
 *
 * 二:  [切割字符串]
 * 用正则表达式描述切割符, split方法传入正则表达式. 例如,连续多个空格相连.
 *
 * 三:  [替换字符串]
 * 正则表达式描述匹配串, 用replaceAll方法替换相应的新字符串.
 *
 * 四: [查找特定字符串]
 * 用正则表达式描述匹配规则, 查找于此匹配规则相符的字符串.
 */
public class RegexDemo {
    public static void main(String[] args){

//        verifyMobile();
//        splitString();
//        replaceContact();
        findStr();
    }

    public static void verifyQQ(){
        Scanner input = new Scanner(System.in);
        while(true){
            String qq = input.next();
            if("quit".equals(qq) || qq == null){
                break;
            }

            String regex = "[1-9]\\d{4,14}";
            if(qq.matches(regex)){
                System.out.println("'" + qq + "' is OK.");
            }else{
                System.out.println("'" + qq + "' is illegal!!!");
            }
        }
    }

    public static void verifyMobile(){
        Scanner input = new Scanner(System.in);
        while(true){
            String mobile = input.next();
            if("quit".equals(mobile) || mobile == null){
                break;
            }

            String regex = "1[358]\\d{9}";
            System.out.println(mobile + ": " + mobile.matches(regex));
        }
    }

    public static void splitString(){
        String str = "zhangsan   lisi wangwu  zhaoliu";

        String[] arr = str.split(" +");
        System.out.println("切割长度: " + arr.length);

        for(String word: arr){
            System.out.println(word);
        }

        //.切割
        String str1 = "zhangsan.lisi.wangwu.zhaoliu";
        String[] arr1 = str1.split("\\.");  // .在正则表达式中代表任意字符, 必须转义, 转义后仍然是正则表达式 \.
        System.out.println("切割长度: " + arr1.length);

        //反斜杠来切割
        String str2 = "c:\\abc\\dir";
        String[] arr2 = str2.split("\\\\");  // .在正则表达式中代表任意字符, 必须转义, 转义后仍然是正则表达式 \.
        System.out.println("切割长度: " + arr2.length);

        //叠字切割, 用到分组和变量. 分组是可以嵌套, 组顺序看第1个左括号的次序.
        String str3 = "abcdeeksifffkfllaa";
        String[] arr3 = str3.split("(.)\\1+"); //任意字符连续出现2次或多次.()表示分组, \n获取分组(n表示分组的编号)
        System.out.println("切割长度: " + arr3.length);

        for(String word: arr3){
            System.out.println(word);
        }
    }

    /**
     * 正则表达式替换
     */
    public static void replaceContact(){
        String str = "加我的QQ883942883, 或打我的电话:13899492993";
        String regex =  "([1-9]\\d{4,14})|(1[358]\\d{9})";
        System.out.println(str.replaceAll(regex,"###"));

        //叠字替换成1个
        String str1 = "abcdeeksifffkfllaa";
        String regex1 = "(.)\\1+";
        System.out.println(str1.replaceAll(regex1, "$1")); //$获取组, $1代表前面匹配规则的第1个组.

    }

    /**
     * 正则表达式查找特定字符串
     * 这里涉及到Pattern对象和Matcher对象.
     * Pattern对象将正则表达式封装成对象, Matcher对象则描述匹配结果, 其定义了很多对匹配结果进行操作的方法.
     */
    public static void findStr(){
        String str = "go to the ant, you sluggard; consider its way and be wise!";
        System.out.println("源字符串: "+ str);
        String regex = "\\b[a-z]{3}\\b";

        //将正则表达式封装成对象
        Pattern pattern = Pattern.compile(regex);
        //Pattern对象的matcher方法将正则对象和要匹配的字符串相关联, 获取Matcher对象.
        Matcher matcher = pattern.matcher(str);

        //Matcher对象有很多方法,
        // find()类似于迭代器, 查找匹配上的字符串.
        // group()返回匹配上的子序列;
        // start()返回匹配上序列的开始位置;
        // end()返回匹配上序列的结束位置.  注: start和end也是包含头, 不包含尾
        while(matcher.find()){
            System.out.println(matcher.group() + ":  " + matcher.start() + "~" + matcher.end());
        }
    }
}
