package com.azhe.coding.newhand.code02;

import com.azhe.coding.newhand.Utils;

import java.util.Random;

/**
 * @author linzherong
 * @date 2023/7/20 18:03
 */
public class Code02_1 {

    public static void main(String[] args) {
        Code02_1 instance = new Code02_1();
        int[] array = new int[21];
        for (int i = 0; i < 500_0000; i++) {
            array[instance.random(new Code02.RandomBox(3,7), 13, 20)]++;
        }
        Utils.sout(array);
    }

    /**
     * 范围和  方法一
     */
    private int rangeSum1(int start, int end, int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (start > end) {
            return 0;
        }
        int[][] help = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            int sum = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                help[i][j] = sum + arr[j];
                sum = help[i][j];
            }
        }
        return start == end? arr[start] : help[start][end];
    }

    /**
     * 范围和2
     */
    public int rangeSum2(int start, int end, int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (start > end) {
            return 0;
        }
        int[] help = new int[arr.length];
        help[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            help[i] = help[i-1] + arr[i];
        }
        return help[end] - help[start] + arr[start];
    }

    private int randomByNum(int num) {
        return new Random().nextInt(num);
    }

    /**
     * 1 到 5
     */
    public int range1_5() {
        return new Random().nextInt(5) + 1;
    }

    public int range0_7_from_1_5() {
        return range0_1_from_1_5() << 2 | range0_1_from_1_5() << 1 | range0_1_from_1_5();
    }

    private int range0_1_from_1_5() {
        int i;
        do {
            i = range1_5();
        } while (i == 3);
        return i < 3? 0 : 1;
    }

    public int random0_1_to_0_8() {
        return (int) (Math.random() * 8);
    }

    /**
     * 不等概率返回 0 1
     */
    public int p() {
        return Math.random() < 0.8? 0 : 1;
    }

    /**
     * 利用 p 等概率 0 到 7
     */
    public int p_to_0_to_7() {
        return range01FromP() << 2 | range01FromP() << 1 | range01FromP();
    }

    private int range01FromP() {
        int i;
        do {
            i = p();
        } while (i == p());
        return i;
    }

    // 这个结构是唯一的随机机制
    // 你只能初始化并使用，不可修改
    public class RandomBox {
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
    public int rand01(Code02.RandomBox randomBox) {
        boolean j = ((randomBox.max() - randomBox.min() + 1) & 1) == 1;  // 是否是奇数个
        int middle = (randomBox.max() - randomBox.min() >> 1) + randomBox.min();
        int i;
        do {
            i = randomBox.random();
        } while (j && i == middle);
        return i <= middle? 0:1;
    }

    // 给你一个RandomBox，这是唯一能借助的随机机制
    // 等概率返回from~to范围上任何一个数
    // 要求from<=to
    public int random(Code02.RandomBox randomBox, int from, int to) {
        int size = to - from;  // 相差数量
        int i = 1;  // 位数
        int n = 1;  // 当前值
        while (n < size) {
            n = 1<<i | n;
            i++;
        }
        int result = 0;
        do {
            for (int j = 0; j < i; j++) {
                result = result | rand01(randomBox) << j;
            }
        } while (result > size);
        return from + result;
    }

}
