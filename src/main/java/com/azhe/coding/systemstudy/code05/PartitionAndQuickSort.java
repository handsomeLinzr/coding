package com.azhe.coding.systemstudy.code05;

import java.util.Arrays;
import java.util.Stack;

/**
 * Description:
 * 小于等于|大于
 * 小于|等于|大于
 * 快排（递归、迭代）（需要每次随机交换的操作）
 *
 * @author Linzherong
 * @date 2022/8/1 11:16 下午
 */
public class PartitionAndQuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,7,9,3,4,0,2,7,5,8,0,2,6,4,6,5,8,5,6,2,6,5};
        quickSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 小于等于|大于
     * @param arr
     */
    public static void partitionLessMore(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int lessIndex = -1;
        int lastIndex = arr.length - 1;
        int last = arr[lastIndex];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= last) {
                swap(arr, i, ++lessIndex);
            }
        }
    }

    /**
     * 小于|等于|大于
     * @param arr
     */
    public static void partitionLessEquipMore(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int lastIndex = arr.length - 1;
        int lessIndex = -1;   // 小于
        int moreIndex = lastIndex;   // 大于
        int last = arr[lastIndex];
        int i = 0;
        while (i < moreIndex) {
            if (arr[i] < last) {
                swap(arr, i++, ++lessIndex);
            } else if (arr[i] > last) {
                swap(arr,i, --moreIndex);
            } else {
                i++;
            }
        }
        swap(arr, moreIndex, lastIndex);
    }

    public static void swap(int[] arr, int p, int q) {
        if (p == q) {
            return;
        }
        int temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
    }

    /**
     * 快排1
     * @param arr
     */
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length <2) {
            return;
        }
        process(arr, 0, arr.length-1);
    }

    public static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        swap(arr, left +  (int) (Math.random()*(right-left+1)), right);  // 随机交换
        int[] partition = partition(arr, left, right);
        if (partition[0] > left+1) {
            process(arr, left, partition[0]-1);
        }
        if (partition[1] < right-1) {
            process(arr, partition[1]+1, right);
        }
    }

    public static int[] partition(int[] arr, int left, int right) {
        int last = arr[right];
        int lessIndex = left;
        int moreIndex = right;
        while (left < moreIndex) {
            if (arr[left] < last) {
                swap(arr, left++, lessIndex++);
            } else if (arr[left] > last) {
                swap(arr, left, --moreIndex);
            } else {
                left++;
            }
        }
        swap(arr, moreIndex, right);
        return new int[]{lessIndex, moreIndex};
    }

    private static class Job {
        int left;
        int right;

        public Job(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 快排2
     * @param arr
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length <2) {
            return;
        }
        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length-1));
        while (!stack.isEmpty()) {
            Job pop = stack.pop();
            swap(arr, pop.left+(int)(Math.random()*(pop.right-pop.left+1)), pop.right);  // 随机交换
            int[] partition = partition(arr, pop.left, pop.right);
            if (partition[0] > pop.left+1) {
                stack.push(new Job(pop.left, partition[0]-1));
            }
            if (partition[1] < pop.right-1) {
                stack.push(new Job(partition[1]+1, pop.right));
            }
        }
    }

}
