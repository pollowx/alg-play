package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/16 9:25 AM
 */
public class MaoPaoOrderArray {

    public static void main(String[] args) {

        int[] middle = new int[]{4, 7, 2, 1, 5, 3, 8, 6};

        int[] data = orderArray(middle);

    }

    private static int[] orderArray(int[] array) {
        if (null == array || array.length <= 0) {
            return null;
        }

        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j-1]) {
                    int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

}
