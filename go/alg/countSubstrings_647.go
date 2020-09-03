package main

import "fmt"

func main() {
	fmt.Println(countSubstrings("abc"))
}

func countSubstrings(s string) int {
	if len(s) <= 0 {
		return 0
	}
	assembleChar := "#"
	for i := 0; i < len(s); i++ {
		assembleChar += string(s[i]) + "#"
	}
	maxLcp := make([]int, len(assembleChar))
	answer := 0
	index := 0
	pR := 0

	for i := 0; i < len(assembleChar); i++ {
		maxLcp[i] = 1
		if pR > i {
			maxLcp[i] = min(maxLcp[2*index-i], pR-i)
		}
		for i+maxLcp[i] < len(assembleChar) && i-maxLcp[i] > -1 {
			if assembleChar[i+maxLcp[i]] == assembleChar[i-maxLcp[i]] {
				maxLcp[i] ++
			} else {
				break
			}
		}

		if i + maxLcp[i] > pR {
			pR = i + maxLcp[i]
			index = i
		}

		answer += maxLcp[i] / 2
	}
	return answer
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
