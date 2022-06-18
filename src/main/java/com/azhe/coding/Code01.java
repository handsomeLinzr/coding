package com.azhe.coding;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Linzherong
 * @date 2022/6/17 12:15 上午
 */
public class Code01 {
    public static void main(String[] args) {
//        print(Integer.MIN_VALUE);
//        System.out.println(mul(5));
        int[] arr = {1,6,4,89,45,2,3,4,7,2,5,31};
//        selectSort(arr);
//        bubbleSort(arr);
        insertSort(arr);
        printArr(arr);
    }

    /**
     * 打印出一个int数字的32位二进制信息
     * @param num
     */
    private static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            // 小端，左边高位
            System.out.print( (num & (1 << i)) == 0? "0" : "1");
        }
    }

    /**
     * 计算n的阶乘相加
     * @param num
     * @return
     */
    private static int mul(int num) {
        int sum = 0;
        int currentMul = 1;
        for (int i = 1; i <= num; i++) {
            currentMul *= i;
            sum += currentMul;
        }
        return sum;
    }

    /**
     * 选择排序
     * 从小到大排序
     */
    private static void selectSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            // i 为需要进行换为位置
            int minValueIndex = i;
            for (int j = i+1; j < arr.length ; j++) {
                // j 为正在比较的位置
                minValueIndex = arr[minValueIndex] > arr[j]? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    /**
     * 冒泡排序
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = arr.length-1; i > 0; i--) {
            // i 为冒泡的目的位置
            for (int j = 1; j <= i; j++) {
                if (arr[j] < arr[j-1]) {
                    swap(arr, j, j-1);
                }
            }
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // i 为当前数最开始需要插入数据的位置
            for (int j = i; j > 0 && arr[j] < arr[j-1]; j--) {
                // j为当前正在插入数据的位置
                swap(arr, j, j-1);
            }
        }
    }

    /**
     * 交换
     * @param arr
     * @param left
     * @param right
     */
    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    /**
     * 打印数组
     * @param arr
     */
    private static void printArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
