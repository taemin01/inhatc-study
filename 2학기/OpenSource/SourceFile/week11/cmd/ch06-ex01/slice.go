package main

import "fmt"

func main() {
	//slice 실습
	//배열을 슬라이스 [:3] 해서 넣으면 복사가 되지만 얕은 복사로 참조하기 때문에 슬라이스든 배열이든
	//둘 중 하나를 바꾸면 둘 다 바뀐다. 깊은 복사가 아님
	subjects := [5]string{"Go", "Python", "Javascript", "Linux", "Java"} //zero value index -> [1] 출력 시 빈 칸 나옴
	slice1 := subjects[:3]
	subjects[0] = "Golang"
	// slice1[0] = "Java"

	for _, susubject := range subjects {
		fmt.Println(susubject)
	}

	for i := 0; i < len(slice1); i++ {
		fmt.Println("slices[", i, "] : ", slice1[i])
	}
}
