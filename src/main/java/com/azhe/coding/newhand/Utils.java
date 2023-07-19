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
            size = RANDOM.nextInt(maxNum);
        } while (size <= 0);
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            int c = RANDOM.nextInt(maxSize);
            result[i] = Math.random() < 0.5? c : -c;
        }
        return result;
    }

    /**
     * 打印数据
     * @param arr
     */
    public static void sout(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}
