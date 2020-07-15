package com.hbx.play.daily;

import java.util.*;

public class TwoArrayContains350 {

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
//        int[] nums1 = {1, 2, 2, 1};
//        int[] nums2 = {2, 2};

        int[] res = intersect(nums1, nums2);
        if (res == null || res.length <= 0) {
            System.out.println("no answers");
        } else {
            for (int ele : res) {
                System.out.println(ele);
            }
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length <= 0 || nums2 == null || nums2.length <= 0) {
            return new int[0];
        }
        int[] smallArray = nums1.length > nums2.length ? nums2 : nums1;
        if (nums1.length != smallArray.length) { // num2 must be smaller
            nums2 = nums1;
        }

        Map<Integer, Integer> smallMap = new HashMap<>(smallArray.length);
        for (int ele : smallArray) {
            int count = null == smallMap.get(ele) ? 0 : smallMap.get(ele);
            smallMap.put(ele, ++count);
        }

        List<Integer> resList = new ArrayList<>();
        for (int ele : nums2) {
            if (smallMap.containsKey(ele)) {
                int count = smallMap.get(ele);
                if (count > 0) {
                    resList.add(ele);
                }
                smallMap.put(ele, --count);
            }
        }

        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i).intValue();
        }
        return res;
    }

}
