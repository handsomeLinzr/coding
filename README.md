# coding 01
- 对于一个数N，~N+1 = -N
- 对于一个int数字，负的部分会大于正的部分1个，因为正和0的最左边都是0，而负的最左边是1，负数独享全部空间
- 数据结构大类分两种，数组 和 链表
- 数组：地址连续，线性地址寻址快（当数组特别大时可能物理地址会分多段，不连续）
# coding 02 
- 对数器
- 随机数的产生（由已经的任意随机函数(相等或不等概率)，获得另外的随机函数, ）
# coding 03
- 二分法变种：二分法不一定要有序
- 时间复杂度:
- 二分法是O(logN)
- 冒泡、选择排序 O(N^2) 即等差数量
- arrayList扩容代价是 O(N),均摊下来 0(N)/N = O(1)
- 因为每次扩容都需要复制之前的所有数据 代价是 已存在的数据量，所以到最后的代价总和是等比数列求和（1+2+4+8+...+N）
- 等比数列求和   a1*((1-q^n)/(1-q)), n=logN(因为扩容次数为接近到N的二次幂位置),而且log2(N)和log9(N)可以看成一样
- a1*((1-q^n)/(1-q)) = a1*((1-q^(logN))/(1-q)) = a1*((1-N)/(1-q)) = a1*((1/1-q)-(N/1-q)),
- 时间复杂度是 O(N),均摊到N个数，每个数的代码是O(1)
# coding 04
- 链表相关算法（多coding）
- 大概思路，定义前后指针，定义当前指针，做变种处理
# coding 05
- 位图（bitMap），可以压缩空间，存储数字可以减少空间浪费（相对用map来存）
- 以为map中一个数据用int类型存储，消耗32位，而在bitMap中，可以直接用1位表示一个数
- num % 64 = num & 63,因为 num % 64 即 取到 num 二进制中表示 64 那位的前边所有位，刚好为 num & 表示64那位之前的所有都是1的那个数，即 63
- 加法运算原理 = (a ^ b) + ((a & b)<<1),因为 a^b得到无进位相加，a&b得到进位，左移一位表示到了进位需要加的位置，当 a&b == 0,则为 a^b 
- 减法运算原理 = a + (-b) = a + (~b + 1)
- 乘法运算原理 = a + (a<< n ) + ..., n 为将b的二进制形式，有1的地方，如：1100 * 0101 = 1100 + (1100<<2) = 111100
- 除法运算原理 = 将a右移到n位置将去b，则c的位置为1，以此类推
```java
public class BitComp02 {
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

}
```
