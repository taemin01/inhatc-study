package main

import (
	"fmt"
	"log"
)

func paintNeeded(width float64, height float64) (float64, error) { //(float64, error)로 float값과 error 리턴하게 설계
	if width < 0  {
		return 0, fmt.Errorf("a width of %0.2f is invalid", width)
	}
	if height < 0 {
		return 0, fmt.Errorf("a height of %0.2f is invalid", height) //Errorf는 문자열로 등록해서 에러를 생성하는 느낌
	}
	area := width * height
	return area / 10.0, nil //nil은 에러 메시지가 없다는 의미로도 사용 가능
}

func main() {
	amount, err := paintNeeded(5.2, 3.5)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("%0.2f liters needed\n", amount)
	amount, err = paintNeeded(4.2, 3.0)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("%0.2f liters needed\n", amount)
}
