package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/16 11:15 AM
 */
public class SelectMinElementFromArray {

    public static void main(String[] args) {

        int[] middle = new int[]{7, 8, 1, 2, 3, 4, 5, 6};

        int data = orderArray2(middle);

        System.out.println(middle[data]);
    }

    private static int orderArray(int[] array) {
        if (null == array || array.length <= 0) {
            return 0;
        }
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private static int orderArray2(int[] array) {
        if (null == array || array.length <= 0) {
            return 0;
        }
        int low = 0, high = array.length - 1;
        while (true) {
            int middle = (low + high) / 2;
            if (array[middle] > array[low]) { // 在右侧
                low = middle - 1;
            } else { // 在左侧
                high = middle -1;
            }
            if (array[middle] > array[high] || array[low] < array[middle]) {
                return middle;
            }
        }
    }

}
