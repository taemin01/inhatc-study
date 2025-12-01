package main

import (
	"fmt"
	"time"
)

func say(s string) {
	for i := 0; i < 5; i++ {
		time.Sleep(200 * time.Millisecond)
		fmt.Println(s)
	}
}

func main() {
	//go routine -> 쿠버네틱스 도커 등 go로 만들어진거임 go로는 경량 쓰레드를 만들기 쉬움
	//발로란트도 백엔드가 go로 되어있음
	//쓰레드가 비동기 병렬 처리로 각자 일하지만 스케쥴링을 해줘야함 멀티 코어 프로세서가 필요
	//작업 순서가 보장이 되진 않아서 실행 할 때마다 순서가 바뀔 가능성이 큼
	//비동기식으로 돌리기 위해 go 라는 키워드를 앞에 붙이면 비동기로 처리함
	//둘 다 go 키워드를 사용하려면 time.Sleep으로 기다려줘야 main이 먼저 끝나지 않고 비동기 루틴이 실행 됨
	//고루틴은 별도로 실행되고 메인은 기다릴 것이 없기에 타입 슬립을 주지 않으면 기다릴 것이 없기에 바로 종료되어서 아무것도 출력 되지 않음
	start := time.Now()
	fmt.Println("start time : ", start)
	go say("고루틴")
	say("메인")
	fmt.Println("end time", time.Since(start))
}
