package main

import (
	"fmt"
	"log"
	datafile "source/week11/cmd/ch05-ex01"
)

func main() {
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
