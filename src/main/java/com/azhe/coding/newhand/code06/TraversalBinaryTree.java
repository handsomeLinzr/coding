package com.azhe.coding.newhand.code06;

/**
 * Description:
 * 平衡二叉树
 *  先序：头 > 左 > 右 （每一棵树）
 *  中序：左 > 头 > 右 （每一棵树）
 *  后序：左 > 右 > 头 （每一棵树）
 * 各种顺序打印所有节点
 * 递归序，任何节点都有3次到达的机会（从上级到，从左子到，从右子到），其中第一次就打印是先序，第二次才打印是中序，第三次才打印是后序
 *
 * leetcode 100 相同树
 * leetcode 101 镜面数
 * leetcode 104 最大深度
 * leetcode 105 先+中构建树  0(n) 时间复杂度
 *
 * @author Linzherong
 * @date 2022/7/10 10:00 下午
 */
public class TraversalBinaryTree {



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


    public static void main(String[] args) {
        TreeNode one1 = new TreeNode(1);
        TreeNode one2 = new TreeNode(2);
        TreeNode one3 = new TreeNode(3);
        TreeNode one4 = new TreeNode(4);
        TreeNode two1 = new TreeNode(5, one1, one2);
        TreeNode two2 = new TreeNode(6, one3, one4);
        TreeNode treeNode = new TreeNode(7, two1, two2);
        process1(treeNode);
        System.out.println();
        System.out.println("===================");
        process2(treeNode);
        System.out.println();
        System.out.println("===================");
        process3(treeNode);
    }

    /**
     * 先序遍历
     * @param treeNode
     */
    public static void process1(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.val + "  ");
        process1(treeNode.left);
        process1(treeNode.right);
    }

    /**
     * 中序遍历
     * @param treeNode
     */
    public static void process2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        process2(treeNode.left);
        System.out.print(treeNode.val + "  ");
        process2(treeNode.right);
    }

    /**
     * 后序遍历
     * @param treeNode
     */
    public static void process3(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        process3(treeNode.left);
        process3(treeNode.right);
        System.out.print(treeNode.val + "  ");
    }

    /**
     * 递归序，每个节点都会三次到底，其中三次中打印的不同时间就是不同的序
     * @param treeNode
     */
    private static void process(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        // 这里打印：先序遍历（第一次到达）
        process(treeNode.left);
        // 这里打印，中序遍历（第二次到达）
        process(treeNode.right);
        // 这里打印，后序遍历（第三次到达）
    }

}
