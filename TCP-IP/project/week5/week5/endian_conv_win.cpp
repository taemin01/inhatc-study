#include "../../Common.h"

void ErrorHandling(const char* message);

int main(int argc, char* argv[])
{
    WSADATA wsaData;
    unsigned short host_port = 0x1234;
    unsigned short net_port;
    unsigned long host_addr = 0x12345678;
    unsigned long net_addr;

    char addr1[] = "1.2.3.4";
    char addr2[] = "1.2.3.256";
    unsigned long conv_addr;

    //바이트 오더, 네트워크 주소 바이트 오더 변환 실습의 if문 
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
        ErrorHandling("WSAStartup() error!");

    //바이트 오더 실습 코드 부분
    net_port = htons(host_port);
    net_addr = htonl(host_addr);

    printf("Host ordered port: %#x \n", host_port);
    printf("Network ordered port: %#x \n", net_port);
    printf("Host ordered address: %#lx \n", host_addr);
    printf("Network ordered address: %#lx \n", net_addr);

    //IPv4 주소 문자열을 inet_addr을 통해 네트워크 바이트 오더로 변환
    //유효한 주소여야 변환이 가능하다. 1.2.3.256은 유효하지 않아 에러 메시지를 출력한다.
    conv_addr = inet_addr(addr1); 
    if (conv_addr == INADDR_NONE)
        printf("Error occurred!\n");
    else
        printf("Network ordered integer addr : %#lx \n", conv_addr);

    conv_addr = inet_addr(addr2);
    if (conv_addr == INADDR_NONE)
        printf("Error occurred!\n");
    else
        printf("Network ordered integer addr : %#lx \n", conv_addr);

    WSACleanup();
    return 0;
}

void ErrorHandling(const char* message)
{
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}