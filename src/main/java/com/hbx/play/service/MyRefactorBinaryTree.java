package com.hbx.play.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/15 11:51 AM
 */
public class MyRefactorBinaryTree {

    private static Map<Integer, Integer> inIndexMap = new HashMap<>();

    public static void main(String[] args) {

        int[] pre = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] middle = new int[]{4, 7, 2, 1, 5, 3, 8, 6};

        BinaryTreeNode treeNode = rebuildBinaryTree(pre, middle);
    }

    private static BinaryTreeNode rebuildBinaryTree(int[] pre, int[] in) {

        for (int i = 0; i < in.length; i++) {
            inIndexMap.put(in[i], i); // 保存中序遍历的位置
        }

        return doRebuild(pre, 0, pre.length - 1,
                         in, 0, in.length - 1);
    }

    private static BinaryTreeNode doRebuild(int[] pre, int preLeft, int preRight,
                                     int[] in, int inLeft, int inRight) {
        if (preLeft == preRight) {
            return new BinaryTreeNode(pre[preLeft]);
        }
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        BinaryTreeNode rootNode = new BinaryTreeNode(pre[preLeft]);
        int rootIndex = inIndexMap.get(rootNode.value);
        int leftSize = rootIndex - preLeft;

        rootNode.left = doRebuild(pre, preLeft + 1, preLeft + leftSize,
                                  in, inLeft, rootIndex - 1);

        rootNode.right = doRebuild(pre, preLeft + 1 + leftSize, preRight,
                in, rootIndex + 1, inRight);

        return rootNode;
    }

    public static class BinaryTreeNode {

        protected int value;

        protected BinaryTreeNode left;

        protected BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }
    }

}
