package com.azhe.coding.newhand.code04;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description:
 * 双链表实现双端队列
 * @author Linzherong
 * @date 2022/7/4 3:01 下午
 */
public class DoubleNodeToDeque {

    /**
     * 双向链表
     * @param <V>
     */
    public static class Node<V> {
        public V value;
        public Node<V> pre;
        public Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        testMyDeque();
    }

    /**
     * 双端队列
     */
    public static class MyDeque<V> {
        int size;

        private Node<V> head;
        private Node<V> tail;

        public void pushHead(V value) {
            Node<V> node = new Node<>(value);
            if (head == null && tail == null) {
                head = tail = node;
            } else {
                node.next = head;
                head.pre = node;
                head = node;
            }
            size ++;
        }
        public void pushTail(V value) {
            Node<V> node = new Node<>(value);
            if (head == null && tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
            size ++;
        }
        public V popHead() {
            if (head == null) {
                return null;
            }
            V value = head.value;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.pre = null;
            }
            size --;
            return value;
        }
        public V popTail() {
            if (tail == null) {
                return null;
            }
            V value = tail.value;
            if (head == tail) {
                head = tail = null;
            } else {
                tail = tail.pre;
                tail.next = null;
            }
            size --;
            return value;
        }
        public V peekHead() {
            return head == null? null : head.value;
        }
        public V peekTail() {
            return tail == null ? null : tail.value;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }

    public static void testMyDeque() {
        int testTime = 1_0000_0000;
        int maxValue = 5_0000_0000;
        MyDeque<Integer> myDeque = new MyDeque<>();
        Deque<Integer> deque = new LinkedList<>();
        boolean flag = true;
        for (int i = 0; i < testTime; i++) {
            if (myDeque.isEmpty() != deque.isEmpty()) {
                flag = false;
                break;
            }
            if (myDeque.size() != deque.size()) {
                flag = false;
                break;
            }
            double random = Math.random();
            if (random < 0.33) {
                int value = (int) (Math.random() * maxValue);
                if (Math.random() < 0.5) {
                    myDeque.pushHead(value);
                    deque.addFirst(value);
                } else {
                    myDeque.pushTail(value);
                    deque.addLast(value);
                }
            } else if (random < 0.66 && !myDeque.isEmpty()) {
                if (Math.random() < 0.5) {
                    int num1 = myDeque.popHead();
                    int num2 = deque.pollFirst();
                    if (num1 != num2) {
                        flag = false;
                        break;
                    }
                } else {
                    int num1 = myDeque.popTail();
                    int num2 = deque.pollLast();
                    if (num1 != num2) {
                        flag = false;
                        break;
                    }
                }
            } else {
                if (!myDeque.isEmpty()) {
                    if (Math.random() < 0.5) {
                        int num1 = myDeque.peekHead();
                        int num2 = deque.peekFirst();
                        if (num1 != num2) {
                            flag = false;
                            break;
                        }
                    } else {
                        int num1 = myDeque.peekTail();
                        int num2 = deque.peekLast();
                        if (num1 != num2) {
                            flag = false;
                            break;
                        }
                    }
                }
            }
        }
        if (myDeque.size() != deque.size()) {
            flag = false;
        }
        if (myDeque.isEmpty() != deque.isEmpty()) {
            flag = false;
        }
        while (!myDeque.isEmpty()) {
            int num1 = myDeque.popHead();
            int num2 = deque.pollFirst();
            if (num1 != num2) {
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("success!!");
        } else {
            System.out.println("fail.....");
        }
    }

}
