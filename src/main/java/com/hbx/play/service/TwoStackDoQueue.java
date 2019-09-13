package com.hbx.play.service;

import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/15 2:01 PM
 */
public class TwoStackDoQueue {

    private static Stack<Integer> stack1 = new Stack<Integer>();
    private static Stack<Integer> stack2 = new Stack<Integer>();


    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < array.length; i++) {
            push(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            System.out.println(pop());
        }
    }

    public static void push(int node) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }

    public static int pop() {
        return stack2.pop();
    }

}
