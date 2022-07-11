package com.azhe.coding.newhand.code05;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: add  delete  contain
 *
 * @author Linzherong
 * @date 2022/7/8 12:55 下午
 */
public class BitMap01 {

    public static void main(String[] args) {
        testBitMap();
    }

    /**
     * 自定义位图
     */
    public static class BitMap {

        // 每个long可以表示64位，最大值是 maxValue 需要的long类型的个数是 (maxValue/64) +1 = (maxValue >>> 6) + 1
        public BitMap(int maxValue) {
            this.repository = new long[(maxValue >>> 6) + 1];
        }

        private final long[] repository;

        /**
         * 加入
         * @param num
         */
        public void add(int num) {
            // repository[num >>> 6] 表示 num 坐在的 long位置, num & 63 即获得num的后6位的值，即除以64得到的值，
            // 相或返则将对应的位置设置为1
            repository[num >>> 6] |= (1L << (num & 63));
        }

        /**
         * 是否包含
         * @param num
         */
        public boolean contain(int num) {
            return (repository[num >>> 6] & (1L << (num & 63))) != 0 ;
        }

        /**
         * 移除
         * @param num
         */
        public void remove(int num) {
            // repository[num >>> 6] 获得 num 所在的 long 数组位置
            // 1L << (num & 63) 获得 num 对应的位置设置为1，取反则是 除了 num的位置是0，其他都为1
            // 相与得到的结果是 将 num 对应的位置 设为 0
            repository[num >>> 6] &= ~(1L << (num & 63));
        }

    }

    public static void testBitMap() {
        int testTimes = 100_0000;
        int maxValue = 100_0000;
        boolean success = true;
        Set<Integer> set = new HashSet<>(maxValue);
        BitMap bitMap = new BitMap(maxValue);
        for (int i = 0; i < testTimes; i++) {
            double c = Math.random();
            if (c <= 0.33) {
                int value = (int) (Math.random() * maxValue);
                set.add(value);
                bitMap.add(value);
            } else if (c <= 0.66) {
                int value = (int) (Math.random() * maxValue);
                set.remove(value);
                bitMap.remove(value);
            } else {
                int value = (int) (Math.random() * maxValue);
                if (set.contains(value) != bitMap.contain(value)) {
                    System.out.println("fail......");
                    success = false;
                    break;
                }
            }
        }
        for (int i = 0; i < maxValue; i++) {
            if (set.contains(i) != bitMap.contain(i)) {
                System.out.println("fail......");
                success = false;
                break;
            }
        }
        if (success) {
            System.out.println("SUCCESS!!!!!");
        }
    }


}
