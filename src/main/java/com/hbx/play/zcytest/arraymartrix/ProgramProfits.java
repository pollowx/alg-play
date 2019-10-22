package com.hbx.play.zcytest.arraymartrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 做项目的最大收益问题
 * @Auther: bingxin
 * @Date: 2019-10-22 11:00
 * @Description:
 */
public class ProgramProfits {

    // 本题的解题思路
    // 项目收益和项目花费，把项目收益置成大根堆，项目花费变成小根堆，初始化小根堆，
    // 然后把符合当前资金的项目加入收益大根堆，收益累加后，check后续的项目还能不能做

    public static void main(String[] args) {

        int[] costs = {5, 4, 1, 2};
        int[] profits = {3, 5, 3, 2};

        System.out.println(getMaxProfits(3, 2, costs, profits));
    }

    public static int getMaxProfits(int W, int K, int[] costs, int[] profits) {
        if (W <= 0 || K <= 0 || null == costs || null == profits || costs.length != profits.length) {
            return 0;
        }
        // 生成小根堆
        PriorityQueue<ProgramPro> costMinHeap = new PriorityQueue<>(new CostMinComp());

        // 生成大根堆
        PriorityQueue<ProgramPro> profitMaxHeap = new PriorityQueue<>(new ProfitMaxComp());

        // 放入小根堆
        for (int i = 0; i < costs.length; i++) {
            costMinHeap.add(new ProgramPro(costs[i], profits[i]));
        }

        // 做项目
        for (int i = 1; i <= K; i++) {
            while (!costMinHeap.isEmpty() && costMinHeap.peek().cost <= W) {
                profitMaxHeap.add(costMinHeap.poll()); // 当前项目的花费小于W, 放入收益堆
            }
            if (profitMaxHeap.isEmpty()) { // 钱不够项目没法做
                return W;
            }
            W += profitMaxHeap.poll().profits;
        }
        return W;
    }

    public static class CostMinComp implements Comparator<ProgramPro> {
        @Override
        public int compare(ProgramPro o1, ProgramPro o2) {
            return o1.cost - o2.cost; // 前面的大, 从小到大排
        }
    }

    public static class ProfitMaxComp implements Comparator<ProgramPro> {
        @Override
        public int compare(ProgramPro o1, ProgramPro o2) {
            return o2.profits - o1.profits; // 后面的大，从大到小排
        }
    }

    public static class ProgramPro {
        int cost;
        int profits;

        public ProgramPro(int cost, int profits) {
            this.cost = cost;
            this.profits = profits;
        }
    }

}
