package main

import (
	"fmt"
	"os"
)

func main() {
	numbers := os.Args[1:] //go run main.go 77.7 56.5 34.2 하면 출력을 슬라이스 진행 후 해준다
	//함수 매개변수도 ...string 이렇게 받으면 여러개를 받을 수 있는 듯

	fmt.Println(numbers)
}
