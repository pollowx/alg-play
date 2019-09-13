package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/21 9:59 AM
 */
public class FindLinkArrayDaoKNode {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);

        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
//        head.next.next.next.next.next.next = new ListNode(7);
//        head.next.next.next.next.next.next.next = new ListNode(8);

        // size = 8, k = 3 -> element = 6;
        //System.out.println(findKthToTail(head, 3).val);

        System.out.println(reversePreNode(head).val);

    }

    public static ListNode findKthToTail(ListNode head, int k) {
        if (null == head || k <= 0) {
            return null;
        }
        ListNode m, n;
        m = n = head;
        int i = 0;
        for (; null != m; i++) {
            if (i >= k) {
                n = n.next;
            }
            m = m.next;
        }
        return i < k ? null : n;
    }

    public static ListNode reversePreNode(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode pre, next;
        pre = next = null;

        while (null != head) {

            // 1. 先把next指向head的下一个
            next = head.next;

            // 2. head的下一个已经暂存在next中了，现在开始干掉head -> one 中间的指向断开，并且pre开始反转挂在head当前节点之后
            head.next = pre;

            // 3. 把当前节点赋值给pre
            pre = head;

            // 4. head头指针往后挪
            head = next;
        }

        return pre;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

}
