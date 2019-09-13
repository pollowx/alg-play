package com.hbx.play.service;

import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/26 4:27 PM
 */
public class FindStackMinElement {

    private static Stack<Integer> stackMin = new Stack<Integer>();
    private static Stack<Integer> stackMax = new Stack<Integer>();

    public static void main(String[] args) {
        // 用两个栈，入栈的时候排序，保持最大值在最上面。最小值在另一个栈的最上面

        push(2);
        push(6);
        push(3);
        push(1);
        push(10);
        push(4);
        push(4);
        push(0);

        System.out.println(top());
        System.out.println(min());

        pop();

        System.out.println(top());
        System.out.println(min());
    }

    public static void push(int node) {
        if (stackMax.empty()) {
            stackMax.push(node);
        } else {
            Stack<Integer> tempStack = new Stack<Integer>();
            while (!stackMax.empty()) {
                int currentIndexValue = stackMax.pop();
                if (currentIndexValue > node) {
                    tempStack.push(currentIndexValue);
                } else {
                    stackMax.push(currentIndexValue);
                    stackMax.push(node);
                    break;
                }
            }

            if (stackMax.empty()) {
                stackMax.push(node);
            }

            while (!tempStack.empty()) {
                stackMax.push(tempStack.pop());
            }
        }

        if (stackMin.empty()) {
            stackMin.push(node);
        } else {
            Stack<Integer> tempStack = new Stack<Integer>();
            while (!stackMin.empty()) {
                int currentIndexValue = stackMin.pop();
                if (currentIndexValue < node) {
                    tempStack.push(currentIndexValue);
                } else {
                    stackMin.push(currentIndexValue);
                    stackMin.push(node);
                    break;
                }
            }

            if (stackMin.empty()) {
                stackMin.push(node);
            }

            while (!tempStack.empty()) {
                stackMin.push(tempStack.pop());
            }
        }
    }

    public static void pop() {
        stackMax.pop();
        stackMin.pop();
    }

    public static int top() {
        return stackMax.peek();
    }

    public static int min() {
        return stackMin.peek();
    }

}
