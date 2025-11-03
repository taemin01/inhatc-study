package ch04ex05

import (
	"fmt"
	"log"
	floatValid "source/week09/modules"
)

func main() {
	fmt.Print("실수 입력 : ")
	score, err := floatValid.GetFloat()

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
