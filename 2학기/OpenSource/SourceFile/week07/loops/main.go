package main

import (
    "fmt"
)

func main(){

    // for문
    for i:=1; i<=5; i++ {
        fmt.Println(i)
    }

    fmt.Println("\n")

    // go언어에서의 while문
    // go언어는 키워드 늘리기 싫어서 while 대신 for로 사용
    i := 1
    for i <= 5 {
        fmt.Println(i)
        i++
    }

    fmt.Println("\n")

    /*
    추가로
    continue
    break
    이는 파이썬과 동일하게 동작하므로
    시험에 나오니 외울것
    */

    for i:=1; i<=5; i++ {
        fmt.Println(i)
        if i == 3 {
            break
        }
    }

    fmt.Println("\n")

    for i:=1; i<=5; i++ {
        if i % 2 == 0 {
            continue
        }
        fmt.Println(i)
    }
}