package com.azhe.coding.systemstudy.code06;

import java.util.Arrays;

/**
 * Description:
 * 大根堆   小根堆
 * heapInsert  上移
 * heapIfy  下沉
 * heapSort
 * 1.先让整个数组成大根堆（两种方法，1 上移   2 下沉）
 * 2.交换头尾，size--
 * 3.头下层，循环交换头尾
 *
 * @author Linzherong
 * @date 2022/8/2 1:05 下午
 */
public class Heap {
    public static void main(String[] args) {
        MyMaxHeap heap = new MyMaxHeap(12, false);
        heap.push(3);
        heap.push(5);
        heap.push(3);
        heap.push(7);
        heap.push(6);
        while (!heap.isEmpty()) {
            System.out.println(heap.pop());
        }
        System.out.println("=========================");
        int[] arr = new int[]{3,6,2,1,7,9,5,7};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("=========================");
        int[] arr1 = new int[]{3,6,2,1,7,9,5,7};
        heapSortSmallBig(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    /**
     * 自定义堆
     */
    private static class MyMaxHeap {
        boolean isBig;
        int[] heap;
        int limit;
        int heapSize;

        public MyMaxHeap(int limit, boolean isBig) {
            this.isBig = isBig;
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            heap[heapSize] = value;
            heapInsert(heapSize++);
        }

        public int pop() {
            int val = heap[0];
            swap(0, --heapSize);
            heapIfy(0, heapSize);
            return val;
        }

        private void heapInsert(int index) {
            while (index > 0) {
                int parentIdx = (index - 1) >> 1;  // 父节点
                if ((isBig && heap[index] > heap[parentIdx]) || (!isBig && heap[index] < heap[parentIdx])) {
                    swap(index, parentIdx);
                    index = parentIdx;
                } else {
                    break;
                }
            }
        }

        private void heapIfy(int index, int maxIndex) {
            int childLeft = index * 2 + 1;  // 左子节点
            while (childLeft < maxIndex) {
                if (isBig) {  // 大根堆
                    int moreIdx = childLeft + 1 < maxIndex && heap[childLeft + 1] > heap[childLeft]?  childLeft + 1 : childLeft;  // 较小的子节点
                    moreIdx = heap[moreIdx] > heap[index] ? moreIdx : index;
                    if (moreIdx == index) {
                        break;
                    }
                    swap(index, moreIdx); // 交换
                    index = moreIdx;
                } else {  // 小根堆
                    int lestIdx = childLeft + 1 < maxIndex && heap[childLeft + 1] < heap[childLeft]?  childLeft + 1 : childLeft;  // 较小的子节点
                    lestIdx = heap[lestIdx] < heap[index] ? lestIdx : index;
                    if (lestIdx == index) {
                        break;
                    }
                    swap(index, lestIdx);
                    index = lestIdx;
                }
                childLeft = index * 2 + 1;
            }
        }

        private void swap(int p, int q) {
            int temp = heap[p];
            heap[p] = heap[q];
            heap[q] = temp;
        }

    }

    /**
     * 堆排序
     * @param arr
     */
    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int size = arr.length;
        // 上移成堆   O(logN)
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
        // 下移成堆 O(N)
        for (int i = arr.length-1; i >= 0; i--) {
            heapIfy(arr, i, arr.length);
        }
        swap(arr, 0, --size);  // 头结点是最大，和最后一个交换，此时最后一个是最大值了
        // O(N*logN)
        while (size > 0) {
            // 由于上一步是首位值互换，则此时头值是新插入的值，所以只能从上往下遍历
            heapIfy(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    private static void heapInsert(int[] arr, int index) {
        // 当 index == 0时候，(index-1)>>1 还是等于0，则一定不符合 arr[index] > arr[(index - 1) >> 1] 的条件
        // 所以这里已经包含了 index > 0 条件限制
        while (arr[index] > arr[((index - 1)/2)]) {
            swap(arr, index, (index-1)>>1);
            index = (index-1) >> 1;
        }
    }

    private static void heapIfy(int[] arr, int index, int maxSize) {
        int childLeft = (index << 1) + 1;
        while (childLeft < maxSize) {
            int moreIdx = childLeft + 1 < maxSize && arr[childLeft+1] > arr[childLeft]? childLeft+1 : childLeft;
            moreIdx = arr[index] > arr[moreIdx]? index : moreIdx;
            if (moreIdx == index) {
                // 当前值比子节点都大，停止比较移动
                break;
            }
            swap(arr, index, moreIdx);
            index = moreIdx;
            childLeft = (index << 1) + 1;
        }
    }

    private static void swap(int[] heap, int p, int q) {
        int temp = heap[p];
        heap[p] = heap[q];
        heap[q] = temp;
    }

    /**
     * 从大到小
     * @param arr
     */
    private static void heapSortSmallBig(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int size = arr.length;
        for (int i = (size-2) >> 1; i >= 0; i--) {  // 因为叶子节点向下只有单独自己一个节点，必定是堆，可以直接忽略
            // 循环构建小根堆，最后头结点是最小值
            heapIfySmall(arr, i, arr.length);
        }
        swap(arr, 0, --size); // 交换首位节点，此时尾节点是最小值
        while (size > 0) {
            heapIfySmall(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    /**
     * 小根堆
     */
    private static void heapIfySmall(int[] arr, int index, int maxSize) {
        int childLeft = index * 2 + 1;
        while (childLeft < maxSize) {
            // 比较index和子节点，获得最小的节点
            int lessIdx = childLeft + 1 < maxSize && arr[childLeft+1] < arr[childLeft]? childLeft+1:childLeft;
            lessIdx = arr[index] < arr[lessIdx]? index : lessIdx;
            if (lessIdx == index) {  // 当前节点已经是最小了，则停止
                break;
            }
            swap(arr, index, lessIdx);
            index = lessIdx;
            childLeft = index * 2 + 1;
        }
    }

    private static void heapInsertSmall(int[] arr, int index) {
        while (arr[index] < arr[(index-1)>>1]) {
            swap(arr, index, (index-1)>>1);
            index = (index-1)>>1;
        }
    }


}
