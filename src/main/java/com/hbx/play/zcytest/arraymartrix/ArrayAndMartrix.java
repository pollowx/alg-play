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

    public static void main(String[] args) {
        int[] arr = {-8, -4, -3, 0, 1, 2, 4, 5, 8, 9};

        printOrderArrayAddIsKValue(arr, 10);
    }

    /**
     * 不重复打印排序数组中相加和为给定值的所有二元数组
     * @param arr
     * @param k
     */
    public static void printOrderArrayAddIsKValue(int[] arr, int k) {
        if (null == arr || arr.length == 0) {
            return;
        }

        int left = 0;
        int right = arr.length - 1;

        while (right > left) {
            if (arr[left] + arr[right] == k) {
                System.out.println(arr[left] + " + " + arr[right] + " = " + k);
                left++;
                right--;
            } else if (arr[left] + arr[right] > k) {
                right--;
            } else {
                left++;
            }
        }
    }

}
