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

	var names []string
	var counts []int

	for _, line := range lines {
		matched := false

		for i, name := range names {
			if name == line {
				counts[i]++
				matched = true
			}
		}

		if !matched { //처음 나온 이름 추가 로직
			names = append(names, line)
			counts = append(counts, 1)
		}
	}

	for i, name := range names {
		fmt.Println(name, " : ", counts[i])
	}
}
