package com.hbx.play.zcytest.arraymartrix;

/**
 * @Auther: bingxin
 * @Date: 2019-10-22 15:33
 * @Description:
 */
public class TrapRainWater {


    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 5, 2, 4};

        System.out.println(getMaxRainWaterA(arr));

        System.out.println(getMaxRainWaterB(arr));
    }

    /**
     * 接雨水，原始版本 - O(N^2) + O(1)
     * @param arr
     * @return
     */
    public static int getMaxRainWaterA(int[] arr) {
        // 思路是当前位置上的value值，和位于他左侧右侧的最大值比较
        // 如果最大值中较小的一个比当前arr[i]大，那么当前位置可以存储max - arr[i]
        // 如果当前值大，那么当前位置的存储备量就是0
        // 即 water[i] = Math.max(Math.min(左侧最大值，右侧最大值) - arr[i], 0);
        // 1. check数据
        if (null == arr || arr.length < 3) { // 无法组成山峰
            return 0;
        }

        int res = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;

            // 左侧找最大值
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, arr[j]);
            }
            for (int k = i + 1; k < arr.length; k++) {
                rightMax = Math.max(rightMax, arr[k]);
            }
            res += Math.max(Math.min(leftMax, rightMax) - arr[i], 0);
        }
        return res;
    }

    /**
     * 接雨水 - 优化版本数组暂存 - O(N) + O(N)
     * @param arr
     * @return
     */
    public static int getMaxRainWaterB(int[] arr) {
        if (null == arr || arr.length < 3) { // 无法组成山峰
            return 0;
        }
        int[] leftMaxs = new int[arr.length];
        int[] rightMaxs = new int[arr.length];

        int leftMax = arr[0];
        for (int i = 0; i < arr.length; i++) {
            leftMaxs[i] = Math.max(leftMax, arr[i]);
            if (arr[i] > leftMax) {
                leftMax = arr[i];
            }
        }

        int rightMax = arr[arr.length - 1];
        for (int i = arr.length - 1; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMax, arr[i]);
            if (arr[i] > rightMax) {
                rightMax = arr[i];
            }
        }

        int res = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            res += Math.max(Math.min(leftMaxs[i], rightMaxs[i]) - arr[i], 0);
        }

        return res;
    }


}
