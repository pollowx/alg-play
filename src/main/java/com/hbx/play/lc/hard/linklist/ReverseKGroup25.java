package com.hbx.play.lc.hard.linklist;

import java.util.Stack;

/**
 * @Auther: bingxin
 * @Date: 2019-11-01 23:34
 * @Description:
 */
public class ReverseKGroup25 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        reverseKGroup(l1, 2);
    }

    public ListNode reverseKGroupByStack(ListNode head, int k) {
        if (null == head || k < 2) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;

        ListNode newHead = head;
        ListNode cur = head;

        Stack<ListNode> helpStack = new Stack<>();
        while (cur != null) {
            helpStack.push(cur);
            next = cur.next;

            if (helpStack.size() == k) {
                pre = reverseKGroupByStackC(helpStack, pre, next);

                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    public ListNode reverseKGroupByStackC(Stack<ListNode> stack, ListNode left, ListNode right) {
        if (stack.isEmpty()) {
            return null;
        }
        ListNode cur = stack.pop(); // 头节点
        if (null != left) {
            left.next = cur;
        }
        ListNode next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();

            cur.next = next;

            cur = next;
        }
        cur.next = right;
        return cur;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || k <= 1) {
            return head;
        }
        ListNode pre = null;
        ListNode start = null;
        ListNode next = null;

        int count = 1;
        ListNode cur = head;
        while (cur != null) {
            next = cur.next;
            if (count == k) {
                start = pre == null ? head : pre.next;

                head = pre == null ? cur : head;

                reverseKGroupC(pre, start, cur, next);

                pre = start;

                count = 0;
            }
            cur = next;
            count++;
        }
        return head;
    }

    public static void reverseKGroupC(ListNode left, ListNode start, ListNode end, ListNode right) {
        ListNode pre = start;
        ListNode cur = start.next;

        ListNode next = null;
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

    public static ListNode reverseKGroup3(ListNode head, int k) {
        if (null == head || k <= 1) {
            return head;
        }
        ListNode pre = null;
        ListNode start = null;

        ListNode cur = head;
        ListNode next = null;

        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == k) {

                start = pre == null ? pre : pre.next;

                head = pre == null ? cur : head;

                reverseKGroupD3(pre, start, cur, next);

                count = 0;
            }

            pre = start;

            count++;
            cur = next;
        }
        return head;
    }

    public static void reverseKGroupD3(ListNode left, ListNode start, ListNode end, ListNode right) {
        ListNode pre = start;
        ListNode cur = start.next;

        ListNode next = null;
        while (cur != right) {
            next = cur.next;

            cur.next = pre;

            pre = cur;

            cur = next;
        }

        if (left != null) {
            left.next = end;
        }
        start.next = right;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

}
