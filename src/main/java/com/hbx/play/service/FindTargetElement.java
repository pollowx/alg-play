package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/14 11:45 AM
 */
public class FindTargetElement {

    public static void main(String[] args) {
        int target = 6;
        int[][][] data = {{{1, 5, 6}, {2, 7, 9}, {3, 8, 10}}};
        int[][][] data2 = {{{1, 2, 6}, {5, 7, 9}, {7, 8, 10}}};

        int[][] data3 = {{1, 5, 6}, {2, 7, 9}};

        System.out.println(findTarget(target, data3));
    }

    public static boolean find(int target, int[][] array) {
        if(array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int m = 0, n = array[0].length - 1;//从右上角开始找，array[0][n-1]

        while(m <= array.length - 1 && n >= 0) {
            if(target == array[m][n]) {
                System.out.println("m:" + (m + 1) + ", n:" + (n + 1));
                return true;
            } else if(target > array[m][n])
                m ++;
            else
                n --;
        }
        return false;
    }

    public static boolean findTarget(int target, int[][] array) {
        if (null == array || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int h = array.length - 1, v = 0; // 从左下角找起，按照对角线find
        while (v < array[0].length && h >= 0) {
            if (array[h][v] == target) {
                System.out.println("h:" + (h + 1) + ", v:" + (v + 1));
                return true;
            } else if (array[h][v] > target) {
                h--;
            } else {
                v++;
            }
        }
        return false;
    }

}
