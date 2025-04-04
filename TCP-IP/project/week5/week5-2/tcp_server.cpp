// 4주차에 작성한 파일을 작업폴더의 상위폴더에 common폴더를 생성하여 복사한다.
#include "../../week5/week5/Common.h"

//클라이언트, 서버 같은 솔루션이 아니라 다른 프로젝트로 나눠서 실습하는 게 편함

/*= ========================================================
*FILENAME : Hello_server_win.cpp(교재 59page 참조 / 수정)
* brief : 서버 프로그램으로 프로토콜로 입력하여 실행
* 클라이언트 프로그램이 접속하면 "Hello World!"를 전송한 후 종료한다.
*
*Param : 포트번호
*
*처리순서
* Step 1 : 프로그램 입력 인수를 확인하여 2개가 아니면 프로그램을 종료한다.
* Step 2 : 윈속 초기화
* Step 3 : 소켓 생성
* Step 4 : IP주소 할당(bind)
* Step 5 : 연결 요청 대기(listen)
* Step 6 : 연결 요청 수락(accept)
* Step 7 : 데이터 전송(send)
* Step 8 : 소켓 종료
* Step 9 : 릴리즈 종료
======================================================== = */

int main(int argc, char* argv[])
{
    // Windows Socket API(WSA)를 사용하여 네트워크 통신을 할 때, Windows 소켓 구현에 대한 정보를 가져올 수 있는 구조체
    WSADATA wsaData;

    SOCKET hServSock, hClntSock; // socket 구조체
    SOCKADDR_IN servAddr, clntAddr; // 접속주소 및 포트를 지정하는 구조체

    int szClntAddr;
    char message[] = "Hello World!";

    if (argc != 2)
    {
        printf("Usage : %s <port>\n", argv[0]);
        exit(1);
    }

    // ================= WSAStartup(wVersionRequested, lpWSAData) ===============
    // WSAStartup : 윈속 초기화 함수
    // 인수명           자료형
    // wVersionRequested : WORD - 주버전 1, 서브버전 2 => MAKEWORD(1,2)
    // lpWSAData          : WSADATA 구조체
    // return 값 : 성공 0 / 실패 에러코드
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
        err_quit("WSAStartup() error!");

    // ================= socket(int af, int type, int protocol) =================
    // 인수명               설명
    // af                  프로토콜 계열 지정(Protocol Family)
    // PF_INET             IPv4 인터넷 프로토콜
    // PF_INET6            IPv6 인터넷 프로토콜
    // PF_LOCAL            로컬 프로세스 통신을 위한 UNIX 계열 소켓
    // PF_PACKET           Low level packet interface
    // PF_IPX              IPX 네트워크 프로토콜
    // type                데이터 전송 방식(스트림/데이터그램)
    // protocol            상위 프로토콜
    hServSock = socket(PF_INET, SOCK_STREAM, 0);
    if (hServSock == INVALID_SOCKET)
        err_quit("socket() error!");

    memset(&servAddr, 0, sizeof(servAddr));
    servAddr.sin_family = AF_INET; // 주소체계: IPv4
    servAddr.sin_addr.s_addr = htonl(INADDR_ANY); // 호스트 바이트 순서를 네트워크 바이트 순서로 변경
    servAddr.sin_port = htons(atoi(argv[1])); // 포트번호: 호스트 바이트 오더를 네트워크 바이트 오더로 변환

    // ================= bind(int socketfd, struct sockaddr* myaddr, socklen_t addrlen) =================
    // 소켓에 주소정보 할당 (IP/PORT번호를 지정)
    if (bind(hServSock, (SOCKADDR*)&servAddr, sizeof(servAddr)) == SOCKET_ERROR)
        err_quit("bind() error!");

    // ================= listen(int socketfd, int backlog) =================
    // 연결이 가능한 상태로 설정하여 대기
    if (listen(hServSock, 5) == SOCKET_ERROR)
        err_quit("listen() error!");

    // ================= accept(int socketfd, struct SOCKADDR *clntAddr, socklen_t *szClntAddr) =================
    // 연결 요청 수락
    szClntAddr = sizeof(clntAddr);
    hClntSock = accept(hServSock, (SOCKADDR*)&clntAddr, &szClntAddr);
    if (hClntSock == INVALID_SOCKET)
        err_quit("accept() error!");

    send(hClntSock, message, sizeof(message), 0);
    closesocket(hClntSock);
    closesocket(hServSock);

    // 릴리즈 해제
    WSACleanup();
    return 0;
}
