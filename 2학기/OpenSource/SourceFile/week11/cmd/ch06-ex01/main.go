package main

import "fmt"

func main() {
	//slice 실습
	subject := []string{"Go", "", "Javascript"} //zero value index -> [1] 출력 시 빈 칸 나옴

	for _, subsubject := range subject {
		fmt.Println(subsubject)
	}
}
