package com.azhe.coding.newhand.code04;

/**
 * Description:
 * 两个链表相加
 *
 * @author Linzherong
 * @date 2022/7/4 3:02 下午
 */
public class AddTowNumber {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        testAddTwoNumber();
    }

    public static Node addTwoNumber(Node left, Node right) {
        if (left == null || right == null) {
            return left == null? right : left;
        }
        // 找到长的链表,作为保存结果
        Node result = getLength(left) >= getLength(right)? left : right;
        Node l = result;
        Node s = l == left? right : left;
        // 记录最后一个节点
        Node last = l;
        int carry = 0;
        int value;
        // 把短链加起来
        while (s != null) {
            value = (l.value + s.value + carry) % 10;
            carry = (l.value + s.value + carry) / 10;
            l.value = value;
            last = l;
            l = l.next;
            s = s.next;
        }
        // 把长链加起来
        while (l != null) {
            value = (l.value + carry) % 10;
            carry = (l.value + carry) / 10;
            l.value = value;
            last = l;
            l = l.next;
        }
        // 判断最后需不需要进位
        if (carry > 0) {
            last.next = new Node(carry);
        }
        return result;
    }

    /**
     * 获得长度
     * @param node
     * @return
     */
    public static int getLength(Node node) {
        int length = 0;
        while (node != null) {
            length ++;
            node = node.next;
        }
        return length;
    }

    public static void testAddTwoNumber() {
        int testTime = 10_0000;
        int maxLength = 7;
        boolean flag = true;
        for (int i = 0; i < testTime; i++) {
            Node node1 = generalNode(maxLength);
            Node node2 = generalNode(maxLength);
            if ((parseNum(node1) + parseNum(node2)) != parseNum(addTwoNumber(node1, node2))) {
                System.out.println("fail....");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("SUCCESS!!!!!");
        }
    }

    public static Node generalNode(int maxLength) {
        int length = (int) (Math.random() * maxLength);
        if (length == 0) {
            return null;
        }
        Node head = new Node((int) (Math.random() * 10));
        Node cur = head;
        length -- ;
        while (length > 0) {
            cur.next = new Node((int) (Math.random() * 10));
            cur = cur.next;
            length --;
        }
        return head;
    }

    public static int parseNum(Node node) {
        int sum = 0;
        int mul = 1;
        while (node != null) {
            sum += node.value * mul;
            mul = mul * 10;
            node = node.next;
        }
        return sum;
    }

}
