package com.hbx.play.service;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/14 7:28 PM
 */
public class TailToHeadPrintLinkedList {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (null == listNode) {
            return new ArrayList<>();
        }
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

}

