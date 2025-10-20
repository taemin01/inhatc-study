package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	// name := "Go developers"
	// fmt.Println("Git/Github for", name)
	// number := 7
	// fmt.Printf("Git/Github for %d\n", number)

	reader := bufio.NewReader(os.Stdin)
	input, err := reader.ReadString('\n') // 줄바꿈이 들어올 때까지 글자 읽기
	fmt.Println('2')
}