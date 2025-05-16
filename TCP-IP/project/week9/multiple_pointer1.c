#include <stdio.h>

int add(int a, int b) {
    return a + b;
}

int square(int x) {
    return x * x;
}


int main() {
    //다중 포인터로 활용 잘 하면 쓸만할 듯
    int a;
    int* p;
    int** q;

    a = 100;
    p = &a;
    q = &p;

    printf("a : %d\n", a);
    printf("p : %d\n", *p);
    printf("q : %d\n", **q);

    //함수 포인터
    int (*addFuncPtr)(int, int) = add;
    int sum = addFuncPtr(3, 6);
    printf("add result : %d\n", sum);

    int (*squareFuncPtr)(int) = square;
    int square = squareFuncPtr(6);
    printf("square result : %d\n", square);

    return 0;
}