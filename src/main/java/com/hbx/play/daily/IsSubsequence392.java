package com.hbx.play.daily;

public class IsSubsequence392 {

    // 给定s，check s是否是t的子序列. s = "abc", t = "ahbgdc", -> true
    public static boolean isSubsequence(String s, String t) {
        if (s.length() <= 0 || s.equals(t)) {
            return true;
        }
        // 双指针来找t中符合s的位置
        int sLeft = 0, tLeft = 0;
        while (sLeft < s.length() && tLeft < t.length()) {
            if (s.charAt(sLeft) == t.charAt(tLeft)) {
                sLeft++;
            }
            tLeft++;
        }
        return sLeft == s.length();
    }

    public static void main(String[] args) {
        String s = "afc", t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }
}
