package com.hbx.play.lc.easy.linklist;

/**
 * @Auther: bingxin
 * @Date: 2019-10-31 16:01
 * @Description:
 */
public class RemoveNthFromEnd {

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

        removeNthFromEndBest(l1, 1);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (null == head) {
            return null;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        if (n > length) {
            return head;
        }
        if (n == length) {
            head = head.next;
        } else {
            ListNode needDeleteNodePre = head;
            int curIndex = 0;
            cur = head;
            while (cur != null) {
                cur = cur.next;
                curIndex++;
                if (curIndex == length + 1 - n) {
                    break;
                }
                if (curIndex > n) {
                    needDeleteNodePre = needDeleteNodePre.next;
                }
            }
            needDeleteNodePre.next = needDeleteNodePre.next.next;
        }
        return head;
    }

    public static ListNode removeNthFromEndBest(ListNode head, int n) {
        if (null == head) {
            return null;
        }
        ListNode cur = head;
        while (cur != null) {
            n--;
            cur = cur.next;
        }
        if (n == 0) {
            head = head.next;
        }
        if (n < 0) {
            cur = head;
            while (++n != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
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
