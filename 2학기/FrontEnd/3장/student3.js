function display_stu(){
  document.write("이름 : " + this.name + "<br />");
  document.write("나이 : " + this.age + "<br />");
}
function student(name, age){
  this.name = prompt("이름을 입력하세요");
  this.age = prompt("나이를 입력하세요");
  this.display_stu = display_stu;
}
student3 = new student();
student3.display_stu();
