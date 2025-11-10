package main

import (
	"fmt"
	"log"

	// datafile "source/week11/cmd/ch05-ex02"
	datafile "github.com/headfirstgo/datafile"
)

func main() {
	// GetFloats 파일에 동적 슬라이스를 반환하는 게 아닌 배열을 반환한다면
	// 텍스트 파일에 라인이 늘어나면 인덱스 에러가 난다.
	// 동적 슬라이스는 라인이 늘어나도 문제가 없다.
	weights, err := datafile.GetFloats("data.txt")

	if err != nil {
		log.Fatal(err)
	}

	sum := 0.0

	for i := 0; i < len(weights); i++ {
		sum += weights[i]
	}
	fmt.Println("Average : ", sum/float64(len(weights)))
}
