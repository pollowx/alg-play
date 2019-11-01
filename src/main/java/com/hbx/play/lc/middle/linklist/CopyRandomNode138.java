package com.hbx.play.lc.middle.linklist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: bingxin
 * @Date: 2019-11-01 17:00
 * @Description:
 */
public class CopyRandomNode138 {

    public static void main(String[] args) {

    }

    public Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();

        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }
    }

}
