package com.azhe.coding.systemstudy.code03;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Description:
 *  1.双链表实现双端队列、栈
 *  2.数组实现队列、栈
 *
 * @author Linzherong
 * @date 2022/7/26 11:57 下午
 */
public class MyQueueStack {

    public static void main(String[] args) {
//        testMyDeque(10_0000, 100_0000, 1000);
//        System.out.println("=======================================");
//        testMyStack(10_0000, 100_0000, 1000);
//        System.out.println("=====================================");
//        testQueueStack(100_0000, 100_0000);
        System.out.println("=====================================");
        testArrayQueue(1000_0000, 10_0000);

    }

    private static class Node<T> {
        private T value;
        private Node pre;
        private Node next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node pre, Node next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

    /**
     * 双端队列
     * @param <T>
     */
    private static class MyDeque<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;

        public void addFirst(T t) {
            Node<T> val = new Node<>(t);
            if (head == null) {
                head = val;
                tail = val;
            } else {
                val.next = head;
                head.pre = val;
                head = val;
            }
            size++;
        }

        public void addLast(T t) {
            Node<T> val = new Node<>(t);
            if (head == null) {
                head = val;
                tail = val;
            } else {
                val.pre = tail;
                tail.next = val;
                tail = val;
            }
            size++;
        }

        public T takeFirst() {
            if (head == null) {
                return null;
            }
            Node<T> value = head;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.pre = null;
            }
            size--;
            return value.value;
        }

        public T takeLast() {
            if (tail == null) {
                return null;
            }
            Node<T> value = tail;
            if (head == tail) {
                head = tail = null;
            } else {
                tail = tail.pre;
                tail.next = null;
            }
            size--;
            return value.value;
        }

        public int size() {
            return size;
        }
    }

    /**
     * 栈
     * @param <T>
     */
    private static class MyStack<T> {
        private T value;
        private Node<T> head;
        private int size;

        public void push(T value) {
            if (head == null) {
                head = new Node<>(value);
            } else {
                Node<T> node = new Node<>(value);
                node.next = head;
                head = node;
            }
            size++;
        }

        public T pop() {
            if (head == null) {
                return null;
            } else {
                Node<T> node = head;
                head = head.next;
                size--;
                return node.value;
            }
        }

        public T peek() {
            return head == null? null : head.value;
        }

        public int size() {
            return size;
        }
    }

    /**
     * 数组实现的队列
     */
    private static class QueueWithArray {
        int[] arr = new int[100];
        int size = 0;
        int putIndex = 0;
        int getIndex = 0;
        public void offer(int value) {
            // 有size记录数量，则不用再考虑是否会覆盖未消费的数据
            if (size >= 100 ) {
                throw new RuntimeException("没有空间了");
            }
            putIndex = putIndex >= 100? 0 : putIndex;
            arr[putIndex++] = value;
            size++;
        }

        public int poll() {
            if (size <= 0) {
                throw new RuntimeException("没有值了");
            }
            getIndex = getIndex >= 100? 0 : getIndex;
            size--;
            return arr[getIndex++];
        }

        public boolean isEmpty() {
            return size <= 0;
        }
    }

    /**
     * 数组实现的栈
     */
    private static class StackWithArray {
        int size = 0;
        int[] arr = new int[100_0000];

        public void push(int value) {
            arr[size++] = value;
        }

        public int pop() {
            int value =  arr[size-1];
            size--;
            return value;
        }

        public int peek() {
            return arr[size-1];
        }

        public boolean isEmpty() {
            return size <= 0;
        }
    }

    public static void testMyDeque(int testTime, int maxValue, int size) {
        for (int i = 0; i < testTime; i++) {
            MyDeque<Integer> deque = new MyDeque<>();
            Deque<Integer> sDeque = new LinkedList<>();
            for (int j = 0; j < size; j++) {
                int value = (int) (Math.random() * maxValue) + 1;
                if (Math.random() > 0.5) {
                    deque.addFirst(value);
                    sDeque.addFirst(value);
                } else {
                    deque.addLast(value);
                    sDeque.addLast(value);
                }
            }
            for (int j = 0; j < size; j++) {
                if (Math.random() > 0.5) {
                    if (!deque.takeFirst().equals(sDeque.pollFirst())) {
                        System.out.println("出错了！");
                        return;
                    }
                } else {
                    if (!deque.takeLast().equals(sDeque.pollLast())) {
                        System.out.println("出错了！");
                        return;
                    }
                }
            }
            if (deque.size() != sDeque.size()) {
                System.out.println("出错了！");
                return;
            }
        }
        System.out.println("成功");
    }

    public static void testMyStack(int testTime, int maxValue, int size) {
        for (int i = 0; i < testTime; i++) {
            MyStack<Integer> stack = new MyStack<>();
            Stack<Integer> sStack = new Stack<>();
            for (int j = 0; j < size; j++) {
                int value = (int) (Math.random() * maxValue) + 1;
                stack.push(value);
                sStack.push(value);
            }
            while (stack.size() > 0 && !sStack.isEmpty()) {
                if (Math.random() > 0.5) {
                    if (!stack.peek().equals(sStack.peek())) {
                        System.out.println("失败了！");
                        return;
                    }
                } else {
                    if (!stack.pop().equals(sStack.pop())) {
                        System.out.println("失败了！");
                        return;
                    }
                }
            }
        }
        System.out.println("成功了");
    }

    public static void testQueueStack(int testTime, int maxValue) {
        StackWithArray arrayStack = new StackWithArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < testTime; i++) {
            double random = Math.random();
            if (random < 0.33) {
                int value = (int) (Math.random() * maxValue) + 1;
                if (arrayStack.size < 100) {
                    arrayStack.push(value);
                    stack.push(value);
                }
            } else if (random < 0.66) {
                if (!stack.isEmpty() && (stack.peek() != arrayStack.peek())) {
                    System.out.println("失败了");
                    return;
                }
            } else {
                if (!arrayStack.isEmpty() && arrayStack.pop() != stack.pop()) {
                    System.out.println("失败了");
                    return;
                }
            }
        }
        if (arrayStack.isEmpty() != stack.isEmpty()) {
            System.out.println("失败了");
            return;
        }
        while (!arrayStack.isEmpty()) {
            if (arrayStack.pop() != stack.pop()) {
                System.out.println("失败了");
                return;
            }
        }
        System.out.println("成功了");
    }

    public static void testArrayQueue(int testTime, int maxValue) {
        Queue<Integer> queue = new LinkedList<>();
        QueueWithArray arrayQueue = new QueueWithArray();
        boolean flag = false;
        for (int i = 0; i < testTime; i++) {
            if ((i & 63) == 0) {
                flag = !flag;
            }
            if (flag) {
                int value = (int) (Math.random() * maxValue) + 1;
                queue.offer(value);
                arrayQueue.offer(value);
            } else {
                if (queue.poll() != arrayQueue.poll()) {
                    System.out.println("失败了");
                    return;
                }
            }
        }
        System.out.println("成功了");
    }

}
