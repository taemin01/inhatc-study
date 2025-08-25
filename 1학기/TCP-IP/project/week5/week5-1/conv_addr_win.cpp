// 4주차에 작성한 파일을 작업폴더의 상위폴더에 common폴더를 생성하여 복사한다.
#include "../../week5/week5/Common.h"

int main(int argc, wchar_t* argv[])
{
    wchar_t strAddr[] = L"203.211.218.102:9190";
    wchar_t strAddrBuf[50];
    SOCKADDR_IN servAddr;
    unsigned long size;

    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        wprintf(L"WSAStartup failed.\n");
        return 1;
    }
    //WSAStringToAddressW() 함수는 문자열로 표현된 IP 주소를 소켓 주소 구조체(SOCKADDR_IN)로 변환

    //WSAAddressToStringW() 함수는 소켓 주소 구조체를 다시 문자열 형식으로 변환

    //wprintf() 함수는 넓은 문자열(wchar_t*)을 출력하기 위해 사용

    //IP 주소는 wchar_t 타입의 문자열로 정의되어 있어 유니코드 환경에서도 처리가 가능
    ZeroMemory(&servAddr, sizeof(servAddr));
    size = sizeof(servAddr);

    if (WSAStringToAddressW(strAddr, AF_INET, NULL, (SOCKADDR*)&servAddr, (LPINT)&size) != 0) {
        wprintf(L"WSAStringToAddress failed.\n");
        WSACleanup();
        return 1;
    }

    size = sizeof(strAddrBuf);
    if (WSAAddressToStringW((SOCKADDR*)&servAddr, sizeof(servAddr), NULL, strAddrBuf, (LPDWORD)&size) != 0) {
        wprintf(L"WSAAddressToString failed.\n");
        WSACleanup();
        return 1;
    }

    wprintf(L"Second conv result: %s \n", strAddrBuf);
    WSACleanup();
    return 0;
}
