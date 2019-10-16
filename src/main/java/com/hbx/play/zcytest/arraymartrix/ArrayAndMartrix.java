package com.hbx.play.zcytest.arraymartrix;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: bingxin
 * @Date: 2019-09-19 15:41
 * @Description:
 */
public class ArrayAndMartrix {

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        printMartrix(array);
    }

    /**
     * 打印旋转矩阵
     * @param martrix
     */
    public static void printMartrix(int[][] martrix) {
        if (null == martrix || martrix.length == 0 ||
                null == martrix[0] || martrix[0].length == 0) {
            return;
        }
        // 按照交换的顺序来打印，行转成列

        List<Integer> hpList = Lists.newArrayList();
        int row = martrix.length; // 二维数组的行数

        while (row != 0) {
            for (int i = 0; i < martrix[0].length; i++) {
                hpList.add(martrix[0][i]);
            }

            martrix = rotateMartrix(martrix);
            if (null == martrix) {
                break;
            }
            row = martrix.length;

            for (Integer i : hpList) {
                System.out.print(i + "\t");
            }
            System.out.println("");
            hpList.clear();
        }
    }

    /**
     * 旋转
     * @param martrix
     * @return
     */
    public static int[][] rotateMartrix(int[][] martrix) {
        if (null == martrix) {
            return null;
        }

        int row = martrix.length;
        int col = martrix[0].length;

        int[][] res = new int[col][row - 1];

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row - 1; j++) {
                res[i][j] = martrix[j + 1][col - 1 - i];
            }
        }
        return res;
    }

}
