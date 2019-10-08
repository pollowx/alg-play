package com.hbx.play.zcytest.dprescur;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: bingxin
 * @Date: 2019-09-27 14:22
 * @Description:
 */
public class RescurAndDP {

//    public static void main(String[] args) {
//
//        System.out.println(fibernaci(15));
//        System.out.println(fibernaciB(15));
//    }

    /**
     * 斐波那契数列-递归
     * @param n
     * @return
     */
    public static int fibernaci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibernaci(n - 1) + fibernaci(n - 2);
    }

    /**
     * 斐波那契数列 - 保存中间变量
     * @param n
     * @return
     */
    public static int fibernaciB(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int sum = 1;

        int temp = 0;
        int pre = 1;
        for (int i = 3; i <= n; i++) {
            temp = sum;

            sum = pre + temp;

            pre = temp;
        }
        return sum;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
//
//        System.out.println(getMinPathByNormal(m));
//        System.out.println(getMinPathBest(m));
//
//    }

    /**
     * Dynamic programing 经典问题 寻找矩阵的最小路径和
     * @param m
     * @return
     */
    public static int getMinPathByNormal(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;

        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0]; // 初始化第一个元素

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }

        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }

    /**
     * Dynamic programing 经典问题 - 最优解(压缩空间算法) 寻找矩阵的最小路径和
     * @param m
     * @return
     */
    public static int getMinPathBest(int[][] m) {
        if (null == m || m.length < 1 || null == m[0] || m[0].length < 1) {
            return 0;
        }
        int less = Math.min(m.length, m[0].length);
        int more = Math.max(m.length, m[0].length);

        boolean rowMore = m.length >= m[0].length; // 到底是哪个大?

        int[] arr = new int[less]; // 辅助数组的长度是小的length，min(row, col)
        arr[0] = m[0][0];

        for (int i = 1; i < less; i++) {
            arr[i] = arr[i - 1] + (rowMore ? m[0][i] : m[i][0]); // 初始化第一组
        }

        for (int i = 1; i < more; i++) {
            arr[0] = arr[0] + (rowMore ? m[i][0] : m[0][i]);
            for (int j = 1; j < less; j++) {
                arr[j] =
                        Math.min(arr[j - 1], arr[j]) +
                                (rowMore ? m[i][j] : m[j][i]);
            }
        }
        return arr[less - 1];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        System.out.println(findRobotWalkWays(7, 4, 9, 5));
//
//        System.out.println(robotWalkByDP(7, 4, 9, 5));
//
//        System.out.println(robotWalkByDPAndZip(7, 4, 9, 5));
//
//    }

    /**
     * @param N, 一共多少位置
     * @param M, 初始位置
     * @param K, 走K步
     * @param P, 最终的位置
     * @return
     */
    public static int findRobotWalkWays(int N, int M, int K, int P) {
        if (N <= 1 || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        return robotWalk(N, M, K, P);
    }

    /**
     * 机器人走路经典问题， 1 -> 2 | N-1 <- N
     * @param N,    一共多少位置
     * @param cur,  当前的位置
     * @param rest, 剩余多少步骤
     * @param P,    最终停下来的位置
     * @return
     */
    public static int robotWalk(int N, int cur, int rest, int P) {
        // 先判断短路的条件
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }

        // 当前位置在1
        if (cur == 1) {
            return robotWalk(N, 2, rest - 1, P);
        }

        // 当前位置在N
        if (cur == N) {
            return robotWalk(N, N - 1, rest - 1, P);
        }

        // 当前位置在1 ~ N之间的位置
        return robotWalk(N, cur - 1, rest - 1, P) +
                robotWalk(N, cur + 1, rest - 1, P);
    }

    /**
     * 机器人走路经典问题-DP， 1 -> 2 | N-1 <- N
     * @param N, 一共的位置
     * @param M, 初始位置
     * @param K, K步数
     * @param P, 最终的位置
     * @return
     */
    public static int robotWalkByDP(int N, int M, int K, int P) {
        if (N <= 1 || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        // 先找到固定的规律，无后效行才能用动态规划，就是未来的决策不受历史的决定所影响
        // 找到不变的参数，N，P不变
        // 找到可变的参数，M，K是变化的(rest & K). 几个可变的参数就生成几维的数组

        int[][] dp = new int[K + 1][N + 1];
        dp[0][P] = 1; // 初始值

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j + 1];
                } else if (j == N) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][M]; // 答案可以理解成就是从当前位置逆向回去
    }

    /**
     * 机器人走路经典问题-DP & 空间压缩算法， 1 -> 2 | N-1 <- N
     * @param N
     * @param M
     * @param K
     * @param P
     * @return
     */
    public static int robotWalkByDPAndZip(int N, int M, int K, int P) {
        if (N <= 1 || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        int[] dp = new int[N + 1];
        dp[P] = 1;

        for (int i = 1; i <= K; i++) {
            int leftUp = dp[1]; // 左上角的值
            for (int j = 1; j <= N; j++) {
                int temp = dp[j];
                if (j == 1) {
                    dp[j] = dp[j + 1];
                } else if (j == N) {
                    dp[j] = leftUp;
                } else {
                    dp[j] = leftUp + dp[j + 1];
                }
                leftUp = temp;
            }
        }
        return dp[M];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        int[] arr = {5, 10, 25, 1};
//
//        System.out.println(findCoinWays(arr, 1000));
//
//        System.out.println(findCoinWaysByMap(arr, 1000));
//
//        System.out.println(findCoinWaysByDP(arr, 1000));
//
//        System.out.println(findCoinWaysByDPAndZip(arr, 1000));
//
//    }

    /**
     * 找钱的方式，arr个零钱种类，求aim的找钱方式 每张面额任意张
     * @param arr
     * @param aim
     * @return
     */
    public static int findCoinWays(int[] arr, int aim) {
        if (null == arr || arr.length < 1 || aim < 1) {
            return 0;
        }
        return findCoinWaysFeb(arr, 0, aim);
    }

    public static int findCoinWaysFeb(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            return aim == 0 ? 1 : 0; //
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += findCoinWaysFeb(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    /**
     * 找钱的方式-记忆搜索法
     * @param arr
     * @param aim
     * @return
     */
    public static int findCoinWaysByMap(int[] arr, int aim) {
        if (null == arr || arr.length < 1 || aim < 1) {
            return 0;
        }

        int[][] map = new int[arr.length + 1][aim + 1];

        return findCoinWaysFebByMap(arr, 0, aim, map);
    }

    public static int findCoinWaysFebByMap(int[] arr, int index, int aim, int[][] map) {
        int res = 0;

        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) { // 有值
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += findCoinWaysFebByMap(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    /**
     * 找钱的方式-DP
     * @param arr
     * @param aim
     * @return
     */
    public static int findCoinWaysByDP(int[] arr, int aim) {
        if (null == arr || arr.length < 1 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];

        // 第一列，dp[index][0] -> index的下标arr[index]组成aim = 0的方式
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        // 第一行，dp[0][aim] -> 用arr[0]组成aim的方式数量
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];

                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }

        return dp[arr.length - 1][aim];
    }

    /**
     * 找钱 - DP压缩算法
     * @param arr
     * @param aim
     * @return
     */
    public static int findCoinWaysByDPAndZip(int[] arr, int aim) {
        if (null == arr || arr.length < 1 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1]; // 一维数组

        // 初始化第一行
        for (int i = 0; i * arr[0] <= aim; i++) {
            dp[i * arr[0]] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) { // j是剩余的金额
                dp[j] = dp[j] +
                        ((j - arr[i] >= 0) ?
                                dp[j - arr[i]] :
                                0);
            }
        }
        return dp[aim];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        int[] arr = {5, 2, 3};
//
//        System.out.println(findCoinLeastCountWaysEnter(arr, 3));
//
//    }

    public static int findCoinLeastCountWaysEnter(int[] arr, int aim) {
        if (null == arr || arr.length == 0 || aim <= 0) {
            return -1;
        }

        return findCoinLeastCountWays(arr, 0, aim);
    }

    /**
     * 找钱 - 最少张数
     * @param arr
     * @param i
     * @param rest
     * @return
     */
    public static int findCoinLeastCountWays(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return rest == 0 ? 0 : -1;
        }

        int res = -1;
        for (int j = 0; j * arr[i] <= rest; j++) {
            int next = findCoinLeastCountWays(arr, i + 1, rest - j * arr[i]);

            if (next != -1) {
                res = res == -1 ? next + j : Math.min(res, next + j);
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
//
//        generateFinalMaxLengthChildSequence(arr, generateDPMaxLengthChildSequenceBest(arr));
//    }

    /**
     * 最大增长子序列-寻找DP数组, 时间复杂度非常高 O(N^2)
     * @param arr
     * @return
     */
    public static int[] generateDPMaxLengthChildSequence(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 比当前值小就把index的最大数组 + 1复制给当前值
                }
            }
        }
        return dp;
    }

    /**
     * 最大增长子序列-寻找DP数组, 时间复杂度 O(N * logN)
     * @param arr
     * @return
     */
    public static int[] generateDPMaxLengthChildSequenceBest(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        // ends数组，表示有效区的概念
        int[] ends = new int[arr.length];
        int[] dp = new int[arr.length]; // dp数组,0 ~ i位置最大子序列的长度

        ends[0] = arr[0]; // 初始化index = 0的元素
        dp[0] = 1;

        int endRight = 0;

        int l = 0;
        int r = 0;
        int middle = 0;

        for (int i = 0; i < arr.length; i++) {
            l = 0;
            r = endRight;

            while (l <= r) {
                middle = (l + r) / 2;

                if (ends[middle] < arr[i]) { //右侧移动
                    l = middle + 1;
                } else {
                    r = middle - 1;
                }
            }
            endRight = Math.max(endRight, l); // 新的有效元素出现
            ends[l] = arr[i]; // l是ends的小的一侧，把当前的元素加入加入有效区
            dp[i] = l + 1;
        }
        return dp;
    }

    /**
     * 最大增长子序列，生成序列
     * @param arr
     * @param dp
     * @return
     */
    public static int[] generateFinalMaxLengthChildSequence(int[] arr, int[] dp) {
        if (null == arr || arr.length == 0 || null == dp || dp.length == 0) {
            return null;
        }
        // 找到dp中的最大值的index位置和dp的长度
        int index = 0;
        int length = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > length) {
                index = i;
                length = dp[i];
            }
        }

        // 给最后一个赋值
        int[] res = new int[length];
        res[--length] = arr[index];

        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                res[--length] = arr[i];
                index = i;
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        String str1 = "1A2C3D4B56";
//        String str2 = "B1D23CA45B6A";
//
//        System.out.println(generateMaxLengthSequenceString(str1, str2));
//    }

    /**
     * 最大公共子序列 - DP
     * @param str1
     * @param str2
     * @return
     */
    public static String generateMaxLengthSequenceString(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return "";
        }

        char[] charsStr1 = str1.toCharArray();
        char[] charsStr2 = str2.toCharArray();

        // 生成dp数组
        int[][] dp = getMaxLengthSequenceDP(charsStr1, charsStr2);

        int m = charsStr1.length - 1;
        int n = charsStr2.length - 1;
        char[] res = new char[dp[m][n]];

        int index = res.length - 1; // 最大子序列的长度多少

        // 逆向解析DP数组，生成字符串
        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) { // 上面来的
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {  // 左边来的
                m--;
            } else {
                res[index--] = charsStr1[m]; // 或者charsStr2[n] // 来自左上角
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static int[][] getMaxLengthSequenceDP(char[] str1, char[] str2) {
        if (null == str1 || null == str2) {
            return null;
        }
        int[][] dp = new int[str1.length][str2.length]; // 动态规划二纬表, 表示从0到i和从0到j的最大子序列的长度
        dp[0][0] = str1[0] == str2[0] ? 1 : 0; // 赋值第一个

        // 第一行
        for (int i = 1; i < str1.length; i++) {
            int currentMax = str1[i] == str2[0] ? 1 : 0;
            dp[i][0] = Math.max(currentMax, dp[i - 1][0]);
        }

        // 第一列
        for (int j = 1; j < str2.length; j++) {
            int currentMax = str1[0] == str2[j] ? 1 : 0;
            dp[0][j] = Math.max(currentMax, dp[0][j - 1]);
        }

        // 除此之外的dp[i][j]，可能来自3个位置, Max(1, 2, 3)的一个
        // 1. dp[i-1][j]
        // 2. dp[i][j-1]
        // 3. dp[i-1][j-1] + 1
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }
        return dp;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        String str1 = "1AB2345CD";
//        String str2 = "12345EF";
//
//        System.out.println(generateMaxLengthChildString(str1, str2));
//
//        System.out.println(generateMaxLengthChildStringBest(str1, str2));

//        int[][] array = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
//        int row = 0;
//        int col = array[0].length - 1;
//
//        while (row < array.length) {
//            int i = row;
//            int j = col;
//
//            int eleCount = 0;
//            while (i < array.length && j < array[0].length) {
//                for (int k = 0; k < eleCount; k++) {
//                    System.out.print(" ↘ ");
//                }
//                System.out.print(array[i][j]);
//                System.out.println("");
//                i++;
//                j++;
//                eleCount++;
//            }
//            System.out.println("-------------------------------");
//
//            if (col > 0) {
//                col--;
//            } else {
//                row++;
//            }
//        }

//    }

    /**
     * 最长公共子串 - DP 时间复杂度O(M * N), 空间复杂度O(M * N)
     * @param str1
     * @param str2
     * @return
     */
    public static String generateMaxLengthChildString(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return null;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int[][] dp = generateMaxLengthChildStringDP(chars1, chars2);
        if (null == dp) {
            return null;
        }

        int endRowIndex = 0;
        int maxLength = 0;

        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars2.length; j++) {
                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j];
                    endRowIndex = i;
                }
            }
        }
        return str1.substring(endRowIndex - maxLength + 1, endRowIndex + 1);
    }

    public static int[][] generateMaxLengthChildStringDP(char[] chars1, char[] chars2) {
        if (null == chars1 || null == chars2) {
            return null;
        }
        int[][] dp = new int[chars1.length][chars2.length];
        // 第一行
        for (int j = 0; j < chars2.length; j++) {
            dp[0][j] = chars1[0] == chars2[j] ? 1 : 0;
        }

        // 第一列
        for (int i = 0; i < chars1.length; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : 0;
        }

        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                dp[i][j] = chars1[i] == chars2[j] ?
                        dp[i - 1][j - 1] + 1 :
                        0;
            }
        }
        return dp;
    }

    /**
     * 最长公共子串 - DP 时间复杂度O(M * N), 空间复杂度O(1)
     * @param str1
     * @param str2
     * @return
     */
    public static String generateMaxLengthChildStringBest(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return null;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int maxLength = 0; // 最大长度
        int endRowIndex = 0; // 找到最大长度时的行index是多少

        int row = 0;
        int col = chars2.length - 1;
        while (row < chars1.length) {
            int i = row;
            int j = col;
            int len = 0;

            while (i < chars1.length && j < chars2.length) {
                // 条件
                if (chars1[i] != chars2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                if (len > maxLength) {
                    maxLength = len;
                    endRowIndex = i;
                }
                i++;
                j++;
            }

            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return str1.substring(endRowIndex - maxLength + 1, endRowIndex + 1);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 9, 0, 7, 0, 2, 1, 3};
        System.out.println(arrayMostEOR(arr));
    }

    /**
     * 最多子数组异或的划分
     * @param arr
     * @return
     */
    public static int arrayMostEOR(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        // 分析，生成dp[]数组，dp[i]代表i之前的0 ～ i元素划分成的最大数组数量
        // dp[i]的情况有两个:
        // A. array[i]异或值不等于0，那么dp[i] = dp[i-1]
        // B. array[i]异或值等于0，那么在0~i之间假设存在k, dp[i] = dp[k-1] + 1, k-1的位置是上一次使得异或和为0的位置，那么找到k的位置就可以找到所有元素
        int[] dp = new int[arr.length];
        dp[0] = arr[0] == 0 ? 1 : 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        map.put(arr[0], 0); // value -> arr的index

        int eor = 0;
        for (int i = 1; i < arr.length; i++) {
            eor = eor ^ arr[i];

            if (map.containsKey(eor)) {
                int preEorIndex = map.get(eor);
                dp[i] = preEorIndex == -1 ? 1 : (dp[preEorIndex] + 1);
            }
            dp[i] = Math.max(dp[i - 1], dp[i]);
            map.put(eor, i);
        }

        return dp[arr.length - 1];
    }

}
