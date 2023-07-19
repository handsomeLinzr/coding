package com.azhe.coding.newhand.code01;

import com.azhe.coding.newhand.Utils;

import java.util.Objects;

/**
 * @author linzherong
 * @date 2023/7/11 22:49
 */
public class Code01_1 {

    public static void main(String[] args) {
        Code01_1 instance = new Code01_1();
//        System.out.println(instance.intTo32(65535));
//        System.out.println(instance.longTo64(65535));
//        System.out.println(instance.mul(5));  // 153
        for (int i = 0; i < 100_0000; i++) {
            int[] array = Utils.getRandomArray(10, 1034);
            if (!instance.compare(instance.selectSort(array), instance.bubbleSort(array))) {
                System.out.println("stop");
                return;
            }
            if (!instance.compare(instance.selectSort(array), instance.insertSort(array))) {
                System.out.println("stop");
                return;
            }
        }
    }

    /**
     * 32位
     * @param num
     * @return
     */
    private String intTo32(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >=0; i--) {
            sb.append((num & 1 << i) == 0? 0 : 1);
            if ((i & 3) == 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * 转64位
     * @param num
     * @return
     */
    private String longTo64(long num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 63; i >=0; i--) {
            sb.append((num & 1L<<i) == 0? 0 : 1);
            if ((i & 7) == 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * 阶乘相加
     * @param i
     * @return
     */
    private long mul(int i) {
        if (i <= 1) {
            return i;
        }
        long result = 1;
        long current = 1;
        for (int j = 2; j <= i; j++) {
            current *= j;
            result += current;
        }
        return result;
    }

    /**
     * 数组复制
     * @param array
     * @return
     */
    private int[] copyArray(int[] array) {
        if (Objects.isNull(array)) {
            return null;
        }
        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);
        return result;
    }

    private void swap(int[] arr, int p, int q) {
        if (p != q) {
            arr[p] = arr[p] ^ arr[q];
            arr[q] = arr[p] ^ arr[q];
            arr[p] = arr[p] ^ arr[q];
        }
    }

    private boolean compare(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 插入排序， O(N^2)，O(1)，无
     * @param array
     * @return
     */
    private int[] selectSort(int[] array) {
        int[] arr = copyArray(array);
        if (Objects.isNull(arr)) {
            return arr;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                min = arr[min] < arr[j] ? min : j;
            }
            swap(arr, i, min);
        }
        return arr;
    }

    /**
     * 冒泡排序,  O(n^2), O(1), 有
     * @param array
     * @return
     */
    private int[] bubbleSort(int[] array) {
        int[] arr = copyArray(array);
        if (Objects.isNull(arr)) {
            return arr;
        }
        for (int i = arr.length-1; i >0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序  O(n^2), O(1), 有
     * @param array
     * @return
     */
    private int[] insertSort(int[] array) {
        int[] arr = copyArray(array);
        if (Objects.isNull(arr)) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j>0 && arr[j]<arr[j-1] ; j--) {
                swap(arr, j, j-1);
            }
        }
        return arr;
    }

}
