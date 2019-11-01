package com.hbx.play.lc.easy.linklist;

/**
 * @Auther: bingxin
 * @Date: 2019-11-01 11:32
 * @Description:
 */
public class DeleteNode237 {

    public static void main(String[] args) {

    }

    public static void deleteNode(ListNode node) {
        if (null == node) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
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
