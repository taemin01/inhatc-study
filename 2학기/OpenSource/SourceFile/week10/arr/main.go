package main

import (
	"fmt"
	"reflect"
)

// 파이썬 리스트 -> 정수 실수 타입 상관없이 넣을 수는 있다 사용할 때는 불편(느림) numpy 배열 -> c로 만들어져있음
func main() {
	var arrBool [3]bool
	//배열 리터럴이라고 함 var과 =를 사용할 수 없음 :=를 사용해서 선언해야함
	arrString := [3]string{"avbd", "Agdsfb", "adfb"}

	fmt.Println(arrBool[1])
	fmt.Printf("%#v\n", arrBool) //%#v는 뒤 값까지 보여주는 듯
	fmt.Println(reflect.TypeOf(arrString))

	// for i := 0; i < len(arrString); i++ {
	// 	fmt.Println("bool array : ", i, arrString[i])
	// 	fmt.Println("string array : ", i, arrBool[i])
	// }

	for index, value := range arrString {
		fmt.Println("arrString : ", index, "번째 값 : ", value)
		fmt.Println("arrBool : ", index, "번째 값 : ", arrBool[index])
	}
}
