package com.hbx.play.zcytest.linklist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: bingxin
 * @Date: 2019-09-17 15:24
 * @Description:
 */
public class BinaryTreeToLinkListAlg {

    public static void main(String[] args) {

    }

    /**
     * 二叉搜索树转换为LinkList双向链表
     * @param binaryTree
     * @return
     */
    public static BinaryTree getBinaryTreeConvertToDoubleNode(BinaryTree binaryTree) {
        if (null == binaryTree) {
            return null;
        }
        // 先把搜索二叉树变成中序的队列 queue
        Queue<BinaryTree> queue = new LinkedList<>();

        middleOrder(queue, binaryTree);

        if (queue.isEmpty()) {
            return binaryTree;
        }

        binaryTree = queue.poll();

        BinaryTree pre = binaryTree;
        pre.left = null;

        BinaryTree curTreeNode = null;
        while (!queue.isEmpty()) {
            curTreeNode = queue.poll();

            pre.right = curTreeNode;

            curTreeNode.left = pre;

            pre = curTreeNode;
        }

        pre.right = null;
        return binaryTree;
    }

    /**
     * @param binaryTree
     */
    public static void middleOrder(Queue<BinaryTree> queue, BinaryTree binaryTree) {
        if (null == binaryTree) {
            return;
        }
        middleOrder(queue, binaryTree.left);

        queue.offer(binaryTree);

        middleOrder(queue, binaryTree.right);
    }

    public static class BinaryTree{

        int value;

        BinaryTree left;

        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
