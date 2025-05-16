#include <stdio.h>

int main() {
    char c = 'P';
    float f = 2.15f;
    double d = 2.15;
    char *pc = &c;
    float *pf = &f;
    double *pd = &d;

    //예제 3번
    int i = 10;
    int *p;

    p = &i;

    printf("c 값 : %c\n", *pc);
    printf("f 값 : %f\n", *pf);
    printf("d 값 : %lf\n", *pd);

    //표준은 void로 넘어오길 바라기에 void로 강제 형변환 하는 것
    //해주지 않아도 문제는 없는 듯
    printf("c 주소 : %p\n", (void*)pc);
    printf("f 주소 : %p\n", (void*)pf);
    printf("d 주소 : %p\n", pd);

    //예제 3
    printf("p = %p\n", p);
    printf("(*p)++ = %d\n", (*p)++);
    printf("*p++ = %d\n", *p++);
    printf("p++ = %p\n", p++);
    
    return 0;
}