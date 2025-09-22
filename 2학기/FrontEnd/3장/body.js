// 모듈을 추출한다.
const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
// 서버를 생성한다.
const app = express();
app.use(express.static('public'));
app.use(morgan('combined'));
app.use(bodyParser.urlencoded({ extended: false }));
// request 이벤트 리스너를 설정한다.
app.get('/', (request, response) => {
    // HTML 형식의 문자열을 생성한다.
    let output = '';
    output += '<form method="post">';
    output += '  <input type="text" name="a" />';
    output += '  <input type="text" name="b" />';
    output += '  <input type="submit" />';
    output += '</form>';
    // 응답한다.
    response.send(output);
});
app.post('/', (request, response) => {
    // 응답한다.
    response.send(request.body);
});
// 서버를 실행한다.
app.listen(52273, () => {
    console.log('Server running at http://127.0.0.1:52273');
});