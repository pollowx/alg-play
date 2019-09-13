package com.hbx.play.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/9/2 5:28 PM
 */
public class SumEqualS {

    public static void main(String[] args) {
        //        List<List<Integer>> re = findSumEqualSS(9);
        //        for (List<Integer> cl : re) {
        //            for (Integer l : cl) {
        //                System.out.print(l + "\t");
        //            }
        //            System.out.println(" ");
        //        }
        //
        //
        //        System.out.println("##################################");
        //
        //        List<List<Integer>> reBest = findSumEqualBest(9);
        //        for (List<Integer> cl : reBest) {
        //            for (Integer l : cl) {
        //                System.out.print(l + "\t");
        //            }
        //            System.out.println(" ");
        //        }

        //////////////////////////////////////////////////////////////////////////////////////////////////
        int[] array = {2, 4, 6, 8, 9, 10};
        List<Integer> reBest = findNumbersWithSum(array, 12);
        for (Integer l : reBest) {
            System.out.print(l + "\t");
        }
        System.out.println(" ");
    }


    /**
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的
     *
     * @param array
     * @param sum
     * @return
     */
    public static ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        // 鉴于之前的那到题目感觉low -> high, 利用数组递增的这个特点
        if (null == array) {
            return new ArrayList<>();
        }
        ArrayList<Integer> result = new ArrayList<>();

        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            if (array[low] + array[high] == sum) {
                if (result.isEmpty() || result.get(0) * result.get(1) > array[low] * array[high]) {
                    result.clear();
                    result.add(array[low]);
                    result.add(array[high]);
                }
                high--;
            } else if (array[low] + array[high] > sum) {
                high--;
            } else {
                low++;
            }
        }
        return result;
    }

    //############################################################################################################//

    /**
     * 这种做法是暴力傻逼做法，不建议这样，时间复杂度很高
     *
     * @param sum
     * @return
     */
    public static List<List<Integer>> findSumEqualSS(int sum) {
        if (sum <= 0) {
            return new ArrayList<>();
        }
        int sIndex = sum;
        if (sum > 40) {
            sIndex = sum / 4;
        }
        List<List<Integer>> re = new ArrayList<>();
        for (int i = 1; i < sIndex; i++) {
            int sumP = 0;
            List<Integer> cL = new ArrayList<>();
            for (int j = i; (j <= sIndex) && (sumP <= sum); j++) {
                if (sumP == sum && !cL.isEmpty()) {
                    re.add(new ArrayList<>(cL));
                    cL.clear();
                } else {
                    sumP += j;
                    cL.add(j);
                }
            }
        }
        return re;
    }

    /**
     * @param sum
     * @return
     */
    public static List<List<Integer>> findSumEqualBest(int sum) {
        // 该方案是有两个指针，加上滑动窗口
        if (sum <= 0) {
            return new ArrayList<>();
        }
        int low = 1;
        int high = 2;

        List<List<Integer>> re = new ArrayList<>();

        while (low < high || high < sum) {
            int result = (low + high) * ((high - low) + 1) / 2;
            if (result == sum) {
                List<Integer> cL = new ArrayList<>();
                for (int i = low; i <= high; i++) {
                    cL.add(i);
                }
                re.add(cL);
                low++;
            } else if (result < sum) {
                // 结果数小于sum，high右移
                high++;
            } else {
                low++;
            }
        }
        return re;
    }

}
