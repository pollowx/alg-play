package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/19 2:29 PM
 */
public class ForgJump {

    public static void main(String[] args) {

        System.out.println(culJumpWay(38));

        System.out.println(culJumpWayB(38));

    }


    private static int culJumpWay(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return culJumpWay(n - 1) + culJumpWay(n - 2);
    }

    private static int culJumpWayB(int n) {
        if (n <= 0) {
            return 0;
        }

        int a1 = 1;
        int sum = 1;

        for (int i = 1; i < n; i++) {
            sum = sum + a1;
            a1 = sum - a1;
        }

        return sum;
    }

}
