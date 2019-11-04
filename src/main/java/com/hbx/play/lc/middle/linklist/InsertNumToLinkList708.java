package com.hbx.play.lc.middle.linklist;

/**
 * @Auther: bingxin
 * @Date: 2019-11-03 14:10
 * @Description:
 */
public class InsertNumToLinkList708 {

    public static void main(String[] args) {
        Node l3 = new Node(3);
        Node l5 = new Node(4);
        Node l1 = new Node(1);

        l3.next = l5;
        l5.next = l1;
        l1.next = l3;

        Node c = insert(l3, 2);

        Node ll3 = new Node(3);
        Node ll5 = new Node(4);
        Node ll1 = new Node(1);

        ll3.next = ll5;
        ll5.next = ll1;
        ll1.next = ll3;

        Node c1 = insert2(ll3, 2);
    }

    public static Node insert(Node head, int insertVal) {
        Node insertNode = new Node(insertVal);
        if (null == head) {
            insertNode.next = insertNode;
            return insertNode;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != head) {
            if (cur.val >= insertVal && pre.val <= insertVal) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        insertNode.next = cur;
        pre.next = insertNode;
        return head;
    }

    public static Node insert2(Node head, int insertVal) {
        Node result = new Node(insertVal);
        if (head == null) {
            result.next = result;
            return result;
        }

        Node node = head;
        while (node.next != head) {
            if (node.val <= insertVal && insertVal <= node.next.val) {
                // 插入非最值
                break;
            } else if (node.val <= insertVal && node.next.val < insertVal && node.val > node.next.val) {
                // 插入一个最大值
                break;
            } else if (node.val > insertVal && node.next.val >= insertVal && node.val > node.next.val) {
                // 插入一个最小值
                break;
            } else {
                node = node.next;
            }
        }
        result.next = node.next;
        node.next = result;
        return head;
    }

    public static class Node {
        int val;
        Node next;

        public Node(int value) {
            this.val = value;
        }
    }
}
