package com.hbx.play.lc.easy;

/**
 * @Auther: bingxin
 * @Date: 2019-10-31 15:06
 * @Description:
 */
public class ReverseLinkList {

    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;

        while (head != null) {
            next = head.next;

            head.next = pre;

            pre = head;

            head = next;
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
