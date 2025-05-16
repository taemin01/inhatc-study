#include <stdio.h>
#include <stdarg.h>

double average(int x[]) {
    int i, sum = 0;
    for(i = 0; i < sizeof(x)/sizeof(int); i++) {
        sum += x[i];
    }

    return (sum / 5.0);
}

int sum(int num, ...) {
    va_list args;
    int total = 0;

    va_start(args, num); // num 이후의 가변 인자 목록을 초기화

    for(int i = 0; i < num; i++) {
        total += va_arg(args, int); // 다음 인자를 가져옴
    }

    va_end(args); // 가변 인자 목록을 정리

    return total;
}



int main() {
    printf("매개변수 3 : 2 + 3 + 4 = %d\n", sum(3, 2, 3, 4));
    printf("매개변수 2 : 5 + 4 = %d\n", sum(2, 5, 4));
    printf("매개변수 5 : 2 + 3 + 4 + 5 + 6 = %d\n", sum(5, 2, 3, 4, 5, 6));

    // int numbers[5];
    // numbers[0] = 11;
    // numbers[1] = 22;
    // numbers[2] = 33;
    // numbers[3] = 44;
    // numbers[4] = 55;

    // for(int i = 0; i < sizeof(numbers)/sizeof(int); i++) {
    //     printf("numbers[%d] = %d\n", i, numbers[i]);
    // }

    int number1[5] = {11, 22, 33, 44, 55};
    int number2[] = {11, 32, 13, 53, 55};
    int number3[5] = {0};
    int number4[5] = {3, 1};

    for(int i = 0; i < sizeof(number1)/sizeof(int); i++) {
        printf("%d ", number1[i]);
        
    }
    printf("\n");
    for(int i = 0; i < sizeof(number2)/sizeof(int); i++) {
        printf("%d ", number2[i]);
    }
    printf("\n");
    for(int i = 0; i < sizeof(number3)/sizeof(int); i++) {
        printf("%d ", number3[i]);
        
    }
    printf("\n");
    for(int i = 0; i < sizeof(number4)/sizeof(int); i++) {
        printf("%d ", number4[i]);
        
    }
    printf("\n");

    double avg;
    int base[] = {3, 7, 2, 4, 5};

    avg = average(base);
    printf("배열의 평균 : %.2lf\n", avg);

    return 0;
}