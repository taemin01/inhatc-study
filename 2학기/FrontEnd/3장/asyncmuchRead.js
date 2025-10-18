// 모듈을 추출한다.
const fs = require('fs');
const async = require('async');

// 병렬적으로 파일을 읽어 들인다.
async.parallel([
   (callback) => { fs.readFile('a.txt', callback); },
   (callback) => { fs.readFile('b.txt', callback); },
   (callback) => { fs.readFile('c.txt', callback); },
], (error, results) => {
   // 파일을 출력한다.
   console.log(results);
});
