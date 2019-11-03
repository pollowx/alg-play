package com.hbx.play.lc.hard.linklist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: bingxin
 * @Date: 2019-11-03 13:28
 * @Description:
 */
public class TreeToDoublyList426 {

    /**
     * 利用队列把中粗遍历放进来
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (null == root) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        inOrderQueue(root, queue);

        root = queue.poll();
        Node pre = root; // 类似的指针

        Node temp = null;
        while (!queue.isEmpty()) {
            temp = queue.poll();

            temp.left = pre;

            pre.right = temp;

            pre = temp;
        }
        root.left = pre;
        pre.right = root; // 双向循环链表

        return root;
    }

    public void inOrderQueue(Node node, Queue<Node> queue) {
        if (node == null) {
            return;
        }
        inOrderQueue(node.left, queue);
        queue.offer(node);
        inOrderQueue(node.right, queue);
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

}
