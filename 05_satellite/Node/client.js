var http = require('http');

// HttpRequest Option
var options = {
    host: 'localhost',
    port: '10002',
    path: '/index.html'
};

// Callback Function
var callback = function(res){
    // response 이벤트 data 발생 시 데이터를 수신
    var content = '';
    res.on('data', function(data){
        content += data;
    });

    // response 이벤트 중 end 이벤트 발생 시 내용을 출력
    res.on('end', function(){
        // 데이터 디스플레이
        console.log("Data Received");
        console.log(content);
    }); 
}

var request = http.request(options, callback);
request.end();