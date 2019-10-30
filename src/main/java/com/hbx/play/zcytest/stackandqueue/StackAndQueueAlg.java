package com.hbx.play.zcytest.stackandqueue;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/9/5 2:56 PM
 */
public class StackAndQueueAlg {

//    public static void main(String[] args) {
//        Stack<Integer> stackandqueue = new Stack<>();
//        stackandqueue.push(1);
//        stackandqueue.push(2);
//        stackandqueue.push(3);
//
//        //getStackLastElement(stackandqueue);
//
//        reverseStack(stackandqueue);
//    }

    /**
     * 反转栈中元素
     * @param stack
     */
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 得到栈中的最后一个元素，并移除该元素
        // 321 -> 32  得到1
        // 32  -> 3   得到2
        // 3   -> 3   得到3v
        int i = getStackLastElement(stack);
        reverseStack(stack);
        // 最后一个元素3先入栈，后面会轮回到上一轮的调用栈，i = 3, i = 2, i = 1
        stack.push(i);
    }

    /**
     * 得到栈中最后一个元素
     * @return
     */
    public static int getStackLastElement(Stack<Integer> stack) {
        int result = stack.pop(); // 得到栈顶元素
        if (stack.isEmpty()) {
            return result;
        } else {
            int lastElement = getStackLastElement(stack);
            stack.push(result);
            return lastElement;
        }
    }


    // ####################################################################### //

//    public static void main(String[] args) {
//        Stack<Integer> stackandqueue = new Stack<>();
//        stackandqueue.push(1);
//        stackandqueue.push(5);
//        stackandqueue.push(3);
//        stackandqueue.push(2);
//
//        //orderStack(stackandqueue);
//
//        orderStackMy(stackandqueue);
//
//    }

    /**
     * 实现栈的排序，不能使用其他的数据结构协助
     * @param stack
     */
    public static void orderStack(Stack<Integer> stack) {
        if (stack.isEmpty() || stack.size() == 1) {
            return;
        }
        Stack<Integer> helpStack = new Stack<>();
        while (!stack.isEmpty()) {
            int topEle = stack.pop();
            // 如果
            while (!helpStack.isEmpty() && helpStack.peek() < topEle) {
                stack.push(helpStack.pop());
            }
            helpStack.push(topEle);
        }
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
    }

    public static void orderStackMy(Stack<Integer> stack) {
        if (stack.isEmpty() || stack.size() == 1) {
            return;
        }
        Stack<Integer> helpStack = new Stack<>();
        while (!stack.isEmpty()) {
            int topEle = stack.pop();
            if (helpStack.isEmpty()) {
                helpStack.push(topEle);
            } else {
                while (!helpStack.isEmpty() && helpStack.peek() > topEle) {
                    stack.push(helpStack.pop());
                }
                helpStack.push(topEle);
            }
        }
    }

    // ####################################################################### //

