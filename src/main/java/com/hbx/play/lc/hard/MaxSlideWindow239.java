package com.hbx.play.lc.hard;

import java.util.LinkedList;

/**
 * @Auther: bingxin
 * @Date: 2019-10-30 16:10
 * @Description:
 */
public class MaxSlideWindow239 {

    public static void main(String[] args) {
        int[] nums = {4, 3, 5, 4, 3, 3, 6, 7};

        for (int i : maxSlidingWindow(nums, 3)) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 滑动窗口的最大值
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (null == nums || nums.length == 0 || nums.length < k) {
            return null;
        }
        // 采用双端链表
        LinkedList<Integer> helpList = new LinkedList<>();
        int[] res = new int[nums.length - k + 1]; // 结果

        for (int i = 0; i < nums.length; i++) {
            while (!helpList.isEmpty() && nums[helpList.peekLast()] <= nums[i]) {
                helpList.pollLast();
            }
            helpList.addLast(i);

            if (helpList.peekFirst() == i - k) {
                helpList.pollFirst();
            }

            if (i + 1 >= k) {
                res[i - k + 1] = nums[helpList.peekFirst()];
            }
        }
        return res;
    }

}
