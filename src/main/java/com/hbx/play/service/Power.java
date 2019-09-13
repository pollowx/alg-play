package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/20 2:25 PM
 */
public class Power {

    public static void main(String[] args) {
        System.out.println(power(5, 9));
    }

    public static double power(double base, int exponent) {
        int absEx = Math.abs(exponent);

        double result = 1.0;
        while (absEx > 0) {
            if ((absEx & 1) == 1) {
                result = result * base;
            }
            base = base * base;
            absEx = absEx >> 1;
        }
        return exponent > 0 ? result : 1 / result;
    }

}
