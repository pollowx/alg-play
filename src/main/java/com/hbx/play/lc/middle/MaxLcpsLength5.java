package com.hbx.play.lc.middle;

/**
 * @Auther: bingxin
 * @Date: 2019-10-30 14:10
 * @Description:
 */
public class MaxLcpsLength5 {

    public static void main(String[] args) {
        System.out.println(longestPalindromeBest("cabadabae"));
    }

    /**
     * 最长的回文子串，返回子串的content - manacher的最优解 - O(N)复杂度
     * @param s
     * @return
     */
    public static String longestPalindromeBest(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        char[] chars = insertIntoCharStr(s);

        int[] pArr = new int[chars.length];
        int pR = -1; // 右侧边界
        int index = -1;

        int res = 0;
        int maxPr = -1; // 最大值的时候右边界的位置
        int maxIndex = -1; // 最大值的时候中心点的位置
        for (int i = 0; i < chars.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < pArr.length && i - pArr[i] > -1) {
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }

            if (pArr[i] > res) {
                res = pArr[i];
                maxPr = pR;
                maxIndex = index;
            }
        }
        int half = maxPr - maxIndex - 1;
        int left = maxIndex - half;
        StringBuffer sb = new StringBuffer();
        for (int i = left; i < maxPr; i++) {
            if (chars[i] != '#') {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public static char[] insertIntoCharStr(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[2 * chars.length + 1];

        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }

    public static char[] insertIntoCharStr2(String s) {
        StringBuffer sb = new StringBuffer();
        sb.append("#");

        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        return sb.toString().toCharArray();
    }

}
