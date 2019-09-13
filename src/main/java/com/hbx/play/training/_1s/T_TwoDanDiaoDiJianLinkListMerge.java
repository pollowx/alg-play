package com.hbx.play.training._1s;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/29 11:39 AM
 */
public class T_TwoDanDiaoDiJianLinkListMerge {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
//        ListNode head1 = new ListNode(1);
//        head1.next = new ListNode(2);
//        head1.next.next = new ListNode(7);
//        head1.next.next.next = new ListNode(9);
//        head1.next.next.next.next = new ListNode(11);
//
//        ListNode head2 = new ListNode(3);
//        head2.next = new ListNode(8);
//        head2.next.next = new ListNode(10);

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(5);
        head1.next.next = new ListNode(9);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(7);
        head2.next.next = new ListNode(8);

        //System.out.println(mergeDiGui(head1, head2));

        System.out.println(mergeNormal(head1, head2));
    }

    public static ListNode mergeDiGui(ListNode list1, ListNode list2) {
        if (null == list2) {
            return list1;
        }
        if (null == list1) {
            return list2;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeDiGui(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeDiGui(list1, list2.next);
            return list2;
        }
    }

    public static ListNode mergeNormal(ListNode list1, ListNode list2) {
        if (null == list2) {
            return list1;
        }
        if (null == list1) {
            return list2;
        }
        ListNode head = null;
        ListNode current = null;

        while (null != list1 && null != list2) {
            if (list1.val < list2.val) {
                if (null == head) {
                    // head, current, list指向同一份数据
                    head = current = list1;
                } else {
                    current.next = list1; // head接上list1的按顺序的元素
                    current = current.next;  // current后移动，能控制head中的current
                }
                list1 = list1.next; // 往后移动
            } else {
                if (null == head) {
                    head = current = list2;
                } else {
                    current.next = list2; // head接上list2的按顺序的元素
                    current = current.next; // current后移动，能控制head中的current
                }
                list2 = list2.next;
            }
        }

        if (null != list1) {
            current.next = list1;
        }

        if (null != list2) {
            current.next = list2;
        }
        return head;
    }

}
