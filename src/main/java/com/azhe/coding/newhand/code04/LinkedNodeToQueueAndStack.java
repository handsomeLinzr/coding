package com.azhe.coding.newhand.code04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Description:
 * 单链表实现队列和栈
 *
 * @author Linzherong
 * @date 2022/7/4 3:00 下午
 */
public class LinkedNodeToQueueAndStack {

    /**
     * 单链表结点
     */
    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
//        testQueue();
        testMyStack();
    }

    /**
     * 队列,先进先出
     */
    public static class MyQueue<V> {

        // 头结点
        private Node<V> head;
        // 尾节点
        private Node<V> tail;
        private int size;

        /**
         * 增加 O(1)
         * @param value
         */
        public void offer(V value) {
            Node<V> node = new Node<>(value);
            if (head == null && tail == null) {
                // 头尾指向新节点
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size ++;
        }

        /**
         * 弹出头结点 O(1)
         * @return
         */
        public V peek() {
            if (head == null) {
                return null;
            }
            return head.value;
        }

        /**
         * 拿到头结点 0(1)
         * @return
         */
        public V poll() {
            if (head == null) {
                return null;
            }
            Node<V> value = head;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size --;
            return value.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

    }

    /**
     * 测试队列
     */
    public static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> queue = new LinkedList<>();
        int maxValue = 50000_0000;
        int testTime = 10000_0000;
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != queue.isEmpty()) {
                System.out.println("fail...");
                return;
            }
            if (myQueue.size != queue.size()) {
                System.out.println("fail...");
                return;
            }
            double random = Math.random();
            if (random < 0.33) {
                int value = (int) (Math.random() * maxValue);
                myQueue.offer(value);
                queue.offer(value);
            } else if (random < 0.66 && !myQueue.isEmpty()) {
                int num1 = myQueue.poll();
                int num2 = queue.poll();
                if (num1 != num2) {
                    System.out.println("fail...");
                    return;
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = queue.peek();
                    if (num1 != num2) {
                        System.out.println("fail.....");
                        return;
                    }
                }
            }
        }
        if (myQueue.size != queue.size()) {
            System.out.println("fail...");
            return;
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = queue.poll();
            if (num1 != num2) {
                System.out.println("fail].....");
                return;
            }
        }
        System.out.println("test over!");
    }

    /**
     * 栈，先进后出
     */
    public static class MyStack<V> {

        int size;
        private Node<V> head;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        /**
         * 进栈 O(1)
         * @param value
         */
        public void push(V value) {
            // 新节点
            Node<V> node = new Node<>(value);
            if (head == null) {
                head = node;
            } else {
                node.next = head;
                head = node;
            }
            size ++;
        }

        /**
         * 弹栈 O(1)
         * @return
         */
        public V pop() {
            if (head == null) {
                return null;
            }
            Node<V> pop = head;
            size --;
            head = pop.next;
            return pop.value;
        }

        /**
         * 获得栈顶元素 O(1)
         * @return
         */
        public V peek() {
            return head == null? null : head.value;
        }

    }

    /**
     * 测试栈
     */
    public static void testMyStack() {
        int maxValue = 50000_0000;
        int testTime = 10000_0000;
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != stack.isEmpty()) {
                System.out.println("fail......");
                return;
            }
            if (myStack.size() != stack.size()) {
                System.out.println("fail.....");
                return;
            }
            double random = Math.random();
            if (random < 0.33) {
                int value = (int) (Math.random() * maxValue);
                myStack.push(value);
                stack.push(value);
            } else if (random < 0.66 && !myStack.isEmpty()) {
                int num1 = myStack.pop();
                int num2 = stack.pop();
                if (num1 != num2) {
                    System.out.println("fail.....");
                    return;
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = stack.peek();
                    if (num1 != num2) {
                        System.out.println("fail.....");
                        return;
                    }
                }
            }
        }
        if (myStack.size() != stack.size()) {
            System.out.println("fail.....");
            return;
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = stack.pop();
            if (num1 != num2) {
                System.out.println("fail.....");
                return;
            }
        }
        System.out.println("test over!");
    }

}
