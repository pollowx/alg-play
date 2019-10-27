package com.hbx.play.zcytest.other;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: bingxin
 * @Date: 2019-10-22 16:44
 * @Description:
 */
public class OtherPot {

//    public static void main(String[] args) {
//        int count = 0;
//        for (int i = 0; i < 10000; i++) {
//            if (getRadom7ByRandom5() == 1) {
//                count++;
//            }
//        }
//        System.out.println(count);
//        System.out.println((double) count / 10000);
//        System.out.println(1.0 / 7);
//    }

    /**
     * 产生1 - 7的随机数
     * @return
     */
    public static int getRadom7ByRandom5() {
        int num = 0;
        do {
            num = (rand1To5() - 1) * 5 + rand1To5() - 1;
        } while (num > 20);
        return num % 7 + 1;
    }

    public static int rand1To5() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 产生 1 - 10的数根据1-7 https://leetcode-cn.com/problems/implement-rand10-using-rand7/submissions/
     * @return
     */
    public static int getRadom10ByRandom7() {
        int num = 0;
        do {
            num = (getRadom7ByRandom5() - 1) * 7 + (getRadom7ByRandom5() - 1); // (0 ~ 49)
        } while (num >= 39);
        return num % 10 + 1;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int rand01p() {
        double p = 0.91; // 可以随意改

        return Math.random() < p ? 0 : 1;
    }

    public static int rand01() {
        int num = 0;
        do {
            num = rand01p();
        } while (num == rand01p());

        return num; // 随机产生0或者1
    }

    public static int rand0To3() {
        return 2 * rand01() + rand01();
    }

    /**
     * 生成1-6之间的随机数利用给定的概率函数
     * @return
     */
    public static int rand1To6() {
        int num = 0;
        do {
            num = rand0To3() * 4 + rand0To3();
        } while (num >= 12);
        // 现在num 取值在0 ~ 11之间
        return num % 6 + 1;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        System.out.println(getTwoNumberMax公约数(12, 10));
//    }

    /**
     * 求两个数的最大公约数
     * @param n
     * @param m
     * @return
     */
    public static int getTwoNumberMax公约数(int m, int n) {
        // 采用辗转相除法
        return n == 0 ? m : getTwoNumberMax公约数(n, m % n);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        System.out.println(getNum的阶乘尾巴上有多少个0(20));
//        System.out.println(getNum的阶乘尾巴上有多少个0Best(20));
//    }

    /**
     * O(N*logN)
     * @param num
     * @return
     */
    public static int getNum的阶乘尾巴上有多少个0(int num) {
        if (num <= 0) {
            return 0;
        }
        int res = 0;
        int cur = 0;

        for (int i = 5; i < num + 1; i += 5) {
            cur = i;
            while (cur % 5 == 0) {
                res++;
                cur = cur / 5;
            }
        }
        return res;
    }

    public static int getNum的阶乘尾巴上有多少个0Best(int num) {
        // 思路，其实这一题是关于数论的，要看尾巴上有多少个0的话，应该分析，0是怎么产生的
        // 5! = 1 * 2 * 3 * 4 * 5 = 120 一个0
        // 可以看出来0是由2*5得到的，5!里面有3个2，一个5
        // 10! = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 = 3628800 2个0
        // 这里面有7个2，两个5，所以看出来，末尾的0是和5的个数密切相关的
        // 那么n!的5的个数就是答案，1 *...* 5 * ... * 10 * ... * 15 * ... * 20 * ... * 25 * ... * 30 * ...
        // 1 ~ 5, 6 ~ 10, 11 ~ 15, 16 ~ 20, 21 ~ 25 每5个数都有一个5，但是25特殊可以分成2个5
        // 超过了25之后，25 ～ 50 里面可以分成5的因子的数是25,30,35,40,45,50
        // 所以能产生5的因子的数是N/5 + N/25 + N/125 + ... + N/5^i (5^i <= N)
        if (num <= 0) {
            return 0;
        }
        int res = 0;
        while (num != 0) {
            res = res + num / 5;
            num = num / 5;
        }
        return res;
    }

    /**
     * @param num
     * @return
     */
    public static int calN阶乘二进制上末尾0的个数(int num) {
        if (num < 1) {
            return -1;
        }
        int res = 0;
        while (num != 0) {
            res += num / 2;
            num /= 2;
        }
        return res;
    }

    /**
     * @param num
     * @return
     */
    public static int calN阶乘二进制上末尾0的个数B(int num) {
        // Z = 2的因子总个数, m是N的二进制上1的个数, 有Z = N - m
        // N = 6, N! = 720, m = 2 所以Z = 6 - 2 = 4
        if (num < 1) {
            return -1;
        }
        int temp = num;
        int res = 0;
        while (num != 0) {
            res += (num & 1) == 1 ? 1 : 0;
            num = num >>> 1;
        }
        return temp - res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        printFlods(1, 2, true);
//    }

    /**
     * 折纸的打印上下折痕 - 二叉树的反中序遍历
     * @param i
     * @param n
     * @param down
     */
    public static void printFlods(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        printFlods(i + 1, n, true);
        System.out.print(down ? " 下 " : " 上 ");
        printFlods(i + 1, n, false);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[][] martrix = {
//                {1, 1, 3, 3},
//                {3, 2, 4, 3},
//                {3, 2, 4, 4},
//                {1, 3, 2, 4},
//                {2, 3, 3, 4},
//        };
//        System.out.println(judgePerfectRectangle(martrix));
//
//        int[][] martrix1 = {
//                {1, 1, 3, 3},
//                {3, 1, 4, 2},
//                {3, 2, 4, 4},
//                {1, 3, 2, 4},
//                {2, 3, 3, 4},
//        };
//        System.out.println(judgePerfectRectangle(martrix1));
//    }

    /**
     * 判断是否是完美矩形
     * @param maxrtrix
     * @return
     */
    public static boolean judgePerfectRectangle(int[][] maxrtrix) {
        if (null == maxrtrix || maxrtrix.length == 0 || null == maxrtrix[0] || maxrtrix[0].length == 0) {
            return false;
        }

        int mostLeft = Integer.MAX_VALUE;
        int mostRight = Integer.MIN_VALUE;
        int mostDown = Integer.MAX_VALUE;
        int mostUp = Integer.MIN_VALUE;

        int area = 0; // 每一个矩形可以组成的面积

        Set<String> set = Sets.newHashSet();
        for (int[] arr : maxrtrix) { // 每一行拿出来处理
            mostLeft = Math.min(mostLeft, arr[0]);
            mostRight = Math.max(mostRight, arr[2]);
            mostDown = Math.min(mostDown, arr[1]);
            mostUp = Math.max(mostUp, arr[3]);

            area += (arr[2] - arr[0]) * (arr[3] - arr[1]);

            String leftDown = arr[0] + "_" + arr[1];
            String leftUp = arr[0] + "_" + arr[3];
            String rightDown = arr[2] + "_" + arr[1];
            String rightUp = arr[2] + "_" + arr[3];

            if (!set.add(leftDown)) { // 没加入成功，可能已经存在了
                set.remove(leftDown);
            }
            if (!set.add(leftUp)) { // 没加入成功，可能已经存在了
                set.remove(leftUp);
            }
            if (!set.add(rightDown)) { // 没加入成功，可能已经存在了
                set.remove(rightDown);
            }
            if (!set.add(rightUp)) { // 没加入成功，可能已经存在了
                set.remove(rightUp);
            }
        }

        if (set.size() != 4 || !set.contains(mostLeft + "_" + mostDown) ||
                !set.contains(mostLeft + "_" + mostUp) ||
                !set.contains(mostRight + "_" + mostDown) ||
                !set.contains(mostRight + "_" + mostUp)) { // 到这里小矩形的每个顶点已经都remove了，剩下的只有大矩形的四个角坐标
            return false;
        }
        return area == (mostRight - mostLeft) * (mostUp - mostDown);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int randKByMax(int max) {
        return (int) (Math.random() * max) + 1; // 产生 1 ~ max之间的值
    }

    /**
     * 蓄水池算法 & 抽样算法 & 等概率算法
     * @param k
     * @param max
     * @return
     */
    public static int[] getKNumEqualRand(int k, int max) {
        if (k <= 0 || max <= 0) {
            return null;
        }

        int[] res = new int[Math.min(k, max)];

        // 前k个数据直接放入res
        for (int i = 0; i < k; i++) {
            res[i] = i + 1;
        }

        // 从第k+1个数据开始，用等概率扔掉一个res中的值，并且把当前值加入进来
        for (int i = k + 1; i <= max; i++) {
            if (randKByMax(i) <= k) { // 同一种概率下，准备放入res
                res[randKByMax(k) - 1] = i; // res中随机扔掉一个，换成i
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {2, 7, 3, 1, 1};
//
//        System.out.println(getArraySplitAbsValue(arr));
//        System.out.println(getArraySplitAbsValueA(arr));
//        System.out.println(getArraySplitAbsValueBest(arr));
//
//        int[] arr1 = {2, 6, 3, 1, 1, 7};
//
//        System.out.println(getArraySplitAbsValue(arr1));
//        System.out.println(getArraySplitAbsValueA(arr1));
//        System.out.println(getArraySplitAbsValueBest(arr1));
//    }

    /**
     * 找到数组的划分方式使得左侧最大值和右侧最大值的差最大 - 普通方式O(N^2) + O(1)
     * @param arr
     * @return
     */
    public static int getArraySplitAbsValue(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;

            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, arr[j]);
            }

            for (int k = i + 1; k < arr.length; k++) {
                rightMax = Math.max(rightMax, arr[k]);
            }

            res = Math.max(res, Math.abs(rightMax - leftMax));
        }
        return res;
    }


    /**
     * 找到数组的划分方式使得左侧最大值和右侧最大值的差最大 - 辅助数组O(N) + O(N)
     * @param arr
     * @return
     */
    public static int getArraySplitAbsValueA(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int[] leftMaxs = new int[arr.length];
        int[] rightMaxs = new int[arr.length];

        leftMaxs[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            leftMaxs[i] = Math.max(leftMaxs[i - 1], arr[i]);
        }

        rightMaxs[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i]);
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = Math.max(res, Math.abs(rightMaxs[i] - leftMaxs[i]));
        }
        return res;
    }

    /**
     * 找到数组的划分方式使得左侧最大值和右侧最大值的差最大 - 最优解O(N) + O(1)
     * @param arr
     * @return
     */
    public static int getArraySplitAbsValueBest(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }

        int allMax = 0;
        for (int i = 0; i < arr.length; i++) {
            allMax = Math.max(allMax, arr[i]);
        }
        return allMax - Math.min(arr[0], arr[arr.length - 1]);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 调整[0, X)区间上的数出现的概率
     * @param k
     * @return
     */
    public static double randXPowerK(int k) {
        // 分析[0, x)上出现的概率，下一次如果k^2需要< x那么下一次出现的数字仍然要在[0, x)区间上
        if (k <= 0) {
            return 0;
        }

        double res = 0;
        for (int i = 1; i <= k; i++) {
            res = Math.max(res, Math.random());
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {9, 1, 4, 9, 0, 4, 8, 9, 0, 1};
//
//        findStaticsticsArrayPath(arr);
//
//        for (int a : arr) {
//            System.out.print(a + "\t");
//        }
//    }

    /**
     * 路径数组变为统计数组
     * @param arr
     * @return
     */
    public static void findStaticsticsArrayPath(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        // 把原始数组变成距离数组
        modifyDistanceArrayPath(arr);

        // 把距离数组变成统计数组，统计每个距离的数量
        modifyStaticsticsArrayPath(arr);
    }

    public static void modifyDistanceArrayPath(int[] arr) {
        // 先从第一个元素出发，用循环推来找到距离首都的距离
        int nextIndex = 0;
        int preIndex = 0;

        int capIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i) { // 首都
                capIndex = i;
                continue;
            }

            if (arr[i] < 0) { // 过滤处理过的
                continue;
            }
            preIndex = i;
            nextIndex = arr[i];

            // 先把当前位置设置为-1
            arr[i] = -1;

            int current = -1;
            while (nextIndex >= 0 && arr[nextIndex] >= 0 && nextIndex != arr[nextIndex]) { // 有值，继续循环推, 或者到首都终止
                current = arr[nextIndex]; // 当前的value;
                arr[nextIndex] = preIndex;
                preIndex = nextIndex;
                nextIndex = current;
            }

            int temp = -1;
            int reverse = arr[nextIndex] < 0 ? arr[nextIndex] : 0;
            while (arr[preIndex] >= 0) { // 正向推完了一轮，开始逆向赋值
                temp = arr[preIndex]; // 回跳的值
                arr[preIndex] = --reverse;
                preIndex = temp;
            }
            if (preIndex == i) { // 回到之前的城市
                arr[i] = --reverse;
            }
        }
        arr[capIndex] = 0;
    }

    public static void modifyStaticsticsArrayPath(int[] arr) {
        int nextIndex = 0;
        // 从第一个元素出发，来统计每个距离的长度
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) { // 已经统计过的城市
                continue;
            }
            nextIndex = Math.abs(arr[i]); // 下一个index

            int temp = 0;
            while (arr[nextIndex] <= 0) { // 说明还没统计过
                temp = Math.abs(arr[nextIndex]);
                arr[nextIndex] = 1;
                nextIndex = temp;
            }

            // 跳到这里说明，1. while处理完到了arr[index]的正值上，++
            // 2. while跳过了，直接到了这里，直接++，并且把arr[i]置0

            if (arr[nextIndex] > 0) {
                arr[nextIndex]++;
                arr[i] = 0;
            }
        }
        arr[0] = 1; // 首都只有一个
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {3, 2, 5};
//
//        System.out.println(findMinCannotAddToSumNormal(arr));
//
//        System.out.println(findMinCannotAddToSumByDp(arr));
//
//        int[] arr1 = {3, 8, 1, 2};
//        System.out.println(findMinCannotAddToSumByContainOneBest(arr1));
//    }

    /**
     * 正数数组中的最小不可组成和 - O(2^N) + O(N)
     * @param arr
     * @return
     */
    public static int findMinCannotAddToSumNormal(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 1;
        }
        // 把所有子集的和加入到set
        Set<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);

        // 找到数组中的最小值
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        for (int i = min; i < Integer.MAX_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    public static void process(int[] arr, int i, int sum, Set<Integer> set) {
        if (i == arr.length) {
            set.add(sum);
            return;
        }
        process(arr, i + 1, sum, set);

        process(arr, i + 1, sum + arr[i], set);
    }

    /**
     * 正数数组中的最小不可组成和 - DP O(N * sum) + O(N)
     * @param arr
     * @return
     */
    public static int findMinCannotAddToSumByDp(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 1;
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        boolean[] dp = new boolean[sum + 1]; // boolean 的index代表，该数字能被arr的子集相加得到
        dp[0] = true;

        // 如果arr[0 ~ i]的子集相加可以得到k
        // 那么arr[0 ~ i]的子集必然可以得到k + arr[i+1]
        // 可以这样解释，即每个元素对应的位置肯定是可以得到的，然后剩下的就是元素累加对应的index的赋值，
        // 那么总和 - 当前对应的index就是剩下可以组成的另外半部分的和，可以一次求出来得到index
        for (int i = 0; i < arr.length; i++) {
            for (int j = sum; j >= arr[i]; j--) {
                dp[j] = dp[j - arr[i]] ? true : dp[j];
            }
        }

        for (int i = min; i < dp.length; i++) {
            if (!dp[i]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 正数数组中(肯定含有1)的最小不可组成和
     * @param arr
     * @return
     */
    public static int findMinCannotAddToSumByContainOneBest(int[] arr) {
        // 思路，肯定含有1，代表每个间隔都能加上1，和上一个元素和的比较增加1，排好序后得到扩大到每个元素的有没有空缺
        if (null == arr || arr.length == 0) {
            return 1;
        }
        Arrays.sort(arr); // 排序

        int range = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= range + 1) {
                range += arr[i];
            } else { // arr[i] > range + 1 说明缺了这个空隙
                return range + 1;
            }
        }
        return range + 1;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 7};
//        System.out.println(needMinCountMakeupToRange(arr, 15));
//
//        int[] arrA = {1, 5, 7};
//        System.out.println(needMinCountMakeupToRange(arrA, 15));
//
//        int[] arrB = {3, 17, 21, 78};
//        System.out.println(needMinCountMakeupToRange(arrB, 67));
//    }

    /**
     * 累加出整个范围的所有数最少还需要几个数
     * @param arr
     * @param range
     * @return
     */
    public static int needMinCountMakeupToRange(int[] arr, int range) {
        if (null == arr || arr.length == 0 || range < 0) {
            return 0;
        }

        int touch = 0; // 初始值
        int resCount = 0; // 所需要的数量

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] - touch <= 1) { // 说明累加值可以直接扩大, 之所以单独写这个if，就是想按类分析单个情况
                touch += arr[i];
            } else { // > 1的时候说明touch的值距离arr[i]还不够，那么开始处理
                // 先拿到范围即(1 ~ arr[i] - 1)
                int currentRange = arr[i] - 1;
                while (touch < currentRange) {
                    touch = touch + (touch + 1);
                    resCount++;
                    if (touch > range) {
                        return resCount;
                    }
                }
                touch += arr[i];
            }
        }

        // 遍历完了数组，那么还是不到range
        while (touch < range) {
            touch = touch + (touch + 1);
            resCount++;
        }
        return resCount;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        System.out.println(getOneAppearTimeNoramal(114));
//
//        System.out.println(getOneAppearTimeBest(211));
//
//    }

    /**
     * 1~N中1出现的次数
     * @param num
     * @return
     */
    public static int getOneAppearTimeNoramal(int num) {
        if (num <= 0) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i <= num; i++) {
            count += getOneNumCount(i);
        }
        return count;
    }

    public static int getOneNumCount(int num) {
        int res = 0;
        while (num != 0) {
            if (num % 10 == 1) {
                res++;
            }
            num /= 10;
        }
        return res;
    }

    /**
     * 1~N中1出现的次数 - Best
     * @param num
     * @return
     */
    public static int getOneAppearTimeBest(int num) {
        if (num <= 0) {
            return 0;
        }
        int length = getLengthOfNum(num);
        if (length == 1) {
            return 1;
        }
        int temp = powerOf10(length - 1);
        int first = num / temp;

        int firstOneCount = first == 1 ? num % temp + 1 : temp; // 首位是1的话，加上1
        int otherOneCount = first * (length - 1) * (temp / 10);

        return firstOneCount + otherOneCount + getOneAppearTimeBest(num % temp);
    }

    public static int getLengthOfNum(int num) {
        int length = 0;
        while (num != 0) {
            length++;
            num /= 10;
        }
        return length;
    }

    public static int powerOf10(int base) {
        return (int) (Math.pow(10, base));
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        //System.out.println(Math.random() * 3 + 1); // (0 ~ 2) -> (1 ~ 3)
//
//        int[] arr = {1, 2, 3, 7};
//
//        for (int i = 0; i < 20; i++) {
//            printMCountFromArrayOfNLength(arr, 3);
//            System.out.println();
//        }
//    }

    /**
     * 等概率打印N长度的数组中的M个数
     * @param arr
     * @param m
     */
    public static void printMCountFromArrayOfNLength(int[] arr, int m) {
        if (null == arr || arr.length == 0 || m > arr.length) {
            return;
        }
        int length = arr.length;

        while (m > 0) {
            int index = (int) (Math.random() * length); // 随机找一个，然后打印

            System.out.print(arr[index] + " ");

            swapTwoEle(arr, index, length - 1);

            m--;
            length--;
        }
    }

    public static void swapTwoEle(int[] arr, int indexA, int indexB) {
        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        System.out.println(judgeOneNumberPalindrome(12121));
//        System.out.println(judgeOneNumberPalindrome(-145676541));
//        System.out.println(judgeOneNumberPalindrome(-890198));
//        System.out.println(judgeOneNumberPalindrome(8910198));
//        System.out.println(judgeOneNumberPalindrome(-22));
//
//        System.out.println(judgeOneNumberPalindrome(2147483647));
//    }

    /**
     * 判断一个数是否是回文数字
     * @param n
     * @return
     */
    public static boolean judgeOneNumberPalindrome(int n) {
        if (n == Integer.MIN_VALUE) {
            return false;
        }
        n = Math.abs(n); // 都变成正值
        if (n <= 10) {
            return false;
        }
        // 思路是次从这个数字的左右两侧拿出一个数，比对两个数字是否一致

        int length = getLengthOfNum(n);

        int left = 0;
        int right = 0;

        while (length > 1) { // 一位数直接跳过
            int equal10Value = powerOf10(length - 1);

            left = n / equal10Value;
            right = n % 10;
            if (left != right) { // 左侧 != 右侧
                return false;
            }
            length = length - 2; // 两位数干掉

            n = (n % equal10Value) / 10;
        }
        return true; // 到最后是回文数字
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] middle = new int[]{7, 8, 1, 2, 3, 4, 5, 6};
//        System.out.println(getMinOfRotateArray(middle));
//
//
//        int[] having = new int[]{1, 2, 0, 1, 1, 1, 1, 1, 1, 1};
//        System.out.println(getMinOfRotateArray(having));
//
//    }

    /**
     * 得到旋转数组中最小值，数组中可能有重复元素
     * @param arr
     * @return
     */
    public static int getMinOfRotateArray(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        int middle = 0;

        while (low < high) {
            if (low == high - 1) {
                break;
            }
            if (arr[low] < arr[high]) {
                return arr[low];
            }
            middle = (low + high) / 2;

            if (arr[low] > arr[middle]) { // 说明最小值在这个里面
                high = middle;
                continue;
            }
            if (arr[middle] > arr[high]) {
                low = middle;
                continue;
            }

            // 走到这里触发这个条件 arr[low] == arr[middle] == arr[high]
            while (low < middle) { // 在low和middle中间找
                if (arr[low] == arr[middle]) {
                    low++;
                } else if (arr[low] < arr[middle]) {
                    return arr[low];
                } else {
                    high = middle;
                    break;
                }
            }
        }
        return Math.min(arr[low], arr[high]);
    }











}
