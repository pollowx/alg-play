package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/19 11:35 AM
 */
public class Fibonacci {

    public static void main(String[] args) {

        System.out.println(fibonacci(38));

        System.out.println(fibonacciA(38));

        System.out.println(fibonacciB(38));

        System.out.println(fibonacciC(38));

    }

    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static long fibonacciA(int n) {
        long[] data = new long[40];

        data[0] = 1;
        data[1] = 1;

        for (int i = 2; i <= n; i++) {
            data[i] = data[i - 1] + data[i - 2];
        }

        return data[n - 1];
    }

    private static long fibonacciB(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 1) {
            return 1;
        }

        long n1 = 0;
        long n2 = 1;
        long sum = 0;

        for (int i = 2; i <= n; i++) {
            sum = n2 + n1;
            n1 = n2;
            n2 = sum;
        }
        return sum;
    }

    private static long fibonacciC(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 1) {
            return 1;
        }
        long n1 = 1;
        long sum = 0;

        for (int i = 1; i <= n; i++) {
            sum = sum + n1;
            n1 = sum - n1;
        }
        return sum;
    }

}
