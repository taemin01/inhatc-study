#include <stdio.h>

void increment() {
    int count = 0; //static을 통해 프로그램 종료 시 메모리에서 사라지기 때문에 static을 제거하면 지역변수로 변해 매번 0으로 초기화된다.
    // static int count = 0; // static을 사용하면 프로그램 종료 시 메모리에서 사라지지 않고, 다음 호출 시에도 값이 유지된다.
    count ++;
    printf("Static count : %d\n", count);
}

int main() {
    increment();
    increment();
    increment();
    return 0;
}