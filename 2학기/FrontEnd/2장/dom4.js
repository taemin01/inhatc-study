var fontsize = 10;
function SetFontSize(size) {
  txt_size = document.getElementById("content");
  if (size == "B") {
    fontsize = fontsize + 1;
    txt_size.style.fontSize = fontsize + "pt";
  } else {
    if (fontsize>10){
      fontsize = fontsize - 1;
      txt_size.style.fontSize = fontsize + "pt";
    }
  }
}

function SetFontColor(color) {
  txt_color = document.getElementById("content");
  if (color == "black")
    txt_color.style.color = "#000";
  else
    txt_color.style.color = "#f00";
}