//    public static void main(String[] args) {
//        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
//
//        int[] res = getWindowMaxMy(arr, 3);
//
//        for (Integer i : res) {
//            System.out.print(i + "\t");
//        }
//    }

    /**
     * 窗口最大元素
     * @param arr
     * @param w
     * @return
     */
    public static int[] getWindowMax(int[] arr, int w) {
        if (null == arr || arr.length == 0 || w > arr.length) {
            return arr;
        }
        int[] res = new int[arr.length - w + 1];

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            // 如果是空，先放在队头
            while (!linkedList.isEmpty() && arr[linkedList.getLast()] <= arr[i]) {
                linkedList.pollLast(); // 扔掉最后一个元素
            }
            // 加入链表
            linkedList.addLast(i);

            if (linkedList.getFirst() == i - w) {
                linkedList.pollFirst(); //超过了窗口，需要扔掉头元素
            }
            // 窗口期
            if (i + 1 >= w) {
                res[i - w + 1] = arr[linkedList.peekFirst()];
            }
        }
        return res;
    }

    public static int[] getWindowMaxMy(int[] arr, int w) {
        if (null == arr || arr.length == 0 || w > arr.length) {
            return arr;
        }

        int[] res = new int[arr.length - w + 1];
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            while (!linkedList.isEmpty() && arr[linkedList.peekLast()] <= arr[i]) {
                linkedList.pollLast();
            }
            linkedList.addLast(i);

            // 超过后需要替换, 扔掉第一个头元素
            if (linkedList.getFirst() == i - w) {
                linkedList.pollFirst();
            }

            // 开始到达窗口期
            if (i + 1 >= w) {
                res[i - w + 1] = arr[linkedList.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 第三次
     * @param arr
     * @param w
     * @return
     */
    public static int[] getWindowMaxMySec(int[] arr, int w) {
        if (null == arr || arr.length == 0 || w <= 0) {
            return null;
        }
        int[] res = new int[arr.length - w + 1]; // 返回的结果长度
        int index = 0;
        LinkedList<Integer> helpList = new LinkedList<>(); // 双端链表，存放数组的下标
        for (int i = 0; i < arr.length; i++) {
            // 弹出元素
            while (!helpList.isEmpty() && arr[helpList.getLast()] <= arr[i]) {
                helpList.pollLast(); // 弹出最后一个元素
            }
            helpList.addLast(i);

            // 到达窗口期
            if (i + 1 >= w) {
                res[index] = arr[helpList.getFirst()];
                index++;
            }

            // 头元素已经过期, 扔掉
            if (helpList.getFirst() == i - w) {
                helpList.pollFirst();
            }
        }
        return res;
    }

    // ####################################################################### //

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 5, 6, 2, 7};

        int[][] res = getNearLessNoRepeatMy(arr);

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int[][] getNearLessNoRepeatMy(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        int[][] res = new int[arr.length][2];

        Stack<Integer> stack = new Stack<>(); // 单调递减栈
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) { // 开始出栈
                int temp = stack.pop();
                res[temp][1] = i; // 右侧的最近最小值
                res[temp][0] = stack.isEmpty() ? -1 : stack.peek(); // 左侧的最近最小值
            }
            stack.push(i);
        }
        // 如果栈里还有其他元素
        while (!stack.isEmpty()) { // 开始出栈
            int temp = stack.pop();
            res[temp][1] = -1; // 右侧的最近最小值
            res[temp][0] = stack.isEmpty() ? -1 : stack.peek(); // 左侧的最近最小值
        }
        return res;
    }

    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<Integer> helpStack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 如果当前元素比栈顶元素小，依次出栈
            while (!helpStack.isEmpty() && arr[helpStack.peek()] > arr[i]) {
                int currentIndex = helpStack.pop();
                int left = -1;
                if (!helpStack.isEmpty()) {
                    left = helpStack.peek();
                }
                res[currentIndex][1] = i; // right;
                res[currentIndex][0] = left;
            }
            helpStack.push(i);
        }
        // 如果栈里面还有其他元素
        while (!helpStack.isEmpty()) {
            int currentIndex = helpStack.pop();
            int left = -1;
            if (!helpStack.isEmpty()) {
                left = helpStack.peek();
            }
            res[currentIndex][0] = left;// left
            res[currentIndex][1] = -1; // right
        }
        return res;
    }

    // ####################################################################### //

    /**
     * 该题目要求在数组中找到Max(arr[i ~ j]) - Min(arr[i ~ j]) <= num的符合条件子数组的数量 普通解法：O(N^3) 最优解法：O(N), 但是需要用到额外空间，需要用到双端链表
     * @param arr
     * @param num
     * @return
     */
    public static int getNum(int[] arr, int num) {
        if (null == arr || arr.length == 0 || num < 0) {
            return 0;
        }
        int res = 0;

        // 利用双端链表，保存最优子结构的元素index
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int i = 0;
        int j = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                if (qmin.isEmpty() || qmin.getLast() != j) {
                    while (!qmin.isEmpty() && arr[qmin.getLast()] >= arr[j]) {
                        qmin.pollLast(); // 扔掉
                    }
                    qmin.addLast(i);
                    while (!qmax.isEmpty() && arr[qmax.getLast()] <= arr[j]) {
                        qmax.pollLast(); // 扔掉
                    }
                    qmax.addLast(i);
                }
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                j++;
            }
            res = res + (j - i);
            if (qmin.getFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.getFirst() == i) {
                qmax.pollFirst();
            }
            i++;
        }
        return res;
    }


}
