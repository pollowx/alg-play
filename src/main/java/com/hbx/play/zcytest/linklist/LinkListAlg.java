package com.hbx.play.zcytest.linklist;

import java.util.HashMap;
import java.util.Stack;

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

        //node15.next = head1;
        //josephusKill(head1, 3);

        //Node node8 = new Node(8);
        //node8.next = head1;
        //judgeSwapNodeEleByGivenIndexValue(node8, 7);

        //reverseKEleGroup(head1, 3);
        //reverseKElementGroup(head1, 3);

//        Node node8 = new Node(8);
//        node8.next = head1;
//        selectSortLinkList(node8);

        Node mergeHead1 = new Node(2);
        Node mergeNode5 = new Node(5);
        Node mergeNode6 = new Node(6);
        mergeHead1.next = mergeNode5;
        mergeNode5.next = mergeNode6;

        Node mergeHead2 = new Node(1);
        Node mergeNode4 = new Node(4);
        Node mergeNode7 = new Node(7);
        mergeHead2.next = mergeNode4;
        mergeNode4.next = mergeNode7;

        mergeTwoLinkList(mergeHead1, mergeHead2);
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

    /**
     * 反转链表的from ～ to之间的内容
     * @param head
     * @param from
     * @param to
     * @return
     */
    private static Node reverseFromToContentElesLinkList(Node head, int from, int to) {
        if (null == head || to <= from) {
            return head; // 不用反转
        }
        // 下面开始check 1 <= from <= to <= 是否成立
        int n = 0;
        Node fPre = null; // from的前一个节点
        Node toNext = null; // to的后一个节点

        Node cur = head;
        while (null != cur) {
            n++; // 计算元素数量
            if (n == from - 1) {
                fPre = cur;
            }
            if (n == to + 1) {
                toNext = cur;
            }
            cur = cur.next;
        }

        if (to > n || from < 1) {
            return head; // 不用反转
        }

        // 下面开始反转from 到 to之间的元素
        Node toHead = fPre == null ? head : fPre.next; // 现在指向to下标的元素

        Node tempNode = toHead.next; // 下一个节点暂存

        toHead.next = toNext; // xiaxia

        Node next = null;
        // 反转链表
        while (tempNode != toNext) {
            next = tempNode.next;

            tempNode.next = toHead;

            toHead = tempNode;

            tempNode = next;
        }

        // 感觉上面写的不对. 应该这样写
//        Node toHead = fPre == null ? head : fPre.next;
//        Node next = null;
//        // 反转链表
//        while (toHead != toNext) {
//            next = toHead.next;
//
//            toHead.next = next;
//
//            next = toHead;
//
//            toHead = next;
//        }
        if (null == fPre) {
            return toHead; // 因为fPre是null，所以反转后的新节点就是头节点
        }

        fPre.next = toHead;
        return head;
    }

    /**
     * 约瑟夫杀人报数问题，m为要杀的报数代号, O(M * N)时间复杂度
     * @param head
     * @param m
     * @return
     */
    public static Node josephusKill(Node head, int m) {
        if (null == head || m < 1 || head == head.next) {
            return head;
        }
        Node last = head;
        while (head != last.next) {
            last = last.next; // 找到最后一个节点
        }

        int count = 0;
        while (head != last) {
            if (++count == m) { // remove该节点
                count = 0;
                last.next = head.next;
            } else {
                last = head.next;
            }
            head = last.next;
        }
        return head;
    }

    /**
     * 判断链表是不是回文结构
     * @param head
     * @return
     */
    public static boolean judgeLinkListMirror(Node head) {
        if (null == head) {
            return false;
        }
        // 1 -> 2 -> 1
        // 1 -> 2 -> 2 -> 1
        Stack<Integer> helpStack = new Stack<>();
        while (null != head) {
            if (!helpStack.empty()) {
                if (helpStack.peek() == head.value) {
                    helpStack.pop(); // 直接出栈，然后下一个
                } else {
                    if (helpStack.size() > 2) {
                        int temp = helpStack.pop();
                        if (helpStack.peek() == head.value) {
                            helpStack.pop(); // 直接出栈，然后下一个
                        } else {
                            return false;
                        }
                    }
                }
            }
            helpStack.push(head.value);
            head = head.next;
        }
        return helpStack.isEmpty();
    }

    /**
     * 判断链表是不是回文结构, 压栈，然后出栈比对
     * @param head
     * @return
     */
    public static boolean judgeLinkListMirror1(Node head) {
        if (null == head) {
            return false;
        }
        Stack<Node> helpStack = new Stack<>();
        // 压栈，然后出栈比对
        Node cur = head;
        while (null != cur) {
            helpStack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (null != cur) {
            if (helpStack.pop().value != cur.value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 判断链表是不是回文结构, 压入链表的右侧入栈，和链表的左侧比对就可以了
     * @param head
     * @return
     */
    public static boolean judgeLinkListMirror2(Node head) {
        if (null == head || null == head.next) {
            return true;
        }
        Node right = head.next;
        Node cur = head;
        while (null != cur.next && null != cur.next.next) {
            right = right.next;
            cur = cur.next.next;
        }

        Stack<Node> helpStack = new Stack<>();
        // right为链表右侧
        while (right != null) {
            helpStack.push(right);
            right = right.next;
        }

        // 和链表左侧比较
        while (!helpStack.isEmpty()) {
            if (helpStack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 把链表的值顺序根据和pivot做比对，小于pivot的放在左边，等于的放在中间，大于地放在右侧
     * @param head
     * @param pivot
     * @return
     */
    public static Node judgeSwapNodeEleByGivenIndexValue(Node head, int pivot) {
        if (null == head) {
            return head;
        }
        Node low = null;
        Node lowNext = null;

        Node equal = null;
        Node equalNext = null;

        Node high = null;
        while (null != head) {
            if (head.value > pivot) {
                if (high == null) {
                    high = head;
                } else {
                    high.next = head;
                }
            } else if (head.value < pivot){
                if (low == null) {
                    low = head;
                } else {
                    low.next = head;
                }
                lowNext = head;
            } else {
                if (equal == null) {
                    equal = head;
                } else {
                    equal.next = head;
                }
                equalNext = head;
            }
            head = head.next;
        }

        if (null == low) {
            if (null == equal) {
                return high;
            } else {
                equalNext.next = high;
                return equal;
            }
        } else {
            if (null == equal) {
                lowNext.next = high;
                return low;
            } else {
                lowNext.next = equal;
                equalNext.next = high;
                return equal;
            }
        }
    }

    /**
     * 复制randomNode链表，最优解，时间复杂度O(N) + 空间复杂度O(1)
     * @param head
     * @return
     */
    public static RandNode copyRandNode(RandNode head) {
        if (null == head) {
            return null;
        }
        // 1. 增加新的链表表头，把原表的next指针指向下一个新增节点，
        // 比如 1 -> 2 -> 3 变成 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        RandNode cur = head;
        while (null != cur) {
            RandNode insertNode = new RandNode(cur.value);
            RandNode nexNode = cur.next;

            insertNode.next = nexNode;

            cur.next = insertNode;

            cur = cur.next;
        }

        // 2. 把原来的random指向也复制到新增的链表上
        cur = head;
        while (null != cur) {
            if (null != cur.rand) {
                RandNode curRandNode = cur.rand;

                cur.next.rand = null == curRandNode ? null : curRandNode.next;
            }
            cur = cur.next.next;
        }

        // 3. 拆开链表
        cur = head;
        RandNode copyHead = head.next;
        while (null != cur) {
            RandNode tempCurrent = cur.next;

            cur.next = tempCurrent.next;

            tempCurrent.next = null == tempCurrent.next ? null : tempCurrent.next.next;

            cur = cur.next;

        }
        return copyHead;
    }

    /**
     * 复制randomNode链表，普通解法解，时间复杂度O(N) + 空间复杂度O(N)
     * @param head
     * @return
     */
    public static RandNode copyRandNodeByMap(RandNode head) {
        if (null == head) {
            return null;
        }
        HashMap<RandNode, RandNode> helpMap = new HashMap<>();

        RandNode cur = head;
        while (null != cur) {
            helpMap.put(cur, new RandNode(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (null != cur) {
            helpMap.get(cur).next = helpMap.get(cur.next);
            helpMap.get(cur).rand = helpMap.get(cur.rand);
            cur = cur.next;
        }

        return helpMap.get(head);
    }

    /**
     * 求出链表相加的结果，返回结果同样生成对应的链表
     * @param node
     * @return
     */
    public static Node addNodeEleAndReturn(Node head1, Node head2) {
        if (null == head1) {
            return head2;
        }
        if (null == head2) {
            return head1;
        }
        // 2 -> 3 -> 7  + 4 -> 3
        // 237 + 43
        // 反转链表 7 -> 3 -> 2 + 3 -> 4, 注意进位
        Node head1Reversed = revrseNodeByEle(head1);
        Node head2Reversed = revrseNodeByEle(head2);

        Node c1 = head1Reversed;
        Node c2 = head2Reversed;

        Node addNode = null;
        Node pre = null;

        int ca = 0; // 进位
        int n1 = 0;
        int n2 = 0;
        int n = 0;

        while (null != c1 || null != c2) {
            n1 = null == c1 ? 0 : c1.value;
            n2 = null == c2 ? 0 : c2.value;

            n = n1 + n2 + ca;

            pre = addNode; // pre是上一个计算的结果，放在pre里面，后续挂载到addNode 后面=> addNode -> pre

            addNode = new Node(n % 10);

            addNode.next =  pre;

            ca = n / 10; // 进位

            c1 = c1 == null ? null : c1.next;
            c2 = c2 == null ? null : c2.next;
        }

        if (ca == 1) { // 还有进位的话
            pre = addNode;

            addNode = new Node(1);

            addNode.next = pre;
        }
        return addNode;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private static Node revrseNodeByEle(Node head) {
        if (null == head) {
            return null;
        }
        Node pre = null;
        Node next = null;
        while (null != head) {
            next = head.next;

            head.next = pre;

            pre = head;

            head = next;
        }
        return pre;
    }

    /**
     * K个一组逆序链表，该方式用栈的结构来实现，时间复杂度O(N) + 空间复杂度O(N)
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKEleGroup(Node head, int k) {
        if (null == head || k <= 1) {
            return head;
        }
        // K个一组，需要记录当前的节点以及，新的头节点

        Node newHead = head; // 新的头节点
        Node cur = head; // 当前节点的位置

        Node pre = null;
        Node next = null;
        Stack<Node> helpStack = new Stack<>();

        while (null != cur) {
            next = cur.next; // 暂存next节点
            helpStack.push(cur);
            if (helpStack.size() == k) { // 达到条件，开始反转
                pre = reverseNodeByStack(helpStack, pre, next);

                newHead = newHead == head ? cur : newHead;
            }
            cur = next; // 后移
        }
        return newHead;
    }

    public static Node reverseNodeByStack(Stack<Node> stack, Node left, Node right) {
        // stack为链表的逆序, left是stack对应链表的左侧节点，right是对应链表的右侧节点
        if (stack.isEmpty()) {
            return null;
        }
        Node cur = stack.pop(); // 头节点，反转后的头节点
        if (null != left) {
            left.next = cur; // 左侧节点连接上cur
        }
        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop(); // next节点暂存

            cur.next = next;

            cur = next; // 当前节点指向下一个
        }
        cur.next = right;
        return cur;
    }

    /**
     * K个一组逆序链表，该方式是最优解，时间复杂度O(N) + 空间复杂度O(1)
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKElementGroup(Node head, int k) {
        if (null == head || k <= 1) {
            return head;
        }
        Node pre = null;
        Node start = null;
        Node next = null;

        int count = 1;

        Node cur = head;
        while (null != cur) {
            next = cur.next;

            if (count == k) {
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head; // 这个是头节点，要记录下来，只有一次的机会会有意义赋值

                reverseLinkListByNode(pre, start, cur, next);

                pre = start; // pre实际上是保存每次的反转之后的第一个节点1, 7, 9, 13这种
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    public static void reverseLinkListByNode(Node left, Node start, Node end, Node right) {
        Node pre = start; // 类似head
        Node cur = start.next; // head.next

        Node next = null;

        while (cur != right) { // 这个就是反转的标准写法
            next = cur.next;

            cur.next = pre;

            pre = cur;

            cur = next;
        }

        if (null != left) {
            left.next = end; // 左侧节点next连上
        }

        start.next = right;
        // start很重要, 每次都需要记录开头的位置
        // 1 -> 7 -> 9
        // 7 -> 13 -> 14
        // 13 ->
    }

    /**
     * 删除链表中重复的节点，借助了hash表，有额外的O(N)的空间
     * 如果O(1)的空间复杂度，需要O(N^2)的时间复杂度, 直接每次往后遍历但前的元素，如果存在就删除
     * @param head
     * @return
     */
    public static Node deleteRepeat(Node head) {
        // 用hash表来保存, 有过的节点
        if (null == head) {
            return null;
        }
        HashMap<Node, Node> helpMap = new HashMap<>();

        Node cur = head;
        Node next = null;
        while (null != cur) {
            next = cur.next;
            if (helpMap.containsKey(cur)) { // 已经存在了，扔掉
                cur.next = next.next;
            }
            helpMap.put(cur, cur);
            cur = next;
        }
        return head;
    }

    /**
     * 从链表中删除该值的节点
     * @param head
     * @param num
     * @return
     */
    public static Node deleteThisValueFromLinkList(Node head, int num) {
        if (null == head) {
            return null;
        }
        Stack<Node> helpStack = new Stack<>();

        Node cur = head;
        Node next = null;
        while (null != cur) {
            next = cur.next;

            if (cur.value != num) {
                helpStack.push(cur);
            }
            cur = next;
        }
        next = helpStack.pop();
        while (!helpStack.isEmpty()) {
            cur = helpStack.pop();

            cur.next = next;
        }
        return cur;
    }

    /**
     * 链表的选择排序
     * @param head
     * @return
     */
    public static Node selectSortLinkList(Node head) {
        if (null == head) {
            return null;
        }
        Node tail = null; // 排序好的节点尾部
        Node small = null; // 最小的节点

        Node smallestPreNode = null; // 最小的节点的pre节点

        // 每次选择最小的放在排序好的头节点后面,然后原链表remove该节点
        Node cur = head;
        while (null != cur) {
            small = cur;

            // 找到最小节点的preNode
            smallestPreNode = getSmallestPreNode(cur);

            if (null != smallestPreNode) {
                small = smallestPreNode.next;

                smallestPreNode.next = small == null ? null : small.next;
            }

            cur = cur == small ? cur.next : cur;
            if (null == tail) {
                head = small; // 初始化，最小的节点给head
            } else {
                tail.next = small;
            }
            tail = small; // 排序好的尾节点，也就是当前找到的samll节点
        }
        return head;
    }

    /**
     * 找到最小元素的节点的pre节点
     * @param head
     * @return
     */
    public static Node getSmallestPreNode(Node head) {
        if (null == head) {
            return null;
        }
        Node smallPre = null;

        Node small = head; // 暂定最小的节点

        Node pre = head;
        Node cur = head.next;
        while (null != cur) {
            if (cur.value < small.value) { // 找到了更小的Node
                smallPre = pre;
                small = cur; // 最小节点
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }

    /**
     * 删除node节点的一种怪异方式
     * @param node
     */
    public static void deleteOneNodeByWired(Node node) {
        if (null == node) {
            return;
        }
        Node next = node.next;
        if (null == next) {
            return;
        }
        node.value = next.value;
        node.next = next.next;
    }

    /**
     * 把num插入到环形链表中(不降序)
     * @param head
     * @param num
     * @return
     */
    public static Node insertNumToLinkList(Node head, int num) {
        Node insertNode = new Node(num);
        if (null == head) { // 如果head是null,num自己组成新节点
            insertNode.next = insertNode;
            return insertNode;
        }

        Node cur = head;
        Node next = null;
        while (head != cur.next) {
            next = cur.next;
            if (cur != head && next.value < num) { // 不是头节点，insert插入进去
                cur.next = insertNode;
                insertNode.next = next;
            }
            cur = next;
        }

        // 这个时候cur的下一个就是head节点，cur是最后一个节点
        if (head.value < num) { // insertNode 到head节点前
            cur.next = insertNode;
            insertNode.next = head;
            return insertNode;
        }
        return head;
    }

    /**
     * merge两个链表，从小到大排序
     * @param head1
     * @param head2
     * @return
     */
    public static Node mergeTwoLinkList(Node head1, Node head2) {
        if (null == head1) {
            return head2;
        }
        if (null == head2) {
            return head1;
        }
        Node newHead = null;
        Node current = null;

        while (null != head1 && null != head1) {
            if (head1.value < head2.value) {
                if (null == newHead) {
                    newHead = current = head1;
                } else {
                    current.next = head1;
                    current = current.next; // current指向下一个
                }
                head1 = head1.next;
            } else {
                if (null == newHead) {
                    newHead = current = head2;
                } else {
                    current.next = head2;
                    current = current.next;
                }
                head2 = head2.next;
            }
        }
        if (null != head1) {
            current.next = head1;
        }
        if (null != head2) {
            current.next = head2;
        }
        return newHead;
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

    public static class RandNode {
        int value;
        RandNode next;
        RandNode rand;

        public RandNode(int value) {
            this.value = value;
        }
    }

}
