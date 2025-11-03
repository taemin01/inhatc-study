package main

// go.mod 파일은 상위에 하나만 두는 것이 좋음
// SourceFile 위치에서 go mod init source로 go.mod 파일을 만들었으며
// week10/packages 밑에 go mod init test를 하니 밑에 greeting에 대한 import문은 에러가 발생했음
// 상 하위에 둘 다 존재하면 에러가 나며, go.mod는 상위 진입점?이라는 것을 알려주는 파일로써 하나만 존재하는 걸 권장
import (
	floatValid "source/week09/modules"
	greeting "source/week10/pacakges"
)

func main() {
	greeting.Hello()
	floatValid.GetFloat()
}
