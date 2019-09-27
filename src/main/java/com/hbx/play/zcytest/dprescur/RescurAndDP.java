package com.hbx.play.zcytest.dprescur;

/**
 * @Auther: bingxin
 * @Date: 2019-09-27 14:22
 * @Description:
 */
public class RescurAndDP {

    public static void main(String[] args) {

        System.out.println(fibernaci(15));
        System.out.println(fibernaciB(15));
    }

    /**
     * 斐波那契数列-递归
     *
     * @param n
     * @return
     */
    public static int fibernaci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibernaci(n - 1) + fibernaci(n - 2);
    }

    /**
     * 斐波那契数列 - 保存中间变量
     *
     * @param n
     * @return
     */
    public static int fibernaciB(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int sum = 1;

        int temp = 0;
        int pre = 1;
        for (int i = 3; i <= n; i++) {
            temp = sum;

            sum = pre + temp;

            pre = temp;
        }
        return sum;
    }

}
