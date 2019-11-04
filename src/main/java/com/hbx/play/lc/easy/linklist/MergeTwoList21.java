package com.hbx.play.lc.easy.linklist;

/**
 * @Auther: bingxin
 * @Date: 2019-11-01 17:59
 * @Description:
 */
public class MergeTwoList21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        ListNode head = null;
        ListNode cur = null;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (null == head) {
                    head = cur = l1;
                } else {
                    cur.next = l1;
                    cur = cur.next;
                }
                l1 = l1.next;
            } else {
                if (null == head) {
                    head = cur = l2;
                } else {
                    cur.next = l2;
                    cur = cur.next;
                }
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return head;
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
