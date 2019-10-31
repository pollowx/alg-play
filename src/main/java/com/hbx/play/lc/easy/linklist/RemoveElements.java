package com.hbx.play.lc.easy.linklist;

/**
 * @Auther: bingxin
 * @Date: 2019-10-31 17:48
 * @Description:
 */
public interface RemoveElements {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(2);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        removeElements(l1, 2);
    }

    public static ListNode removeElements(ListNode head, int val) {
        while (null != head && head.val == val) {
            head = head.next;
        }
        if (null == head) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = head;
        while (null != cur) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
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
