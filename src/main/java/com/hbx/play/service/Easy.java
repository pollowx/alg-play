package com.hbx.play.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/19 5:34 PM
 */
public class Easy {

    public static void main(String[] args) {

        //System.out.println(getTrueNumber(-13432434));

        //printArrayFromMinToMax();

        //findAppearanceOnlyOnce();

        //int[] array = new int[]{2, 4, 6, 1, 3};
        //reOrderArrayB(array);

        int[] array = new int[100000];
        for (int i = 0; i < 100000; i++) {
            array[i] = new Random().nextInt(1000);
        }

        long startTime1 = System.currentTimeMillis();
        reOrderArrayB(array);
        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1 - startTime1);

        long startTime = System.currentTimeMillis();
        reOrderArrayA(array);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    private static int getTrueNumber(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    private static void printArrayFromMinToMax() {
        int[] array = new int[]{2, 7, 8, 4, 3, 1, 5, 2, 2, 2};

        int[] temp = new int[10];

        for (int i = 0; i < array.length; i++) {
            temp[array[i]]++;
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < temp[i]; j++) {
                System.out.println(i);
            }
        }
    }

    private static void findAppearanceOnlyOnce() {
        int[] array = new int[]{1, 2, 2, 1, 5, 5, 88, 88, 9, 100, 11, 100, 11};

        int temp = array[0];
        for (int i = 1; i < array.length; i++) {
            temp ^= array[i];
        }
        System.out.println(temp);
    }

    public static void reOrderArray(int[] array) {
        if (null == array || array.length <= 0) {
            return;
        }
        List<Integer> listJi = new ArrayList<>();
        List<Integer> listOu = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                listOu.add(array[i]);
            } else {
                listJi.add(array[i]);
            }
        }
        listJi.addAll(listOu);

        for (Integer integer : listJi) {
            System.out.println(integer);
        }
    }

    public static void reOrderArrayA(int[] array) {
        if (null == array || array.length <= 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] % 2 == 1 && array[i] % 2 == 0) {
                    int temp = array[i];
                    array[i] = array[j];

                    while (j - (i + 1) > 0) {
                        array[j] = array[j - 1];
                        j--;
                    }
                    array[j] = temp;
                    break;
                }
            }
        }

//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
    }

    public static void reOrderArrayB(int[] array) {
        if (null == array || array.length <= 0) {
            return;
        }

        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                continue;
            }
            int temp = array[i];
            while (i > k) {
                array[i] = array[i - 1];
                i--;
            }
            array[k] = temp;
            k++;
        }

//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
    }

}
