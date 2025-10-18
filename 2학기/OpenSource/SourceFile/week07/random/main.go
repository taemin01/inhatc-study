package main

import (
    "fmt"
    "math/rand"
)

func main(){
    dice := rand.Intn(6) + 1 // 더하기1을 해야 1 ~ 6 사이 난수값 생성
    fmt.Println(dice)
}