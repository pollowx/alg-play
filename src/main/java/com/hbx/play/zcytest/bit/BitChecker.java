package com.hbx.play.zcytest.bit;

/**
 * @Auther: bingxin
 * @Date: 2019-10-15 13:24
 * @Description:
 */
public class BitChecker {

//    public static void main(String[] args) {
//
//        System.out.println(addByBit(3, 5));
//
//        System.out.println(jianfaByBit(3, 5));
//        System.out.println(jianfaByBit(3, -1));
//
//        System.out.println(chengfaByBit(3, -4));
//
//    }

    /**
     * 位运算加法
     * @param a
     * @param b
     * @return
     */
    public static int addByBit(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * 减法
     * @param a
     * @param b
     * @return
     */
    public static int jianfaByBit(int a, int b) {
        return addByBit(a, negNum(b));
    }

    /**
     * 求补码
     * @param n
     * @return
     */
    public static int negNum(int n) {
        return addByBit(~n, 1);
    }

    /**
     * 乘法
     * @param a
     * @param b
     * @return
     */
    public static int chengfaByBit(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = addByBit(res, a);
            }
            b = b >>> 1;
            a = a << 1;
        }
        return res;
    }



}
