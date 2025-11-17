package main

import "fmt"

type subscriber struct {
	name  string
	price int
}

func applyPrice(s *subscriber) { //여기서 그 주소에 대한 price 필드를 10000으로 할당
	s.price = 10000
	s.name = "Inha"
}

func main() {
	var s1 subscriber
	var p *subscriber = &s1
	applyPrice(&s1) //sub s1의 주소 인자로 전송

	fmt.Println("name : ", s1.name, " / price : ", s1.price)     //구조체로 직접 접근
	fmt.Println("name : ", p.name, " / price : ", p.price)       //포인터로 접근
	fmt.Println("name : ", (*p).name, " / price : ", (*p).price) //포인터로 접근
}
