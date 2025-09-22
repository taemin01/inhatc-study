function subject(cho){
var ans;
ans = confirm(cho);
if (ans == true)
  return "확인";
else
  return "취소";
}
var cho, choice;
choice = subject("확인 또는 취소 선택");
document.write(choice);
