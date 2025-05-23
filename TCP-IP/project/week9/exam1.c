#include <stdio.h>

double triangleArea(double base, double height);

double triangleArea(double base, double height) {
    return (base * height) / 2.0;

}

int main() {
    double base = 10.0, height = 5.0;
    double area = 0.0;

    area = triangleArea(base, height);

    printf("삼각형의 밑변이 %.2lf, 높이가 %.2lf인일 때\n", base, height);
    printf("삼각형의 넓이는 %.2lf입니다.\n", area);

    return 0;
}
// int main(){

//     int num_li[5] = {0, 0, 0, 0, 0};
//     for(int i=0; i<5; i++){
        
//         *num_li+=1;
//         printf("%d\n", num_li[0]);
//         printf("num_li[%d]=%d\n", i, num_li[0]);
//     }
//     return 0;
// }