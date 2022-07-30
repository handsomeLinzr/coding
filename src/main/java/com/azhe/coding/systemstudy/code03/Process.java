package com.azhe.coding.systemstudy.code03;

/**
 * Description: 递归
 * 1.递归求数组最大值
 *
 * @author Linzherong
 * @date 2022/7/27 1:08 下午
 */
public class Process {
    public static void main(String[] args) {
        test(100_0000, 100_0000, 100);
    }

    private static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l >= r) {
            return arr[l];
        }
        int middle = l + ((r-l) >> 2);
        return Math.max(process(arr, l, middle), process(arr, l+1, r));
    }


    private static int getMaxTest(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    private static void test(int testTime, int maxValue, int maxSize) {
        for (int i = 0; i < testTime; i++) {
            int[] arr = generalArr(maxValue, maxSize);
            if (getMax(arr) != getMaxTest(arr)) {
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("成功");
    }

    private static int[] generalArr(int maxValue, int maxSize) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

}
