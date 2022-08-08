package com.azhe.coding.systemstudy.code06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Description:
 * 一个不完全无序数组,排成有序,每个数最多移动不超过k,排列有序数组
 *
 * @author Linzherong
 * @date 2022/8/2 11:27 下午
 */
public class HeapWithK {

    public static void main(String[] args) {
        int[] arr = new int[]{2,4,5,1,3,7,6};
        sortLessK(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 排序k
     * @param arr
     * @param k
     */
    private static void sortLessK(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(); // 小根堆
        int idx = 0;
        // 获得头结点可能在的位置，即 0 到 k 的所有位置
        int headMaybe = Math.min(arr.length - 1, k);
        for (int i = 0; i <= headMaybe; i++) {
            heap.add(arr[i]);
        }
        // 弹出来的一定是最小值
        arr[idx++] = heap.poll();
        for (int i = k+1; i < arr.length; i++) {
            heap.add(arr[i]);
            arr[idx++] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[idx++] = heap.poll();
        }
    }

}
