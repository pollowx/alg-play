package com.hbx.play.zcytest.arraymartrix;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: bingxin
 * @Date: 2019-09-19 15:41
 * @Description:
 */
public class ArrayAndMartrix {

    public static void main(String[] args) {

    }

    /**
     * 数组值相加等于k的最大子数组长度
     * @param arr
     * @param k
     * @return
     */
    public int findMaxLengthThatElePulsEqualK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                Integer index = map.get(sum - k);
                if (null != index) { // 找到了
                    len = Math.max(i - index, len);
                }
            } else {
                map.put(sum, i);
            }
        }
        return len;
    }

}
