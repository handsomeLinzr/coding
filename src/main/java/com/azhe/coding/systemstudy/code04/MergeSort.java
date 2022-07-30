package com.azhe.coding.systemstudy.code04;

/**
 * Description:
 * 1.递归方法
 * 2.步长方法
 * 3.求得数组中的小和总和，即每个值左边中小于当前数相加后的总和（可以转成 每个数的右边，有多少个数大于当前数，数量乘当前数就为当前数的总和）
 * 4.求得数组中有多少个逆序对，即数组中每个数的右边有多少个数比它小
 * 5.求得数组中每个数的右边有多少个数满足，右边的数乘以2还是比它小，求个数总和
 *
 * @author Linzherong
 * @date 2022/7/27 10:30 下午
 */
public class MergeSort {

    public static void main(String[] args) {
        test(10_0000, 100_0000, 100);
        System.out.println("==============");
        testLeftMinSum(100_0000, 100_0000, 100);
        System.out.println("==============");
        testDescOrderSum(100_0000, 100_0000, 100);
        System.out.println("============");
        testMulti2Sum(100_0000, 100_0000, 100);
    }

    /**
     * 归并排序1
     * @param arr
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length-1);
    }

    public static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + ((right-left) >> 1);
        process(arr, left, middle);
        process(arr, middle+1, right);
        merge(arr, left, middle, right);
    }

    public static void merge(int[] arr, int left, int m, int right) {
        int[] temp = new int[right - left + 1];
        int start = left;
        int i = 0;
        int middle = m + 1;
        while (left <= m && middle <= right) {
            temp[i++] = arr[left] < arr[middle]? arr[left++] : arr[middle++];
        }
        while (left <= m) {
            temp[i++] = arr[left++];
        }
        while (middle <= right) {
            temp[i++] = arr[middle++];
        }
        for (int j = 0; j < i; j++) {
            arr[start + j] = temp[j];
        }
    }

    /**
     * 归并排序2
     */
    private static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int length = arr.length;
        int mergeSize = 1;
        int middle;
        int right;
        while (mergeSize < length) {
            // 每一新轮开始都是从0开始
            int start = 0;
            while (start <= length - 1 - mergeSize) {
                middle = start + mergeSize - 1;
                right = length - mergeSize - 1 < middle? length-1 : middle+mergeSize;
                merge(arr, start, middle, right);
                start = right+1;
            }
            if (mergeSize > (length >> 1)) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    private static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j-1]) {
                swap(arr, j, --j);
            }
        }
    }

    /**
     * 交换
     * @param arr
     * @param p
     * @param q
     */
    private static void swap(int[] arr, int p, int q) {
        arr[p] = arr[p] ^ arr[q];
        arr[q] = arr[p] ^ arr[q];
        arr[p] = arr[p] ^ arr[q];
    }

    /**
     * 获得左边中小于当前数的总和
     * @return
     */
    private static int leftMinSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return processLeftMinSum(arr, 0, arr.length - 1);
    }

    private static int processLeftMinSum(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        // 中间值
        int middle = left + ((right - left) >> 1);
        return processLeftMinSum(arr, left, middle) +
                processLeftMinSum(arr, middle+1, right) +
                mergeLeftMinSum(arr, left, middle, right);
    }

    private static int mergeLeftMinSum(int[] arr, int left, int middle, int right) {
        int sum = 0;
        int m = middle + 1;
        int begin = left;
        int[] temp = new int[right - left + 1];
        int i = 0;
        while (left <= middle && m <= right) {
            if (arr[left] < arr[m]) {
                temp[i++] = arr[left];
                // 左边数移动，需要看右边有多少个比这个数大的
                sum += (arr[left++] * (right - m + 1));
            } else {
                // 右边数移动，则直接移动即可，以为当前组内数据已经算过
                temp[i++] = arr[m++];
            }
        }
        while (left <= middle) {
            temp[i++] = arr[left++];
        }
        while (m <= right) {
            temp[i++] = arr[m++];
        }
        for (int j = 0; j < i; j++) {
            arr[begin+j] = temp[j];
        }
        return sum;
    }

    private static int getLeftMinSumTest(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }

    /**
     * 求得逆序对
     * @param arr
     * @return
     */
    private static int getDescCount(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return processDescCount(arr, 0, arr.length-1);
    }

    private static int processDescCount(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int m = ((right-left) >> 1) + left;
        return processDescCount(arr, left, m)
                + processDescCount(arr, m+1, right)
                + mergeDescCount(arr, left, m, right);
    }

    private static int mergeDescCount(int[] arr, int left, int middle, int right) {
        int count = 0;
        int[] temp = new int[right - left + 1];
        int i = 0;
        int begin = left;
        int m = middle + 1;
        while (left <= middle && m <= right) {
            if (arr[left] > arr[m]) {
                temp[i++] = arr[m++];
                count += (middle - left + 1);
            } else {
                temp[i++] = arr[left++];
            }
        }
        while (left <= middle) {
            temp[i++] = arr[left++];
        }
        while (m <= right) {
            temp[i++] = arr[m++];
        }
        for (int j = 0; j < i; j++) {
            arr[begin + j] = temp[j];
        }
        return count;
    }

    private static int getDescCountTest(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    count ++;
                }
            }
        }
        return count;
    }

    /**
     * 每个数右边多少个数满足，右边的数乘2比它小，求总个数
     * @return
     */
    private static int getMulti2Sum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return processMulti2Sum(arr, 0, arr.length - 1);
    }

    private static int processMulti2Sum(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        return processMulti2Sum(arr, left, middle) +
                processMulti2Sum(arr, middle+1, right) +
                mergeMulti2Sum(arr, left, middle, right);
    }

    private static int mergeMulti2Sum(int[] arr, int left, int middle, int right) {
        int count = 0;
        int[] temp = new int[right - left + 1];
        int i = 0;
        int begin = left;
        int m = middle + 1;
        while (left <= middle && m <= right) {
            if (arr[left] > arr[m]) {
                // 找到最接近的一个符合的值
                int n = left;
                while (n <= middle && arr[n] <= 2 * arr[m]) {
                    n++;
                }
                count += (middle - n + 1);
                temp[i++] = arr[m++];
            } else {
                temp[i++] = arr[left++];
            }
        }
        while (left <= middle) {
            temp[i++] = arr[left++];
        }
        while (m <= right) {
            temp[i++] = arr[m++];
        }
        for (int j = 0; j < i; j++) {
            arr[begin + j] = temp[j];
        }
        return count;
    }

    private static int getMulti2SumTest(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j] * 2) {
                    count ++;
                }
            }
        }
        return count;
    }

    private static void test(int testTime, int maxValue, int maxLength) {
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generalArr(maxValue, maxLength);
            int[] arr2 = copy(arr1);
            int[] arr3 = copy(arr1);
            insertSort(arr1);
            mergeSort1(arr2);
            mergeSort2(arr3);
            if (!equal(arr1, arr2) || !equal(arr1, arr3)) {
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("成功了");
    }

    /**
     * 生成随机数组
     * @param maxValue
     * @param maxLength
     * @return
     */
    private static int[] generalArr(int maxValue, int maxLength) {
        int length = (int) (Math.random() * maxLength) + 1;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    /**
     * 复制数组
     * @param arr
     * @return
     */
    private static int[] copy(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] arrCopy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrCopy[i] = arr[i];
        }
        return arrCopy;
    }

    /**
     * 判断相等
     * @param arr1
     * @param arr2
     * @return
     */
    private static boolean equal(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return arr1 == null && arr2 == null;
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

    private static void testLeftMinSum(int testTime, int maxValue, int maxSize) {
        for (int i = 0; i < testTime; i++) {
            int[] arr = generalArr(maxValue, maxSize);
            if (getLeftMinSumTest(arr) != leftMinSum(arr)) {
                System.out.println("失败");
                return;
            }
        }
        System.out.println("成功");
    }

    private static void testDescOrderSum(int testTime, int maxValue, int maxSize) {
        for (int i = 0; i < testTime; i++) {
            int[] arr = generalArr(maxValue, maxSize);
            if (getDescCountTest(arr) != getDescCount(arr)) {
                System.out.println("失败");
                return;
            }
        }
        System.out.println("成功");
    }

    private static void testMulti2Sum(int testTime, int maxValue, int maxSize) {
        for (int i = 0; i < testTime; i++) {
            int[] arr = generalArr(maxValue, maxSize);
            if (getMulti2SumTest(arr) != getMulti2Sum(arr)) {
                System.out.println("失败");
                return;
            }
        }
        System.out.println("成功");
    }

}
