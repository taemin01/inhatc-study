#include <stdio.h>

int main() {
    volatile int flag = 0;
    //다른 스레드나 인터럽트 서비스 루틴이 변수 falg를 변경할 수 있다고 가정
    //flag의 값이 변경되지 않게 하는 컴파일러 최적화

    while(!flag) {
        //대기 상태
    }

    printf("Flag is now : %d\n", flag);
    return 0;
}