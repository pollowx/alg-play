package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/9/3 10:50 AM
 */
public class StringAlg {

    public static void main(String[] args) {
        String s = "abcde";

        System.out.println(leftRotateString(s, 1));
        System.out.println(leftRotateStringTemp(s, 1));

        System.out.println(leftRotateString(s, 2));
        System.out.println(leftRotateStringTemp(s, 2));

        System.out.println(leftRotateString(s, 3));
        System.out.println(leftRotateStringTemp(s, 3));

        System.out.println(leftRotateString(s, 4));
        System.out.println(leftRotateStringTemp(s, 4));
    }

    /**
     * 循环左移字符串
     *
     * @param str
     * @param n
     * @return
     */
    public static String leftRotateString(String str, int n) {
        // 注意n的长度如果是str长度的倍数就取余
        if (str.isEmpty()) {
            return "";
        }
        int length = str.length();

        if (n <= 0) {
            return str;
        } else if (n > str.length()) {
            n = n % str.length();
        }
        String tail = String.copyValueOf(str.toCharArray(), 0, n);
        return new StringBuffer(str.substring(n, length)).append(tail).toString();
    }

    public static String leftRotateStringTemp(String str, int n) {
        // 注意n的长度如果是str长度的倍数就取余
        if (str.isEmpty()) {
            return "";
        }
        if (n <= 0) {
            return str;
        } else if (n > str.length()) {
            n = n % str.length();
        }

        int length = str.length();
        char[] chars = str.toCharArray();

        rotate(chars, 0, n - 1);
        rotate(chars, n, length - 1);
        rotate(chars, 0, length - 1);

        return new String(chars);
    }

    public static void rotate(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[end];
            chars[end] = chars[start];
            chars[start] = temp;

            start++;
            end--;
        }
    }
}
