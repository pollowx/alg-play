package com.hbx.play.lc.easy.linklist;

import java.util.Stack;

/**
 * 回文链表
 * @Auther: bingxin
 * @Date: 2019-11-01 16:23
 * @Description:
 */
public class IsPalindrome234 {

    public static void main(String[] args) {

    }

    public boolean isPalindrome(ListNode head) {
        if (null == head) {
            return true;
        }
        Stack<ListNode> helpStack = new Stack<>();

        ListNode cur = head;
        while (cur != null) {
            helpStack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            if (helpStack.pop().val != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

//    public boolean isPalindrome(ListNode head) {
//        if (null == head) {
//            return true;
//        }
//        Stack<ListNode> helpStack = new Stack<>();
//
//        ListNode cur = head;
//        while (cur != null) {
//            helpStack.push(cur);
//            cur = cur.next;
//        }
//
//        cur = head;
//        while (cur != null) {
//            if (helpStack.pop().val != cur.val) {
//                return false;
//            }
//            cur = cur.next;
//        }
//        return true;
//    }

    public boolean isPalindrome2(ListNode head) {
        if (null == head || head.next == null) {
            return true;
        }
        ListNode cur = head;
        ListNode right = head.next;
        while (cur.next != null && cur.next.next != null) {
            cur = cur.next.next;
            right = right.next;
        }
        Stack<ListNode> helpStack = new Stack<>();
        while (right != null) {
            helpStack.push(right);
            right = right.next;
        }

        cur = head;
        while (!helpStack.isEmpty()) {
            if (helpStack.pop().val != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
