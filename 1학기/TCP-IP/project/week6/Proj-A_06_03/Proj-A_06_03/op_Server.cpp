#include "../../Common.h"  // ���� ��� ���� ����

#define BUF_SIZE 1024             // ���� ũ�� ����
#define OPSZ 4                    // �ǿ����� ũ�� ����

void ErrorHandling(const char* message);  // ���� ó�� �Լ� ����
int calculate(int opnum, int opnds[], char op);  // ��� �Լ� ����

int main(int argc, char* argv[]) {
    WSADATA wsaData;                       // ���� �ʱ�ȭ�� ����ü
    SOCKET hServSock, hClntSock;          // ����/Ŭ���̾�Ʈ ����
    char opinfo[BUF_SIZE];                // ���� ���� ���� ����
    int result, opndCnt, i;               // �����, �ǿ����� ��, ���� ����
    int recvCnt, recvLen;                 // ���� ����
    SOCKADDR_IN servAdr, clntAdr;         // ����/Ŭ���̾�Ʈ �ּ�
    int clntAdrSize;

    if (argc != 2) {
        printf("Usage : %s <port>\n", argv[0]);  // ��Ʈ �Է� �ȳ�
        exit(1);
    }

    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
        ErrorHandling("WSAStartup() error!");

    hServSock = socket(PF_INET, SOCK_STREAM, 0);  // ���� ����
    if (hServSock == INVALID_SOCKET)
        ErrorHandling("socket() error");

    memset(&servAdr, 0, sizeof(servAdr));         // �ּ� ����ü �ʱ�ȭ
    servAdr.sin_family = AF_INET;
    servAdr.sin_addr.s_addr = htonl(INADDR_ANY);
    servAdr.sin_port = htons(atoi(argv[1]));

    if (bind(hServSock, (SOCKADDR*)&servAdr, sizeof(servAdr)) == SOCKET_ERROR)
        ErrorHandling("bind() error");

    if (listen(hServSock, 5) == SOCKET_ERROR)
        ErrorHandling("listen() error");

    clntAdrSize = sizeof(clntAdr);

    for (i = 0; i < 5; i++) {  // �ִ� 5�� Ŭ���̾�Ʈ ó��
        opndCnt = 2;
        hClntSock = accept(hServSock, (SOCKADDR*)&clntAdr, &clntAdrSize);
        recv(hClntSock, (char*)&opndCnt, 1, 0);

        recvLen = 0;
        while ((opndCnt * OPSZ + 1) > recvLen) {
            recvCnt = recv(hClntSock, &opinfo[recvLen], BUF_SIZE - 1, 0);
            recvLen += recvCnt;
        }

        result = calculate(opndCnt, (int*)opinfo, opinfo[recvLen - 1]);
        send(hClntSock, (char*)&result, sizeof(result), 0);
        closesocket(hClntSock);
    }

    closesocket(hServSock);
    WSACleanup();
    return 0;
}

// ���� ���� �Լ�
int calculate(int opnum, int opnds[], char op) {
    int result = opnds[0];
    int i;

    switch (op) {
    case '+':
        for (i = 1; i < opnum; i++) result += opnds[i];
        break;
    case '-':
        for (i = 1; i < opnum; i++) result -= opnds[i];
        break;
    case '*':
        for (i = 1; i < opnum; i++) result *= opnds[i];
        break;
    }

    return result;
}

// ���� �޽��� ��� �Լ�
void ErrorHandling(const char* message) {
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}
