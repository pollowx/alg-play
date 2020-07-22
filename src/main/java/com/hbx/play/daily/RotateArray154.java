package com.hbx.play.daily;

public class RotateArray154 {

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 5, 1, 2};
        int[] array1 = new int[]{2, 2, 2, 0, 1};

        System.out.println(minArray(array));
        System.out.println(minArray(array1));
    }

    // 找到旋转数组中的最小值
    // 思路，用双指针来做，left - right.
    //      如果旋转数组的最左侧值比最右侧大，那么left--, 右移动
    //      如果旋转数组的最左侧值比最右侧小，那么最小值就是array[0]
    public static int minArray(int[] numbers) {
        if (null == numbers || numbers.length <= 0) {
            return 0;
        }
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] < numbers[right]) {
                break;
            } else if (numbers[left] > numbers[right]) {
                left++;
            } else {
                right--;
            }
        }
        return numbers[left];
    }

}
