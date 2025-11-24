package main

import (
	"fmt"
	"log"
	calendar "source/week13/pkg/calender"
)

func main() {
	today := calendar.Date{}

	err := today.SetYear(2019)
	if err != nil {
		log.Fatal(err)
	}
	err = today.SetMonth(5)
	if err != nil {
		log.Fatal(err)
	}
	err = today.SetDay(27)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(today.Year())
	fmt.Println(today.Month())
	fmt.Println(today.Day())
	fmt.Println(today.Year(), "년 ", today.Month(), "월", today.Day(), "일")

}
