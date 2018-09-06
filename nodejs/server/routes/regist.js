var express = require('express');
var router = express.Router();
var url = require('url');

/* regist */
/* id, password, month, height, weight, gender*/
router.post('/', function(req, res, next) {
  var q = url.parse(req.url,true).query;
  var id = q.id;
  var pwd = q.pwd;
  var month = q.month;
  var height = q.height;
  var weight = q.weight;
  var gender = q.gender;
  res.send('ID : ' + id + '\n' +'비밀번호 : ' + pwd +'\n' + month + '개월' + '\n' 
  + height +'Cm' + '\n' + weight +'Kg'+ '\n' + gender);
});

module.exports = router;