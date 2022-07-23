package com.azhe.coding.systemstudy.code02;

import java.util.*;

/**
 * Description:
 *
 * @author Linzherong
 * @date 2022/7/23 12:08 下午
 */
public class Code02 {

    public static void main(String[] args) {
//        testGetJiShuGe1(100_0000, 100, 12);
//        testGetJiShuGe2(100_0000, 100, 12);
//        testGetKShu(100_000, 500, 20, 20, 20);
        testGetKShuOrNo(100_0000, 500, 20, 20, 20);

    }

    /**
     * 一个数组，有一个数出现奇数个，其他都出现了偶数个，找出这个数
     * @param arr
     * @return
     */
    public static int getJiShuGe1(int[] arr) {
        int result = 0;
        for (int i : arr) {
            result ^= i;
        }
        return result;
    }

    public static int testGetJiShuGe1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i)+1);
            } else {
                map.put(i, 1);
            }
        }
        for (Integer integer : map.keySet()) {
            if ((map.get(integer) & 1) == 1) {
                return integer;
            }
        }
        return -1;
    }

    /**
     * 一个数组，有两个数出现奇数个，其他都出现了偶数个，找出这个数
     * @param arr
     * @return
     */
    public static int[] getJiShuGe2(int[] arr) {
        int temp1 = 0;
        for (int i : arr) {
            // 最后获得两个奇数个数的数字异或
            temp1 ^= i;
        }
        // 获得最后一位1，则两个数的二进制在这个位置一定不同
        int difference = temp1 & (~temp1+1);
        int temp2 = 0;
        for (int i : arr) {
            if ((i & difference) == 0) {
                // 最后获得其中一个数
                temp2 ^= i;
            }
        }
        temp1 = temp1 ^ temp2;
        return new int[]{temp1,temp2};
    }

    public static int[] testGetJiShuGe2(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        int m = 0;
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        for (Integer integer : map.keySet()) {
            if ((map.get(integer) & 1) == 1) {
                if (m == 2) {
                    System.out.println(1);
                }
                result[m++] = integer;
            }
        }
        return result;
    }

    /**
     * 有一个数出现了k数，其他数出现了m次， m>1, k<m，找出出现k次的数
     * 额外空间复杂度O(1)，时间复杂度O(N)
     *
     * 二进制每一位都相加，如果是m的倍数则该数在改为为0，如果不为m的倍数则为1
     *
     * @param arr
     * @return
     */
    public static int getKShu(int[] arr, int k, int m) {
        int[] tempArr = new int[32];
        for (int num : arr) {
            for (int i = 31; i >=0; i--) {
                // 将对应的位相加
                tempArr[31 - i] += ((num >> i) & 1);
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (tempArr[i] % m != 0) {
                // 当前位置为1
                result |= (1 << 31 - i);
            }
        }
        return result;
    }

    public static int testGetKShu(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Integer num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 一个数可能出现k次，其他数出现m次，如果出现k出找出，不是则-1
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int getKShuOrNo(int[] arr, int k, int m) {
        int[] tempArr = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                // 32位数组，每一位都是所有数对应位上相加的结果
                tempArr[i] += ((num >> (31-i)) & 1);
            }
        }
        // 结果
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result |= (tempArr[i] % m == k? 1<<31-i : 0);
        }
        // result = 0 的情况，上边得到 tempArr[i] % m 一直都为0
        if (result == 0) {
            int t = 0;
            for (int num : arr) {
                t += (num == 0 ? 1 : 0);
            }
            return t == k? 0 : -1;
        }
        return result;
    }

    /**
     * 获取一个数最后边的1
     * @param num
     * @return
     */
    public static int getRightOne(int num) {
        return num & (~num+1);
    }

    /**
     * 随机创建一个数组，其中一个数出现奇数次，另外的出现偶数次
     * @param maxValue
     * @param count
     * @return
     */
    public static int[] genArr1(int maxValue, int count) {
        List<Integer> list = new ArrayList<>();
        // 存放已经存在的数
        Set<Integer> tempSet = new HashSet<>();
        // 奇数的值
        int value = (int) (Math.random() * maxValue);
        int valueCount = 0;
        do {
            valueCount = (int) (Math.random() * maxValue) + 1;
        } while ((valueCount & 1) == 0);
        tempSet.add(value);
        value = Math.random() < 0.5? value : -value;
        for (int j = 0; j < valueCount; j++) {
            list.add(value);
        }
        // 还需要的数
        int numCount = (int) (Math.random() * count) + 1;
        for (int j = 1; j < numCount; j++) {
            // 获得当前循环对应的数
            int currentValue = 0;
            do {
                currentValue = (int) (Math.random() * maxValue);
            } while (tempSet.contains(currentValue));
            // 获得当前数的循环数
            int currentCount = 0;
            do {
                currentCount = (int) (Math.random() * count) + 1;
            } while ((currentCount & 1) == 1);
            tempSet.add(currentValue);
            currentValue = Math.random() < 0.5 ? currentCount : -currentValue;
            for (int k = 0; k < currentCount; k++) {
                list.add(currentValue);
            }
        }
        int[] arr = new int[list.size()];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = list.get(j);
        }
        // 打乱顺序
        for (int j = 0; j < arr.length; j++) {
            swap(arr, j, (int) (Math.random() * arr.length));
        }
        return arr;
    }

    /**
     * 两个奇数个，其他偶数个
     * @param maxValue
     * @param count
     * @return
     */
    public static int[] genArr2(int maxValue, int count) {
        List<Integer> list = new ArrayList<>();
        // 存放已经存在的数
        Set<Integer> tempSet = new HashSet<>();
        // 奇数的值
        for (int i = 0; i < 2; i++) {
            int value = 0;
            do {
                value = (int) (Math.random() * maxValue) + 1;
            } while (tempSet.contains(value));
            tempSet.add(value);
            value = Math.random() < 0.5? value : -value;
            int valueCount = 0;
            do {
                valueCount = (int) (Math.random() * maxValue) + 1;
            } while ((valueCount & 1) == 0);
            for (int j = 0; j < valueCount; j++) {
                list.add(value);
            }
        }
        // 还需要的数
        int numCount = (int) (Math.random() * count) + 1;
        for (int j = 2; j < numCount; j++) {
            // 获得当前循环对应的数
            int currentValue = 0;
            do {
                currentValue = (int) (Math.random() * maxValue) + 1;
            } while (tempSet.contains(currentValue));
            // 获得当前数的循环数
            int currentCount = 0;
            do {
                currentCount = (int) (Math.random() * count) + 1;
            } while ((currentCount & 1) == 1);
            tempSet.add(currentValue);
            currentValue = Math.random() < 0.5? currentValue : -currentValue;
            for (int k = 0; k < currentCount; k++) {
                list.add(currentValue);
            }
        }
        int[] arr = new int[list.size()];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = list.get(j);
        }
        // 打乱顺序
        for (int j = 0; j < arr.length; j++) {
            swap(arr, j, (int) (Math.random() * arr.length));
        }
        return arr;
    }

    /**
     * k个数 + m个其他数, k < m
     * @param maxValue
     * @return
     */
    public static int[] genArrKM(int maxValue, int k, int m, int maxCount) {
        List<Integer> list = new ArrayList<>();
        // 存放已经存在的数
        Set<Integer> tempSet = new HashSet<>();
        int value = (int) (Math.random() * maxValue) + 1;
        tempSet.add(value);
        value = Math.random() < 0.5? value : -value;
        // 存放k个数
        for (int i = 0; i < k; i++) {
            list.add(value);
        }
        int count = (int) (Math.random() * maxCount) + 1;
        for (int i = 0; i < count; i++) {
            int other;
            do {
                other = (int) (Math.random() * maxValue) + 1;
            } while (tempSet.contains(other));
            tempSet.add(other);
            other = Math.random() < 0.5? other : -other;
            for (int j = 0; j < m; j++) {
                list.add(other);
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        // 打乱顺序
        for (int j = 0; j < arr.length; j++) {
            swap(arr, j, (int) (Math.random() * arr.length));
        }
        return arr;
    }


    /**
     * 有可能个数 + m个其他数, k < m
     * @param maxValue
     * @return
     */
    public static int[] genArrKMOrNo(int maxValue, int k, int m, int maxCount) {
        List<Integer> list = new ArrayList<>();
        // 存放已经存在的数
        Set<Integer> tempSet = new HashSet<>();
        int value = (int) (Math.random() * maxValue) + 1;
        tempSet.add(value);
        value = Math.random() < 0.5? value : -value;
        k = Math.random() < 0.5? k : k-1;
        // 存放k个数
        for (int i = 0; i < k; i++) {
            list.add(value);
        }
        int count = (int) (Math.random() * maxCount) + 1;
        for (int i = 0; i < count; i++) {
            int other;
            do {
                other = (int) (Math.random() * maxValue) + 1;
            } while (tempSet.contains(other));
            tempSet.add(other);
            other = Math.random() < 0.5? other : -other;
            for (int j = 0; j < m; j++) {
                list.add(other);
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        // 打乱顺序
        for (int j = 0; j < arr.length; j++) {
            swap(arr, j, (int) (Math.random() * arr.length));
        }
        return arr;
    }


    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }




    public static void testGetJiShuGe1(int testTime, int maxValue, int count) {
        for (int i = 0; i < testTime; i++) {
            int[] arr = genArr1(maxValue, count);
            if (getJiShuGe1(arr) != testGetJiShuGe1(arr)) {
                System.out.println("失败了");
                return;
            }
        }
        System.out.println("成功了");
    }

    public static void testGetJiShuGe2(int testTime, int maxValue, int count) {
        for (int i = 0; i < testTime; i++) {
            int[] arr = genArr2(maxValue, count);
            if (!getEqual(getJiShuGe2(arr), testGetJiShuGe2(arr))) {
                System.out.println("失败");
                return;
            }
        }
        System.out.println("成功");
    }

    public static void testGetKShu(int testTime, int maxValue, int maxK, int maxM, int maxCount) {
        for (int i = 0; i < testTime; i++) {
            // 获得k，m
            int k = (int) (Math.random() * maxK) + 1;
            int m = k + 1;
            int[] arr = genArrKM(maxValue, k, m, maxCount);
            if (testGetKShu(arr, k, m) != getKShu(arr, k, m)) {
                System.out.println("失败了");
                return;
            }
            testGetKShu(arr, k, m);
        }
        System.out.println("成功");
    }

    public static void testGetKShuOrNo(int testTime, int maxValue, int maxK, int maxM, int maxCount) {
        for (int i = 0; i < testTime; i++) {
            // 获得k，m
            int k = (int) (Math.random() * maxK) + 1;
            int m = k + 1;
            int[] arr = genArrKMOrNo(maxValue, k, m, maxCount);
            if (testGetKShu(arr, k, m) != getKShuOrNo(arr, k, m)) {
                System.out.println("失败了");
                return;
            }
            testGetKShu(arr, k, m);
        }
        System.out.println("成功");
    }

    /**
     * 比较两个数组
     * @param a1
     * @param a2
     * @return
     */
    private static boolean getEqual(int[] a1, int[] a2) {
        Arrays.sort(a1);
        Arrays.sort(a2);
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

}
