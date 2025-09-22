var kor = [81, 88, 92];
var math = [86, 100, 96];
var eng = [87, 94, 98];
var sum_kor = 0, sum_math = 0, sum_eng = 0;

for (var i in kor){
  sum_kor = sum_kor + kor[i]; 
}

for (var i in math){
  sum_math = sum_math + math[i]; 
}

for (var i in eng){
  sum_eng = sum_eng + eng[i]; 
}

document.write("국어 평균 : " + (sum_kor/3) + "<br />");
document.write("수학 평균 : " + (sum_math/3) + "<br />");
document.write("영어 평균 : " + (sum_eng/3) + "<br />");
