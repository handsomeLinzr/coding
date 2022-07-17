package com.azhe.coding.newhand.code08;

/**
 * Description: 归并排序
 * 递归方法
 * 非递归方法
 *
 * @author Linzherong
 * @date 2022/7/13 10:48 下午
 */
public class MergeSort {

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
            mergeSort1(arr1);
            mergeSort2(arr2);
            selectSort(arr3);
            if (!isEqual(arr1, arr3) || !isEqual(arr2, arr3)) {
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

    public static void swap(int[] arr, int p, int q) {
        if (p == q) {
            return;
        }
        arr[p] = arr[p] ^ arr[q];
        arr[q] = arr[p] ^ arr[q];
        arr[p] = arr[p] ^ arr[q];
    }


    /**
     * 归并排序1，递归
     * @param arr
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length-1);
    }

    /**
     * 分别排序，然后合并
     * @param arr
     * @param left
     * @param right
     */
    public static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        // 中间
        int middle = left + ((right - left) >> 1);
        process(arr, left, middle);
        process(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    /**
     * 合并排序
     */
    public static void merge(int[] arr, int p, int m, int q) {
        if (p == q) {
            return;
        }
        int[] temp = new int[q-p+1];
        int p1 = p;
        int p2 = m+1;
        int i = 0;
        // 比较两边
        while (p1 <= m && p2 <= q) {
            temp[i++] = arr[p1] <= arr[p2]? arr[p1++] : arr[p2++];
        }
        // 比较后，将剩余的数按顺序复制下来
        while (p1 <= m) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= q) {
            temp[i++] = arr[p2++];
        }
        // 复制
        for (int j = 0; j < temp.length; j++) {
            arr[p+j] = temp[j];
        }
    }


    /**
     * 归并排序，非递归，步长
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 步长，从1开始
        int mergeSize = 1;
        int length = arr.length;
        while (mergeSize < length) {
            int p = 0;
            // p < length-mergeSize+1 条件限制了 p到m时m已经超过length的情况，这种情况不需要排序，本身就有序，
            // 也避免了越界问题
            while (p < length && p < length - mergeSize ) {
                // 中间位置
                int m = p + mergeSize - 1;
                // 右边位置
                int q = m < length - mergeSize - 1? m + mergeSize : length - 1;
//                int q = m + mergeSize;
                merge(arr, p, m, q);
                // 由于上边处理了，q最大只能到 length-1，所以 q+1 不会越界，不用考虑越界情况
                p = q + 1;
            }
            // 考虑越界，可以判断当mergeSize达到长度的一半，则可以跳出循环
            // 不能等于，因为等于的情况有可能是长度一半后向下取整得到的结果
            if (mergeSize > (length >>1)) {
                break;
            }
            mergeSize <<=1;
        }
    }

}
