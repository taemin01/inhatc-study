package main

import "fmt"

func abc(channel chan string) {
	channel <- "내 고향 스페셜\n"
	channel <- "KBS 뉴스 광장\n"
	channel <- "인간 극장\n"
}

func def(channel chan string) {
	channel <- "건강의 재구성(재)\n"
	channel <- "오늘N\n"
	channel <- "찾아가는 꾸러기 교실\n"
}

func main() {
	//채널을 통해 순서를 가지도록 동기화 하는 것
	//abc def의 순서가 보장이 된다.
	channel1 := make(chan string)
	channel2 := make(chan string)
	go abc(channel1)
	go def(channel2)
	fmt.Print(<-channel1)
	fmt.Print(<-channel2)
	fmt.Print(<-channel1)
	fmt.Print(<-channel2)
	fmt.Print(<-channel1)
	fmt.Print(<-channel2)
	fmt.Println()
}
