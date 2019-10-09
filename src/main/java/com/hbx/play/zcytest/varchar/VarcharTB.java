package com.hbx.play.zcytest.varchar;

import org.springframework.util.StringUtils;

/**
 * @Auther: bingxin
 * @Date: 2019-10-09 10:59
 * @Description:
 */
public class VarcharTB {

//    public static void main(String[] args) {
//
//        String str1 = "123";
//        String str2 = "2321";
//
//        System.out.println(twoWordsBianxing(str1, str2));
//    }

    /**
     * 判断两个字符串出现的每个次数都一样
     * @param str1
     * @param str2
     * @return
     */
    public static boolean twoWordsBianxing(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2) || str1.length() != str2.length()) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int[] map = new int[256];

        for (int i = 0; i < chars1.length; i++) {
            map[chars1[i]]++;
        }
        for (int j = 0; j < chars2.length; j++) {
            if (map[chars2[j]]-- <= 0) {
                return false;
            }
        }
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        String str1 = "cdab";
//        String str2 = "abcd";
//
//        System.out.println(judgeTwoWordsXuanzhuan(str1, str2));
//    }

    /**
     * 判断两个次互为旋转词
     * @param a
     * @param b
     * @return
     */
    public static boolean judgeTwoWordsXuanzhuan(String a, String b) {
        if (StringUtils.isEmpty(a) || StringUtils.isEmpty(b) || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        return b2.contains(a);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        String str1 = "acaacbccd";

        System.out.println(getCountStringAppear(str1));
    }

    /**
     * 统计每个char出现的次数，词频
     * @param str
     * @return
     */
    public static String getCountStringAppear(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        char[] chars = str.toCharArray();

        String res = "";

        int num = 1;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i + 1] == chars[i]) {
                num++;
            } else {
                // 开始连接
                String sp = i == 0 ? "" : "_";
                res = res + sp + chars[i] + "_" + num;
                num = 1;
            }
        }

        if (num >= 1) {
            res = res + "_" + chars[chars.length - 1] + "_" + num;
        }
        return res;
    }


}
