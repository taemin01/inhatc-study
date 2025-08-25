#pragma wrining(disable: 4996)
#include <stdio.h>

int main() {
    int i, j, array[3][4];
    // 사이즈 구하는 부분 이해하기
    int iSize = sizeof(array) / sizeof(*array);
    int jSize = sizeof(*array) / sizeof(*array[0]);

    for(i = 0; i < iSize; i++) {
        for(j = 0; j < jSize; j++) {
            printf("정수 입력 : ");
            scanf("%d", &array[i][j]);
        }
    }

    for(i = 0; i < iSize; i++) {
        for(j = 0; j < jSize; j++) {
            printf("%d\t", array[i][j]);
        }
        printf("\n");
    }
    printf("\n");

    return 0;
}