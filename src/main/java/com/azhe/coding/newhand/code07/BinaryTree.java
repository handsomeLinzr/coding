package com.azhe.coding.newhand.code07;

import java.util.*;

/**
 * Description:
 * leetcode 107
 * 平衡二叉树树（左子树是平衡树，又子树是平衡树，左右两边的高度差不超过1）
 * 搜索二叉树 （所有 树，左节点比它小，右节点比它大，中序遍历结果是严格递增的二叉树是搜索二叉树）
 * leetcode 98
 * leetcode 112 路径和
 * leetcode 113 路劲和，输出路径
 *
 * @author Linzherong
 * @date 2022/7/11 11:08 下午
 */
public class BinaryTree {

    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }
        List<List<Integer>> result = new LinkedList<>();
        // 创建一个队列用来循环存放节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            // 记录当前队列的长度
            size = queue.size();
            //存放当前层级的集合
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // queue.size 为当前队列的长度，即当前层的节点数量
                TreeNode node = queue.poll();
                list.add(node.val);
                // 判断不为空再存入, 避免将空存进队列，后续导致空数组
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 放到首位
            result.add(0, list);
        }
        return result;
    }


}
