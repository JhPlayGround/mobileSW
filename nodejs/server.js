var express = require('express');
var app = express();

var http = require('http');
var url = require('url');

var hostname = '192.168.0.9';
var port = 8080;

var server = http.createServer(function (req, res) {
	
	res.writeHead(200, {'Content-Type': 'text/html; charset=utf-8'});
	var q = url.parse(req.url, true).query;
	var service = q.choiceNum;
	if(q.choiceNum == 1)
	{
		res.write("회원가입 신청");
		res.end();
	}
	else if(q.choiceNum == 2)
	{
		res.write("아이 성장 상태 비교");
		res.end();
	}
	else if(q.choiceNum == 3)
	{
		res.write("데일리 이유식 추천");
		res.end();
	}
	else
	{
		res.write("에러다.");
		res.end();
	}
});

server.listen(port, function(){ 
    console.log('Server is running...');
});