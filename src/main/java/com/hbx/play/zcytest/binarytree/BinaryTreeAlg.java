package com.hbx.play.zcytest.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther: bingxin
 * @Date: 2019-09-18 13:20
 * @Description:
 */
public class BinaryTreeAlg {

    public static void main(String[] args) {
        Node root = new Node(1);

        Node Node4 = new Node(4);
        Node Node5 = new Node(5);

        Node Node6 = new Node(6);

        Node Node2 = new Node(2);
        Node Node3 = new Node(3);

        Node2.left = Node4;
        Node2.right = Node5;

        Node3.right = Node6;

        root.left = Node2;
        root.right = Node3;

        inOrderByNormal(root);
    }

    /**
     * 递归前序遍历
     * @param head
     */
    public static void preOrder(Node head) {
        if (null == head) {
            return;
        }
        System.out.println(head.value + "\t");
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * 递归中序遍历
     * @param head
     */
    public static void inOrder(Node head) {
        if (null == head) {
            return;
        }
        preOrder(head.left);
        System.out.println(head.value + "\t");
        preOrder(head.right);
    }

    /**
     * 递归后序遍历
     * @param head
     */
    public static void posOrder(Node head) {
        if (null == head) {
            return;
        }
        preOrder(head.left);
        preOrder(head.right);
        System.out.println(head.value + "\t");
    }

    /**
     * 前序普通方式遍历
     * @param head
     */
    public static void preOrderByNormal(Node head) {
        if (null == head) {
            return;
        }
        Stack<Node> helpStack = new Stack<>();
        helpStack.push(head);

        while (!helpStack.isEmpty()) {
            Node temp = helpStack.pop();
            System.out.println(temp.value);

            if (null != temp.left) {
                helpStack.push(temp.left);
            }
            if (null != temp.right) {
                helpStack.push(temp.right);
            }
        }
    }

    /**
     * 中序遍历二叉树
     * @param head
     */
    public static void inOrderByNormal(Node head){
        if (null == head) {
            return;
        }
        Stack<Node> helpStack = new Stack<>();
        while (!helpStack.isEmpty() || null != head) {
            if (null != head) {
                helpStack.push(head);
                head = head.left;
            } else {
                Node temp = helpStack.pop(); // 第一次是最左子节点
                System.out.print(temp.value + "\t");
                head = temp.right; // 最左的子节点有没有右子树， 没有的话打印上一个根节点
            }
        }
    }

    /**
     * 后续遍历，用两个栈
     * @param head
     */
    public static void posOrderByNormalTwoStack(Node head){
        if (null == head) {
            return;
        }
        Stack<Node> helpStack1 = new Stack<>();
        Stack<Node> helpStack2 = new Stack<>();

        helpStack1.push(head);
        while (!helpStack1.isEmpty()) {
            Node temp = helpStack1.pop();
            helpStack2.push(temp);
            if (null != temp.left) {
                helpStack2.push(temp.left);
            }
            if (null != temp.right) {
                helpStack2.push(temp.right);
            }
        }

        while (!helpStack2.isEmpty()) {
            System.out.print(helpStack2.pop().value + "\t");
        }
    }

    /**
     * BFS遍历二叉树，广度优先遍历
     * @param root
     */
    private void bfsBinaryTree(Node root) {
        if (null == root) {
            return;
        }
        LinkedList<Node> helpList = new LinkedList<Node>();
        helpList.add(root);

        while(!helpList.isEmpty()) {
            Node temp = helpList.poll();
            System.out.print(temp.value + "\t");
            if (null != temp.left) {
                helpList.add(temp.left);
            }

            if (null != temp.right) {
                helpList.add(temp.right);
            }
        }
    }

    /**
     * DFS遍历二叉树，深度优先遍历
     * @param root
     */
    private void dfsBinaryTree(Node root) {
        if (null == root) {
            return;
        }
        Stack<Node> helpStack = new Stack<>();
        helpStack.push(root);

        while (!helpStack.isEmpty()){
            Node temp = helpStack.pop();

            System.out.print(temp.value + "\t");

            if (null != temp.right) {
                helpStack.push(temp.right);
            }

            if (null != temp.left) {
                helpStack.push(temp.left);
            }
        }
    }

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

}
