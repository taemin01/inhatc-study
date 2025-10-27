package main

import (
	"fmt"
)

func swap(first *int, second *int) { //포인터 변수로 메모리 번지 주소를 받음
	temp := 0
	temp = *first //값 참조하여 스왑
	*first = *second
	*second = temp
}

func double(number *int) { //포인터 변수로 메모리 번지 주소를 받음
	*number *= 2
}

func main() {
	var a, b int = 1, 5
	fmt.Println(a, b)
	swap(&a, &b) //주소 넘김
	fmt.Println(a, b)

	number := 8
	double(&number)
	fmt.Println(number)
}
