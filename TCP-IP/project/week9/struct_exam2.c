#include <stdio.h>
#include <string.h>

struct Student {
    char name[50];
    int id;
    float gradePoints;
};

int main() {
    struct Student student1, student2;
    char uname[50]; // 버퍼 크기를 name과 동일하게 설정

    printf("학생1 이름 : ");
    fgets(uname, sizeof(uname), stdin);
    uname[strcspn(uname, "\n")] = '\0'; // 개행 문자 제거
    strcpy(student1.name, uname);

    printf("학생1 학번 : ");
    scanf("%d", &student1.id);
    printf("학생1 학점 : ");
    scanf("%f", &student1.gradePoints);
    while ((getchar()) != '\n'); // 입력 버퍼 비우기
    printf("\n");

    printf("학생2 이름 : ");
    fgets(uname, sizeof(uname), stdin);
    uname[strcspn(uname, "\n")] = '\0'; // 개행 문자 제거
    strcpy(student2.name, uname);

    printf("학생2 학번 : ");
    scanf("%d", &student2.id);
    printf("학생2 학점 : ");
    scanf("%f", &student2.gradePoints);

    printf("\n학생 1의 정보\n");
    printf("이름 : %s\n", student1.name);
    printf("학번 : %d\n", student1.id);
    printf("학점 : %.2f\n\n", student1.gradePoints);

    printf("학생 2의 정보\n");
    printf("이름 : %s\n", student2.name);
    printf("학번 : %d\n", student2.id);
    printf("학점 : %.2f\n", student2.gradePoints);

    return 0;
}
