package main

// go.mod 파일은 상위에 하나만 두는 것이 좋음
// SourceFile 위치에서 go mod init source로 go.mod 파일을 만들었으며
// week10/packages 밑에 go mod init test를 하니 밑에 greeting에 대한 import문은 에러가 발생했음
// 상 하위에 둘 다 존재하면 에러가 나며, go.mod는 상위 진입점?이라는 것을 알려주는 파일로써 하나만 존재하는 걸 권장
import (
	"fmt"
	"log"

	// floatValid "source/week10/cmd/ch04-ex03"
	greeting "source/week10/pacakges"
	korean "source/week10/pacakges/test"

	keyboard "github.com/headfirstgo/keyboard" //go get github.com/headfirstgo/keyboard 에러 없이 가능 go.mod에 추가 됨
)

func main() {
	greeting.Hello()
	korean.Anyung()
	korean.Anyunghaseayo()
	score, err := keyboard.GetFloat()

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
