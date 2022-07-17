package com.azhe.coding.newhand.code08;

import java.util.Arrays;
import java.util.Stack;

/**
 * Description:
 * 将小于等于某个值的放左边，其他放右边
 * 小于   |   等于    |   大于
 * 快排，递归
 * 快排，非递归  用任务拆解
 *
 * @author Linzherong
 * @date 2022/7/13 10:59 下午
 */
public class PartitionAndQuickSort {

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            selectSort(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3)) {
                System.out.println("Oops!");
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println(succeed ? "Nice!" : "Oops!");
    }

    // 插入排序
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            // i 为需要进行换为位置
            int minValueIndex = i;
            for (int j = i+1; j < arr.length ; j++) {
                // j 为正在比较的位置
                minValueIndex = arr[minValueIndex] > arr[j]? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    /**
     * 小于等于 | 大于
     * @param arr
     */
    public static void partition1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int lessEquip = 0;
        int i = 0;
        int lastIndex = arr.length - 1;
        while (i < lastIndex) {
            // 符合条件
            if (arr[i] <= arr[lastIndex]) {
                // 交换
                swap(arr, i, lessEquip++);
            }
            i++;
        }
        swap(arr, lessEquip, lastIndex);
    }

    /**
     * 小于 | 等于 | 大于
     * @param arr
     */
    public static void partition2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int i = 0;
        int less = 0;
        int more = arr.length - 2;
        int lastIndex = arr.length - 1;
        while (i <= more) {
            if (arr[i] < arr[lastIndex]) {
                swap(arr, i++, less++);
            } else if (arr[i] > arr[lastIndex]) {
                swap(arr, i, more--);
            } else {
                i++;
            }
        }
        swap(arr, ++more, lastIndex);
        System.out.println(less);
        System.out.println(more);
    }

    public static void swap(int[] arr, int p, int q) {
        if (p == q) {
            return;
        }
        arr[p] = arr[p] ^ arr[q];
        arr[q] = arr[p] ^ arr[q];
        arr[p] = arr[p] ^ arr[q];
    }

    /**
     * 快排
     * @param arr
     */
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length-1);

    }

    public static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] equal = partition(arr, left, right);
        if (equal[0] > left) {
            process(arr, left, equal[0]-1);
        }
        if (equal[1] < right) {
            process(arr, equal[1] + 1, right);
        }
    }

    /**
     * 分层
     */
    public static int[] partition(int[] arr, int p, int q) {
        int less = p;
        int more = q-1;
        int i = p;
        // 最后一个值
        int t = arr[q];
        while (i <= more) {
            if (arr[i] < t) {
                swap(arr, i++, less++);
            } else if (arr[i] > t) {
                // 交换后，i不用自增，因为交换后i位置的数值是还没比较过的
                swap(arr, i, more--);
            } else {
                i++;
            }
        }
        swap(arr, ++more, q);
        return new int[]{less, more};
    }

    /**
     * 任务类
     */
    public static class Job {
        int left;
        int right;
        public Job(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 快排，任务方式
     * @param arr
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Stack<Job> stack = new Stack<>();
        // 推任务
        stack.push(new Job(0, arr.length-1));
        while (!stack.isEmpty()) {
            Job job = stack.pop();
            int[] partition = partition(arr, job.left, job.right);
            if (partition[0] > job.left) {
                stack.push(new Job(job.left, partition[0] - 1));
            }
            if (partition[1] < job.right) {
                stack.push(new Job(partition[1] + 1, job.right));
            }
        }

    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }



}
