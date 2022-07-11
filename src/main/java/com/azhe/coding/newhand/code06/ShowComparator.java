package com.azhe.coding.newhand.code06;

import java.util.*;

/**
 * Description:
 * 比较器：Comparator
 * 优先级队列：priorityQueue
 * leetcode 23 ，利用小根堆（优先级队列）
 *
 * @author Linzherong
 * @date 2022/7/10 6:30 下午
 */
public class ShowComparator {

    public static void main(String[] args) {
        testTreeSet();
        System.out.println("===================");
        testArray();
        System.out.println("===================");
        testPriority();
    }

    /**
     * 自定义学生类
     */
    private static class Student {
        int age;
        String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }



        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * treeSet 红黑树 有序
     */
    public static void testTreeSet() {
//        TreeSet<Integer> tree = new TreeSet<>((o1, o2) -> -o1.compareTo(o2));
//        tree.add(1);
//        tree.add(4);
//        tree.add(5);
//        tree.add(2);
//        tree.add(0);
//        print(tree);
        // 自定义比较器,返回负数则o1在前，返回正数则o1在后
        TreeSet<Student> tree = new TreeSet<>(Comparator.comparingInt(s -> s.age));
        tree.add(new Student(18, "azhe"));
        tree.add(new Student(23, "arong"));
        tree.add(new Student(20, "阿哲"));
        tree.add(new Student(25, "阿荣"));
        print(tree);
    }

    private static void print(TreeSet treeSet) {
        while (!treeSet.isEmpty()) {
            System.out.println(treeSet.pollFirst());
        }
    }
    private static void print(List list) {
        for (Object o : list) {
            System.out.print(o + "  ");
        }
        System.out.println();
    }
    private static void print(PriorityQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    private static void testArray() {
        int[] arr = new int[]{1,2,6,7,4,98,45,12};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("==========================");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(34);
        list.add(12);
        list.add(16);
        list.add(23);
        list.add(12);
        print(list);
        list.sort(Comparator.comparingInt(Integer::intValue));
        print(list);
        list.sort(Comparator.reverseOrder());
        print(list);
    }

    private static void testPriority() {
        // 优先队列
        PriorityQueue<Student> queue = new PriorityQueue<>((s1, s2) -> s2.age - s1.age);
        queue.add(new Student(23, "zhangsan"));
        queue.add(new Student(25, "lisi"));
        queue.add(new Student(16, "wangwu"));
        queue.add(new Student(20, "zhaoliu"));
        queue.add(new Student(18, "zhouqi"));
        print(queue);
        System.out.println("============================");
    }

    /**
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 先判断
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 定义优先堆，比较的方法是比较 ListNode 的 val，从小到大
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(l->l.val));
        // 放入优先堆，自动排序
        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        // 首先弹出的的是头结点
        ListNode head = heap.poll();
        if (head.next != null) {
            // 蒋头结点的下一个节点压入堆排序
            heap.add(head.next);
        }
        ListNode curNode = head;
        while (!heap.isEmpty()) {
            curNode.next = heap.poll();
            curNode = curNode.next;
            if (curNode.next != null){
                heap.add(curNode.next);
            }
        }
        return head;
    }


}
