package main.ArrayDemo;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ArrayDemo {

    public static void main(String[] args) {
        int[] arr1 = new int[3];  //定义1：an array of 3 elements
        int[] arr2 = new int[]{3, 1, 5, 6, 7};  //定义2： 初始化数组
        int[] arr3 = {3, 1, 5, 6, 7};  //等效于定义2

        printArr("排序前", arr2);
        bubbleSort(arr2);
        printArr("排序后", arr2);

        System.out.println(halfSearch1(arr2, 5));
    }

    //最值，注意max的初始值
    public static int getmax1(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int getmax2(int[] arr) {
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[max]) {
                max = i;
            }
        }
        return arr[max];
    }

    //排序，选择排序{3,5,2,6,2,1,-1},  每次循环，将剩余元素的最小值替换i的位置。
    public static void selectSort1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    //这种方式需要在堆内存中换好几次位置，比较高效的方法每一趟记录一次最大值的指针，最后只交换最大值和目的节点位置，这样节省堆内排序的时间。  --待验证
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //查找
    public static int halfSearch1(int[] arr, int key) {
        int min, max, mid;
        min = 0;
        max = arr.length - 1;
        mid = (max + min) / 2;

        while (arr[mid] != key) {
            if (arr[mid] < key) {
                min = mid + 1;
            } else if (arr[mid] > key) {
                max = mid - 1;
            }
            mid = (max + min) / 2;
            if (min > max) {
                return -1;
            }
        }
        return mid;
    }


    //辅助方法
    public static void printArr(String markStr, int[] arr) {
        System.out.print(markStr + ": [");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1)
                System.out.print(arr[i] + ", ");
            else
                System.out.println(arr[i] + "]");
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}

