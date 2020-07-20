package main

import "fmt"

func main() {
	array := []int{2, 3, 4}
	target := 6

	fmt.Println(twoSum(array, target))
}

func twoSum(numbers []int, target int) []int {
	if nil == numbers || len(numbers) <= 0 || target < numbers[0] {
		return []int{-1, -1}
	}
	left, right := 0, len(numbers)-1
	for left < right {
		if numbers[left]+numbers[right] > target {
			right--
		} else if numbers[left]+numbers[right] < target {
			left++
		} else {
			return []int{left + 1, right + 1}
		}
	}
	return []int{-1, -1}
}
