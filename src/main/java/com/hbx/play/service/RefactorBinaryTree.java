package com.hbx.play.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/15 10:48 AM
 */
public class RefactorBinaryTree {

    public static void main(String[] args) {

        int[] pre = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] middle = new int[]{4, 7, 2, 1, 5, 3, 8, 6};

        TreeNode treeNode = reConstructBinaryTreeBest(pre, middle);
    }

    /**
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree3(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                node.left = reConstructBinaryTree3(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                node.right = reConstructBinaryTree3(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return node;
    }


    public static TreeNode reConstructBinaryTreeBest(int[] pre, int[] in) {

        TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);

        return root;
    }

    private static TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn)
            return null;
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++) {
            if (in[i] != pre[startPre]) { // 找到中序里面根结点的位置 i
                continue;
            }
            root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
            root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
            break;
        }
        return root;
    }


    // other method
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (null == pre || null == in) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i); //key: in的值，value: 值在in中位置
        }

        return getBiTree(pre, 0, pre.length - 1,
                in, 0, in.length - 1, map);
    }

    private static TreeNode getBiTree(int[] pre, int preLeft, int preRight, //前序遍历及当前在前序遍历中的区间
                                      int[] in, int inLeft, int inRight, Map<Integer, Integer> map) { //中序遍历及当前在前序遍历中的区间
        if (preLeft == preRight) { //即根据前序遍历，当前结点无子结点
            return new TreeNode(pre[preLeft]);
        }
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preLeft]);
        int inIndex = map.get(root.val);
        int leftTreeSize = inIndex - inLeft;//该结点左半部分的结点数

        //递归，获取左子树及右子树
        root.left = getBiTree(pre, preLeft + 1, preLeft + leftTreeSize, //左半部分在前序遍历中的区间
                in, inLeft, inIndex - 1, map);//左半部分在中序遍历中的区间
        root.right = getBiTree(pre, preLeft + 1 + leftTreeSize, preRight, //右半部分在前序遍历中的区间
                in, inIndex + 1, inRight, map);//右半部分在中序遍历中的区间

        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
