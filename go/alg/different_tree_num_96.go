package main

import "fmt"

func numTrees(n int) int {
	if n <= 1 {
		return 1
	}
	dp := make([]int, n+1)
	dp[0], dp[1] = 1, 1

	for i := 2; i <= n; i++ {
		for j := 1; j <= i; j++ {
			dp[i] += dp[j-1] * dp[i-j]
		}
	}
	return dp[n]
}

func main() {
	for i := 1; i <= 20; i++ {
		fmt.Println(numTrees(i))
		fmt.Println(numTrees2(i))
		fmt.Println(numTrees3(i))
	}
}

func numTrees2(n int) int {
	res := []int{1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700, 1767263190, 6564120420,}
	return res[n-1]
}

func numTrees3(n int) int {
	res := map[int]int{
		1: 1, 2: 2, 3: 5, 4: 14, 5: 42, 6: 132, 7: 429, 8: 1430, 9: 4862, 10: 16796,
		11: 58786, 12: 208012, 13: 742900, 14: 2674440, 15: 9694845, 16: 35357670,
		17: 129644790, 18: 477638700, 19: 1767263190, 20: 6564120420,}
	return res[n]
}
