var student1 = {
name : "진달래",
age : 19,
st_method : function(){
  document.write("이름 : " + this.name + "<p />");
  document.write("나이 : " + this.age + "<p />");
}
};
student1.st_method();
