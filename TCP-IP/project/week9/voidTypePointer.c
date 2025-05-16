#include <stdio.h>

int main () {
    int m_int = 15;
    double m_double = 3.1477;
    char m_char = 'M';
    void* mp;

    //배열과 포인터 간접 참조
    //포인터 변수가 가리키는 주소는 직접주소, 즉 메모리 위치이고
    //*을 붙이면 그 주소가 가리키는 위치 값을 가져오는 것, 이 과정을 간접 접근이라고 한다.
    //*(a+1) 이렇게 하면 자료형 만큼 증가해서 다음 번지 주소를 먼저 가져오고
    //그 후에 그 주소를 간접 접근 하기에 밑 a 배열에서 값 4가 나온다.
    //인덱스랑 동일하다고 설명할 수 있다.
    //요약
    //p → 직접 주소(메모리 위치)
    //*p → 간접 접근(그 주소에 저장된 값)
    int a[5] = {2, 4, 6, 8, 22};
    printf("*a : %d, a[0] : %d\n", *a, a[0]);

    mp = &m_int;
    printf("m_int %d\n", *(int*)mp); //(int*) 자체는 캐스트이고 *(int*)로 하면 int* mp를 역참조 하여 해당 값을 가져올 수 있다.

    mp = &m_double;
    printf("m_double %.1f\n", *(double*)mp);

    mp = &m_char;
    printf("m_char %c\n", *(char*)mp);

    return 0;
}