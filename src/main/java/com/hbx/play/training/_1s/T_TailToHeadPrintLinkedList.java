package com.hbx.play.training._1s;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/29 5:38 PM
 */
public class T_TailToHeadPrintLinkedList {

    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode1 = new ListNode(1);

        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode1;

        for(Integer l : printListFromTailToHead(listNode3)) {
            System.out.println(l);
        }
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (null == listNode) {
            return new ArrayList<>();
        }
        Stack<Integer> helpStack = new Stack<>();
        while (null != listNode) {
            helpStack.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!helpStack.isEmpty()) {
            result.add(helpStack.pop());
        }
        return result;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

}
