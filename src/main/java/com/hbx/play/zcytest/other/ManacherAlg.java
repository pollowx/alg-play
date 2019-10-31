package com.hbx.play.zcytest.other;

import org.springframework.util.StringUtils;

/**
 * @Auther: bingxin
 * @Date: 2019-10-29 20:16
 * @Description:
 */
public class ManacherAlg {

    public static void main(String[] args) {

        System.out.println(getVarcharMaxLcpsLength("cabadabae"));

    }

    /**
     * 字符串的最大回文长度
     * @param str
     * @return
     */
    public static int getVarcharMaxLcpsLength(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        // 先插针在字符串中插入特殊字符，过滤掉偶数长度
        char[] chars = insertCharIntoString(str).toCharArray();

        int[] charsEveryIndexMaxLcp = new int[chars.length]; // 保存chars中的每个i的最大回文长度

        int index = -1; // 中心点对称轴, 每次更新pR的时候更新index
        int pR = -1; // 能到达的右边界index，对称轴 + 对称数量 + 1
        int maxRes = Integer.MIN_VALUE; // 最大的回文长度
        for (int i = 0; i < chars.length; i++) {
            charsEveryIndexMaxLcp[i] = pR > i // pR > i说明是存在有回文的段，那么需要check index的上一个对称点的lcp值, i在PR内部
                    ? Math.min(
                    charsEveryIndexMaxLcp[2 * index - i], // 上一个对称点index
                    pR - i) // pR - i是上一个对称的中心index
                    : 1; // 1代表回文长度是1
            while (i + charsEveryIndexMaxLcp[i] < chars.length && i - charsEveryIndexMaxLcp[i] > -1) {
                if (chars[i + charsEveryIndexMaxLcp[i]] == chars[i - charsEveryIndexMaxLcp[i]]) { // 两边的字符是不是相等
                    charsEveryIndexMaxLcp[i]++;
                } else {
                    break;
                }
            }
            if (i + charsEveryIndexMaxLcp[i] > pR) {
                pR = i + charsEveryIndexMaxLcp[i];
                index = i;
            }
            maxRes = Math.max(maxRes, charsEveryIndexMaxLcp[i]);
        }
        return maxRes - 1;
    }

    public static String insertCharIntoString(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append("#");

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            sb.append("#");
        }
        return sb.toString();
    }

}
