package com.azhe.coding.systemstudy.code05;

import java.util.Arrays;

/**
 * Description:
 * leetcode 327 区间和
 * 1.求得前缀和
 * 2.拿前缀和作为数组遍历，以当前遍历到的数为后节点，当前数表示原始数组从0到当前角标的前缀和
 * 3.如果当前角标为尾节点的区间在要求的区间里，当前的前缀和为m，则在当前角标前的数据，假设前边的l符合规则，
 *   即必有 m-l 需要在 [lower,upper]中（m-l获得l角标到m角标的和），所以以m为右节点，左节点符合要求的条件为
 *   l在[m-upper, m-lower]
 *
 * @author Linzherong
 * @date 2022/8/1 10:56 下午
 */
public class CountOfRangeSum {

    public static void main(String[] args) {
        int[] arr = new int[]{-2,5,-1};
        int i = countRangeSum(arr, -2, 2);
        System.out.println(i);
    }

    /**
     * 区间和
     * 1.前缀和
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 转换为前缀和
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = nums[i] + sums[i-1];
        }
        return process(sums, 0, sums.length-1, lower, upper);
    }

    /**
     * 归并
     */
    public static int process(long[] sums, int left, int right, int lower, int upper) {
        // 相等的情况，则看当前值是否在范围内，代表从0到i位置的和
        if (left == right) {
            return sums[left] >= lower && sums[right] <= upper? 1 : 0;
        }
        int middle = left + ((right-left) >> 1);
        return process(sums, left, middle, lower, upper) +
                process(sums, middle+1, right, lower, upper) +
                merge(sums, left, middle, right,  lower, upper);
    }

    public static int merge(long[] sums, int left, int middle, int right, int lower, int upper) {
        int windowL = left;  // 左角标边界，不回退
        int windowR = left;  // 右角标边界，不回退
        int sum = 0;
        for (int i = middle+1; i <= right; i++) {
            long min = sums[i] - upper;
            long max = sums[i] - lower;
            while (windowR <= middle && sums[windowR] <= max) {
                windowR++;
            }
            while (windowL <= middle && sums[windowL] < min) {
                windowL++;
            }
            sum += (windowR-windowL);
        }
        // 排序
        long[] temp = new long[right-left+1];
        int m = middle + 1;
        int i = 0;
        int start = left;
        while (left <=middle && m <= right) {
            temp[i++] = sums[left] < sums[m]? sums[left++] : sums[m++];
        }
        while (left <= middle) {
            temp[i++] = sums[left++];
        }
        while (m <= right) {
            temp[i++] = sums[m++];
        }
        for (int j = 0; j < i; j++) {
            sums[start + j] = temp[j];
        }
        return sum;
    }

}
