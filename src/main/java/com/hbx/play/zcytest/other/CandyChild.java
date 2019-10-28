package com.hbx.play.zcytest.other;

/**
 * @Auther: bingxin
 * @Date: 2019-10-28 15:52
 * @Description:
 */
public class CandyChild {

    public static void main(String[] args) {
        int[] middle = new int[]{1, 4, 5, 9, 3, 2, 4, 5};

        System.out.println(candyChild(middle));
    }

    /**
     * 按照数组的分数来小朋友分糖果
     * @param arr
     * @return
     */
    public static int candyChild(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }

        int index = getNextMinIndexStation(arr, 0); // 第一次返回index;
        int res = getCandsSum(arr, 0, index++);

        int next = 0;
        int lbase = 1; // 左侧的候选数量初始值 = 1
        int rbase = 0; // 右侧候选数量 = 0
        int rcands = 0; // 右侧的right -> left的累加和

        while (index < arr.length) {
            if (arr[index] > arr[index - 1]) { // 初始值index = 1
                // 现在这种case是递增的
                res += ++lbase;
                index++;
            } else if (arr[index] < arr[index - 1]) { // 找到了转折点
                next = getNextMinIndexStation(arr, index - 1);
                rcands = getCandsSum(arr, index - 1, next++);
                rbase = next - index + 1;
                res += rcands + (lbase > rbase ? -rbase : lbase);
                index = next;
                lbase = 1;
            } else {
                res += 1;
                lbase = 1;
                index++;
            }
        }
        return res;
    }

    /**
     * arr的下一个
     * @param arr
     * @param start
     * @return
     */
    public static int getNextMinIndexStation(int[] arr, int start) {
        for (int i = start; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    /**
     * 得到right=1到left的递增和
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int getCandsSum(int[] arr, int left, int right) {
        int n = right - left + 1;
        return n + n * (n - 1) / 2;
    }

}
