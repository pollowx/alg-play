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

//        int[] candy = new int[]{0, 1, 2, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1};
//
//        System.out.println(candyChild2(candy));

        int[] candy = new int[]{1, 4, 3, 3, 2, 2, 2, 4, 4, 5};

        System.out.println(candyChild2(candy));
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


    /**
     * 按照数组的分数来小朋友分糖果 - 进阶版本
     * @param arr
     * @return
     */
    public static int candyChild2(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int index = getNextMinIndexStation2(arr, 0);
        int[] data = getRightCandsAndRBase(arr, 0, index++); // rightCands - RightBase
        int res = data[0];
        int same = 1; // 相同元素的数量
        int next = 0;
        int lbase = 1;

        while (index < arr.length) {
            if (arr[index] > arr[index - 1]) {
                res += ++lbase;
                index++;
                same = 1;
            } else if (arr[index] < arr[index - 1]) { // 断点出现
                next = getNextMinIndexStation2(arr, index - 1);
                data = getRightCandsAndRBase(arr, index - 1, next++);
                if (data[1] <= lbase) { // 说明左侧爬坡到右侧降坡，左侧的坡顶大
                    res += data[0] - data[1];
                } else {
                    res += -lbase * same + data[0] - data[1] + data[1] * same; // 右侧的坡顶大
                }
                index = next;
                lbase = 1;
                same = 1;
            } else {
                res += lbase;
                same++;
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
    public static int getNextMinIndexStation2(int[] arr, int start) {
        for (int i = start; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public static int[] getRightCandsAndRBase(int[] arr, int left, int right) {
        int rbase = 1;
        int rcands = 1;

        for (int i = right - 1; i >= left; i--) {
            if (arr[i] == arr[i + 1]) {
                rcands += rbase;
            } else {
                rcands += ++rbase; // 这个时候不等的话，rbase就累加
            }
        }
        return new int[]{rcands, rbase};
    }


}
