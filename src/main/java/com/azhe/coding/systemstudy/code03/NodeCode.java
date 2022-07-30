package com.azhe.coding.systemstudy.code03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Description: 链表练习，对数器验证
 * 1.单链表反转
 * 2.双链表反转
 * 3.链表中删除所有value=x的节点
 *
 * @author Linzherong
 * @date 2022/7/26 11:33 下午
 */
public class NodeCode {

    public static void main(String[] args) {
        testSingle(100_0000, 100_0000, 100);
        System.out.println("================================");
        testDouble(100_0000,100_0000,100);
        System.out.println("==================================");
        testDeleteValue(100_0000, 50, 500);
    }

    private static class SingleNode<V> {
        private V value;
        private SingleNode<V> next;

        public SingleNode() {
        }

        public SingleNode(V value) {
            this.value = value;
        }

        public SingleNode(V value, SingleNode<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    private static class DoubleNode<V> {
        private V value;
        private DoubleNode<V> next;
        private DoubleNode<V> pre;

        public DoubleNode() {
        }

        public DoubleNode(V value) {
            this.value = value;
        }

        public DoubleNode(V value, DoubleNode<V> pre, DoubleNode<V> next) {
            this.value = value;
            this.next = next;
            this.pre = pre;
        }

        public void next(DoubleNode<V> next) {
            this.next = next;
            if (next != null) {
                next.pre = this;
            }
        }
    }

    /**
     * 翻转单链表
     */
    private static SingleNode<Integer> flipSingleNode(SingleNode<Integer> node) {
        SingleNode<Integer> pre = null;
        SingleNode<Integer> next;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * 翻转双链表
     */
    private static DoubleNode<Integer> flipDoubleNode(DoubleNode<Integer> node) {
        DoubleNode<Integer> pre = null;
        DoubleNode<Integer> next;
        while (node != null) {
            next = node.next;
            node.next = pre;
            node.pre = next;
            pre = node;
            node = next;
        }
        return pre;
    }

    private static void testSingle(int testTime, int maxValue, int maxSize) {
        for (int i = 0; i < testTime; i++) {
            int length = (int) (Math.random() * maxSize) + 1;
            LinkedList<Integer> list = new LinkedList<>();
            list.addFirst(1);
            SingleNode<Integer> head = new SingleNode<>(1);
            SingleNode<Integer> cur = head;
            for (int j = 0; j < length; j++) {
                int value = (int) (Math.random() * maxValue) + 1;
                list.addLast(value);
                cur.next = new SingleNode<>(value);
                cur = cur.next;
            }
            head = flipSingleNode(head);
            while (!list.isEmpty()) {
                if (!list.pollLast().equals(head.value)) {
                    System.out.println("出错了");
                    return;
                }
                head = head.next;
            }
        }
        System.out.println("成功");
    }

    private static void testDouble(int testTime, int maxValue, int maxSize) {
        for (int i = 0; i < testTime; i++) {
            int length = (int) (Math.random() * maxSize) + 1;
            LinkedList<Integer> list = new LinkedList<>();
            list.addLast(1);
            DoubleNode<Integer> head = new DoubleNode<>(1);
            DoubleNode<Integer> current = head;
            for (int j = 0; j < length; j++) {
                int value = (int) (Math.random() * maxValue) + 1;
                list.addLast(value);
                current.next(new DoubleNode<>(value));
                current = current.next;
            }
            head = flipDoubleNode(head);
            DoubleNode<Integer> pre = head;
            LinkedList<Integer> ano = new LinkedList<>(list);
            while (!list.isEmpty()) {
                if (!list.pollLast().equals(head.value)) {
                    System.out.println("失败");
                    return;
                }
                pre = head;
                head = head.next;
            }
            while (!ano.isEmpty()) {
                if (!ano.pollFirst().equals(pre.value)) {
                    System.out.println("失败");
                    return;
                }
                pre = pre.pre;
            }
        }
        System.out.println("成功");
    }

    /**
     * 删除给出的值的所有节点
     * @param value
     * @return
     */
    private static SingleNode<Integer> deleteGivingValue(SingleNode<Integer> node, int value) {
        // 先从头结点找到第一个不是value的节点
        while (node != null && node.value == value) {
            node = node.next;
        }
        SingleNode<Integer> head = node;
        SingleNode<Integer> next;
        while (node != null) {
            next = node.next;
            // 寻找下一个不是value的点
            while (next != null && next.value == value) {
                next = next.next;
            }
            node.next = next;
            node = next;
        }
        return head;
    }

    private static void testDeleteValue(int testTime, int maxValue, int maxSize) {
        for (int i = 0; i < testTime; i++) {
            int length = (int) (Math.random() * maxSize) + 1;
            List<Integer> list = new ArrayList<>(length + 1);
            list.add(37);
            SingleNode<Integer> node = new SingleNode<>(37);
            SingleNode<Integer> cur = node;
            for (int j = 0; j < length; j++) {
                int value = (int) (Math.random() * maxValue) + 1;
                list.add(value);
                cur.next = new SingleNode<>(value);
                cur = cur.next;
            }
            int deleteValue = list.get((int) (Math.random() * length));
            node = deleteGivingValue(node, deleteValue);
            for (Integer value : list) {
                if (value == deleteValue) {
                    continue;
                }
                if (!value.equals(node.value)) {
                    System.out.println("失败了！");
                    return;
                }
                node = node.next;
            }
            if (node != null) {
                System.out.println("失败了！");
                return;
            }
        }
        System.out.println("成功");
    }

}
