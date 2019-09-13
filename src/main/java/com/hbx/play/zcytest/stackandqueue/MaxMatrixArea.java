package com.hbx.play.zcytest.stackandqueue;

import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/9/9 2:20 PM
 */
public class MaxMatrixArea {

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        System.out.println(splitCalMap(map));

        int[] height = {3, 4, 5, 4, 3, 6};
        System.out.println(calArrayMaxArea(height));
        System.out.println(calArrayMaxAreaMy(height));
    }

    public static int splitCalMap(int[][] map) {
        if (null == map || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(calArrayMaxArea(height), maxArea);
        }
        return maxArea;
    }

    public static int calArrayMaxArea(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> helpStack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!helpStack.isEmpty() && height[helpStack.peek()] >= height[i]) {
                int j = helpStack.pop(); //栈顶元素
                int k = helpStack.isEmpty() ? -1 : helpStack.peek();
                int tempMaxArea = (i - k - 1) * height[j];
                maxArea = Math.max(tempMaxArea, maxArea);
            }
            helpStack.push(i);
        }

        while (!helpStack.isEmpty()) {
            int j = helpStack.pop();
            int k = helpStack.isEmpty() ? -1 : helpStack.peek();
            int tempMaxArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(tempMaxArea, maxArea);
        }
        return maxArea;
    }

    public static int calArrayMaxAreaMy(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> helpStack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!helpStack.isEmpty() && height[i] <= height[helpStack.peek()]) {
                int j = helpStack.pop();
                int k = helpStack.isEmpty() ? -1 : height[helpStack.peek()];
                int tempMaxArea = (i - k - 1) * height[j];
                maxArea = Math.max(tempMaxArea, maxArea);
            }
            helpStack.push(i);
        }

        while (!helpStack.isEmpty()) {
            int j = helpStack.pop();
            int k = helpStack.isEmpty() ? -1 : height[helpStack.peek()];
            int tempMaxArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(tempMaxArea, maxArea);
        }
        return maxArea;
    }

}
