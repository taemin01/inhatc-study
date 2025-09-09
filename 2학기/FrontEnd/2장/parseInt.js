var com, js;
com = prompt("computer : ", "점수 입력");
js = prompt("javascript : ", "점수 입력");
document.write("총점 : " + (parseInt(com) + parseInt(js)) + "<br />");
document.write("평균 : " + (parseInt(com) + parseInt(js))/2);
