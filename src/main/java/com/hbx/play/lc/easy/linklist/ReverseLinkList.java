package com.hbx.play.lc.easy.linklist;

/**
 * @Auther: bingxin
 * @Date: 2019-10-31 15:06
 * @Description:
 */
public class ReverseLinkList {

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

        reverseList(l1, 2, 4);
    }

    public static ListNode reverseList(ListNode head) {
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

    /**
     * 1 <= m <= n <= length
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseList(ListNode head, int m, int n) {
        if (null == head || n < m || m < 1 || n < 1 || n == m) {
            return head;
        }
        ListNode cur = head;
        ListNode top = null;
        ListNode end = null;

        int len = 0;
        while (null != cur) {
            len++;
            top = (len == (m - 1)) ? cur : top;
            end = (len == (n + 1)) ? cur : end;
            cur = cur.next;
        }
        if (len == 1) {
            return head;
        }
        ListNode nodeH = top != null ? top.next : head, next = null;
        len = n - m;
        while (len >= 0) {
            next = nodeH.next; // 暂存

            nodeH.next = end; // 关键赋值

            end = nodeH; // 反转挂载节点

            nodeH = next;

            len--;
        }
        if (top == null) {
            return end;
        } else {
            top.next = end; // 头节点连接
        }
        return head;
    }

    public static ListNode reverseList2(ListNode head) {
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

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
