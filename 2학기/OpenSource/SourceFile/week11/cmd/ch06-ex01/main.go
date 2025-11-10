package main

import "fmt"

func main() {
	//slice 실습
	subjects := []string{"Go", "Python", "Javascript", "Linux", "Csharp"} //zero value index -> [1] 출력 시 빈 칸 나옴
	slice1 := subjects[0:2]

	for _, susubject := range subjects {
		fmt.Println(susubject)
	}

	for i := 0; i < len(slice1); i++ {
		fmt.Println("slices[", i, "] : ", slice1[i])
	}
}
