package com.hbx.play.zcytest.varchar;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

//    public static void main(String[] args) {
//        String str1 = "acaacbccd";
//
//        System.out.println(getCountStringAppear(str1));
//    }

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        char[] arr = {'1','a','2','b','3','f','4','2','h'};
//
//        System.out.println(judgeArrayEleAppearOnce(arr));
//
//    }

    /**
     * char数组里面的元素都只出现过一次，最优解空间复杂度需要O(1), 利用排序算法空间复杂度达到O(1)然后就可以完成此最优解
     * @param arr
     * @return
     */
    public static boolean judgeArrayEleAppearOnce(char[] arr) {
        if (null == arr || arr.length == 0) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(Character.valueOf(arr[i]))) {
                return false;
            } else {
                map.put(Character.valueOf(arr[i]), 1);
            }
        }
        return true;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        String[] stringArray = {null, "a", "b", "b", null, null, null, null, "d", "d"};
//
//        System.out.println(findFirstIndexOfAppearInArray(stringArray, "b"));
//
//        System.out.println(findFirstIndexOfAppearInArray(stringArray, "d"));
//    }


    /**
     * 在String的strings数组中，找到str第一次出现的位置
     * @param stringArray
     * @param str
     * @return
     */
    public static int findFirstIndexOfAppearInArray(String[] stringArray, String str) {
        if (null == stringArray || stringArray.length == 0 || StringUtils.isEmpty(str)) {
            return -1;
        }
        // 采用二分查找
        int left = 0;
        int right = stringArray.length - 1;

        int middle = 0;
        int res = -1;
        int i = 0; // 当中间值为null的时候，最右或者最左时不是null的位置

        while (left <= right) {
            middle = (left + right) / 2;
            if (stringArray[middle] != null) {
                // 判断middle和str的大小
                if (stringArray[middle].equals(str)) { // 继续往左寻找
                    res = middle;
                    right = middle - 1;
                } else if (stringArray[middle].compareTo(str) > 0) { // 在左侧
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else { // 中间的为null, 需要往左往右找
                i = middle;
                while (stringArray[i] == null && --i >= left) ; // 默认往左找，因为左边小，其实往右找也可以

                if (i < left || stringArray[i].compareTo(str) < 0) { // 说明要找的在右侧，left挪到middle + 1的位置
                    left = middle + 1;
                } else {
                    res = stringArray[i].equals(str) ? i : res;
                    right = i - 1;
                }
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        String str = "*ab*c**d";
//
//        modifyArrayEdit(str.toCharArray());
//    }

    /**
     * char数组中的*号都转移到最左侧
     * @param chas
     */
    public static void modifyArrayEdit(char[] chas) {
        if (null == chas || chas.length == 0) {
            return;
        }

        int splitIndex = chas.length - 1;
        for (int i = splitIndex; i >= 0; i--) {
            if (chas[i] != '*') {
                chas[splitIndex--] = chas[i];
            }
        }

        // 处理完非*, 开始处理*
        for (; splitIndex >= 0; splitIndex--) {
            chas[splitIndex] = '*';
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        String str = "ABCDE";
//
//        rotatePartStringByIndex(str, 3);
//
//    }

    /**
     * 整体旋转字符串 当size = 3时ABCDE -> DEABC
     * @param str
     * @param size
     */
    public static void rotatePartStringByIndex(String str, int size) {
        if (StringUtils.isEmpty(str) || size == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        reverseAllString(chars, 0, size - 1);
        reverseAllString(chars, size, chars.length - 1);
        reverseAllString(chars, 0, chars.length - 1);

        System.out.println(String.valueOf(chars));
    }

    public static void reverseAllString(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[end];

            chars[end] = chars[start];

            chars[start] = temp;

            start++;
            end--;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        String[] strs = {"1", "3", "3", "3", "2", "3", "1"};
//
//        System.out.println(getTwoStrMinLength(strs, "1", "2"));
//    }

    /**
     * 判断字符串数组中str1,str2两个之间的最小距离
     * @param strs
     * @param str1
     * @param str2
     * @return
     */
    public static int getTwoStrMinLength(String[] strs, String str1, String str2) {
        if (null == strs || strs.length == 0 || null == str1 || null == str2) {
            return -1;
        }
        if (str1.equals(str2)) {
            return 0;
        }
        // 循环判断str1的第一次的位置，以及str2的位置，两者之间的最小距离就可以
        int last1 = -1; // 记录str1最近出现的位置
        int last2 = -1; // 记录str2最近出现的位置

        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < strs.length; i++) { // 排查整个数组
            if (strs[i].equals(str1)) { // 找到了str1的位置
                minLength = Math.min(minLength, last1 == -1 ? minLength : i - last2);
                last1 = i;
            }
            if (strs[i].equals(str2)) { // 找到了str2的位置
                minLength = Math.min(minLength, last2 == -1 ? minLength : i - last1);
                last2 = i;
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    /**
     * 判断字符串数组中str1,str2两个之间的最小距离 - by map
     * @param strs
     * @param str1
     * @param str2
     * @return
     */
    public static int getTwoStrMinLengthByMap(String[] strs, String str1, String str2) {
        if (null == strs || strs.length == 0 || null == str1 || null == str2) {
            return -1;
        }
        if (str1.equals(str2)) {
            return 0;
        }
        Map<String, Map<String, Integer>> map = new HashMap<>();

        for (int i = 1; i < strs.length - 1; i++) {
            String left = strs[i - 1];
            String right = strs[i + 1];

            int leftJu = left.equals(strs[i]) ? 0 : 1;
            int rightJu = right.equals(strs[i]) ? 0 : 1;

            Map<String, Integer> lJuMap = new HashMap<>();
            lJuMap.put(left, leftJu);
            map.put(strs[i], lJuMap);

            Map<String, Integer> rJuMap = new HashMap<>();
            rJuMap.put(left, leftJu);
            map.put(strs[i], rJuMap);
        }

        if (null == map.get(str1) || null == map.get(str2)) {
            return -1;
        }
        Map contentMap = (HashMap<String, Integer>) map.get(str1);
        int res = 0;

        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        List<String> list = Stream.of("cab", "acc", "cbc", "ccc", "cac", "cbb", "aab", "abb").collect(Collectors.toList());
//
//        String start = "abc";
//        String to = "cab";
//
//        // 每次只能变换一个字母，要在list中找到start -> to的最短路径
//        List<List<String>> res = findMinStringRotatePaths(start, to, list);
//
//        for (List<String> ss : res) {
//            for (String s : ss) {
//                System.out.print(s + " -> ");
//            }
//            System.out.println(" ");
//        }
//    }

    /**
     * 寻找字符串的转换的最短路径
     * @param start
     * @param to
     * @param list
     * @return
     */
    public static List<List<String>> findMinStringRotatePaths(String start, String to, List<String> list) {
        // 先过滤参数
        if (StringUtils.isEmpty(start) || StringUtils.isEmpty(to) || list.isEmpty()) {
            return new ArrayList<>();
        }
        list.add(start); // list中把start也加进去，可以构成环状，不然无法找到对应的路径

        // 处理步骤
        // 1. 先获取start的变换一个字母的next字串在list中的map结构
        Map<String, List<String>> next = generateMinStringRotatePathNextMap(start, to, list);

        // 2. 然后处理从start出发，遍历完list，生成distance的map
        Map<String, Integer> distanceMap = generateMinStringRotatePathDistanceMap(start, next);

        // 3. 根据生成的distance map深度搜索找到最小距离
        List<List<String>> res = new ArrayList<>();
        processMinStringRotatePath(start, to, next, distanceMap, new LinkedList<>(), res);

        return res;
    }

    public static Map<String, List<String>> generateMinStringRotatePathNextMap(String start, String to, List<String> list) {
        // 在list里面找每次只变一个字母的next
        Map<String, List<String>> stringListMap = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            stringListMap.put(list.get(i), new ArrayList<>());
        }

        Set<String> dict = new HashSet<>(list);
        for (int i = 0; i < list.size(); i++) {
            stringListMap.put(list.get(i), getMinStringRotatePathNext(list.get(i), dict));
        }
        return stringListMap;
    }

    /**
     * 在list中寻找start的每个单词的下一个
     * @param word
     * @param dict
     * @return
     */
    public static List<String> getMinStringRotatePathNext(String word, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] wordChars = word.toCharArray();

        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < wordChars.length; i++) {
                if (wordChars[i] != cur) {
                    char temp = wordChars[i];

                    wordChars[i] = cur;
                    if (dict.contains(String.valueOf(wordChars))) { // list里面包含替换某一个字母后的词语
                        res.add(String.valueOf(wordChars));
                    }

                    // 再把这个位置的换回来
                    wordChars[i] = temp;
                }
            }
        }
        return res;
    }

    /**
     * start开始到达next中的每一个的路径长度
     * @param start
     * @param to
     * @param next
     * @return
     */
    public static Map<String, Integer> generateMinStringRotatePathDistanceMap(String start,
                                                                              Map<String, List<String>> next) {
        Map<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 0);

        Queue<String> queue = new LinkedList<>();
        ((LinkedList<String>) queue).add(start);

        Set<String> set = new HashSet<>();
        set.add(start);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String str : next.get(cur)) {
                if (!set.contains(str)) { // 处理过的，不再处理，防止环状出现
                    distanceMap.put(str, distanceMap.get(cur) + 1);

                    ((LinkedList<String>) queue).add(str);
                    set.add(str);
                }
            }
        }
        return distanceMap;
    }

    public static void processMinStringRotatePath(String cur, String to,
                                                  Map<String, List<String>> next,
                                                  Map<String, Integer> distance,
                                                  LinkedList<String> singleRes,
                                                  List<List<String>> res) {
        singleRes.add(cur); // 开头把start加上

        if (cur.equals(to)) {
            res.add(new LinkedList<>(singleRes));
        } else {
            for (String str : next.get(cur)) {
                if (distance.get(str) == distance.get(cur) + 1) { // 是下一个路径，继续处理
                    processMinStringRotatePath(str, to, next, distance, singleRes, res);
                }
            }
        }
        singleRes.pollLast();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

    }

    /**
     * 字符串中任意位置插入最少的字符使得原来的字符串有回文结构 - DP
     * @param str
     * @return
     */
    public static int[][] getHuiWenStringDP(String str) {
        char[] chars = str.toCharArray();

        int[][] dp = new int[chars.length][chars.length]; // DP

        for (int i = 1; i < chars.length; i++) {
            dp[i - 1][i] = chars[i] == chars[i - 1] ? 0 : 1;
            for (int j = i - 2; j >= 0; j--) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }


}
