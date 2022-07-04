package com.azhe.coding.newhand.code03;

import java.util.Arrays;
import java.util.Objects;

/**
 * Description:
 *
 * @author Linzherong
 * @date 2022/6/27 12:59 下午
 */
public class Code03 {
    public static void main(String[] args) {
        int times = 1000;
        int maxValue = 100;
        int maxSize = 35;
        boolean result = true;
        for (int i = 0; i < times; i++) {

            int[] array = randomArray(maxSize, maxValue);
            int index = lestInRange(array);
            if (!checkLestRange(array, index)) {
                System.out.println(Arrays.toString(array));
                System.out.println(index);
                System.out.println("出错了");
                return;
            }
//            int[] array = generateRandomArray(maxSize, maxValue);
////            int num = array[(int) (Math.random() * array.length)];
//            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
//            Arrays.sort(array);
//            if (rightestTest(array, value) != rightestNotMoreNumIndex(array, value)) {
//                System.out.println(Arrays.toString(array));
//                System.out.println(value);
//                result = false;
//                break;
//            }

//            if (leftestTest(array, value) != leftestNotLessNumIndex(array, value)) {
//                System.out.println(Arrays.toString(array));
//                System.out.println(value);
//                result = false;
//                break;
//            }

//            int r1 = find(array, num);
//            int r2 = test(array, num);
//            if (r1 != r2 && array[r1] != array[r2]) {
//                System.out.println("出错了！");
//                System.out.println(num);
//                System.out.println(Arrays.toString(array));
//                System.out.println(r1);
//                System.out.println(r2);
//                result = false;
//                break;
//            }
        }
        System.out.println("成功");
    }

    /**
     * 二分法
     * @param arr
     * @param value
     * @return
     */
    public static int find(int[] arr, int value) {
        // 校验数组
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0, right = arr.length - 1;
        int middle = -1;
        while (left <= right) {
            // 中间值
            middle = (left + right) >> 1;
            if (arr[middle] == value) {
                return middle;
            } else if (arr[middle] > value) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return arr[middle] == value? middle:-1;
    }

    /**
     * 用来比较的
     * @param sortedArr
     * @param num
     * @return
     */
    public static int test(int[] sortedArr, int num) {
        if (Objects.isNull(sortedArr) || sortedArr.length == 0) {
            return -1;
        }
        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] == num) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取不小于num的最左边的数
     * @param arr
     * @param num
     * @return
     */
    public static int leftestNotLessNumIndex(int[] arr, int num) {
        // 判断数组
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int index = -1;
        int left = 0, right = arr.length - 1;
        int middle = -1;
        while (left <= right) {
            middle = (left + right) >> 1;
            if (arr[middle] >= num) {
                index = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return index;
    }

    public static int leftestTest(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取小于等于num的最右边的数
     * @param arr
     * @param num
     * @return
     */
    public static int rightestNotMoreNumIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int index = -1;
        int left = 0, right = arr.length - 1;
        int middle = -1;
        while (left <= right) {
            middle = (left + right) >> 1;
            if (arr[middle] <= num) {
                index = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return index;
    }

    public static int rightestTest(int[] arr, int value) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 相邻不相等数组局部最小值
     * @param arr
     * @return
     */
    public static int lestInRange(int[] arr) {
        // 边界判断
        if (Objects.isNull(arr) || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length-1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        // 二分
        int left = 0, right = arr.length - 1;
        int middle ;
        while (left < right - 1) {
            middle = (left + right) >> 1;
            if (arr[middle] < arr[middle - 1] && arr[middle] < arr[middle + 1]) {
                return middle;
            }
            if (arr[middle] < arr[middle - 1]) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return arr[left] < arr[right]? left : right;
    }

    /**
     * 随机数组
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // 长度
        int length;
        do {
            length = (int) (Math.random() * (maxSize + 1));
        } while (length == 0);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) ((Math.random() * (maxValue + 1)) - (Math.random() * maxValue));
        }
        return arr;
    }

    /**
     * 随机相邻不相等数组
     * @param maxLength
     * @param maxValue
     * @return
     */
    public static int[] randomArray(int maxLength, int maxValue) {
        // 长度
        int length = (int)(Math.random() * maxLength);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            do {
                arr[i] = (int)(Math.random() * maxValue);
            } while (i > 0 && arr[i] == arr[i - 1]);
        }
        return arr;
    }

    /**
     * 检验局部最小
     * @param array
     * @param index
     * @return
     */
    public static boolean checkLestRange(int[] array, int index) {
        if (array.length == 0) {
            return index == -1;
        }
        boolean leftBigger = index == 0 || array[index] < array[index - 1];
        boolean rightBigger = index == array.length - 1 || array[index] < array[index + 1];
        return leftBigger && rightBigger;
    }

}
