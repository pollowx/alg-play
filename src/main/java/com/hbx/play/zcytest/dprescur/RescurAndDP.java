package com.hbx.play.zcytest.dprescur;

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
     *
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
     *
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

    public static void main(String[] args) {

        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};

        System.out.println(getMinPathByNormal(m));
        System.out.println(getMinPathBest(m));

    }

    /**
     * Dynamic programing 经典问题 寻找矩阵的最小路径和
     *
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
     *
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


}
