package com.azhe.coding.newhand.code04;

/**
 * Description:
 * 单向链表K个节点的组内反转调整  leetcode 25
 *
 * @author Linzherong
 * @date 2022/7/4 3:02 下午
 */
public class SingleNodeGroupReverse {

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node node1 = generateRandomNode(8, 50);
        Node node2 = groupReverse(node1, 3);
        System.out.println(node1);
        System.out.println(node2);
    }

    /**
     * 组内反转
     * @param head
     * @param k
     * @return
     */
    public static Node groupReverse(Node head, int k) {
        Node last = getLastNode(head, k);
        if (last == null) {
            return head;
        }
        // 够一组
        Node nextHead = last.next;
        // 头结点（不变）
        Node result = reverseGroup(head, nextHead);
        // 下一组的最后一个节点
        Node nextLast = getLastNode(nextHead, k);
        // 下下一组的头结点
        Node nextNextHead;
        // 当前头结点
        Node curHead;
        // 上组尾结点
        Node lastEnd = head;

        while (nextLast != null) {
            nextNextHead = nextLast.next;
            // 反转获得当前头结点
            curHead = reverseGroup(nextHead, nextNextHead);
            // 上组尾结点和当前头结点串联
            lastEnd.next = curHead;
            // 上组尾结点指向当前尾结点(反转前头结点)
            lastEnd = nextHead;
            // 下组头结点指向下下组头结点
            nextHead = nextNextHead;
            // 查看下组的最后一个节点
            nextLast = getLastNode(nextHead, k);
        }

        return result;

    }

    /**
     * 获得当前组最后一个
     * @param node
     * @param num
     * @return
     */
    public static Node getLastNode(Node node, int num) {
        while (num > 1 && node != null) {
            node = node.next;
            num --;
        }
        return node;
    }

    /**
     * 反转链表
     * @param node 链表
     * @param nextHead 下一组节点的头结点
     * @return
     */
    public static Node reverseGroup(Node node, Node nextHead) {
        Node cur = node;
        Node next = node.next;
        Node nextNext;
        // 将头结点和下一个头结点串起来
        while (next != nextHead) {
            nextNext = next.next;
            next.next = cur;
            cur = next;
            next = nextNext;
        }
        node.next = nextHead;
        return cur;
    }

    /**
     * 随机链表
     * @param maxLength
     * @param maxValue
     * @return
     */
    public static Node generateRandomNode(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength);
        if (length == 0) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        Node cur = head;
        for (int i = 1; i < maxLength; i++) {
            cur.next = new Node((int) (Math.random() * maxValue));
            cur = cur.next;
        }
        return head;
    }

}
