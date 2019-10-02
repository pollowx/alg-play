package com.hbx.play.lc.middle;

import java.util.Stack;

/**
 * @Auther: bingxin
 * @Date: 2019-09-30 18:17
 * @Description:
 */
public class Middle {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(2);
        ListNode head1To4 = new ListNode(4);
        ListNode head1To3 = new ListNode(3);

        head1.next = head1To4;
        head1To4.next = head1To3;


        ListNode head2 = new ListNode(5);
        ListNode head2To6 = new ListNode(6);
        ListNode head2To4 = new ListNode(4);

        head2.next = head2To6;
        head2To6.next = head2To4;

        //addTwoNumbersHavingReverseLinkList(head1, head2);
        //addTwoNumbersHavingReverseLinkList(new ListNode(5), new ListNode(5));


        ListNode head8 = new ListNode(8);
        ListNode head9 = new ListNode(9);
        ListNode head99 = new ListNode(9);
        head8.next = head9;
        head9.next = head99;

        ListNode head1111 = new ListNode(2);
        addTwoNumbersHavingReverseLinkList(head8, head1111);
    }

    /**
     * 两个链表的逆序输出，list1 + list2 -> list3
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbersHavingReverseLinkList(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }

        int add = 0;
        int num = 0;
        Stack<ListNode> helpStack = new Stack<>();

        while (l1 != null || l2 != null) {
            int l1Value = l1 == null ? 0 : l1.val;
            int l2Value = l2 == null ? 0 : l2.val;

            num = l1Value + l2Value + add;
            if (num / 10 > 0) {
                num = num % 10;
                add = 1;
            } else {
                add = 0;
            }
            helpStack.push(new ListNode(num));

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (add > 0) {
            helpStack.push(new ListNode(add));
        }

        ListNode head = helpStack.pop();
        while (!helpStack.isEmpty()) {
            ListNode temp = helpStack.pop();

            temp.next = head;

            head = temp;
        }

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
