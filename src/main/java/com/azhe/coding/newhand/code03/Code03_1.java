package com.azhe.coding.newhand.code03;

import com.azhe.coding.newhand.Utils;

import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.Random;

/**
 * Description:
 *
 * @author Linzherong
 * @date 2022/6/27 12:59 下午
 */
public class Code03_1 {

    public static void main(String[] args) {
        Code03_1 instance = new Code03_1();
        for (int i = 0; i < 100_0000; i++) {
            int[] arr = Utils.getRandomArraySorted(4, 25);
            int num = new Random().nextInt(arr[arr.length-1] <= 0? 1 : arr[arr.length-1]);
            if (instance.standardSearch(arr, num) != instance.halfSearch(arr, num)) {
                System.out.println("错误");
                return;
            }
        }
    }

    private int standardSearch(int[] arr, int num) {
        if (Objects.isNull(arr) || arr.length < 1) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     */
    private int halfSearch(int[] arr, int num) {
        if (Objects.isNull(arr) || arr.length < 1) {
            return -1;
        }
        int left = 0, right = arr.length-1;
        int middle ;
        while (left < right) {
            middle = (left + right) >> 1;
            if (arr[middle] == num) {
                return middle;
            }
            if (arr[middle] < num) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return arr[left] == num? left:-1;
    }

    /**
     * 局部最小值
     */
    private int lestInRange(int[] arr) {
        return 1;
    }

    /**
     * 字符串自定义替换
     * @param source 字符串源
     * @param charAt 开始替换的位置
     * @param length 替换的长度
     * @param targetSymbol 替换的字符
     * @return
     */
    public static String replaceWords(String source, int charAt, int length, char targetSymbol) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        // 总长度
        int totalLength = source.length();
        if (charAt + length > totalLength) {
            throw new RuntimeException("字符串替换有误，长度不匹配");
        }
        // 头部
        String begin = source.substring(0, charAt);
        // 尾部
        String end = source.substring(charAt + length, totalLength);
        // 中间替换
        StringBuilder middle = new StringBuilder();
        for (int i = 0; i < length; i++) {
            middle.append(targetSymbol);
        }
        return begin + middle + end;
    }

}
