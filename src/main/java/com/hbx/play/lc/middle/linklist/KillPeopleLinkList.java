package com.hbx.play.lc.middle.linklist;

/**
 * @Auther: bingxin
 * @Date: 2019-11-01 15:59
 * @Description:
 */
public class KillPeopleLinkList {

    public static void main(String[] args) {

    }

    public static Node josephusKill(Node head, int m) {
        if (null == head || m < 0) {
            return head;
        }
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }

        int n = 0;
        while (head != last) {
            if (++n == m) {
                last.next = head.next;
                n = 0;
            } else {
                last = last.next;
            }
            head = head.next;
        }
        return head;
    }

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

}
