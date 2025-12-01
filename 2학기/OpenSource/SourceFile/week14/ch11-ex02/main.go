package main

import "fmt"

func safeDivide(a, b int) {
	defer func() {
		//키워드 : interface, defer, panic, recorver 시험에 나옴
		//err에 panic 메시지가 담기는 듯 defer로 뒤로 미뤄놨기에 safeDivide가
		//끝나면서 err에 panic 메시지가 담겨오기에 출력해주고 recover로 복구하는 것
		//예외처리임
		if err := recover(); err != nil { //panic으로 끝나고 recover로 복구하기에 프로그램 계속 실행됨이 출력 되는 것임
			fmt.Println("에러 발생:", err)
		}
	}()

	if b == 0 {
		panic("0으로 나눌 수 없습니다!")
	}

	result := a / b
	fmt.Println("결과:", result)
}

func main() {
	fmt.Println("첫 번째 호출")
	safeDivide(10, 2)

	fmt.Println("\n두 번째 호출")
	safeDivide(10, 0)

	fmt.Println("\n프로그램 계속 실행됨")
}
