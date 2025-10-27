// Package keyboard reads user input from the keyboard.
package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
	"log"
	"fmt"
)

// Head First Go PDF chpater 4 124 page
// GetFloat reads a floating-point number from the keyboard.
// It returns the number read and any error encountered.
// 패키지를 만드는 수업 GetFloat()는 온도 입력, 점수 입력 등 실수를 입력받는 프로그램에서 사용 가능한 함수
func GetFloat() (float64, error) {
	reader := bufio.NewReader(os.Stdin)
	input, err := reader.ReadString('\n')
	if err != nil {
		return 0, err
	}

	input = strings.TrimSpace(input)
	number, err := strconv.ParseFloat(input, 64)
	if err != nil {
		return 0, err
	}
	return number, nil
}

func main() {
	fmt.Print("점수 입력 : ")
	score, err := GetFloat()

	if err != nil {
		log.Fatal(err)
	}

	var status string
	if score >= 60 {
		status = "passing"
	} else {
		status = "fail"
	}

	fmt.Printf("%.2f - %s\n", score, status)
}