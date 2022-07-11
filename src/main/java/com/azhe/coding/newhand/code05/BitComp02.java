package com.azhe.coding.newhand.code05;

/**
 * Description: + - * /
 * leetcode 29
 * 32位最小值的数，相反数还是原数，因为负数比正数多一位，导致对应的正数超过32位
 * 最小值的问题需要转换，不能直接通过转绝对值来算
 *
 * @author Linzherong
 * @date 2022/7/8 1:00 下午
 */
public class BitComp02 {

    public static void main(String[] args) {
        test();
    }

    /**
     * 加法运算
     * 位运算为：异或 + 与 操作
     * @param a  0(1)，有限次的循环，最多31次
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        int n = a ^ b;
        int m = (a & b) << 1;
        int temp;
        while (m != 0) {
            temp = n;
            n ^= m;
            m = (temp & m) << 1 ;
        }
        return n;
    }


    /**
     * 减法运算
     * @return 减法 = 加一个负数，负数 = 正数取反 + 1
     */
    public static int subtract(int a, int b) {
        return add(a, negate(b));
    }

    /**
     * 乘法运算  二进制形式，a * b，当b的n位上为1则将a左移n位再相加
     * 0011 * 0101 = 0011 + 1100 = 1111 = 15
     * O(1)
     * @param a
     * @param b
     * @return
     */
    public static int multiply(int a, int b) {
        int sum = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                sum += a;
            }
            a <<=1;
            b >>>=1;
        }
        return sum;
    }

    /**
     * 除法运算 a/b
     * 运用a右移 再减去 b，当能减的地方，则为 1
     * 需要考虑正负
     * 负数需要考虑最小，因为最小的负数没有对应的正数
     * @param a
     * @param b
     * @return
     */
    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            // 需要做特殊处理，避免除到的数超过2^31 - 1
            if (b == negate(1)) {
                return Integer.MAX_VALUE;
            }
            // 分两步计算，先算(Integer.MIN_VALUE + 1)/b，然后再算补偿
            int t = div(add(a, 1), b);  // 先算出前一部分
            return add(t, div(subtract(a, multiply(t, b)), b));  // 再算出补偿的部分
        } else {
            return div(a, b);
        }
    }

    /**
     * 不考虑 Integer.MIN_VALUE 的除法
     * @param a
     * @param b
     * @return
     */
    public static int div(int a, int b) {
        // 转换为绝对值，先忽略符号
        int x = isNegate(a)? negate(a) : a;
        int y = isNegate(b)? negate(b) : b;

        int result = 0 ;
        for (int i = 30; i >= 0 && x > 0; i-- ) {
            if (x >>> i >= y) {
                result |= 1<<i;
                x -= y<<i;
            }
        }
        // 符号处理
        return isNegate(a) ^ isNegate(b) ? negate(result) : result;
    }

    /**
     * 相反数
     * @param a
     * @return
     */
    public static int negate(int a) {
        return ~a + 1;
    }

    /**
     * 是否是负数
     * @param a
     * @return
     */
    public static boolean isNegate(int a) {
        return a < 0;
    }

    public static void test() {
        int testTime = 1_0000_0000;
        int maxValue = Integer.MAX_VALUE;
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * maxValue);
            int b = (int) (Math.random() * maxValue) + 1;
//            if ((a + b) != add(a, b)) {
//            if ((a - b) != subtract(a, b)) {
            if ((a / b) != divide(a, b)) {
                System.out.println("fail......");
                break;
            }
        }
    }

}
