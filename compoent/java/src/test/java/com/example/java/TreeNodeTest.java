package com.example.java;

import org.junit.Test;

public class TreeNodeTest {
    @Test
    public void main() {
        TreeNode e = new TreeNode(1);
        TreeNode g = new TreeNode(2);
        TreeNode h = new TreeNode(3);
        TreeNode i = new TreeNode(4);
        TreeNode d = new TreeNode(5, null, g);
        TreeNode f = new TreeNode(6, h, i);
        TreeNode b = new TreeNode(7, d, e);
        TreeNode c = new TreeNode(8, f, null);
        TreeNode root = new TreeNode(9, b, c);
        printTree(root);

    }

    public static void printTree(TreeNode t) {
        if (t != null) {
            printTree(t.left);
            System.out.print(t.val + " ");
            printTree(t.right);
        }
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            super();
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
