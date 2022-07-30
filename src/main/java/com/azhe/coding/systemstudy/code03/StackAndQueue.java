package com.azhe.coding.systemstudy.code03;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Description:
 * 1.如何使用栈实现队列
 * 2.如何使用队列实现栈
 *
 * @author Linzherong
 * @date 2022/7/27 8:27 上午
 */
public class StackAndQueue {

    public static void main(String[] args) {
        testStack(100_0000, 10000);
        System.out.println("=====================");
        testQueue(100_00000, 10000);
    }

    /**
     * 队列实现栈
     * @param <V>
     */
    private static class MyStack<V> {
        // push队列
        private Queue<V> pushQueue = new ArrayDeque<>();
        // pop队列
        private Queue<V> popQueue = new ArrayDeque<>();

        public void push(V value) {
            pushQueue.add(value);
        }

        public V pop() {
            while (pushQueue.size() > 1) {
                // 从 pushQueue 往 popQueue 中 转移数据到剩下最后一个
                popQueue.offer(pushQueue.poll());
            }
            // poll出结果
            V value = pushQueue.poll();
            // 交换
            Queue<V> temp = pushQueue;
            pushQueue = popQueue;
            popQueue = temp;
            return value;
        }

        public V peek() {
            while (pushQueue.size() > 1) {
                // 从 pushQueue 往 popQueue 中 转移数据到剩下最后一个
                popQueue.offer(pushQueue.poll());
            }
            // poll出结果
            V value = pushQueue.poll();
            // 交换
            Queue<V> temp = pushQueue;
            pushQueue = popQueue;
            popQueue = temp;
            pushQueue.offer(value);

            return value;
        }

        public int size() {
            return pushQueue.size() + popQueue.size();
        }

        public boolean isEmpty() {
            return size() <= 0;
        }
    }

    /**
     * 栈实现队列
     * @param <V>
     */
    private static class MyQueue<V> {

        // 加 栈
        private Stack<V> offerStack = new Stack<>();
        // 减 栈
        private Stack<V> pollStack = new Stack<>();

        public void offer(V value) {
            offerStack.push(value);
        }

        public V poll() {
            if (!pollStack.isEmpty()) {
                return pollStack.pop();
            }
            while (!offerStack.isEmpty()) {
                // 转移
                pollStack.push(offerStack.pop());
            }
            return pollStack.pop();
        }

        public V peek() {
            if (!pollStack.isEmpty()) {
                return pollStack.peek();
            }
            while (!offerStack.isEmpty()) {
                // 转移
                pollStack.push(offerStack.pop());
            }
            return pollStack.peek();
        }

        public int size() {
            return offerStack.size() + pollStack.size();
        }

        public boolean isEmpty() {
            return size() <= 0;
        }
    }

    private static void testStack(int testTime, int maxValue) {
        Stack<Integer> stack = new Stack<>();
        MyStack<Integer> myStack = new MyStack<>();
        for (int i = 0; i < testTime; i++) {
            double random = Math.random();
            if (random <0.25) {
                if (stack.size() != myStack.size()) {
                    System.out.println("失败1");
                    return;
                }
            } else if (random < 0.5) {
                if (!myStack.isEmpty() && !stack.peek().equals(myStack.peek())) {
                    System.out.println("失败2");
                    return;
                }
            } else if (random < 0.75) {
                int value = (int) (Math.random() * maxValue) + 1;
                stack.push(value);
                myStack.push(value);
            }
            else {
                if (!myStack.isEmpty() && !stack.pop().equals(myStack.pop())) {
                    System.out.println("失败3");
                    return;
                }
            }
        }
        if (stack.isEmpty() != myStack.isEmpty()) {
            System.out.println("失败4");
            return;
        }
        if (!myStack.isEmpty()) {
            if (stack.size() != myStack.size()) {
                System.out.println("失败");
                return;
            }
        }
        while (!myStack.isEmpty()) {
            if (!stack.pop().equals(myStack.pop())) {
                System.out.println("失败");
                return;
            }
        }
        System.out.println("成功");
    }

    private static void testQueue(int testTime, int maxValue) {
        Queue<Integer> queue = new LinkedList<>();
        MyQueue<Integer> myQueue = new MyQueue<>();
        for (int i = 0; i < testTime; i++) {
            double random = Math.random();
            if (random < 0.25) {
                if (queue.isEmpty() != myQueue.isEmpty()) {
                    System.out.println("失败");
                    return;
                }
                if (queue.size() != myQueue.size()) {
                    System.out.println("失败");
                    return;
                }
            } else if (random < 0.5) {
                int value = (int) (Math.random() * maxValue) + 1;
                queue.offer(value);
                myQueue.offer(value);
            } else if (random < 0.75) {
                if (!myQueue.isEmpty() && !myQueue.poll().equals(queue.poll())) {
                    System.out.println("失败");
                    return;
                }
            } else {
                if (!myQueue.isEmpty() && !myQueue.peek().equals(queue.peek())) {
                    System.out.println("失败");
                    return;
                }
            }
        }
        if (myQueue.isEmpty() != queue.isEmpty()) {
            System.out.println("失败");
            return;
        }
        if (!myQueue.isEmpty() && myQueue.size() != queue.size()) {
            System.out.println("失败");
            return;
        }
        while (!myQueue.isEmpty()) {
            if (!myQueue.poll().equals(queue.poll())) {
                System.out.println("失败");
                return;
            }
        }
        System.out.println("成功");
    }

}
