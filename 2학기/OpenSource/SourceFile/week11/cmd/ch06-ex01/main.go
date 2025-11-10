package main

import "fmt"

func main() {
	var subject []string
	subject = make([]string, 3)
	subject[0] = "Go"
	subject[2] = "Javascript"

	for _, subsubject := range subject {
		fmt.Println(subsubject)
	}
}
