package com.hbx.play.service;

import java.util.ArrayList;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/26 2:23 PM
 */
public class XuanzhuanJuZhengPrint {

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(array);
    }

    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        if (null == matrix) {
            return new ArrayList<>();
        }
        ArrayList<Integer> pork = new ArrayList<>();
        // 先打印第一行，或者先暂存

        int row = matrix.length; // 二维数组的行数
        while (row != 0) { // 循环结束的条件，一定是需要一个循环体的变量出口
            // 暂存第一行
            for (int i = 0; i < matrix[0].length; i++) { // matrix[0].length是第一行的元素数量
                pork.add(matrix[0][i]);
            }
            // 旋转数组
            matrix = rollMatrix(matrix); // 旋转数组
            if (null == matrix) {
                break;
            }
            row = matrix.length;
        }

        for (Integer p : pork) {
            System.out.println(p);
        }

        return pork;
    }

    public static int[][] rollMatrix(int[][] matrix) {
        if (null == matrix) {
            return null;
        }
        int row = matrix.length;    // 4
        int col = matrix[0].length; // 4

        // 旋转矩阵,初始化, 需要找到对应的规律
        // (4, 4) -> (4, 3) -> (3, 3) -> (3, 2) -> (2, 2) -> (2, 1)
        int[][] temp = new int[col][row - 1];

        // 旋转矩阵,赋值, 需要找到规律
        // 原矩阵的坐标，第一行已经去除
        // (1, 3) -> (0, 0)   (2, 3) -> (0, 1)   (3, 3) -> (0, 2)
        // (1, 2) -> (1, 0)   (2, 2) -> (1, 1)   (3, 2) -> (1, 2)
        // (1, 1) -> (2, 0)   (2, 1) -> (2, 1)   (3, 1) -> (2, 2)
        // (1, 0) -> (3, 0)   (2, 0) -> (3, 1)   (3, 0) -> (3, 2)
        for (int j = col - 1; j >= 0; j--) { // col = 4, col是旋转后的行数
            for (int i = 1; i < row; i++) { // row = 4, 列数 [1, 3]
                temp[col - j - 1][i - 1] = matrix[i][j]; // 找到对应关系
            }
        }
        return temp;
    }

}
