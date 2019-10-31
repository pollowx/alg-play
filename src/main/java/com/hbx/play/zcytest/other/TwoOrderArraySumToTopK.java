package com.hbx.play.zcytest.other;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @Auther: bingxin
 * @Date: 2019-10-29 13:01
 * @Description:
 */
public class TwoOrderArraySumToTopK {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {3, 5, 7, 9, 11};

        for (int t : getTwoOrderArraySumToTopK(arr1, arr2, 4)) {
            System.out.print(t + "\t");
        }
    }

    /**
     * 两个有序数组间相加和为TopK的问题
     * @param arr1
     * @param arr2
     * @param topK
     * @return
     */
    public static int[] getTwoOrderArraySumToTopK(int[] arr1, int[] arr2, int topK) {
        if (null == arr1 || null == arr2 || topK < 1) {
            return null;
        }
        topK = Math.min(topK, arr1.length * arr2.length);
        int[] res = new int[topK];

        PriorityQueue<Node> maxHeap = new PriorityQueue<Node>(new MaxHelpHeap());
        HashSet<String> alreadySets = new HashSet<>();

        int i1 = arr1.length - 1;
        int i2 = arr2.length - 1;

        maxHeap.add(new Node(i1, i2, arr1[i1] + arr2[i2]));
        alreadySets.add(i1 + "_" + i2);

        int currentIndex = 0;
        while (currentIndex < topK) {
            Node node = maxHeap.poll();
            res[currentIndex++] = node.value;

            i1 = node.index1;
            i2 = node.index2;

            if (!alreadySets.contains((i1 - 1) + "_" + i2)) {
                maxHeap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
                alreadySets.add((i1 - 1) + "_" + i2);
            }

            if (!alreadySets.contains(i1 + "_" + (i2 - 1))) {
                maxHeap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
                alreadySets.add(i1 + "_" + (i2 - 1));
            }
        }
        return res;
    }

    public static class MaxHelpHeap implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.value - o1.value;
        }
    }

    public static class Node {
        public int index1;
        public int index2;

        public int value;

        public Node(int index1, int index2, int value) {
            this.index1 = index1;
            this.index2 = index2;
            this.value = value;
        }
    }

}
