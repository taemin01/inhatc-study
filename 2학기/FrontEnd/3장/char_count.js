var content = prompt("내용을 입력하세요.", "6글자 이상 입력");
if (content.length < 6)
  alert("6글자 이상으로 입력하세요.");
else
  alert("글자수는 " + content.length + "자 입니다.");
