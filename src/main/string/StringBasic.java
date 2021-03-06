package main.string;

/**
 * Created by Administrator on 2016/8/17.
 */
public class StringBasic {

    /** 翻转整个字符串
     * @param str 待翻转的字符串。
     * @return 已翻转的字符串
     * */
    public static String reverse(String str){
        return reverse(str, 0, str.length());
    }

    /**
     * 翻转字符串的部分字符
     * @param str 待翻转的字符串
     * @param beginIndex 翻转部分开始位置
     * @param endIndex 翻转部分结束位置
     * @return 指定位置已翻转的字符串
     * */
    public static String reverse(String str, int beginIndex, int endIndex){
        char[] cstr = reverse(str.toCharArray(), beginIndex, endIndex);
        return new String(cstr);
    }

    public static char[] reverse(char[] arr){
        for(int begin = 0, end = arr.length - 1; begin < end; begin++, end--){
            swap(arr, begin, end);
        }
        return arr;
    }
    public static char[] reverse(char[] arr, int beginIndex, int endIndex){
        //java中的惯例，如果是范围，则包含头，不包含尾。
        for(int begin = beginIndex, end = endIndex - 1; begin < end; begin++, end--){
            swap(arr, begin, end);
        }
        return arr;
    }

    private static void swap(char[] arr, int index1, int index2){
        char temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**获取key在str中出现的次数*/
    public static int getSubStringCount(String str, String key){
        int count = 0;
        int index = 0;
        while((index = str.indexOf(key,index)) != -1){
            index += key.length();
            count++;
        }
        return count;
    }

    /**找到最长公共子串的低效方法*/
    public static String getMaxSubString(String str1, String str2){
        String longer = str1.length() > str2.length()? str1: str2;
        String shorter = longer == str1 ? str2 : str1;

        int minLength = shorter.length();
        for(int i = 0; i < minLength; i++){
            for(int j = 0, k = minLength - i; k != minLength + 1; j++, k++){
                String subStr = shorter.substring(j, k);
                if(longer.contains(subStr)){
                    return subStr;
                }
            }
        }
        return "";
    }

    public static void main(String[] args){
        String str = "CDEB";
        System.out.println(reverse(str, 1, 3));
        String str1 = "ABBCDEBBABC";
        System.out.println("key'BB' Count:" + getSubStringCount(str1, "BB"));
        System.out.println(getMaxSubString(str, str1));
    }
}
