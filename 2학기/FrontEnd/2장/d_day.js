var now = new Date();
var before = new Date(2025, 12, 25);
var interval = now.getTime() - before.getTime();
// 밀리초 단위의 시간을 날짜 단위로 변환한다.
interval = Math.floor(interval / (1000*60*60*24));
alert("D-Day : " + interval + "일");
