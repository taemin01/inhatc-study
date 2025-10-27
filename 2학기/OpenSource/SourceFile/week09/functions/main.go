package main

import (
	"fmt"
)

func swap(first *int, second *int) {
	temp := 0
	temp = *first
	*first = *second
	*second = temp
}

func main() {
	var a, b int = 1, 5
	fmt.Println(a, b)
	swap(&a, &b) //주소 넘김
	fmt.Println( a, b)
}
