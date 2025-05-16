//예제 4번
#include <stdio.h>

void function2(int x, int y) {
    printf("매개변수가 있고 반환값이 없는 함수\n");
    printf("두 개의 매개변수 : %d, %d\n", x, y);
    printf("반환값 없음 void\n");
}

int main() {
    int a = 10, b = 30;
    function2(a, b);
    return 0;
}