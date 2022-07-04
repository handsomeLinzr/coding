package com.azhe.coding.newhand.code04;

import java.util.LinkedList;

/**
 * Description:
 * 单链表反转
 * 双链表反转
 * 单链表实现队列和栈
 * 双链表实现双端队列
 * 单向链表K个节点的组内反转调整  leetcode 25
 * 两个链表相加
 * 两个有序链表合并
 *
 * @author Linzherong
 * @date 2022/7/3 3:06 下午
 */
public class ReverseNode {

    public static void main(String[] args) {
        for (int i = 0; i < 1000_000; i++) {
            SingleNode node = generateRandomSingNode();
            LinkedList<Integer> list = changeList(node);
            if (!checkSingleReverse(list, reverseLinkedList(node))) {
                System.out.println("fail...");
                return;
            }
            DoubleNode doubleNode = generateRandomDoubleNode();
            LinkedList<Integer> doubleList = changeList(doubleNode);
            if (!checkDoubleReverse(doubleList, reverseDoubleNode(doubleNode))) {
                System.out.println("fail...");
                return;
            }
        }
        System.out.println("success!");

    }

    /**
     * 单链表反转  O(n)
     * @param node
     * @return
     */
    private static SingleNode reverseLinkedList(SingleNode node) {
        // 需要处理的下一节点
        SingleNode next = null;
        // 前边节点
        SingleNode pre = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * 随机单向链表
     * @return
     */
    private static SingleNode generateRandomSingNode() {
        int length = (int) (Math.random() * 20);
        if (length == 0){
            return null;
        }
        SingleNode head = new SingleNode((int) (Math.random() * 50));
        SingleNode pre = head;
        for (int i = 1; i < length; i++) {
            SingleNode node = new SingleNode((int) (Math.random() * 50));
            pre.next = node;
            pre = node;
        }
        pre.next = null;
        return head;
    }
    private static LinkedList<Integer> changeList(SingleNode node) {
        LinkedList<Integer> list = new LinkedList<>();
        while (node!=null) {
            list.add(node.value);
            node = node.next;
        }
        return list;
    }
    private static boolean checkSingleReverse(LinkedList<Integer> list, SingleNode node) {
        if (list.isEmpty() && node == null) {
            return true;
        }
        for (int i = list.size()-1; i >= 0; i--) {
            if (list.get(i) != node.value) {
                return false;
            }
            node = node.next;
        }
        return node == null;
    }

    /**
     * 双链表反转 O(n)
     * @param node
     * @return
     */
    private static DoubleNode reverseDoubleNode(DoubleNode node) {
        // 下一个结点
        DoubleNode next;
        // 前一个节点
        DoubleNode pre = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            node.last = next;
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * 随机双向链表
     * @return
     */
    private static DoubleNode generateRandomDoubleNode(){
        int length = (int) (Math.random() * 20);
        if (length == 0) {
            return null;
        }
        DoubleNode head = new DoubleNode((int) (Math.random() * 50));
        DoubleNode pre = head;
        length -- ;
        while (length > 0) {
            DoubleNode next = new DoubleNode((int) (Math.random() * 50));
            pre.next = next;
            next.last = pre;
            pre = next;
            length --;
        }
        return head;
    }
    private static boolean checkDoubleReverse(LinkedList<Integer> list, DoubleNode node) {
        if (list.isEmpty() && node == null) {
            return true;
        }
        DoubleNode end = node;
        for (int i = list.size() - 1; i >= 0 ; i--) {
            if (list.get(i) != node.value) {
                return false;
            }
            end = node;
            node = node.next;
        }
        if (node != null) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != end.value) {
                return false;
            }
            end = end.last;
        }
        return end == null;
    }
    private static LinkedList<Integer> changeList(DoubleNode node) {
        LinkedList<Integer> list = new LinkedList<>();
        while (node != null) {
            list.add(node.value);
            node = node.next;
        }
        return list;
    }


    /**
     * 单链表节点
     */
    public static class SingleNode {
        public int value;
        public SingleNode next;

        public SingleNode(int value) {
            this.value = value;
        }
    }

    /**
     * 双向链表节点
     */
    public static class DoubleNode {
        public int value;
        public DoubleNode next;
        public DoubleNode last;

        public DoubleNode(int value) {
            this.value = value;
        }
    }


}
