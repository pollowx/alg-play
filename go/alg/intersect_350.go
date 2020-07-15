package main

import (
	"fmt"
	"sort"
)

func main() {
	//num1 := []int{1, 2, 2, 1}
	//num2 := []int{2, 2}

	num1 := []int{4, 9, 5}
	num2 := []int{9, 4, 9, 8, 4}
	fmt.Println(intersect2(num1, num2))
}

func intersect(nums1 []int, nums2 []int) []int {
	if len(nums1) <= 0 || len(nums2) <= 0 {
		return []int{}
	}
	if len(nums2) < len(nums1) {
		return intersect(nums2, nums1)
	}
	maps := make(map[int]int, len(nums1))
	for _, value := range nums1 {
		maps[value] ++
	}

	intersect := make([]int, 0)
	for _, value := range nums2 {
		if maps[value] > 0 {
			intersect = append(intersect, value)
			maps[value] --
		}
	}
	return intersect
}

func intersect2(nums1 []int, nums2 []int) []int {
	if len(nums1) <= 0 || len(nums2) <= 0 {
		return []int{}
	}
	sort.SliceStable(nums1, func(i, j int) bool {
		return nums1[i] < nums1[j]
	})
	sort.SliceStable(nums2, func(i, j int) bool {
		return nums2[i] < nums2[j]
	})
	index1 := 0
	index2 := 0
	intersect := make([]int, 0)
	for index1 < len(nums1) && index2 < len(nums2) {
		if nums1[index1] > nums2[index2] {
			index2++
		} else if nums1[index1] < nums2[index2] {
			index1++
		} else {
			intersect = append(intersect, nums1[index1])
			index1++
			index2++
		}
	}
	return intersect
}