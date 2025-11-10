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

	//슬라이스는 :3 까지 가져오는데 그 뒤에 추가하기 때문에
	//원본 배열의 리눅스 부분이 덮어씌워지는 일이 생긴다
	//배열은 고정 사이즈라서 여러개 추가할 때 인덱스를 벗어나면 추가가 되지 않는다.
	//밑 코드에서 타입스크립트 뒤에 하나를 더 추가하면 배열은 변화가 없다.
	//슬라이스는 동적이라 계속 추가가 가능 (타입에 따른 제로 벨류 존재(float 0, bool false))
	//동적 메모리가 넘어간다면 새로운 공간에 할당함
	slice1 = append(slice1, "SQL", "Typescript", "Bash")

	for _, susubject := range subjects {
		fmt.Println(susubject)
	}

	for i := 0; i < len(slice1); i++ {
		fmt.Println("slices[", i, "] : ", slice1[i])
	}
}
