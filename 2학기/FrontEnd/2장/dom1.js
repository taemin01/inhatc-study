function btn_Function(){
  var btn = document.createElement("button");
  var t = document.createTextNode("생성된 버튼");
  btn.appendChild(t);
  document.body.appendChild(btn);
};
function hello(){
  var header = document.createElement("h1");
  var textNode = document.createTextNode("안녕하세요!");
  header.appendChild(textNode);
  document.body.appendChild(header);
};
