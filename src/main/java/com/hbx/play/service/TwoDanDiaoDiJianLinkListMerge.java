package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/21 3:56 PM
 */
public class TwoDanDiaoDiJianLinkListMerge {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode merge(ListNode list1, ListNode list2) {
        if (null == list1) {
            return null == list2 ? null : list2;
        }
        if (null == list2) {
            return null == list1 ? null : list1;
        }

        // 1 -> 3 -> 7 -> 9
        // 2 -> 8 -> 10
        ListNode mergeHead = null;
        ListNode current = null;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                if (mergeHead == null) { // 初始化
                    mergeHead = current = list1;
                } else {
                    current.next = list1;
                    current = current.next;
                }
                list1 = list1.next;
            } else {
                if (mergeHead == null) {
                    mergeHead = current = list2;
                } else {
                    current.next = list2;
                    current = current.next;
                }
                list2 = list2.next;
            }
        }
        if (list1 == null) {
            current.next = list2;
        } else {
            current.next = list1;
        }
        return mergeHead;
    }

    public static ListNode mergeA(ListNode list1, ListNode list2) {
        if (null == list1) {
            return null == list2 ? null : list2;
        }
        if (null == list2) {
            return null == list1 ? null : list1;
        }
        // 1 -> 3 -> 7 -> 9
        // 2 -> 8 -> 10
        if (list1.val >= list2.val) {
            list2.next = mergeA(list1, list2.next);
            return list2;
        } else {
            list1.next = mergeA(list1.next, list2);
            return list1;
        }
    }


    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(7);
        head1.next.next.next = new ListNode(9);
        head1.next.next.next.next = new ListNode(11);

        ListNode head2 = new ListNode(3);
        head2.next = new ListNode(8);
        head2.next.next = new ListNode(10);

        System.out.println(merge(head1, head2));

        //System.out.println(reverseK(head1, 2));
    }

    /**
     * 递归
     *
     * @return
     */
    private static ListNode myMerge(ListNode list1, ListNode list2) {
        if (null == list1) {
            return list2;
        }
        if (null == list2) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = myMerge(list1.next, list2);
            return list1;
        } else {
            list2.next = myMerge(list1, list2.next);
            return list2;
        }
    }

    private static ListNode myMergeB(ListNode list1, ListNode list2) {
        if (null == list1) {
            return list2;
        }
        if (null == list2) {
            return list1;
        }

        ListNode head = null;
        ListNode current = null;

        while (null != list1 && null != list2) {
            if (list1.val <= list2.val) {
                if (null == head) {
                    head = current = list1;
                } else {
                    current.next = list1;
                    current = current.next;
                }
                list1 = list1.next; // 后移
            } else {
                if (null == head) {
                    head = current = list2;
                } else {
                    current.next = list2;
                    current = current.next;
                }
                list2 = list2.next; // 后移
            }
        }

        if (list1 == null) {
            current.next = list2;
        } else {
            current.next = list1;
        }
        return head;
    }

    private static ListNode reverseK(ListNode head, int k) {
        if (null == head) {
            return null;
        }

        int count = 1;
        ListNode mergeHead = null;
        ListNode group = null;
        ListNode groupTail = null;

        while (null != head) {
            if (null == group) {
                group = new ListNode(head.val);
            } else {
                group.next = new ListNode(head.val);
            }
            head = head.next;

            if (count % k == 0) { // k个
                if (null == mergeHead) {
                    mergeHead = reverseSingleListNode(group);
                } else {
                    mergeHead.next = reverseSingleListNode(group);
                }
                group = null;
            }
            count++;
        }
        return mergeHead;
    }

    private static ListNode reverseSingleListNode(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode pre = null;
        ListNode next = null;

        while (null != head) {
            next = head.next;

            head.next = pre;

            pre = head;

            head = next;
        }
        return pre;
    }

}
