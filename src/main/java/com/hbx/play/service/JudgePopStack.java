package com.hbx.play.service;

import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/27 11:10 AM
 */
public class JudgePopStack {

    public static void main(String[] args) {

        int[] pushArray = new int[]{1, 2, 3, 4, 5};
        int[] popArray = new int[]{4, 5, 3, 2, 1};
        int[] popArrayA = new int[]{4, 5, 1, 3, 1};

        System.out.println(judgePopStack(pushArray, popArray));
        System.out.println(judgePopStack(pushArray, popArrayA));
    }

    public static boolean judgePopStack(int[] pushA, int[] popA) {
        if (null == pushA || null == popA || pushA.length != popA.length) {
            return false;
        }
        Stack<Integer> helpStack = new Stack<Integer>();

        int k = 0;
        for (int i = 0; i < pushA.length; i++) {
            // 先入栈
            helpStack.push(pushA[i]);

            while (!helpStack.empty() && helpStack.peek() == popA[k]) {
                // 出栈，后移k
                helpStack.pop();
                k++;
            }
        }

//        int i = 0;
//        int k = 0;
//        do {
//            // 先入栈
//            if (i < pushA.length) {
//                helpStack.push(pushA[i]);
//                i++;
//            }
//            // 判断栈顶元素
//            if (helpStack.peek() == popA[k]) {
//                // 出栈，后移k
//                helpStack.pop();
//                k++;
//            }
//            if (k >= popA.length) {
//                break;
//            }
//
//        } while (!helpStack.empty());

        // 如果栈为空，证明是一个弹出序列，反之不是
        return helpStack.empty();
    }


}
