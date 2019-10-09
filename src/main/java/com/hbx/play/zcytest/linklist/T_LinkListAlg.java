package com.hbx.play.zcytest.linklist;

import java.util.Stack;

/**
 * @Auther: bingxin
 * @Date: 2019-09-16 22:37
 * @Description:
 */
public class T_LinkListAlg {

    public static Node reverseKEleGroup(Node head, int k) {
        if (null == head || k <= 1) {
            return head;
        }
        Node newHead = head; // 新的头节点
        Node cur = head; //

        Node pre = null;
        Node next = null;
        Stack<Node> helpStack = new Stack<>();

        while(null != cur) {
            next = cur.next;
            helpStack.push(cur);
            while (helpStack.size() == k) { // 达到条件
                pre = reverseNodeByStack(helpStack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    public static Node reverseNodeByStack(Stack<Node> stack, Node left, Node right) {
        if (stack.isEmpty()) {
            return null;
        }
        Node cur = stack.pop();
        if (null != left) {
            left.next = cur;
        }

        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();

            cur.next = next; // cur的next指向next元素

            cur = next; // 后移一个元素
        }
        cur.next = right;
        return cur;
    }

    public static Node reverseKGroupElement(Node head, int k) {
        if (null == head || k <= 1) {
            return head;
        }
        Node newHead = head;
        Node cur = head;

        Stack<Node> helpStack = new Stack<>();
        Node pre = null;
        Node next = null;

        while (null != cur) {
            next = cur.next;
            helpStack.push(cur);
            if (helpStack.size() == k) {
                pre = reverseByStack(helpStack, pre, next);
                newHead = head == newHead ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    public static Node reverseByStack(Stack<Node> stack, Node left, Node right) {
        if (stack.isEmpty()) {
            return null;
        }
        Node cur = stack.pop(); // 当前节点
        if (null != left) {
            left.next = cur;
        }

        Node next = null;
        while(!stack.isEmpty()) {
            next = stack.pop(); // 节点暂存

            cur.next = next;

            cur = next;
        }

        cur.next = right;

        return cur;
    }

    public static Node reverseKElementGroupByNode(Node head, int k) {
        if (null == head || k <= 1) {
            return head;
        }

        Node pre = null;
        Node start = null;
        Node next = null;
        int count = 1;

        Node cur = head;

        while (null != cur) {
            next = cur.next;
            if (count == k) {
                start = pre == null ? head : pre.next;

                head = pre == null ? cur : head;

                revsrseLinkListByNode(pre, start, cur, next);

                pre = start;

                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    public static void revsrseLinkListByNode(Node left, Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;

        Node next = null;
        while (cur != right) {
            next = cur.next;

            cur.next = pre;

            pre = cur;

            cur = next;
        }

        if (null != left) {
            left.next = end;
        }

        start.next = right;
    }

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

}
