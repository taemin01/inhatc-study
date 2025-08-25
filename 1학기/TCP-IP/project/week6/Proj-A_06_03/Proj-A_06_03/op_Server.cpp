#include "../../Common.h"  // 공통 헤더 파일 포함

#define BUF_SIZE 1024             // 버퍼 크기 정의
#define OPSZ 4                    // 피연산자 크기 정의

void ErrorHandling(const char* message);  // 에러 처리 함수 선언
int calculate(int opnum, int opnds[], char op);  // 계산 함수 선언

int main(int argc, char* argv[]) {
    WSADATA wsaData;                       // 윈속 초기화용 구조체
    SOCKET hServSock, hClntSock;          // 서버/클라이언트 소켓
    char opinfo[BUF_SIZE];                // 연산 정보 저장 버퍼
    int result, opndCnt, i;               // 결과값, 피연산자 수, 루프 변수
    int recvCnt, recvLen;                 // 수신 길이
    SOCKADDR_IN servAdr, clntAdr;         // 서버/클라이언트 주소
    int clntAdrSize;

    if (argc != 2) {
        printf("Usage : %s <port>\n", argv[0]);  // 포트 입력 안내
        exit(1);
    }

    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
        ErrorHandling("WSAStartup() error!");

    hServSock = socket(PF_INET, SOCK_STREAM, 0);  // 소켓 생성
    if (hServSock == INVALID_SOCKET)
        ErrorHandling("socket() error");

    memset(&servAdr, 0, sizeof(servAdr));         // 주소 구조체 초기화
    servAdr.sin_family = AF_INET;
    servAdr.sin_addr.s_addr = htonl(INADDR_ANY);
    servAdr.sin_port = htons(atoi(argv[1]));

    if (bind(hServSock, (SOCKADDR*)&servAdr, sizeof(servAdr)) == SOCKET_ERROR)
        ErrorHandling("bind() error");

    if (listen(hServSock, 5) == SOCKET_ERROR)
        ErrorHandling("listen() error");

    clntAdrSize = sizeof(clntAdr);

    for (i = 0; i < 5; i++) {  // 최대 5번 클라이언트 처리
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

// 연산 수행 함수
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

// 에러 메시지 출력 함수
void ErrorHandling(const char* message) {
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}
