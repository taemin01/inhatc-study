// 모듈을 추출한다.
const express = require('express');
// 서버를 생성한다.
const app = express();
// request 이벤트 리스너를 설정한다. 
app.use((request, response) => {
    response.send('<h1>Hello express</h1>');
});
// 서버를 실행한다.
app.listen(52273, () => {
    console.log('Server running at http://127.0.0.1:52273');
});