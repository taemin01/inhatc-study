package main

import (
	"fmt"
	"log"
	"os"
	"strconv"
)

func mean(numbers ...float64) float64 {
	var sum float64 = 0
	for _, number := range numbers {
		sum += number
	}
	return sum / float64(len(numbers))
}

func main() {
	arguments := os.Args[1:] //사용자가 몇 개 입력할지 모르기 때문에 1:~ 슬라이스 진행 1부터인 이유는 명령어 다음에 오는 것부터 담기 위해서 1부터 그 뒤까지 슬라이스
	var numbers []float64
	for _, argument := range arguments {
		number, err := strconv.ParseFloat(argument, 64)
		if err != nil {
			log.Fatal(err)
		}
		numbers = append(numbers, number)
	}
	fmt.Printf("평균 고기 소비량: %0.2f\n", mean(numbers...))
}
