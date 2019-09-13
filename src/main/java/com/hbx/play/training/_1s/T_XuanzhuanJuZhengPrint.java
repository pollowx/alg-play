package com.hbx.play.training._1s;

import java.util.ArrayList;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/28 8:54 PM
 */
public class T_XuanzhuanJuZhengPrint {

//    public static void main(String[] args) {
//        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
//        printMatrix(array);
//    }

//    public static ArrayList<Integer> printMatrix(int[][] matrix) {
//        if (null == matrix) {
//            return new ArrayList<>();
//        }
//
//        ArrayList<Integer> list = new ArrayList<>();
//
//        int kLength = matrix.length;
//
//        while (kLength != 0) {
//            for (int i = 0; i < matrix[0].length; i++) {
//                list.add(matrix[0][i]);
//            }
//            matrix = rollMatrix(matrix);
//            if (null == matrix) {
//                break;
//            }
//            kLength = matrix.length;
//        }
//
//        for (Integer p : list) {
//            System.out.print(p + "\t");
//        }
//        return list;
//    }
//
//    public static int[][] rollMatrix(int[][] matrix) {
//
//        int row = matrix.length;
//        int col = matrix[0].length;
//
//        int[][] array = new int[col][row - 1];
//
//        for (int j = col - 1; j >= 0; j--) {
//            for (int i = 1; i < row; i++) {
//                array[col - j - 1][i - 1] = matrix[i][j];
//            }
//        }
//        return array;
//    }

}
