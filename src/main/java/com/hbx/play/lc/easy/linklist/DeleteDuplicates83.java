package com.hbx.play.lc.easy.linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: bingxin
 * @Date: 2019-11-02 12:34
 * @Description:
 */
public class DeleteDuplicates83 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(2);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        ListNode c = deleteDuplicates2(l1);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        Set<Integer> sets = new HashSet<>();

        ListNode pre = head;
        ListNode cur = head.next;
        sets.add(head.val);

        while (cur != null) {
            if (sets.contains(cur.val)) {
                pre.next = cur.next;
            } else {
                sets.add(cur.val);
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode slow = dummy.next;
        ListNode fast = dummy.next.next;
        while (fast != null) {
            if (slow.val == fast.val)
                fast = fast.next;
            else {
                if (slow.next != fast) {
                    ListNode fastRecord = fast;
                    fast = fast.next;
                    slow = fastRecord;
                    prev.next = slow;
                } else {
                    fast = fast.next;
                    slow = slow.next;
                    prev = prev.next;
                }
            }
        }
        if (slow.next != null) {
            prev.next = null;
        }
        return dummy.next;
    }

    public static ListNode deleteDuplicates2My(ListNode head) {
        if (null == head || head.next != null) {
            return head;
        }
        ListNode zero = new ListNode(-1); // 哑巴节点
        zero.next = head;

        ListNode pre = zero;
        ListNode slow = zero.next;
        ListNode fast = zero.next.next;

        ListNode temp = null;

        while (fast != null) {
            if (slow.val == fast.val) {
                fast = fast.next; // fast指针往后走
            } else {
                if (slow.next != fast) { // 说明fast的指针往后移动了
                    temp = fast; // 这里一定要变量交换，java是引用类型，会导致值的改变

                    fast = fast.next;

                    slow = temp;

                    pre.next = slow;
                } else {
                    pre = pre.next;
                    slow = slow.next;
                    fast = fast.next;
                }
            }
            fast = fast.next;
        }
        if (slow.next != null) {
            pre.next = null;
        }
        return zero.next;
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
