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
     *
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
     *
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
     *
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

    public static void main(String[] args) {

        int[] arr = {5, 10, 25, 1};

        System.out.println(findCoinWays(arr, 1000));

        System.out.println(findCoinWaysByMap(arr, 1000));

    }

    /**
     * 找钱的方式，arr个零钱种类，求aim的找钱方式 每张面额任意张
     *
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
            return aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += findCoinWaysFeb(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

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

}
