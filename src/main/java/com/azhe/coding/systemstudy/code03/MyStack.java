package com.azhe.coding.systemstudy.code03;

import java.util.Stack;

/**
 * Description:
 * 实现一个栈，除了基本功能，增加一个返回栈中最小值的功能
 * pop、push、getMin 时间复杂度都是 O(1)
 * 可以使用现成的栈
 *
 * @author Linzherong
 * @date 2022/7/27 12:06 上午
 */
public class MyStack {

    public static void main(String[] args) {
        MyStackWitMin stack = new MyStackWitMin();
        stack.push(6);
        stack.push(5);
        stack.push(6);
        stack.push(3);
        stack.push(2);
        stack.push(7);
        while (!stack.isEmpty()) {
            System.out.println(stack.peek());
            System.out.println(stack.getMin());
            System.out.println(stack.pop());
            System.out.println("================");
        }
    }

    private static class MyStackWitMin {

        /**
         * 存放值
         */
        private Stack<Integer> stackValue;
        /**
         * 存放最小值
         */
        private Stack<Integer> minStack;

        private int size;

        public MyStackWitMin() {
            this.stackValue = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(Integer value) {
            // 存放值
            stackValue.push(value);
            // 当前的最小值
            // 最小值栈中存放当前位置得到最小值
            minStack.push( minStack.isEmpty() || minStack.peek() > value? value : minStack.peek());
            size++;
        }

        public Integer pop() {
            minStack.pop();
            size--;
            return stackValue.pop();
        }

        public Integer peek() {
            return stackValue.peek();
        }

        public Integer getMin() {
            return minStack.peek();
        }

        public boolean isEmpty() {
            return size <= 0;
        }
    }

}
