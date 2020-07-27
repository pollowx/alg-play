package main

import "fmt"

func main() {
	array := []int{3, 4, 5, 1, 2}
	array1 := []int{2, 2, 2, 0, 1}

	fmt.Println(minArray(array))
	fmt.Println(minArray(array1))
}

func minArray(numbers []int) int {
	if nil == numbers || len(numbers) <= 0 {
		return 0
	}
	left, right := 0, len(numbers)-1
	for left < right {
		if numbers[left] < numbers[right] {
			break
		} else if numbers[left] > numbers[right] {
			left++
		} else {
			right--
		}
	}
	return numbers[left]
}
