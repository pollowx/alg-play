package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/19 3:18 PM
 */
public class CrazyForgJumper {

    public static void main(String[] args) {
        System.out.println(jumpFloorII(14));

        System.out.println(jumpFloorIIB(14));

        System.out.println(jumpFloorIIC(14));

    }

    public static int jumpFloorII(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        return (int) Math.pow(2, target - 1);
    }

    public static int jumpFloorIIB(int target) {
        if (target <= 0) {
            return 0;
        }

        int sum = 1;
        for (int i = 1; i < target; i++) {
            sum = 2 * sum;
        }
        return sum;
    }

    public static int jumpFloorIIC(int target) {
        if (target <= 0) {
            return 0;
        }

        return 1 << --target;
    }

}
