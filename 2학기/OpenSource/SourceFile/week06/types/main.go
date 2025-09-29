package main

import (
	"fmt"
	"reflect"
)

func main() {
	//변수 이름은 숫자로 시작할 수 없음
	//긴 변수 이름도 쓸 수 있음
	//단어 바뀔 땐 카멜 표기법으로 해야함 문제는 없지만 권장(관례)
	//외부 보이는 건 TotalUnits 외부 안 보이는 건 totalUnits
	//index -> i 반복문
	//권장 문법들

	//var name string //1
	//name = "An Taemin"

	//var name = "An Taemin" //2

	name := "An Taemin" //3 := 는 선언할 때 사용하는 것 이미 선언 후에는 할당하는 것으로 = 를 사용

	var num int32
	var str string

	fmt.Println("name :", name, "\ntype :", reflect.TypeOf(name))
	fmt.Println(num, "\n", reflect.TypeOf(num))
	fmt.Println(str, "\n", reflect.TypeOf(str))
}
