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

    //����Ʈ ����, ��Ʈ��ũ �ּ� ����Ʈ ���� ��ȯ �ǽ��� if�� 
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
        ErrorHandling("WSAStartup() error!");

    //����Ʈ ���� �ǽ� �ڵ� �κ�
    net_port = htons(host_port);
    net_addr = htonl(host_addr);

    printf("Host ordered port: %#x \n", host_port);
    printf("Network ordered port: %#x \n", net_port);
    printf("Host ordered address: %#lx \n", host_addr);
    printf("Network ordered address: %#lx \n", net_addr);

    //IPv4 �ּ� ���ڿ��� inet_addr�� ���� ��Ʈ��ũ ����Ʈ ������ ��ȯ
    //��ȿ�� �ּҿ��� ��ȯ�� �����ϴ�. 1.2.3.256�� ��ȿ���� �ʾ� ���� �޽����� ����Ѵ�.
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