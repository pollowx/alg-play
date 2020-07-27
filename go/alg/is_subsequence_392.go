package main

import "fmt"

func main() {
	s := "abc"
	t := "ahbgdc"

	fmt.Println(isSubsequence(s, t))
}

func isSubsequence(s string, t string) bool {
	if len(s) <= 0 {
		return true
	}
	sLeft, tLeft := 0, 0

	for sLeft < len(s) && tLeft < len(t) {
		if s[sLeft] == t[tLeft] {
			sLeft++
		}
		tLeft++
	}
	return sLeft == len(s)
}
