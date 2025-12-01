package main

import (
	"fmt"
)

func main() {
	//defer는 스택처럼 쌓이는 것
	//Last In First Out
	//파일 읽는 중 출력 후 마지막에 쌓인 3부터 2 1 순서로 감
	//쓰다보면 요긴하게 쓰임 시험에 나오니까 알아두기
	defer fmt.Println("1")
	defer fmt.Println("2")
	defer fmt.Println("3")

	fmt.Println("파일 읽는 중...")
}
