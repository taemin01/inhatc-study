﻿var s_code = prompt("성별입력", "성별 코드를 1 또는 2로 입력하세요");
switch (s_code){
  case "1":
    alert("남자입니다."); break;
  case "2":
    alert("여자입니다."); break;
  default:
    alert("잘못 입력하셨습니다."); break;
}
