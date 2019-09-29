package com.hbx.play.lc.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: bingxin
 * @Date: 2019-09-29 13:38
 * @Description:
 */
public class Easy {


    public int guessAnswerGame(int[] guess, int[] answer) {
        if (null == guess || null == answer) {
            return 0;
        }
        int index = guess.length > answer.length ? answer.length : guess.length;

        int suc = 0;
        for (int i = 0; i < index; i++) {
            if (answer[i] == guess[i]) {
                suc++;
            }
        }
        return suc;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        System.out.println(
                romanToInt("MCMXCIV")
        );
    }

    /**
     * 罗马数字
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        }

        Map<Character, Integer> baseMap = new HashMap<>();
        baseMap.put("I".toCharArray()[0], 1);
        baseMap.put("V".toCharArray()[0], 5);
        baseMap.put("X".toCharArray()[0], 10);
        baseMap.put("L".toCharArray()[0], 50);
        baseMap.put("C".toCharArray()[0], 100);
        baseMap.put("D".toCharArray()[0], 500);
        baseMap.put("M".toCharArray()[0], 1000);

        Map<String, Integer> sMap = new HashMap<>();
        sMap.put("IV", 4);
        sMap.put("IX", 9);
        sMap.put("XL", 40);
        sMap.put("XC", 90);
        sMap.put("CD", 400);
        sMap.put("CM", 900);

        int res = 0;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) { // 还有下一个
                String key = new String(Arrays.copyOfRange(chars, i, i + 2));
                if (sMap.containsKey(key)) {
                    res += sMap.get(key);
                    i++;
                } else {
                    if (baseMap.get(chars[i]) != null) {
                        res += baseMap.get(chars[i]);
                    }
                }
            } else {
                if (baseMap.get(chars[i]) != null) {
                    res += baseMap.get(chars[i]);
                }
            }
        }
        return res;
    }

}
