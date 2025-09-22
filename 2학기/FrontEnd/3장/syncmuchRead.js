// 모듈을 추출한다.
const fs = require('fs');
// 파일을 읽어 들인다.
const a = fs.readFileSync('a.txt');
const b = fs.readFileSync('b.txt');
const c = fs.readFileSync('c.txt');
// 파일을 출력한다.
console.log(a, b, c);