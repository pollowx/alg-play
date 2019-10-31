package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/19 11:13 AM
 */
public class ArrayRotateFindMinElement {

    private static int findMinElement(int[] array) {
        if (null == array || array.length == 0) {
            return 0;
        }

        int left = 0, right = array.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (array[middle] > array[right]) { // -> right
                left = middle + 1;
            } else if (array[middle] < array[right]) { // -> left
                right = middle;
            } else { // equal this, right point -> left
                right--;
            }
        }
        return array[left];
    }

    public static void main(String[] args) {

        int[] middle = new int[]{7, 8, 1, 2, 3, 4, 5, 6};
        System.out.println(findMinElement(middle));


        int[] having = new int[]{1, 2, 0, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(findMinElement(having));
    }

}
