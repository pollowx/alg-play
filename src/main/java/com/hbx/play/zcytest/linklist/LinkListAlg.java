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
        Node node10 = new Node(10);
        Node node13 = new Node(13);
        Node node14 = new Node(14);
        Node node15 = new Node(15);


        head1.next = node2;
        node2.next = node3;
        node3.next = node7;
        node7.next = node9;
        node9.next = node10;
        node10.next = node13;
        node13.next = node14;
        node14.next = node15;


        Node head2 = new Node(3);
        Node node2_6 = new Node(6);
        Node node2_7 = new Node(7);
        head2.next = node2_6;
        node2_6.next = node2_7;

        //printTwoLinkListSameEle(head1, head2);

        //deleteMiddleNode(head1);

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

            // 下面的逻辑没有问题，只是缺少null的判断
            cur.next = cur.next.next;
            cur.next.last = cur;
        }
        return head;
    }

    /**
     * 删除链表中间节点的函数
     * @param head
     * @return
     */
    private static Node deleteMiddleNode(Node head) {
        // 这个题目的思路是这样，如果要删除中间节点，需要先找下中间节点的规律
        // 链表长度>2后，每增加两个节点，中间节点的位置就加一，所以我们遍历完最后一个节点，
        // 当最后一个节点的next值为null或者next.next为null, pre节点就是middle的上一个index位置
        // 1 -> 0
        //------------
        // 2 -> 1
        //------------
        // 3 -> 2
        // 4 -> 2
        //------------
        // 5 -> 3
        // 6 -> 3
        //------------
        // 7 -> 4
        // 8 -> 4
        //------------
        // 9 -> 5
        if (null == head || null == head.next) {
            return head;
        }

        if (null == head.next.next) {
            return head.next;
        }

        Node pre = head;
        Node cur = head.next.next;
        while (null != cur.next && null != cur.next.next) {
            pre = pre.next; // 每次移动一个节点
            cur = cur.next.next; // 每次移动两个节点
        }
        pre.next = pre.next.next;
        return head;
    }

    /**
     * 删除a/b之间的元素，比如3/5处在整个链表的哪个区间上，就删除那个区间的元素
     * @param head
     * @param a
     * @param b
     * @return
     */
    private static Node deleteA0BNodeEle(Node head, int a, int b) {
        if (null == head || a < 1 || a > b) {
            return head;
        }
        // 现在我们分析下这个题目，和上面的题目做出对比，我们仍然需要找到要删除元素的前一个位置的元素
        // 假设链表的长度为N, 要删除的元素位置为K，那么 K / N ~= a / b
        // 则K = (N * a ) / b
        int n = 0; // 链表长度
        Node cur = head;
        while (null != cur) {
            n++;
            cur = cur.next;
        }

        int k = (int) Math.ceil((double) n * a / (double) b); // 向上取整
        if (k == 1) {
            return head.next;
        }

        if (k > 1) {
            cur = head;
            while (--k != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }

    /**
     * 反转单链表
     * @param head
     * @return
     */
    private static Node reverseSingleLinkList(Node head) {
        if (null == head) {
            return null;
        }
        Node pre = null;
        Node next = null;

        while (null != head) {
            next = head.next; // 暂存head的下一个元素

            head.next = pre; // head的下一个断开，并且，pre如果有值就把当前元素的next指向pre反转后的某段节点了

            pre = head; // 把head 给pre

            head = next;
        }
        return pre;
    }

    /**
     * 反转双链表
     * @param head
     * @return
     */
    private static DoubleNode reverseDoubleNodeLinkList(DoubleNode head) {
        if (null == head) {
            return null;
        }
        DoubleNode pre = null;
        DoubleNode next = null;

        while (null != head) {
            next = head.next;

            head.next = pre;

            head.last = next;

            pre = head;

            head = next;
        }
        return pre;
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
