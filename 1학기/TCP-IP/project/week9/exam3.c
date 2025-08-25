#include <stdio.h>

int main() {
    int mainVar = 5;
    printf("main 함수의 초기 mainVar : %d\n", mainVar);

    {
        int mainVar = 10;
        printf("블록 안에서 선언된 blockVar : %d\n", mainVar);

        mainVar = 20;
        printf("블록 안에서 변경된 mainVar : %d\n", mainVar);
    }

    printf("블록 밖에서의 mainVar : %d\n", mainVar);
    return 0;
}