package com.azhe.coding.newhand.code08;

import java.util.Arrays;

/**
 * Description:
 * 将小于等于某个值的放左边，其他放右边
 * 小于   |   等于    |   大于
 * 快排，递归
 * 快排，非递归  用任务拆解
 *
 * @author Linzherong
 * @date 2022/7/13 10:59 下午
 */
public class PartitionAndQuickSort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 6, 7, 4, 3, 9, 0, 1, 2, 4, 3};
//        splitNum1(arr);
        splitNum2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 小于等于左边，大于右边
     */
    public static void splitNum1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int p = 0;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] <= arr[arr.length-1]) {
                swap(arr, i++, p++);
            } else {
                i++;
            }
        }
    }

    /**
     * 小于左边，等于中间，大于右边
     */
    public static void splitNum2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int p = 0; // 小于
        int q = arr.length-1;  // 大于
        int i = 0;
        while (i < q) {
            if (arr[i] < arr[arr.length-1]) {
                // 和p位置交换
                swap(arr, i++, p++);
            } else if (arr[i] > arr[arr.length-1]) {
                // 和q位置交换，此时i不变，因为交换后i位置的数据还没比较过
                swap(arr, i, --q);
            } else {
                i++;
            }
        }
        swap(arr, arr.length - 1, q);
    }

    /**
     * 交换数组
     * @param arr
     * @param l1
     * @param l2
     */
    public static void swap(int[] arr, int l1, int l2) {
        if (l1 == l2) {
            return;
        }
        int i = arr[l1];
        arr[l1] = arr[l2];
        arr[l2] = i;
    }


}
