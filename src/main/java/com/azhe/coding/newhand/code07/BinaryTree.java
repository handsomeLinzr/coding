package com.azhe.coding.newhand.code07;

import java.util.*;

/**
 * Description:
 * leetcode 107
 * 平衡二叉树（左子树是平衡树，右子树是平衡树，左右两边的高度差不超过1）
 * 搜索二叉树 （所有 树，左节点比它小，右节点比它大，中序遍历结果是严格递增的二叉树是搜索二叉树）
 * leetcode 98
 * leetcode 110
 * leetcode 112 路径和
 * leetcode 113 路劲和，输出路径
 *
 * @author Linzherong
 * @date 2022/7/11 11:08 下午
 */
public class BinaryTree {

    public static void main(String[] args) {
//        TreeNode a = new TreeNode(15);
//        TreeNode b = new TreeNode(7);
//        TreeNode c = new TreeNode(9);
//        TreeNode d = new TreeNode(20, a, b);
//        TreeNode root = new TreeNode(3, c, d);
//        List<List<Integer>> lists = levelOrderBottom1(root);
//        System.out.println(lists);
//        System.out.println(hasPathSum(new TreeNode(1, new TreeNode(2), new TreeNode(5)), 6));
        System.out.println(pathSum(new TreeNode(1, new TreeNode(2), new TreeNode(5)), 6));
    }

    public static class TreeNode {
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

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        processPath(root, targetSum, list, result);
        return result;
    }

    /**
     * 查询路径循环体
     * @param node
     * @param num
     * @param list
     * @param result
     */
    public static void processPath(TreeNode node, int num, List<Integer> list, List<List<Integer>> result) {
        if (node.left == null && node.right == null) {
            // 叶子节点
            if (node.val == num) {
                // 加上本节点
                list.add(node.val);
                // 加入复制的集合
                result.add(copyList(list));
                // 移除本节点，因为集合还需要被其他分支继续执行
                list.remove(list.size()-1);
                return;
            }
        }
        // 加上当前节点
        list.add(node.val);
        if (node.left != null) {
            processPath(node.left, num - node.val, list, result);
        }
        if (node.right != null) {
            processPath(node.right, num - node.val, list, result);
        }
        // 移除当前节点
        list.remove(list.size()-1);
    }

    public static List<Integer> copyList(List<Integer> list) {
        List<Integer> target = new ArrayList<>(list.size());
        target.addAll(list);
        return target;
    }

    /**
     * 目标和
     * @param root
     * @param targetSum
     * @return
     */

    /**
     * 公共变量
     */
    public static boolean has = false;

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        has = false;
        if (root == null) {
            return false;
        }
        processHas(root, targetSum);
        return has;
    }

    /**
     * 循环执行判断
     * @param node
     * @param num   1，,2，,3  5
     */
    public static void processHas(TreeNode node, int num) {
        if (node.left == null && node.right == null) {
            if (num == node.val) {
                has = true;
            }
            return;
        }
        if (node.left != null) {
            processHas(node.left, num - node.val);
        }
        if (node.right != null) {
            processHas(node.right, num - node.val);
        }
    }



    /**
     * 平衡二叉树
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        return processBalance(root).isBalance;
    }

    /**
     * 平衡二叉树需要的条件
     */
    public static class BalanceInfo {
        int maxDepth;
        boolean isBalance;
        public BalanceInfo(int maxDepth, boolean isBalance) {
            this.maxDepth = maxDepth;
            this.isBalance = isBalance;
        }
    }

    /**
     * 平衡树循环体
     * @param treeNode
     * @return
     */
    public static BalanceInfo processBalance(TreeNode treeNode) {
        if (treeNode == null) {
            return new BalanceInfo(0, true);
        }
        // 左子树平衡信息
        BalanceInfo leftInfo = processBalance(treeNode.left);
        // 右子树平衡信息
        BalanceInfo rightInfo = processBalance(treeNode.right);
        // 坐子树平衡 && 右子树平衡 && 左和右相差不超过1
        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.maxDepth - rightInfo.maxDepth)<=1;
        int maxDepth = Math.max(leftInfo.maxDepth, rightInfo.maxDepth) + 1;
        return new BalanceInfo(maxDepth, isBalance);
    }

    /**
     * 二叉搜索树
     * 左边 < 头 < 右边
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return processSearch(root).isSearch;
    }

    /**
     * 二叉搜索树
     * 是否搜索树
     * 最大值
     * 最小值
     */
    public static class SearchInfo {
        boolean isSearch;
        int max;
        int min;
        public SearchInfo(boolean isSearch, int max, int min) {
            this.isSearch = isSearch;
            this.max = max;
            this.min = min;
        }
    }

    /**
     * 搜索树循环体
     * @param node
     * @return
     */
    public static SearchInfo processSearch(TreeNode node) {
        SearchInfo leftInfo = null;
        SearchInfo rightInfo = null;
        int max = node.val;
        int min = node.val;
        if (node.left != null) {
            leftInfo = processSearch(node.left);
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (node.right != null) {
            rightInfo = processSearch(node.right);
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        boolean moreLeft = leftInfo == null || node.val > leftInfo.max;
        boolean leftBST = leftInfo == null || leftInfo.isSearch;
        boolean lessRight = rightInfo == null || node.val < rightInfo.min;
        boolean rightBST = rightInfo == null || rightInfo.isSearch;
        return new SearchInfo(moreLeft && leftBST && lessRight && rightBST, max, min);
    }

    /**
     * 自底向上遍历
     * @param root
     * @return
     */
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


    /**
     * 每层从右往左遍历的改法
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom1(TreeNode root) {
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
                list.add(0, node.val);  // 每层遍历到的都放首位置
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
