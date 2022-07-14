package com.azhe.coding.newhand.code06;

import java.util.Stack;

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
        System.out.println();
        System.out.println("===================");
        System.out.println(isSameTree2(new TreeNode(0, new TreeNode(-5), null), new TreeNode(0, new TreeNode(-8), null)));
        System.out.println("=====================");
        System.out.println(maxDepth(new TreeNode(0, new TreeNode(-5), null)));
    }

    /**
     * 从先序遍历和中序遍历组装树
     * 思考：
     * 先序遍历: 头——>左——>右   第一个是头结点
     * 中序遍历: 左——>头——>右   都结点左右各自组成树
     * @param preorder 先序遍历
     * @param inorder 中序遍历
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        return makeTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    /**
     * 构建树
     * @return
     */
    public static TreeNode makeTree(int[] p, int l1, int r1, int[] q, int l2, int r2) {
        if (l1 > r1 || l2 > r2) {
            // 已经越过
            return null;
        }
        // 当前节点，先序遍历中，第一个为头结点
        TreeNode node = new TreeNode(p[l1]);
        // 获得头结点在中序遍历中的位置，则其左边的为左节点的数组，右边为右节点数组
        int find = l2;
        while (p[l1] != q[find]) {
            find++;
        }
        // find 是头结点，find-l2 得到左节点的数量，则 l1+find-f2 得到先序遍历中左节点的最后一个数
        node.left = makeTree(p, l1+1, l1+find-l2, q, l2, find-1);
        node.right = makeTree(p, l1+find-l2+1, r1, q, find+1, r2);
        return node;
    }

    /**
     * 最大深度
     * @param root
     * @return
     */
    public static int maxDepth2(TreeNode root) {
      if (root == null) {
          return 0;
      }
      return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }

    /**
     * 最大深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        return getMaxDepth(0, root);
    }

    public static int getMaxDepth(int curDepth, TreeNode node) {
        if (node == null) {
            return curDepth;
        }
        return Math.max(getMaxDepth(curDepth + 1, node.left), getMaxDepth(curDepth + 1, node.right));
    }

    /**
     * 镜面树
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    /**
     * 是否互成镜面
     * @param p
     * @param q
     * @return
     */
    public static boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null) {
            return true;
        }
        return p.val == q.val && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

    /**
     * 相同树
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p== null ^ q== null) {
            return false;
        }
        if ( p== null ) {
            return true;
        }
        return p.val == q.val && isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);
    }

    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p);
        stack.push(q);
        while (!stack.isEmpty()) {
            p = stack.pop();
            q = stack.pop();
            if (p== null ^ q== null) {
                return false;
            }
            if ( p == null ) {
                continue;
            }
            if (p.val != q.val) {
                return false;
            }
            stack.push(p.left);
            stack.push(q.left);
            stack.push(p.right);
            stack.push(q.right);
        }
        return true;
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
