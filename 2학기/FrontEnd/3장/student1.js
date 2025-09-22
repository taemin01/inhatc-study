function prt(){ // "prt" 함수 정의
  document.write("이름 : " + this.name + "<p />");
  document.write("나이 : " + this.age + "<p />");
}
function student(name, age){ // 생성자 함수 정의
  this.name = name; // "name" 속성 추가
  this.age = age; // "age" 속성 추가
  this.st_method = prt; // "prt()" 메소드 추가
}
student1 = new student("진달래", 19);
// "student1" 객체를 새로 만들고 "name" , "age" 속성에 각각 “진달래”, 19를 지정
student1.st_method(); // "student1" 객체의 "name", "age" 속성값을 웹 문서에 출력
