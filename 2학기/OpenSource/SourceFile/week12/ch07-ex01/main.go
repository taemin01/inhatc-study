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

	// var names []string
	// var counts []int

	counts := make(map[string]int)
	// var counts1 = map[string]int
	// ranks = make(map[string]int)
	// ranks := make(map[string]int)

	for _, line := range lines {
		counts[line]++
	}

	for name, count := range counts {
		fmt.Println(name, " : ", count)
	}
}
