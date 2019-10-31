package com.hbx.play.zcytest.other;

import org.springframework.util.StringUtils;

/**
 * @Auther: bingxin
 * @Date: 2019-10-30 13:24
 * @Description:
 */
public class KMPAlg {

    public static void main(String[] args) {

        for (int i : getNextArray("ABCDABDE")) {
            System.out.print(i + "\t");
        }

        System.out.println(getIndexOf("ACBDBCBDBCA", "BDBCA"));

    }

    public static int getIndexOf(String str, String match) {
        if (null == str || null == match || match.length() < 1 || str.length() < match.length()) {
            return -1;
        }
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();

        int[] next = getNextArray(match);

        int si = 0;
        int mi = 0;

        while (si < strChars.length && mi < matchChars.length) {
            if (strChars[si] == matchChars[mi]) { // 如果相等那么应该继续下一个
                si++;
                mi++;
            } else if (next[mi] == -1) { //
                si++;
            } else { // 如果不等，那么下一个往哪里跳呢？
                mi = next[mi];
            }
        }
        return mi == matchChars.length ? si - mi : -1;
    }

    /**
     * 求match的前缀后缀匹配,求的下一步如果不匹配的时候str往哪个地方跳-> (j - next[j])
     * @param match
     * @return
     */
    public static int[] getNextArray(String match) {
        char[] chars = match.toCharArray();

        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;

        int cn = 0; // 下一个匹配串的位置

        for (int i = 2; i < next.length; i++) {
            if (chars[i - 1] == chars[cn]) {
                next[i] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i] = 0;
            }
        }
        return next;
    }

}
