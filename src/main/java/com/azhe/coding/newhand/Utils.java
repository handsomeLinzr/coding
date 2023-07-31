package com.azhe.coding.newhand;

import java.util.Arrays;
import java.util.Random;

/**
 * 工具类
 * @author linzherong
 * @date 2023/7/16 11:59
 */
public class Utils {

    private static final Random RANDOM = new Random();

    /**
     * 随机数组
     * @param maxNum
     * @param maxSize
     * @return
     */
    public static int[] getRandomArray(int maxNum, int maxSize) {
        // 获取长度
        int size;
        do {
            size = RANDOM.nextInt(maxSize);
        } while (size <= 0);
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            int c = RANDOM.nextInt(maxSize);
            result[i] = Math.random() < 0.5? c : -c;
        }
        return result;
    }

    public static int[] getRandomArraySorted(int maxNum, int maxSize) {
        int size;
        do {
            size = RANDOM.nextInt(maxSize);
        } while (size <= 0);
        int[] arr = new int[size];
        arr[0] = -5;
        for (int i = 1; i < arr.length; i++) {
            int c;
            do {
               c = RANDOM.nextInt(maxNum);
            } while (c == 0);
            arr[i] = arr[i-1] + c;
        }
        return arr;
    }

    /**
     * 打印数据
     * @param arr
     */
    public static void sout(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}
