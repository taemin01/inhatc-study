// 4������ �ۼ��� ������ �۾������� ���������� common������ �����Ͽ� �����Ѵ�.
#include "../../week5/week5/Common.h"

//Ŭ���̾�Ʈ, ���� ���� �ַ���� �ƴ϶� �ٸ� ������Ʈ�� ������ �ǽ��ϴ� �� ����

/*= ========================================================
*FILENAME : Hello_server_win.cpp(���� 59page ���� / ����)
* brief : ���� ���α׷����� �������ݷ� �Է��Ͽ� ����
* Ŭ���̾�Ʈ ���α׷��� �����ϸ� "Hello World!"�� ������ �� �����Ѵ�.
*
*Param : ��Ʈ��ȣ
*
*ó������
* Step 1 : ���α׷� �Է� �μ��� Ȯ���Ͽ� 2���� �ƴϸ� ���α׷��� �����Ѵ�.
* Step 2 : ���� �ʱ�ȭ
* Step 3 : ���� ����
* Step 4 : IP�ּ� �Ҵ�(bind)
* Step 5 : ���� ��û ���(listen)
* Step 6 : ���� ��û ����(accept)
* Step 7 : ������ ����(send)
* Step 8 : ���� ����
* Step 9 : ������ ����
======================================================== = */

int main(int argc, char* argv[])
{
    // Windows Socket API(WSA)�� ����Ͽ� ��Ʈ��ũ ����� �� ��, Windows ���� ������ ���� ������ ������ �� �ִ� ����ü
    WSADATA wsaData;

    SOCKET hServSock, hClntSock; // socket ����ü
    SOCKADDR_IN servAddr, clntAddr; // �����ּ� �� ��Ʈ�� �����ϴ� ����ü

    int szClntAddr;
    char message[] = "Hello World!";

    if (argc != 2)
    {
        printf("Usage : %s <port>\n", argv[0]);
        exit(1);
    }

    // ================= WSAStartup(wVersionRequested, lpWSAData) ===============
    // WSAStartup : ���� �ʱ�ȭ �Լ�
    // �μ���           �ڷ���
    // wVersionRequested : WORD - �ֹ��� 1, ������� 2 => MAKEWORD(1,2)
    // lpWSAData          : WSADATA ����ü
    // return �� : ���� 0 / ���� �����ڵ�
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
        err_quit("WSAStartup() error!");

    // ================= socket(int af, int type, int protocol) =================
    // �μ���               ����
    // af                  �������� �迭 ����(Protocol Family)
    // PF_INET             IPv4 ���ͳ� ��������
    // PF_INET6            IPv6 ���ͳ� ��������
    // PF_LOCAL            ���� ���μ��� ����� ���� UNIX �迭 ����
    // PF_PACKET           Low level packet interface
    // PF_IPX              IPX ��Ʈ��ũ ��������
    // type                ������ ���� ���(��Ʈ��/�����ͱ׷�)
    // protocol            ���� ��������
    hServSock = socket(PF_INET, SOCK_STREAM, 0);
    if (hServSock == INVALID_SOCKET)
        err_quit("socket() error!");

    memset(&servAddr, 0, sizeof(servAddr));
    servAddr.sin_family = AF_INET; // �ּ�ü��: IPv4
    servAddr.sin_addr.s_addr = htonl(INADDR_ANY); // ȣ��Ʈ ����Ʈ ������ ��Ʈ��ũ ����Ʈ ������ ����
    servAddr.sin_port = htons(atoi(argv[1])); // ��Ʈ��ȣ: ȣ��Ʈ ����Ʈ ������ ��Ʈ��ũ ����Ʈ ������ ��ȯ

    // ================= bind(int socketfd, struct sockaddr* myaddr, socklen_t addrlen) =================
    // ���Ͽ� �ּ����� �Ҵ� (IP/PORT��ȣ�� ����)
    if (bind(hServSock, (SOCKADDR*)&servAddr, sizeof(servAddr)) == SOCKET_ERROR)
        err_quit("bind() error!");

    // ================= listen(int socketfd, int backlog) =================
    // ������ ������ ���·� �����Ͽ� ���
    if (listen(hServSock, 5) == SOCKET_ERROR)
        err_quit("listen() error!");

    // ================= accept(int socketfd, struct SOCKADDR *clntAddr, socklen_t *szClntAddr) =================
    // ���� ��û ����
    szClntAddr = sizeof(clntAddr);
    hClntSock = accept(hServSock, (SOCKADDR*)&clntAddr, &szClntAddr);
    if (hClntSock == INVALID_SOCKET)
        err_quit("accept() error!");

    send(hClntSock, message, sizeof(message), 0);
    closesocket(hClntSock);
    closesocket(hServSock);

    // ������ ����
    WSACleanup();
    return 0;
}
