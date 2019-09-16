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

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

}
