package com.hbx.play.service;

import java.util.HashMap;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/28 5:15 PM
 */
public class RandomListSolution {

    public static void main(String[] args) {
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        RandomListNode node5 = new RandomListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node1.random = node3;
        node2.random = node5;
        node4.random = node2;

        RandomListNode c = clone(node1);
    }

    /**
     * <p> 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
     * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空） </p>
     *
     * @param pHead
     * @return
     */
    public static RandomListNode clone(RandomListNode pHead) {
        if (null == pHead) {
            return null;
        }
        // 这题的解题思路比较新颖，之前确实没有见过。我自己做之前想了一下，head节点的特征是什么，得出的结论是没有特征，所以说，该解题思路是不在于寻找特征，直接操刀整体赋值
        // 把大象放进冰箱分为这几步骤
        // 1. 复制所有原来的节点，插入到原来的节点后面 A -> B -> C 且 A -> C ===> A -> A' -> B -> B' -> C -> C' 且 A -> C
        RandomListNode current = pHead;

        while (current != null) {
            RandomListNode insertNode = new RandomListNode(current.label);
            RandomListNode nextNode = current.next;

            // A -> C  =====> B -> C; A -> B
            insertNode.next = nextNode;

            current.next = insertNode;

            current = nextNode;
        }

        // 2. 复制所有节点的random指向 A -> B -> C 且 A -> C ===> A -> A' -> B -> B' -> C -> C' 且 A -> C 且 A' -> C'
        current = pHead;
        while (current != null) {
            if (null != current.random) {
                current.next.random = current.random == null ? null : current.random.next;
            }
            current = current.next.next;
        }

        // 3. 拆开复制和原链表
        current = pHead;
        RandomListNode cloneHead = pHead.next;
        while (null != current) {
            RandomListNode tempCurrentNext = current.next;

            current.next = tempCurrentNext.next;

            tempCurrentNext.next = tempCurrentNext.next == null ? null : tempCurrentNext.next.next;

            current = current.next;
        }
        return cloneHead;
    }

    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * 用额外空间暂存的方式
     *
     * @param pHead
     * @return
     */
    public static RandomListNode cloneWithMap(RandomListNode pHead) {
        if (null == pHead) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode current = pHead;

        while (null != current) {
            map.put(current, new RandomListNode(current.label)); // (A, A'), (B, B') ...
            current = current.next;
        }

        current = pHead;
        while (null != current) {
            map.get(current).next = map.get(current.next);
            current = current.next;
        }

        RandomListNode reHead = map.get(pHead);
        current = pHead;

        while (null != current) {
            map.get(current).random = map.get(current.random);
            current = current.next;
        }
        return reHead;
    }


//    public static RandomListNode myClone(RandomListNode pHead) {
//        if (null == pHead) {
//            return null;
//        }
//
//        RandomListNode current = pHead;
//
//        while (null != current) {
//            RandomListNode insertNode = new RandomListNode(current.label);
//            RandomListNode nextNode = current.next;
//
//            insertNode.next = nextNode;
//
//            current.next = insertNode;
//
//            current = nextNode;
//        }
//
//        current = pHead;
//        while (null != current) {
//            if (null != current.random) {
//                current.next.random = current.random == null ? null : current.random.next; // A->C ===> A'->C'
//            }
//            current = current.next.next;
//        }
//
//        current = pHead;
//
//        RandomListNode reHead = pHead.next;
//
//        while (null != current) {
//            RandomListNode tempIndexNode = current.next;
//
//            current.next = tempIndexNode.next;
//
//            tempIndexNode.next = tempIndexNode.next == null ? null : tempIndexNode.next.next;
//
//            current = current.next;
//        }
//
//        return reHead;
//    }

}
