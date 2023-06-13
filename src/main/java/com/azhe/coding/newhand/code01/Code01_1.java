package com.azhe.coding.newhand.code01;

import java.util.Arrays;

/**
 * 打印出一个int数字的32位二进制信息
 * 打印出一个int数字的64位二进制信息
 * 计算n的阶乘相加
 * 选择排序
 *
 * @author linzherong
 * @date 2023/6/12 17:05
 */
public class Code01_1 {

    public static void main(String[] args) {
        Code01_1 code01_1 = new Code01_1();
        System.out.println(code01_1.print32(25));
        System.out.println(code01_1.print64(25));
        System.out.println(code01_1.mul(5));
        int[] arr = {1,7,9,3,4,23,78,34,-1,-45,44,30,34,78,26,-1,-20,-33,45};
        System.out.println(Arrays.toString(code01_1.selectSort(arr)));
        System.out.println(Arrays.toString(code01_1.bubbleSort(arr)));
        System.out.println(Arrays.toString(code01_1.insertSort(arr)));
    }

    /**
     * 打印出一个int数字的32位二进制信息
     * @param num
     * @return
     */
    public String print32(int num) {
        String result = "";
        for (int i = 31; i >= 0; i--) {
            if ((i+1 & 7) == 0) {
                result += " ";
            }
            result += (num & (1<<i)) == 0?"0":"1";
        }
        return result;
    }

    /**
     * 打印出一个long数字的64位二进制信息
     * @param num
     * @return
     */
    public String print64(long num) {
        String result = "";
        for (int i = 63; i >= 0; i--) {
            if ((i+1 & 7) == 0) {
                result += " ";
            }
            result += (num & (1L << i)) == 0? "0" : "1";
        }
        return result;
    }

    /**
     * 计算n的阶乘相加
     * @param n
     * @return
     */
    public int mul(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return n * mul(n-1);
    }

    /**
     * 选择排序，O(n^2)，稳定性：无（涉及到随机替换，没有稳定性）
     * @param arr
     * @return
     */
    public int[] selectSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        // 复制，避免修改到源数组
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        for (int i = 0; i < copy.length-1; i++) {
            int min = i;
            for (int j = i+1; j < copy.length; j++) {
                min = copy[min] < copy[j]? min : j;
            }
            swap(copy, i, min);
        }
        return copy;
    }

    /**
     * 冒泡排序，O(n^2)，稳定性（有）
     * @param arr
     * @return
     */
    public int[] bubbleSort(int[] arr) {
        if (arr.length <=1) {
            return arr;
        }
        // 复制
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        for (int i = copy.length-1; i >=0; i--) {
            for (int j = 0; j < i; j++) {
                if (copy[j] > copy[j+1]) {
                    swap(copy, j, j+1);
                }
            }
        }
        return copy;
    }

    /**
     * 插入排序，O(n^2)，稳定性（有）
     * @param arr
     * @return
     */
    public int[] insertSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        for (int i = 1; i < copy.length; i++) {
            for (int j = i; j > 0 && copy[j] < copy[j-1] ; j--) {
                swap(copy, j, j-1);
            }
        }
        return copy;
    }

    /**
     * 交换
     */
    public void swap(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        arr[left] = arr[left] ^ arr[right];
        arr[right] = arr[left] ^ arr[right];
        arr[left] = arr[left] ^ arr[right];
    }


}
