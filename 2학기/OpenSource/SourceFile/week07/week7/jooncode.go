package main

import (
    "fmt"
    "reflect"
    "time"
    "strings"
    "bufio"
    "os"
    "log"
    "strconv"
)


/*
파일 생성: touch main.go
열기: go mod init loops
*/

// conversions: 변환
// go는 묵시적형변환이 없으므로 무조건 명시하기
// 바꿀타입(변수명) -> 파이썬과 같이 형변환 이뤄짐.(원본객체는 그대로, inplace=False)

func main() {

    var length float64 = 3.2
    var width int = 2
    fmt.Println("면적은", int(length)*width)
    fmt.Println("length > width?", int(length) > width)
    fmt.Println("형변환", reflect.TypeOf(int(length)))
    fmt.Println("원본", reflect.TypeOf(length))
    
    // fmt.Println("Hello world!")

    var now time.Time = time.Now() // 타입 조심하기
    var month time.Month = now.Month()
    var day int = now.Day()
    fmt.Println(month, day)


    broken := "G# r#cks!"
    replacer := strings.NewReplacer("#", "o") // 문자열에 속한 #를 o로 바꾸는 함수 할당하기
    fixed := replacer.Replace(broken) // Go rocks!
    fmt.Println(fixed)


    /*
    var b에서 타입을 명시하고 싶은데 변수 int가 호출되며 에러 발생.
    변수명 지을 때 되도록 예약어(패키지, 타입, 등) 사용 금지
    내가 지은 변수명으로 기존 것들을 가리는 것을 쉐도잉이라 함.
    
    var int int = 99
    fmt.Println(int)
    var b int = 8
    var fmt string = "test"
    */

    
    
    var a string = "60"
    // fmt.Println(a > 70) // 타입이 메칭이 안 된다는 에러 발생
    score := strconv.ParseFloat(a, 64) // a를 float형으로 변환

    fmt.Println(a > 70)

    if a > 70 {
        shadow := "test"
    }
    // fmt.Println(shadow) // 에러 발생. 코드블록 {} 안에서 선언된 변수는 블록 안에서만 사용 가능. 이를 쉐도우라 함.
    // 그러므로 {} 바깥에서 선언하고 {} 안에서는 할당하는 방식으로 진행하기
    
    
    reader := bufio.NewReader(os.Stdin) // 터미널에서 글자 읽을 준비
    input, err := reader.ReadString('\n') // 줄바꿈이 들어올 때까지 글자 읽기
    // ReadString 함수는 리턴값이 두 개이므로 input, _ := 하거나 _ 대신 err로 받아서 사용

    strconv.ParseInt(input, 16, 32) //16 -> 문자열 진법, 32 -> 리턴값 크기
    
    // fmt.Println(err) // 에러 출력
    if err != nil {
        log.Fatal(err) // 에러 출력 후 프로그램 종료
    } // else if {} else {} 와 같이 소괄호 빼면 타 언어와 같은 if문, &&과 || 연산자로 and, or 연산 진행

    input = strings.TrimSpace(input) // 문자열 주위에 붙은 공란 및 탭 키 등 제거
    // 파이썬으로 치면 input = input.strip()
    fmt.Println(input)
}