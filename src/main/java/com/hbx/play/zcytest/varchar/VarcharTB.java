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
            for (int j = i - 2; j >= 0; j--) { // 中间位置往后加一开始对比
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        System.out.println(getStringKuoHaoValidCount("()"));
//        System.out.println(getStringKuoHaoValidCount("()()"));
//        System.out.println(getStringKuoHaoValidCount("(()())"));
//        System.out.println(getStringKuoHaoValidCount("()()(()())"));
//
//        System.out.println(getStringKuoHaoValidCount("())"));
//        System.out.println(getStringKuoHaoValidCount("()("));
//        System.out.println(getStringKuoHaoValidCount("()7()"));
//        System.out.println(getStringKuoHaoValidCount("()())()"));
//    }

    /**
     * 判断字符串的括号是否是有效的
     * @param str
     * @return
     */
    public static boolean judgeStringKuoHaoValid(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        char[] chars = str.toCharArray();

        int leftParenCount = 0;
        int rightParenCount = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '(' && chars[i] != ')') {
                return false;
            }
            if (rightParenCount > leftParenCount) {
                return false;
            }
            if (chars[i] == '(') {
                leftParenCount++;
            }
            if (chars[i] == ')') {
                rightParenCount++;
            }
        }
        return leftParenCount == rightParenCount;
    }

    /**
     * 判断字符串的括号是否是有效的-进阶版本 -> 得到最大有效的长度 by dp
     * @param str
     * @return
     */
    public static int getStringKuoHaoValidCount(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        char[] chars = str.toCharArray();

        // 利用动态规划，这种凡是依赖上次或者不确定的，求最大特性的都是动态规划
        int[] dp = new int[chars.length]; // 代表从0 ～ i这段下的最大有效数量

        // 现在分析
        int res = 0;
        int pre = 0; // 成对之前的位置

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') { // 到结尾才会分析
                pre = i - dp[i - 1] - 1; // index
                if (pre >= 0 && chars[pre] == '(') { // 是成对的
                    dp[i] = dp[i - 1] + 2  // 这里是说明找到了i-1之后成对出现的两个，所以 + 2
                            + (pre > 0 ? dp[pre - 1] : 0); // 再加上pre之前可能出现的成对的数量
                }

                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        System.out.println(getZeroLeftIsOneCount(5));
//        System.out.println(getFibernacciZeroLeftIsOneCount(5));
//
//        System.out.println(getZeroLeftIsOneCount(6));
//        System.out.println(getFibernacciZeroLeftIsOneCount(6));
//
//        System.out.println(getZeroLeftIsOneCount(7));
//        System.out.println(getFibernacciZeroLeftIsOneCount(7));
//
//        System.out.println(getZeroLeftIsOneCount(8));
//        System.out.println(getFibernacciZeroLeftIsOneCount(8));
//    }

    /**
     * N个字符串的数量下，0左边必是1的数量
     * @param n
     * @return
     */
    public static int getZeroLeftIsOneCount(int n) {
        // 先分析字符串的种类和组成
        // N = 1 的时候 "1" -> 1种
        // N = 2 的时候 "10","11" -> 2种
        // N = 3 的时候 "101", "110", "111" -> 3种

        // p(i) 代表0 ～ i上的位置已经固定的情况下， i ~ N的位置上穷举的所有可能性
        // 如果p(i)的位置上是1，那么p(i+1)的数量就是i+1 ~ N的穷举数量
        // 如果p(i)的位置上是0，那么p(i+1)的位置上必须要是1，所以p(i+1)的数量就变成了p(i+2)的穷举数量
        // 所以得出结论p(i) = p(i+1) + p(i+2)

        if (n < 1) {
            return 0;
        }
        return processZeroLeftIsOneCount(1, n);
    }

    public static int processZeroLeftIsOneCount(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return processZeroLeftIsOneCount(i + 1, n) + processZeroLeftIsOneCount(i + 2, n);
    }

    /**
     * N个字符串的数量下，0左边必是1的数量 - 非递归版本
     * @param n
     * @return
     */
    public static int getFibernacciZeroLeftIsOneCount(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }
        int pre = 1;
        int sum = 1; // 求和

        int temp = 0;
        for (int i = 2; i <= n; i++) {
            temp = sum;

            sum = sum + pre;

            pre = temp;
        }
        return sum;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        String str = "abcdcdeaab";
//        System.out.println(getMaxUniqueStringCount(str));
//    }

    /**
     * 找到字符串中最长无重复的子串长度
     * @param str
     * @return
     */
    public static int getMaxUniqueStringCount(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        char[] chars = str.toCharArray();

        int[] map = new int[256];
        for (int i = 0; i < 256; i++) { // 默认记录为-1
            map[i] = -1;
        }

        int len = 0;
        int pre = -1;
        int curLength = 0;

        for (int i = 0; i < chars.length; i++) {
            pre = Math.max(pre, map[chars[i]]); // 当前char之前出现的位置

            curLength = i - pre; // 当前长度

            len = Math.max(len, curLength);

            map[chars[i]] = i; // 更新最近的一次出现的位置
        }
        return len;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        String str1 = "adabbca";
        String str2 = "acb";

        System.out.println(getString1ContainString2的MinLength(str1, str2));
    }

    /**
     * 字符串1包含字符串2的最小长度
     * @param str1
     * @param str2
     * @return
     */
    public static int getString1ContainString2的MinLength(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2) ||
                str2.length() > str1.length()) {
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int[] map = new int[256];
        for (int i = 0; i != chars2.length; i++) {
            map[chars2[i]]++; // 默认是1
        }
        int minLength = Integer.MAX_VALUE; // 最中的最小长度
        int left = 0; // 左指针
        int right = 0; // 右指针
        int match = chars2.length; // 需要match的长度

        while (right != chars1.length) {
            // right右移动
            map[chars1[right]]--; // 初始值是1， 现在找到chars1[right]对应的位置上--
            if (map[chars1[right]] >= 0) { // 说明right--有效果，match--, 还回去到str2一个字符
                match--;
            }
            // 找到了所有的匹配后，开始移动left，找到最小的窗口
            if (match == 0) {
                while (map[chars1[left]] < 0) { // 说明str1多还了一个，不需要
                    map[chars1[left++]]++; // left++开始右移，对应的map值++
                }
                minLength = Math.min(minLength, right - left + 1);
                match++; // 匹配数量++，因为str1右拿走了一个
                map[chars1[left++]]++;
            }
            right++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
