#include <stdio.h>

void name_print(const char * Name); //함수 정의 - 프로토타입을 정의한다. 사용하기 전에 선언부터 해야함(컴파일러에게 프로토타입을 통해 알려줘야한다.)
int counter; //전역변수는 초기화가 되는데 지역변수는 안되기 때문에 초기화 해줘야 함

struct CIP {
	int no;
	char ch;
	double avg;
};

struct CIP cip; //구조체 변수를 통해 구조체에 있는 맴버변수를 사용할 수 있다.
struct CIP* cip2;

int main() { //메인은 운영체제에게 반환한다.
	int* cnt; //포인터변수
	cnt = &counter; //counter 변수 주소를 cnt 포인터변수에 넣는다.

	cip.no = 7;
	cip.ch = 'k';
	cip.avg = 0.2;

	cip2->no = 7; //구조체를 사용하는 포인터 변수일 때는 -> 로 멤버를 표시해야 한다.
	cip2->ch = 'k';
	cip2->avg = 0.2;

	printf("cip2 avg = %f, no = %d, ch = %c", cip2->avg, cip2->no, cip2->ch);

	name_print("a");
	printf("counter : %d\n", *cnt);
	name_print("b");
	printf("counter : %d\n", *cnt);
	name_print("c");
	printf("counter : %d\n", *cnt);
	return 0;
}

void name_print(const char * Name) {
	for (int i = 0; i < 5; i++) {
		printf("%s %d\n",Name, i);
		//int counter = 0;
		counter++;
	}
	printf("local : %d\n", counter);
}