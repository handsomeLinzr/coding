package com.azhe.coding.newhand.code04;

import java.util.Arrays;

/**
 * Description:
 * 两个有序链表合并
 *
 * @author Linzherong
 * @date 2022/7/4 3:03 下午
 */
public class MergeTwoSortedList {

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        testMergeTwoSortedList();
    }

    /**
     * 合并有序链表
     * @param node0
     * @param node1
     * @return
     */
    public static Node merge(Node node0, Node node1) {
        if (node0 == null) {
            return node1;
        }
        if (node1 == null) {
            return node0;
        }
        // 指向头结点
        Node head = node0.value <= node1.value ? node0 : node1;
        // 指向当前节点
        Node cur = head;

        // 两个下标
        Node left = head.next;
        Node right = head == node0? node1 : node0;
        while (left != null && right != null) {
            if (left.value <= right.value) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left == null? right : left;
        return head;
    }

    public static void testMergeTwoSortedList() {
        int testTime = 100_0000;
        int maxValue = 500_0000;
        int maxLength = 100;
        boolean flag = true;
        for (int i = 0; i < testTime; i++) {
            Node node1 = generalSortedNode((int) (Math.random() * maxLength), maxValue);
            Node node2 = generalSortedNode((int) (Math.random() * maxLength), maxValue);
            int size1 = getLength(node1) + getLength(node2);
            Node mergeNode = merge(node1, node2);
            if ( size1 != getLength(mergeNode)) {
                System.out.println("fail.....");
                flag = false;
                break;
            }
            if (mergeNode == null || mergeNode.next == null) {
                continue;
            }
            Node cur = mergeNode;
            Node next = mergeNode.next;
            while (next != null) {
                if (cur.value > next.value) {
                    System.out.println("fail............");
                    flag = false;
                    break;
                }
                cur = next;
                next = next.next;
            }
        }
        if (flag) {
            System.out.println("SUCCESS!!!");
        }
    }

    /**
     * 随机有序链表
     * @param length
     * @param maxValue
     * @return
     */
    public static Node generalSortedNode(int length, int maxValue) {
        if (length == 0) {
            return null;
        }
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int)(Math.random() * maxValue);
        }
        Arrays.sort(arr);
        Node head = new Node(arr[0]);
        Node cur = head;
        for (int i = 1; i < length; i++) {
            Node node = new Node(arr[i]);
            cur.next = node;
            cur = node;
        }
        return head;
    }

    public static int getLength(Node node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length ++;
        }
        return length;
    }

}
