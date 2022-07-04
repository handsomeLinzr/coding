package com.azhe.coding.newhand.code02;

import java.util.Arrays;
import java.util.Objects;
import static java.util.Optional.*;

/**
 * Description:
 *
 * @author Linzherong
 * @date 2022/6/22 12:30 下午
 */
public class Code02 {
    public static void main(String[] args) {
//        random0_1_to_0_8(5, 1000_0000);
//        random0_k(5, 1000_0000);
//        randomXToXPower2(0.17, 1000_0000);
//        randomXToXPower3(0.23, 1000_0000);
//        randomXToXPower2_2(0.23, 1000_0000);
        testRandom();
    }

    /**
     * 范围和 方法一 二维数组
     * @return
     */
    public static int rangeSum1(int start, int end, int[] arr) {
        if (Objects.isNull(arr) || arr.length == 0 || start > end || arr.length -1  < end) {
            return 0;
        }
        // 创建二维数组，用于保存各个区间和的大小
        int[][] resultArr = new int[arr.length][arr.length];
        for (int startIndex = 0;  startIndex < arr.length; startIndex++) {
            int currentSum = 0;
            for (int endIndex = startIndex; endIndex < arr.length; endIndex++) {
                resultArr[startIndex][endIndex] = currentSum + arr[endIndex];
                currentSum += arr[endIndex];
            }
        }
        return resultArr[start][end];
    }

    /**
     * 前缀和 方法二 help数组
     * @return
     */
    public static int rangeSum2(int start, int end, int[] arr) {
        if (Objects.isNull(arr) || arr.length == 0 || start > end || arr.length - 1 < end) {
            return 0;
        }
        // 中间数组，保存 0 到 index 的和
        int[] tempArr = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            tempArr[i] = sum + arr[i];
            sum += arr[i];
        }
        // 获得中间的和
        return start == 0? tempArr[end]:tempArr[end] - tempArr[start - 1];
    }

    /**
     * 随机数组
     */
    public static int[] randomArr() {
        int length = (int) (Math.random()*20);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int)(Math.random() * 100);
        }
        return arr;
    }

    /**
     * 等概率概率输出
     */
    public static void random() {
        int size = 1000_0000;
        int[] arr = new int[10];
        for (int i = 0; i < size; i++) {
            arr[(int) (Math.random() * 10)] ++;
        }
        printRandomArr(arr, size);
    }

    public static void printRandomArr(int[] arr, int size) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "=====》出现的概率=====》" + (double) arr[i]/(double) size);
        }
    }

    /**
     * 等概率1到5
     * @return
     */
    public static int range1_5() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 利用 range1_5函数获得 0_7函数
     * @return
     */
    public static int range0_7_from_1_5() {
        return (a_from_1_5() << 2) + (a_from_1_5() << 1) + a_from_1_5();
    }
    // 0 1 发生器
    public static int a_from_1_5() {
        int a = 0;
        do {
            a = range1_5();
        } while (a == 3);
        return a < 3? 0 : 1;
    }

    /**
     * [0,1) 中0到x的
     * @param x
     */
    public static void randomX(double x, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (Math.random() < x) {
                count ++;
            }
        }
        System.out.println((double)count/(double)size);
    }

    // [0,1) ——> [0,8)
    public static void random0_1_to_0_8(double x, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (Math.random() * 8 < x) {
                count ++;
            }
        }
        System.out.println((double)count/(double)size);
        System.out.println((double)x/(double)8);
    }

    // [0,1) ——> [0,k - 1]
    public static void random0_k(int k, int size) {
        int[] arr = new int[k];
        for (int i = 0; i < size; i++) {
            arr[(int) (Math.random() * k)] ++;
        }
        printRandomArr(arr, size);
    }
    // [0,x)——>概率为x平方
    public static void randomXToXPower2(double x, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (Math.max(Math.random(), Math.random()) < x) {
                count ++;
            }
        }
        System.out.println((double)count/(double)size);
        System.out.println(Math.pow(x, 2));
    }
    // 转 Math.min
    // min(x,y) 只要有一个符合即可, 每个概率 x
    // 1 - (1-x)(1-x)
    public static void randomXToXPower2_2(double x, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (Math.min(Math.random(), Math.random()) < x) {
                count ++;
            }
        }
        System.out.println((double)count/(double)size);
        System.out.println(1 - Math.pow((1-x), 2));
    }

    // [0,x)——>概率为x立方
    public static void randomXToXPower3(double x, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (Math.max(Math.max(Math.random(), Math.random()), Math.random()) < x) {
                count ++;
            }
        }
        System.out.println((double)count/(double)size);
        System.out.println(Math.pow(x, 3));
    }
    // 不等概率返回 0,1
    public static int p() {
        return Math.random() < 0.8? 0 : 1;
    }

    // 利用p函数等概率返回0~7
    // 思路，利用 p函数 获得两个等概率的事件，如：调用两次，0，1 和 1，0 的概率是一样的，则可以以此入手
    public static int p_to_0_7() {
        // 转等概率0到7
        int r;
        do {
            r = p();
        } while (r == p());
        return r;
    }

    // 这个结构是唯一的随机机制
    // 你只能初始化并使用，不可修改
    public static class RandomBox {
        private final int min;
        private final int max;

        // 初始化时请一定不要让mi==ma
        public RandomBox(int mi, int ma) {
            min = mi;
            max = ma;
        }

        // 13 ~ 17
        // 13 + [0,4]
        public int random() {
            return min + (int) (Math.random() * (max - min + 1));
        }

        public int min() {
            return min;
        }

        public int max() {
            return max;
        }
    }
    // 利用条件RandomBox，如何等概率返回0和1
    public static int rand01(RandomBox randomBox) {
        int min = randomBox.min();  // 最小
        int max = randomBox.max();  // 最大
        int size = max - min + 1;  // 拥有可能的数量
        boolean odd = (size & 1) == 1;  // true 为 奇数
        int middle = (size >> 1);  // 中间的数
        int ans;
        do {
            ans = randomBox.random() - min;
        } while (odd && ans == middle);
        return ans < middle? 0 : 1;
    }
    // 给你一个RandomBox，这是唯一能借助的随机机制
    // 等概率返回from~to范围上任何一个数
    // 要求from<=to
    public static int random(RandomBox randomBox, int from, int to) {
        // 相等则直接返回
        if (from == to) {
            return from;
        }
        int size = to - from + 1;
        int offset = 0;  // 需要的二进制数
        while (size > (1 << offset)) {
            offset ++;
        }
        int n;
        do {
            n = 0;
            for (int i = 0; i < offset; i++) { // 做大需要移动 offset-1 位
                n |= rand01(randomBox) << i;  // 由于是1的移动，其他位都为0，所以可以用或运算替代加运算
            }
        } while (n > size - 1);
        return from + n;
    }

    public static void testRandom() {
        RandomBox box = new RandomBox(5, 15);
        int size = 1000_0000;
        int[] arr = new int[15];
        for (int i = 0; i < size; i++) {
            arr[random(box, 2,7)] ++;
        }
        printRandomArr(arr, size);
    }

}
