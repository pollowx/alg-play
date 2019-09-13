package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/14 3:45 PM
 */
public class ReplaceBlank {

    public static void main(String[] args) {
        StringBuffer test = new StringBuffer("I will just do it.");
        //StringBuffer test = new StringBuffer();

        System.out.println(replace3(test));
    }

    private static String replace(StringBuffer str) {
        if (null == str || "".equals(str.toString())) {
            return "";
        }
        StringBuffer sb = new StringBuffer(str);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                sb.append("sb");
            }
        }

        int j = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != ' ') {
                sb.setCharAt(j, str.charAt(i));
                j++;
            } else {
                sb.setCharAt(j, '%');
                j++;
                sb.setCharAt(j, '2');
                j++;
                sb.setCharAt(j, '0');
                j++;
            }
        }
        return sb.toString();
    }

    private static String replace2(StringBuffer str) {
        if (null == str || "".equals(str.toString())) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    private static String replace3(StringBuffer str) {
        if (null == str || "".equals(str.toString())) {
            return "";
        }
        return str.toString().replaceAll("\\ ", "%20");
    }

}
