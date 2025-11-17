package main

import (
	"fmt"
	"log"

	"github.com/headfirstgo/datafile"
)

func main() {
	lines, err := datafile.GetStrings("../votes.txt")
	if err != nil {
		log.Fatal(err)
	}
	//map은 파이썬의 딕셔너리와 동일
	counts := make(map[string]int) // make로 생성하면 내부 자료구조가 할당 된 빈 map := 초기화까지 타입 추론과 함께 생성
	// var counts1 = map[string]int 이 선언은 타입만 선언하고 초기화하지 않아서 nil 값의 map이 된다.
	// ranks = make(map[string]int)
	// ranks := make(map[string]int)

	for _, line := range lines {
		counts[line]++
	}

	for name, count := range counts {
		fmt.Println(name, " : ", count)
	}
}
