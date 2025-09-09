function tick() {
var month, date, hours, minutes, seconds, ap;
var intYear, intDate, intMon, intHours, intMinutes, intSeconds;
var today;
today = new Date();
intYear = today.getFullYear(); 
intMon = today.getMonth() + 1;
intDate = today.getDate();
intHours = today.getHours(); 
intMinutes = today.getMinutes();
intSeconds = today.getSeconds();
if (intMon < 10) month = "0" + intMon; // 1~9월은 숫자 앞에 "0"을 붙여 준다.
else month = intMon;
if (intDate < 10) date = "0" + intDate; // 1~9일은 숫자 앞에 "0"을 붙여 준다.
else date = intDate;
if (intHours == 0) { // 0시이면 "AM 12:"로 표시한다.
  hours = "12:";
  ap = "AM";
} else if (intHours < 12) { // 오전이면 "AM"으로 표시한다.
  hours = intHours + ":";
  ap = "AM";
} else if (intHours == 12) { // 12시이면 "PM 12:"로 표시한다.
  hours = "12:";
  ap = "PM";
} else { // 오후이면 "PM"으로 표시하고, 12시간 단위로 표시한다.
  intHours = intHours - 12
  hours = intHours + ":";
  ap = "PM";
}
if (intMinutes < 10) minutes = "0" + intMinutes + ":"; // 0~9분은 앞에 "0"을 붙여 준다.
else minutes = intMinutes + ":";
if (intSeconds < 10) seconds = "0" + intSeconds; // 0~9초는 앞에 "0"을 붙여 준다.
else seconds = intSeconds;
timeString = intYear + "-" + month + "-" + date + " " + hours + minutes + seconds + " " + ap;
Clock.innerHTML = timeString;
window.setTimeout("tick();", 100);
}
window.onload = tick;

