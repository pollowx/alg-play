package com.hbx.play.service;

import java.util.ArrayList;

/**
 * //num1,num2分别为长度为1的数组。传出参数
 * //将num1[0],num2[0]设置为返回结果
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
 *
 * @author danyu.bx
 * @description: TODO
 * @date 2019/9/2 11:32 AM
 */
public class FindAppearTwiceInt {

    public static void main(String[] args) {
//        System.out.println(1 ^ 7 ^ 8 ^ 9 ^ 6 ^ 1 ^ 8 ^ 6);
//        System.out.println(1 & 7 & 8 & 9 & 6 & 1 & 8 & 6);
//        System.out.println(" ");

//        System.out.println(7 ^ 9); // x ^ y = z
//        System.out.println(" ");
//
//        System.out.println(7 ^ 9 ^ 1); //
//        System.out.println(7 ^ 9 ^ 1 ^ 7); // 9 ^ 1
//        System.out.println(7 ^ 9 ^ 1 ^ 7 ^ 8); // 9 ^ 1 ^ 8
//        System.out.println(7 ^ 9 ^ 1 ^ 7 ^ 8 ^ 9); // 1 ^ 8
//        System.out.println(7 ^ 9 ^ 1 ^ 7 ^ 8 ^ 9 ^ 6); // 1 ^ 8 ^ 6
//        System.out.println(7 ^ 9 ^ 1 ^ 7 ^ 8 ^ 9 ^ 6 ^ 1); // 8 ^ 6
//        System.out.println(7 ^ 9 ^ 1 ^ 7 ^ 8 ^ 9 ^ 6 ^ 1 ^ 8); // 7 ^ 6
//        System.out.println(7 ^ 9 ^ 1 ^ 7 ^ 8 ^ 9 ^ 6 ^ 1 ^ 8 ^ 6); // 0
//
//        System.out.println(" ");
//        System.out.println(2 ^ 5);
//        System.out.println(2 ^ 2 + 2 ^ 3);
//        System.out.println(2 ^ 1 + 2 ^ 4);

        //System.out.println(findFirstIndexTrue(2048));

        int[] array = {1, 7, 8, 9, 6, 1, 8, 6};
        int[] num1 = new int[1];
        int[] num2 = new int[1];

        findNumsAppearOnce(array, num1, num2);

        System.out.println(num1[0]);
        System.out.println(num2[0]);

    }

    public static void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        // 直接位运算，这个属于找规律的题目，但是我第一次做的时候没有找到这个规律，这种一定要把二进制写出来
        if (null == array || array.length <= 0) {
            return;
        }
        if (array.length == 2) {
            num1[0] = array[0];
            num2[0] = array[1];
            return;
        }

        int xy = 0;
        for (int i = 0; i < array.length; i++) {
            xy ^= array[i];
        }
        int firstIndexTrue = findFirstIndexTrue(xy);
        int num = (int) Math.pow(2, firstIndexTrue - 1);

        ArrayList<Integer> aList = new ArrayList<>();
        ArrayList<Integer> bList = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if ((array[i] & num) == num) {
                aList.add(array[i]);
            } else {
                bList.add(array[i]);
            }
        }
        for (Integer l : aList) {
            num1[0] ^= l;
        }

        for (Integer l : bList) {
            num2[0] ^= l;
        }

        return;
    }

    private static int findFirstIndexTrue(int data) {
        int index = 0;

        while (data != 0) {
            index++;
            if ((data & 1) == 1) { // 找到
                break;
            }
            data = data >> 1;
        }
        return index;
    }

}
