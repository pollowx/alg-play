package com.hbx.play.daily;


public class CountSubstrings647 {

    public static void main(String[] args) {

        System.out.println(countSubstrings("aaa"));

    }

    public static int countSubstrings(String str) {
        if (null == str || str.length() <= 0) {
            return 0;
        }
        char[] strCharArray = transientCharArray(str);
        int[] maxCharLcp = new int[strCharArray.length];
        int answer = 0;

        int index = -1;
        int pR = -1;
        for (int i = 0; i < strCharArray.length; i++) {
            maxCharLcp[i] = pR > i ? Math.min(maxCharLcp[2 * index - i], pR - i) : 1;
            while (i + maxCharLcp[i] < strCharArray.length && i - maxCharLcp[i] > -1) {
                if (strCharArray[i + maxCharLcp[i]] == strCharArray[i - maxCharLcp[i]]) {
                    maxCharLcp[i]++;
                } else {
                    break;
                }
            }
            if (i + maxCharLcp[i] > pR) {
                pR = i + maxCharLcp[i];
                index = i;
            }
            answer += maxCharLcp[i] / 2;
        }
        return answer;
    }

    public static char[] transientCharArray(String string) {
        StringBuffer sb = new StringBuffer("#");
        for (int i = 0; i < string.length(); i++) {
            sb.append(string.charAt(i));
            sb.append("#");
        }
        return sb.toString().toCharArray();
    }

}
