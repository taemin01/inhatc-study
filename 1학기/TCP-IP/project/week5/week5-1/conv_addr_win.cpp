// 4������ �ۼ��� ������ �۾������� ���������� common������ �����Ͽ� �����Ѵ�.
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
    //WSAStringToAddressW() �Լ��� ���ڿ��� ǥ���� IP �ּҸ� ���� �ּ� ����ü(SOCKADDR_IN)�� ��ȯ

    //WSAAddressToStringW() �Լ��� ���� �ּ� ����ü�� �ٽ� ���ڿ� �������� ��ȯ

    //wprintf() �Լ��� ���� ���ڿ�(wchar_t*)�� ����ϱ� ���� ���

    //IP �ּҴ� wchar_t Ÿ���� ���ڿ��� ���ǵǾ� �־� �����ڵ� ȯ�濡���� ó���� ����
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
