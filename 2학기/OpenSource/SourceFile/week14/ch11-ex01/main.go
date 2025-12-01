package main

import "fmt"

//defer -> 지연시켜놓고 나중에 실행시켜주는 키워드
//함수 안에 넣어두면 함수가 끝날 때까지 지연시켰다가 실행 시켜줌
//위치가 중요한 키워드임

// Pokemon 인터페이스 정의
type Pokemon interface { //규격? 이 4가지의 함수를 구현하면 Pokemon 타입이 되는 것
	//밑 포켓몬 타입을 파라미터로 받는 것을 Charmander로 하면 다른 구조체들은 타입이 맞지 않아 에러가 남
	//이 4가지를 구현하지 않는다면 Pokemon 타입이 될 수 없음
	//아구몬은 디지몬이지만 4가지만 구현하면 포켓몬 타입이 될 수 있음
	//덕타이핑 : 유연한 성격을 가진 go가 위 아구몬 얘기처럼 구현만 하면 인정해주는 특징이 있음
	Name() string
	Type() string
	Attack() int
	Defense() int
}

// Charmander 구조체
type Charmander struct { //배열은 같은 타입, 구조체는 필드의 집합체(다양한 자료형 가능)
	hp int
}

func (c Charmander) Name() string { //c라는 리시버, 리시버가 있기 때문에 Name은 메서드 string은 리턴 타입 리시버 없으면 일반 함수
	return "리자드"
}

func (c Charmander) Type() string {
	return "불꽃"
}

func (c Charmander) Attack() int {
	return 52
}

func (c Charmander) Defense() int {
	return 43
}

// Squirtle 구조체
type Squirtle struct {
	hp int
}

func (s Squirtle) Name() string {
	return "꼬부기"
}

func (s Squirtle) Type() string {
	return "물"
}

func (s Squirtle) Attack() int {
	return 48
}

func (s Squirtle) Defense() int {
	return 65
}

// Bulbasaur 구조체
type Bulbasaur struct {
	hp int
}

func (b Bulbasaur) Name() string {
	return "이상해씨"
}

func (b Bulbasaur) Type() string {
	return "풀"
}

func (b Bulbasaur) Attack() int {
	return 49
}

func (b Bulbasaur) Defense() int {
	return 49
}

// Pokemon을 파라미터로 받는 함수
func printPokemonInfo(p Pokemon) {
	fmt.Printf("%s (%s)\n", p.Name(), p.Type())
	fmt.Printf("공격: %d, 방어: %d\n\n",
		p.Attack(), p.Defense())
}

func main() {
	charmander := Charmander{hp: 39}
	squirtle := Squirtle{hp: 44}
	bulbasaur := Bulbasaur{hp: 45}

	// 모두 Pokemon 인터페이스로 사용 가능
	printPokemonInfo(charmander)
	printPokemonInfo(squirtle)
	printPokemonInfo(bulbasaur)
}
