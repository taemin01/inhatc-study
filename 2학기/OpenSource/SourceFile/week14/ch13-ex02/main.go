package main

import "fmt"

func main() {
	//쓰레드 == 고루틴으로 이해하면 됨
	//경량 쓰레드
	//chan 키워드가 채널인 듯
	ch := make(chan int) // int 채널 생성

	go func() {
		ch <- 123 // 채널에 값 보내기
	}()

	x := <-ch // 채널에서 값 받기 / 받을 때 <- 라는 것을 이용해야 함
	fmt.Println(x)
}
