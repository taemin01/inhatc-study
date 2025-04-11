//각자에 작성한 파일을 common폴더의 상위폴더에 common폴더를 생성하여 복사한다.
#include "../../Common.h"

#define BUF_SIZE 1024
#define RLT_SIZE 4
#define OPSZ 4

void ErrorHandling(const char* message);
int calculate(int opnum, int opnds[], char oprator);

int main(int argc, char* argv[])
{
    WSADATA wsaData;
    SOCKET hServSock, hClntSock;
    char opinfo[BUF_SIZE];
    int result, i;
    int recvCnt, recvLen;
    SOCKADDR_IN servAdr, clntAdr;
    int clntAdrSz;

    if (argc != 2) {
        printf("Usage : %s <port>\n", argv[0]);
        exit(1);
    }

    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
        ErrorHandling("WSAStartup() error!");

    hServSock = socket(PF_INET, SOCK_STREAM, 0);
    if (hServSock == INVALID_SOCKET)
        ErrorHandling("socket() error");

    memset(&servAdr, 0, sizeof(servAdr));
    servAdr.sin_family = AF_INET;
    servAdr.sin_addr.s_addr = htonl(INADDR_ANY);
    servAdr.sin_port = htons(atoi(argv[1]));

    if (bind(hServSock, (SOCKADDR*)&servAdr, sizeof(servAdr)) == SOCKET_ERROR)
        ErrorHandling("bind() error");

    if (listen(hServSock, 5) == SOCKET_ERROR)
        ErrorHandling("listen() error");

    clntAdrSz = sizeof(clntAdr);
    hClntSock = accept(hServSock, (SOCKADDR*)&clntAdr, &clntAdrSz);

    recv(hClntSock, opinfo, 1, 0);                // 피연산자 개수 받음
    recvLen = 0;
    while ((opinfo[0] * OPSZ + 1) > recvLen) {    // 피연산자 및 연산자 수신
        recvCnt = recv(hClntSock, &opinfo[1 + recvLen], BUF_SIZE - 1, 0);
        recvLen += recvCnt;
    }

    result = Calculate(opinfo[0], (int*)&opinfo[1], opinfo[recvLen]);
    send(hClntSock, (char*)&result, sizeof(result), 0);

    closesocket(hClntSock);
    closesocket(hServSock);
    WSACleanup();
    return 0;
}

int Calculate(int opnum, int opnds[], char op)
{
    int result = opnds[0], i;

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

void ErrorHandling(const char* message)
{
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}
