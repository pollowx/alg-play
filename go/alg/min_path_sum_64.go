package main

import "fmt"

func main() {
	arr := [][]int{
		{1, 3, 1},
		{1, 5, 1},
		{4, 2, 1},
		{1, 2, 5},
	}

	fmt.Println(minPathSum(arr))
	fmt.Println(minPathSum2(arr))
}

func minPathSum(grid [][]int) int {
	if len(grid) == 0 || len(grid[0]) == 0 {
		return 0
	}
	length := len(grid[0])
	res := make([]int, length)
	res[0] = grid[0][0]

	for i := 1; i < length; i++ {
		res[i] = res[i-1] + grid[0][i]
	}
	for i := 1; i < len(grid); i++ {
		res[0] += grid[i][0]
		for j := 1; j < length; j++ {
			cad1 := res[j-1] + grid[i][j]
			cad2 := res[j] + grid[i][j]

			if cad1 < cad2 {
				res[j] = cad1
			} else {
				res[j] = cad2
			}
		}
	}
	return res[length-1]
}

func minPathSum2(grid [][]int) int {
	if len(grid) == 0 || len(grid[0]) == 0 {
		return 0
	}
	max, min := 0, 0
	rowIsSmall := false
	if len(grid) > len(grid[0]) {
		max = len(grid)
		min = len(grid[0])
	} else {
		rowIsSmall = true
		max = len(grid[0])
		min = len(grid)
	}
	res := make([]int, min)
	res[0] = grid[0][0]

	for i := 1; i < min; i++ {
		temp := grid[i][0]
		if rowIsSmall {
			temp = grid[0][i]
		}
		res[i] = res[i-1] + temp
	}

	for i := 1; i < max; i++ {
		temp := 0
		if rowIsSmall {
			temp = grid[i][0]
		} else {
			temp = grid[0][i]
		}
		res[0] += temp
		for j := 1; j < min; j++ {
			temp := grid[i][j]
			if rowIsSmall {
				temp = grid[j][i]
			}
			cad1 := res[j-1] + temp
			cad2 := res[j] + temp

			if cad1 < cad2 {
				res[j] = cad1
			} else {
				res[j] = cad2
			}
		}
	}
	return res[min-1]
}
