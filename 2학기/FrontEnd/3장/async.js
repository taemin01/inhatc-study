const fs = require('fs');
const async = require('async');
// 파일A를 읽습니다.
async.parallel({
    fileA: (callback) => {
        fs.readFile('fileA.txt', callback);
    },
    fileB: (callback) => {
        fs.readFile('fileB.txt', callback);
    },
    fileC: (callback) => {
        fs.readFile('fileC.txt', callback);
    }
}, (error, results) => {
    console.log(results.fileA);
    console.log(results.fileB);
    console.log(results.fileC);
});
