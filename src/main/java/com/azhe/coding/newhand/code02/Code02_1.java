package com.azhe.coding.newhand.code02;

import java.util.Arrays;

/**
 * 范围和
 * 随机数组
 * 等概率数组验证
 * @author linzherong
 * @date 2023/6/13 22:37
 */
public class Code02_1 {

    public static void main(String[] args) {
        Code02_1 code02_1 = new Code02_1();
//        int[] arr = {1,4,5,7,12,14,16,23,25};
//        System.out.println(code02_1.rangeSum1(0, 3, arr));
//        System.out.println(code02_1.rangeSum2(0, 3, arr));
//        System.out.println(code02_1.rangeSum1(3, 7, arr));
//        System.out.println(code02_1.rangeSum2(3, 7, arr));

//        System.out.println(Arrays.toString(code02_1.randomArray(7, 20)));
//        System.out.println(Arrays.toString(code02_1.random()));

        int[] arr = new int[8];
        for (int i = 0; i < 100_0000; i++) {
            arr[code02_1.random(new Code02.RandomBox(13,17), 0, 7)]++;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println((double) arr[i]/100_0000);
        }

    }

    /**
     * 范围和（1）
     */
    public int rangeSum1(int start, int end, int[] arr) {
        // 定义前缀和数组
        int[] sumArr = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sumArr[i] = (sum+=arr[i]);
        }
        // 获取范围和
        return start == 0? sumArr[end] : sumArr[end] - sumArr[start-1];
    }

    /**
     * 范围和（2）
     */
    public int rangeSum2(int start, int end, int[] arr) {
        // 定义二维数组, 最后一个x轴不需要记录，因为当start是最后一个，end只可能是最后一个，否则就是不符合规范
        int[][] help = new int[arr.length-1][arr.length];
        for (int i = 0; i < arr.length-1; i++) {
            int sum = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                sum += arr[j];
                help[i][j] = sum;
            }
        }
        return start == end? arr[start] : help[start][end];
    }

    /**
     * 随机数组
     */
    public int[] randomArray(int maxSize, int max) {
        // size
        int size = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    /**
     * 等概率数组
     */
    public double[] random() {
        int size = 100_0000;
        int[] arr = new int[10];
        for (int i = 0; i < size; i++) {
            // 0~9
            arr[(int)(Math.random() * 10)] ++;
        }
        double[] target = new double[10];
        for (int i = 0; i < arr.length; i++) {
            target[i] = (double) arr[i] / size;
        }
        return target;
    }

    /**
     * 等概率1到5
     * @return
     */
    private int range1_5() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 等概率0到1
     * @return
     */
    public int range0_1() {
        int n;
        do {
            n = range1_5();
        } while (n == 3);
        return n & 1;
    }

    /**
     * 利用 range1_5函数获得 0_7函数
     */
    public int range0_7() {
        return (range0_1() << 2) + (range0_1() << 1) + range0_1();
    }

    /**
     * 不等概率返回 0,1
     */
    private int p() {
        return Math.random() < 0.8? 0 : 1;
    }

    /**
     * 等概率 0，1
     */
    private int p_to_0_and_1() {
        int i;
        do {
            i = p();
        } while (i == p());
        return i;
    }

    /**
     * 利用p函数等概率返回0~7
     */
    public int p_to_0_and_7() {
        int i;
        return (p_to_0_and_1() << 2) + (p_to_0_and_1() << 1) + p_to_0_and_1();
    }

    /**
     * 利用box等概率返回0,1
     */
    public int range01FromBox(Code02.RandomBox randomBox) {
        int min = randomBox.min();
        int max = randomBox.max();
        // 范围是否是奇数
        boolean flag = ((max - min) & 1) == 0;
        int middle = ((max - min) >> 1) + min;
        int i;
        do {
            i = randomBox.random();
        } while (flag && i == middle);
        return i <= middle? 0 : 1;
    }

    /**
     * 返回 [from,to]
     */
    public int random(Code02.RandomBox randomBox, int from, int to) {
        // 中间相差的可能数据
        int f = to - from;
        // bit 数
        int i = 0;
        do {
            i++;
            f >>= 1;
        } while (f > 0);
        int num;
        do {
            num = 0;
            for (int j = i-1; j >=0; j--) {
                num += (range01FromBox(randomBox) << j);
            }
        } while (num > to - from);
        return from + num;
    }

}
