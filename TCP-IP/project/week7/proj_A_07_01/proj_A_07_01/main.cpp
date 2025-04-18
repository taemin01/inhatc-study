#include <stdio.h>

void name_print(const char * Name); //�Լ� ���� - ������Ÿ���� �����Ѵ�. ����ϱ� ���� ������� �ؾ���(�����Ϸ����� ������Ÿ���� ���� �˷�����Ѵ�.)
int counter; //���������� �ʱ�ȭ�� �Ǵµ� ���������� �ȵǱ� ������ �ʱ�ȭ ����� ��

struct CIP {
	int no;
	char ch;
	double avg;
};

struct CIP cip; //����ü ������ ���� ����ü�� �ִ� �ɹ������� ����� �� �ִ�.
struct CIP* cip2;

int main() { //������ �ü������ ��ȯ�Ѵ�.
	int* cnt; //�����ͺ���
	cnt = &counter; //counter ���� �ּҸ� cnt �����ͺ����� �ִ´�.

	cip.no = 7;
	cip.ch = 'k';
	cip.avg = 0.2;

	cip2->no = 7; //����ü�� ����ϴ� ������ ������ ���� -> �� ����� ǥ���ؾ� �Ѵ�.
	cip2->ch = 'k';
	cip2->avg = 0.2;

	printf("cip2 avg = %f, no = %d, ch = %c", cip2->avg, cip2->no, cip2->ch);

	name_print("a");
	printf("counter : %d\n", *cnt);
	name_print("b");
	printf("counter : %d\n", *cnt);
	name_print("c");
	printf("counter : %d\n", *cnt);
	return 0;
}

void name_print(const char * Name) {
	for (int i = 0; i < 5; i++) {
		printf("%s %d\n",Name, i);
		//int counter = 0;
		counter++;
	}
	printf("local : %d\n", counter);
}