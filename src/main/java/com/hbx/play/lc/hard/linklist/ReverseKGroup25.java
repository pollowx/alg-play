package com.hbx.play.lc.hard.linklist;

import java.util.Stack;

/**
 * @Auther: bingxin
 * @Date: 2019-11-01 23:34
 * @Description:
 */
public class ReverseKGroup25 {

    public static void main(String[] args) {

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

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

}
