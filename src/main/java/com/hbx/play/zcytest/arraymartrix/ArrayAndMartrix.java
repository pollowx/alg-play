package com.hbx.play.zcytest.arraymartrix;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: bingxin
 * @Date: 2019-09-19 15:41
 * @Description:
 */
public class ArrayAndMartrix {

    /**
     * 数组值相加等于k的最大子数组长度
     * @param arr
     * @param k
     * @return
     */
    public int findMaxLengthThatElePulsEqualK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                Integer index = map.get(sum - k);
                if (null != index) { // 找到了
                    len = Math.max(i - index, len);
                }
            } else {
                map.put(sum, i);
            }
        }
        return len;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
//
//        printMartrix(array);
//    }

    /**
     * 打印旋转矩阵
     * @param martrix
     */
    public static void printMartrix(int[][] martrix) {
        if (null == martrix || martrix.length == 0 ||
                null == martrix[0] || martrix[0].length == 0) {
            return;
        }
        // 按照交换的顺序来打印，行转成列

        List<Integer> hpList = Lists.newArrayList();
        int row = martrix.length; // 二维数组的行数

        while (row != 0) {
            for (int i = 0; i < martrix[0].length; i++) {
                hpList.add(martrix[0][i]);
            }

            martrix = rotateMartrix(martrix);
            if (null == martrix) {
                break;
            }
            row = martrix.length;

            for (Integer i : hpList) {
                System.out.print(i + "\t");
            }
            System.out.println("");
            hpList.clear();
        }
    }

    /**
     * 旋转
     * @param martrix
     * @return
     */
    public static int[][] rotateMartrix(int[][] martrix) {
        if (null == martrix) {
            return null;
        }

        int row = martrix.length;
        int col = martrix[0].length;

        int[][] res = new int[col][row - 1];

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row - 1; j++) {
                res[i][j] = martrix[j + 1][col - 1 - i];
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
//
//        print之字型martrix(array);
//    }

    public static void print之字型martrix(int[][] martrix) {
        if (null == martrix || martrix.length == 0 ||
                null == martrix[0] || martrix[0].length == 0) {
            return;
        }
        int endR = martrix.length - 1;
        int endC = martrix[0].length - 1;

        int tR = 0;
        int tC = 0;

        int dR = 0;
        int dC = 0;

        boolean printDirection = false; // bottomToUp

        while (tR != endR + 1) {
            print之字型martrixLevel(
                    martrix,
                    tR, tC,
                    dR, dC, printDirection);

            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;

            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;

            printDirection = !printDirection;
        }
        System.out.println();
    }

    public static void print之字型martrixLevel(int[][] m, int uLR, int uLC, int bLR, int bLC, boolean direction) {
        if (direction) {
            while (uLR != bLR + 1) {
                System.out.print(m[uLR++][uLC--] + " ");
            }
        } else { // 从下往上
            while (bLR != uLR - 1) {
                System.out.print(m[bLR--][bLC++] + " ");
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        int[] arr = {5, 7, 6, 1, 2};
//
//        int[] res = getKMinNumByHeap(arr, 3);
//
//        for (int r : res) {
//            System.out.println(r);
//        }
//
//    }

    /**
     * 在数组中找到最小的K个数 - 大根堆
     * @param arr
     * @param k
     * @return
     */
    public static int[] getKMinNumByHeap(int[] arr, int k) {
        if (null == arr || arr.length == 0 || k > arr.length) {
            return arr;
        }

        int[] kHeap = new int[k];
        // 先排成大顶堆
        for (int i = 0; i != k; i++) {
            heapInsert(kHeap, arr[i], i);
        }

        for (int i = k; i != arr.length; i++) {
            if (arr[i] < kHeap[0]) {
                kHeap[0] = arr[i];
                heapify(kHeap, 0, k);
            }
        }
        return kHeap;
    }

    public static void heapInsert(int[] arr, int value, int index) {
        arr[index] = value; // 先插入
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (arr[parent] < arr[index]) {
                swapTwoEle(arr, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int largest = index;

        while (left < heapSize) {
            if (arr[left] > arr[index]) {
                largest = left;
            }
            if (arr[right] > arr[index] && right < heapSize) {
                largest = right;
            }

            if (largest != index) {
                swapTwoEle(arr, largest, index);
            } else {
                break;
            }
            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    public static void swapTwoEle(int[] arr, int indexA, int indexB) {
        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {1, 5, 3, 4, 2, 6, 7};
//
//        System.out.println(get需要排序的最短子数组长度(arr));
//    }

    /**
     * @param arr
     * @return
     */
    public static int get需要排序的最短子数组长度(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        // {1, 5, 3, 4, 2, 6, 7};
        // 最短的子数组长度是{1, 2, 6, 7}
        // 现在来找规律
        // 从右往左找，来找最小值，如果不是最小值那么，记录不是最小值的位置，最终会落在5的身上，即i = 1
        // 从左往右找，来找最大值，如果不是最大值那么，记录不是最大值的位置，最终会落在2的身上，即i = 4
        // 那么从5到4，即i从1 ～ 4，4 - 1 + 1 = 4，那么长度就是4
        int minIndex = 0;
        int minValue = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else {
                minIndex = i; // 最终minIndex = 1;
            }
        }

        int maxIndex = 0;
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            } else {
                maxIndex = i;  // 最终maxIndex = 4;
            }
        }
        return maxIndex - minIndex + 1;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        int[] arr = {1, 2, 1, 3, 5, 1, 7, 1, 1};
//
//        printHalfMajorAppearValue(arr);
//
//        printKMajorAppearValue(arr, 9);
//    }

    /**
     * 找到数组中出现大于一半的数字
     * @param arr
     */
    public static void printHalfMajorAppearValue(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        int cand = 0;
        int times = 0;

        for (int i = 0; i != arr.length; i++) {
            if (times == 0) {
                cand = arr[i];
                times = 1;
            } else if (arr[i] == cand) {
                times++;
            } else {
                times--;
            }
        }

        times = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] == cand) {
                times++;
            }
        }

        if (times > arr.length / 2) {
            System.out.println(cand);
        } else {
            System.out.println("no such number appear.");
        }
    }

    /**
     * 找到数组中出现大于N/K的所有数字
     * @param arr
     * @param k
     */
    public static void printKMajorAppearValue(int[] arr, int k) {
        if (null == arr || arr.length == 0 || k < 2) {
            return;
        }
        Map<Integer, Integer> candMap = Maps.newHashMap();
        for (int i = 0; i != arr.length; i++) {
            if (candMap.containsKey(arr[i])) {
                candMap.put(arr[i], candMap.get(arr[i]) + 1);
            } else {
                if (candMap.size() == k - 1) { // 马上要超出啦
                    // 开始处理超出
                    processKMajorAppearValueKeyMinusOne(candMap);
                } else {
                    candMap.put(arr[i], 1);
                }
            }
        }
        Map<Integer, Integer> realMap = keyByStatistics(arr, candMap);
        boolean canPrint = false;
        for (Map.Entry<Integer, Integer> entry : candMap.entrySet()) {
            Integer key = entry.getKey();
            if (realMap.get(key) > arr.length / k) {
                canPrint = true;
                System.out.print(key + "\t");
            }
        }
        System.out.println(canPrint ? "" : "no such number appear.");
    }

    public static void processKMajorAppearValueKeyMinusOne(Map<Integer, Integer> candMap) {
        List<Integer> removeListKey = Lists.newArrayList();
        for (Map.Entry<Integer, Integer> entry : candMap.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            if (value == 1) {
                removeListKey.add(key);
            }
            candMap.put(key, value - 1); // 所有的统计 -1
        }

        for (Integer key : removeListKey) {
            candMap.remove(key);
        }
    }

    /**
     * @param arr
     * @param candMap
     * @return
     */
    public static Map<Integer, Integer> keyByStatistics(int[] arr,
                                                        Map<Integer, Integer> candMap) {
        Map<Integer, Integer> realMap = Maps.newHashMap(); // 出现的数量

        for (int i = 0; i != arr.length; i++) {
            int currentNum = arr[i];
            if (candMap.containsKey(currentNum)) {
                if (realMap.containsKey(currentNum)) {
                    realMap.put(currentNum, realMap.get(currentNum) + 1); // + 1
                } else {
                    realMap.put(currentNum, 1);
                }
            }
        }
        return realMap;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[][] array = {{0, 1, 2, 5}, {2, 3, 4, 7}, {4, 4, 4, 8}, {5, 7, 7, 9}};
//
//        System.out.println(findElementInMartrixOfOrdered(array, 7));
//
//        System.out.println(findElementInMartrixOfOrdered(array, 6));
//    }

    /**
     * 在排好序的矩阵中找到指定的数字(每一行每一列都是递增)，时间复杂度限制O(M+N).空间复杂度要求O(1)
     * @param martrix
     * @return
     */
    public static boolean findElementInMartrixOfOrdered(int[][] martrix, int k) {
        if (null == martrix || martrix.length == 0) {
            return false;
        }
        // 思路：要利用好有序这个特点，从右上角找起
        int row = martrix.length;
        int col = martrix[0].length;

        int i = 0;
        int j = col - 1; // 右上角

        while (i < row && j >= 0) {
            if (martrix[i][j] > k) {
                j--;
            } else if (martrix[i][j] < k) {
                i++;
            } else {
                System.out.println(martrix[i][j]);
                return true;
            }
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        int[] arr = {5, 5, 3, 2, 6, 4, 3};
//
//        System.out.println(getMaxLengthOfOrderArray(arr));
//
//    }

    /**
     * 最大可整合子数组的长度 O(N^2)
     * @param arr
     * @return
     */
    public static int getMaxLengthOfOrderArray(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        // 思路很重要，如果一个数组中没有重复的数字，且max - min + 1 == length的长度，那么这一段数组就是可整合的子数组
        Set<Integer> set = Sets.newHashSet();
        int length = 0;

        for (int i = 0; i < arr.length; i++) { // 外层循环
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for (int j = i; j < arr.length; j++) {
                if (set.contains(arr[j])) {
                    break;
                }
                set.add(arr[j]);

                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                if (max - min == j - i) {
                    length = Math.max(length, j - i + 1);
                }
            }
            set.clear();
        }
        return length;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {-8, -4, -3, 0, 1, 2, 4, 5, 8, 9};
//
//        printOrderArrayAddIsKValue(arr, 10);
//
//        printThreeOrderArrayAddIsKValue(arr, 10);
//    }

    /**
     * 不重复打印排序数组中相加和为给定值的所有二元数组
     * @param arr
     * @param k
     */
    public static void printOrderArrayAddIsKValue(int[] arr, int k) {
        if (null == arr || arr.length < 2) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;

        while (right > left) {
            if (arr[left] + arr[right] == k) {
                if (left == 0 || arr[left] != arr[left - 1]) { // 不重复打印
                    System.out.println(arr[left] + " + " + arr[right] + " = " + k);
                    left++;
                    right--;
                }
            } else if (arr[left] + arr[right] > k) {
                right--;
            } else {
                left++;
            }
        }
    }

    /**
     * 不重复打印排序数组中相加和为给定值的所有三元数组
     * @param arr
     * @param k
     */
    public static void printThreeOrderArrayAddIsKValue(int[] arr, int k) {
        if (null == arr || arr.length < 3) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                printThreeOrderArrayAddIsKValueRest(arr, i + 1, arr.length - 1, i, k - arr[i]);
            }
        }
    }

    public static void printThreeOrderArrayAddIsKValueRest(int[] arr, int left, int right, int currentIndex, int k) {
        while (right > left) {
            if (arr[left] + arr[right] == k) {
                if (left == currentIndex + 1 || arr[left] != arr[left - 1]) { // 不重复打印
                    System.out.println(arr[currentIndex] + " + " + arr[left] + " + " + arr[right] + " = " + (k + arr[currentIndex]));
                    left++;
                    right--;
                }
            } else if (arr[left] + arr[right] > k) {
                right--;
            } else {
                left++;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {2, 1, 1, 1, 1};
//
//        System.out.println(getUnorderedArrayAddVauleIsKMaxLength(arr, 2));
//    }

    /**
     * 未排序的正整数数组中相加和为给定值的最大子数组长度
     * @param arr
     * @param k
     * @return
     */
    public static int getUnorderedArrayAddVauleIsKMaxLength(int[] arr, int k) {
        if (null == arr || arr.length == 0 || k < 0) {
            return 0;
        }
        int left = 0;
        int right = 0;

        int length = 0; // 最大子数组长度
        int sum = arr[0]; // 最大和

        while (right < arr.length) {
            if (sum == k) { // 找到了某一个值
                length = Math.max(length, right - left + 1); // 更新length
                sum = sum - arr[left];
                left++;
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum = sum + arr[right];
            } else {
                sum = sum - arr[left];
                left++;
            }
        }
        return length;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 3};
//
//        System.out.println(getUnorderedArrayAddVauleIsKMaxLength_arrarValue(arr, 6));
//    }

    /**
     * 未排序的数组中相加和为给定值的最大子数组长度O(N) + O(N)
     * @param arr
     * @param k
     * @return
     */
    public static int getUnorderedArrayAddVauleIsKMaxLength_arrarValue(int[] arr, int k) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int maxLength = 0;
        int sum = 0;

        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];

            if (map.containsKey(sum - k)) {
                maxLength = Math.max(maxLength, i - map.get(sum - k));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLength;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//
//        int[] arr = {2, 1, 5, 7, 4, 3, 6};
//
//        // sort自然数数组的排序(arr);
//
//        sort自然数数组的排序A(arr);
//
//        for (int i : arr) {
//            System.out.print(i + "\t");
//        }
//    }

    /**
     * 自然数数组的排序O(N) + O(1) - 成环推
     * @param arr
     */
    public static void sort自然数数组的排序(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        int temp = 0;
        int next = 0;

        for (int i = 0; i < arr.length; i++) {
            temp = arr[i];
            while (arr[i] != i + 1) {
                next = arr[temp - 1];
                arr[temp - 1] = temp;
                temp = next;
            }
        }
    }

    /**
     * 自然数数组的排序O(N) + O(1) - 交换
     * @param arr
     */
    public static void sort自然数数组的排序A(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i + 1) {
                temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[] arr = {2, 1, 5, 7, 4, 3, 6};
//
//        modifyArrayValueEvenOdd(arr);
//
//        for (int i : arr) {
//            System.out.print(i + "\t");
//        }
//    }

    /**
     * 奇数下标都是奇数，或者偶数下标是偶数
     * @param arr
     */
    public static void modifyArrayValueEvenOdd(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        int even = 0; // 偶数
        int odd = 1; // 奇数
        int end = arr.length - 1;

        while (even < arr.length && odd < arr.length) {
            if (arr[end] % 2 == 0) { // 偶数
                swapModifyArrayValueEvenOdd(arr, even, end);
                even = even + 2;
            } else { // 奇数
                swapModifyArrayValueEvenOdd(arr, odd, end);
                odd = odd + 2;
            }
        }
    }

    public static void swapModifyArrayValueEvenOdd(int[] arr, int left, int right) {
        arr[left] = arr[left] ^ arr[right];
        arr[right] = arr[left] ^ arr[right];
        arr[left] = arr[left] ^ arr[right];
    }

//    public static void main(String[] args) {
//        int[] arr = {1, -2, 3, 5, -2, 6, -1};
//
//        System.out.println(getChildArrayMaxValue(arr));
//    }

    /**
     * 子数组的最大累加和
     * @param arr
     * @return
     */
    public static int getChildArrayMaxValue(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int currentIndex = 0;

        int max = 0;
        int sum = 0;
        while (currentIndex < arr.length) {
            sum += arr[currentIndex];
            if (sum < 0) { // 重新计算
                sum = 0;
            } else {
                max = Math.max(max, sum);
            }
            currentIndex++;
        }
        return max;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[][] martrix = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};
//
//        System.out.println(getChildMaxtrixMaxValue(martrix));
//    }

    /**
     * 子矩阵最大累加和
     * @param martrix
     * @return
     */
    public static int getChildMaxtrixMaxValue(int[][] martrix) {
        if (null == martrix || martrix.length == 0 || null == martrix[0] || martrix[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] addArray = null;

        for (int i = 0; i < martrix.length; i++) {
            addArray = new int[martrix[0].length];
            for (int j = i; j < martrix.length; j++) {
                cur = 0;
                for (int k = 0; k < addArray.length; k++) {
                    addArray[k] += martrix[j][k];
                    cur += addArray[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 子数组中的最大累乘积
     * @param arr
     * @return
     */
    public static double getChildArrayMaxMulityValue(double[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }

        // 分析到哪一个会是最大值
        // 1. arr[i]之前是正数字，那么最大值出现可能之一是arr[i] * max
        // 2. arr[i]之前是正数字, 但是是小数，那么最大值 = arr[i];
        // 3. arr[i]之前是负数，arr[i]也是负数，那么最大值 = arr[i] * min

        double res = arr[0]; // 最终结果
        double max = arr[0];
        double min = arr[0];

        double maxEnd = 0;
        double minEnd = 0;

        for (int i = 1; i < arr.length; i++) {
            maxEnd = max * arr[i];
            minEnd = min * arr[i];

            max = Math.max(Math.max(maxEnd, minEnd), arr[i]);
            min = Math.min(Math.min(maxEnd, minEnd), arr[i]);

            res = Math.max(max, res);
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        int[][] m = {
//                {0, 1, 1, 1, 1},
//                {0, 1, 0, 0, 1},
//                {0, 1, 0, 0, 1},
//                {0, 1, 1, 1, 1},
//                {0, 1, 0, 1, 1}};
//
//        System.out.println(getMaxSquareSize(m));
//    }

    /**
     * 边界是1的最大正方形大小
     * @param m
     * @return
     */
    public static int getMaxSquareSize(int[][] m) {
        if (null == m || m.length == 0 || null == m[0] || m[0].length == 0) {
            return 0;
        }
        // 生成两个辅助矩阵，用来判断正方形的四个角，check right矩阵和down矩阵的size是否符合边长
        // right[][]矩阵
        //  down[][]矩阵

        // 1. 辅助矩阵
        int[][] down = new int[m.length][m[0].length];
        int[][] right = new int[m.length][m[0].length];

        // 2. 生成辅助矩阵
        setupHelpedMartrix(m, down, right);

        // 3. check正方形是否存在
        int borderSize = Math.min(m.length, m[0].length);
        for (int size = borderSize; size > 0; size--) {
            if (checkSizeCanBorder(size, down, right)) {
                return size;
            }
        }
        return 0;
    }

    public static void setupHelpedMartrix(int[][] m,
                                          int[][] down,
                                          int[][] right) {
        int row = m.length;
        int col = m[0].length;

        // 两个辅助矩阵的右下角都是1
        if (m[row - 1][col - 1] == 1) {
            down[row - 1][col - 1] = 1;
            right[row - 1][col - 1] = 1;
        }

        // 以m的最后一列生成辅助矩阵down和right的最后一列
        for (int i = row - 2; i >= 0; i--) {
            if (m[i][col - 1] == 1) {
                right[i][col - 1] = 1;
                down[i][col - 1] = down[i + 1][col - 1] + 1;
            }
        }

        // 以m的最后一行生成辅助矩阵down和right的最后一列
        for (int i = col - 2; i >= 0; i--) {
            if (m[row - 1][i] == 1) {
                down[row - 1][i] = 1;
                right[row - 1][i] = right[row - 1][i + 1] + 1;
            }
        }

        // 处理剩下的位置
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                if (m[i][j] == 1) {
                    down[i][j] = down[i + 1][j] + 1;
                    right[i][j] = right[i][j + 1] + 1;
                }
            }
        }
    }

    public static boolean checkSizeCanBorder(int size,
                                             int[][] down,
                                             int[][] right) {
        for (int i = 0; i < right.length - size + 1; i++) {
            for (int j = 0; j < right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

}
