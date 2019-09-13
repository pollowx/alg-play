package com.hbx.play.zcytest.linklist;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/9/11 6:17 PM
 */
public class LinkListAlg {

    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node7 = new Node(7);
        Node node9 = new Node(9);
        head1.next = node2;
        node2.next = node3;
        node3.next = node7;
        node7.next = node9;


        Node head2 = new Node(3);
        Node node2_6 = new Node(6);
        Node node2_7 = new Node(7);
        head2.next = node2_6;
        node2_6.next = node2_7;

        printTwoLinkListSameEle(head1, head2);
    }

    /**
     * 打印两个链表相同的元素
     */
    public static void printTwoLinkListSameEle(Node head1, Node head2) {
        if (null == head1 || null == head2) {
            return;
        }
        while (null != head1 && null != head2) {
            if (head1.value > head2.value) {
                head2 = head2.next;
            } else if (head1.value == head2.value) {
                System.out.print(head1.value + "\t");
                head1 = head1.next;
                head2 = head2.next;
            } else {
                head1 = head1.next;
            }
        }
    }

    /**
     * 删除单链表中倒数第k个元素
     * @param head
     * @param lastKth
     * @return
     */
    private static Node removeLastKthEleNode(Node head, int lastKth) {
        if (null == head || lastKth < 1) {
            return head;
        }
        Node cur = head;
        // 往后移动，寻找便利完一边K原本的位置
        while (null != cur) {
            lastKth--;
            cur = cur.next;
        }
        // 遍历完之后其实有三种case
        // 1.lastKth > 0, 该元素不存在
        // 2.lastKth == 0, 该元素就是头元素
        // 3.lastKth < 0, 该元素在中间的某个位置, 需要进一步遍历第二轮
        if (lastKth == 0) {
            head = head.next; // 扔掉头元素
        }

        if (lastKth < 0) { // 需要遍历第二轮
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            // 条件结束，lastKth为0，这个时候cur的位置就是k + 1的位置，删掉第K的位置
            cur.next = cur.next.next;
        }
        return head;
    }

    /**
     * 删除双链表中倒数第K个元素
     * @param head
     * @param lastKth
     * @return
     */
    private static DoubleNode removeLastKthEleDoubleNode(DoubleNode head, int lastKth) {
        if (null == head || lastKth < 1) {
            return head;
        }

        DoubleNode cur = head;
        // 现循环一遍
        while (null != cur) {
            lastKth--;
            cur = cur.next;
        }

        // 和单链表一样，这里也会出现3中case
        if (lastKth == 0) { // 头节点
            head = head.next;
            head.last = null;
        }

        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            // lastKth == 0
            DoubleNode kNodeNext = cur.next.next;
            cur.next = kNodeNext;
            if (null != kNodeNext) {
                kNodeNext.last = cur;
            }

            cur.next = cur.next.next;
            cur.next.last = cur;
        }

    }


    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode {
        int value;
        DoubleNode last;
        DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

}
