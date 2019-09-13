package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/26 11:18 AM
 */
public class BinaryMirror {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void Mirror(TreeNode root) {
        if (null == root) {
            return;
        }
        // 交互左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        Mirror(root.left);
        Mirror(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);

        TreeNode treeNode6 = new TreeNode(6);

        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.right = treeNode6;

        root.left = treeNode2;
        root.right = treeNode3;

        Mirror(root);
    }

}
