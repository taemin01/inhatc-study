package main

import "fmt"

// 파이썬 리스트 -> 정수 실수 타입 상관없이 넣을 수는 있다 사용할 때는 불편(느림) numpy 배열 -> c로 만들어져있음
func main() {
	var arrBool [3]bool
	var arrInt [3]int

	fmt.Println(arrBool[1])
	fmt.Println(arrInt[1])
	arrInt[1]++
	arrInt[1]++
	fmt.Println(arrInt[1])
}
