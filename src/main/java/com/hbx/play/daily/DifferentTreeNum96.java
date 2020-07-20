package com.hbx.play.daily;

public class DifferentTreeNum96 {

    // 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
    // 思路:
    /*
     * 当n = 1时，(1 ~ n)取值为1，1只能作为根节点，res = 1
     * 当n = 2时，(1 ~ n)取值为1/2，1/2分别可以作为跟节点，res = 2
     * 当n = 3时，(1 ~ 3)取值为1/2/3, res = 2 + 2 + 1 = 5
     *      当3为根节点，1/2都只能为左子树，1为左子树2作为1的右子树有一种情况，2为左子树1作为2的左子树，res = 2
     *      当2为根节点，1作为左子树，3作为右子树，res = 1
     *      当1为根节点，2/3都只能为右子树，2作为右子树3作为2的右子树，3作为右子树2作为3的左子树，res = 2
     *
     * 我们会发现一个规律，(i ~ n)中当i作为根节点的时候，左子树的数量为G(i-1),右子树的数量为G(n-i)
     * 所以G(i) = G(i-1) * G(n-i)
     */
    public static int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println(numTrees(i));
        }
    }

}
