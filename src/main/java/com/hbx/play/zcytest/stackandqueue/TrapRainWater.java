package com.hbx.play.zcytest.stackandqueue;

import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/9/11 3:24 PM
 */
public class TrapRainWater {

    public static void main(String[] args) {

        int[] data = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        //System.out.println(getTrapNormal(data));
        //System.out.println(getTrapDynamicSave(data));
        //System.out.println(getTrapDynamicDoublePoint(data));
        System.out.println(getTrapByDecStack(data));
    }

    /**
     * 暴力穷举
     *
     * @return
     */
    public static int getTrapNormal(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;

            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            res = res + Math.min(maxLeft, maxRight) - height[i];
        }
        return res;
    }

    /**
     * 动态存储，左右的记录
     *
     * @param height
     * @return
     */
    public static int getTrapDynamicSave(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }
        int[] maxLeftArray = new int[height.length];
        int[] maxRightArray = new int[height.length];

        // 先找到每个元素最左边的最大高度，包含自身，存下来
        maxLeftArray[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            maxLeftArray[i] = Math.max(height[i], maxLeftArray[i - 1]);
        }

        // 找到每个元素最右边的最大高度，包含自身，存下来
        maxRightArray[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRightArray[i] = Math.max(height[i], maxRightArray[i + 1]);
        }

        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            res += Math.min(maxLeftArray[i], maxRightArray[i]) - height[i];
        }
        return res;
    }

    /**
     * 动态规划双指针版本
     *
     * @param height
     * @return
     */
    public static int getTrapDynamicDoublePoint(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }
        int res = 0;
        int maxLeft = 0;
        int maxRight = 0;

        int left = 1;
        int right = height.length - 2;

        for (int i = 1; i < height.length - 1; i++) {
            // 从左向右
            if (height[left - 1] < height[right + 1]) {
                // 找到左侧最大值
                maxLeft = Math.max(maxLeft, height[left - 1]);
                int min = maxLeft;
                if (min > height[left]) {
                    res += (min - height[left]);
                }
                left++;
            } else {
                // 找到左侧最大值
                maxRight = Math.max(maxRight, height[right + 1]);
                int max = maxRight;
                if (max > height[right]) {
                    res += (max - height[right]);
                }
                right--;
            }
        }
        return res;
    }

    /**
     * 单调递减栈
     *
     * @param height
     * @return
     */
    public static int getTrapByDecStack(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> helpStack = new Stack<>();

        for (int i = 0; i < height.length; i++) {
            // 大于当前栈顶元素，就出栈计算
            while (!helpStack.isEmpty() && height[helpStack.peek()] < height[i]) {
                int tempIndex = helpStack.pop(); // height的下标
                if (helpStack.isEmpty()) {
                    break;
                }
                int distance = i - helpStack.peek() - 1;  // 距离，是当下递减栈中pop之后的第二个元素，也就是递减栈中pop后的距离
                int min = Math.min(height[helpStack.peek()], height[i]); // 哪个小依据哪个，因为要存水
                res += distance * (min - height[tempIndex]);
            }
            helpStack.push(i);
        }
        return res;
    }

}
