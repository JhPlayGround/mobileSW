<?
    header('content-type: text/html; charset=utf-8'); 
    // 데이터베이스 접속 문자열. (db위치, 유저 이름, 비밀번호)
    $connect=mysql_connect( "db주소", "로그인ID", "로그인PW") or  
        die( "SQL server에 연결할 수 없습니다.");
    
    mysql_query("SET NAMES UTF8");
   // 데이터베이스 선택
   mysql_select_db("DB이름",$connect);
 
   // 세션 시작
   session_start();
 
   $id = $_POST[u_id];
   $pw = $_POST[u_pw]; 
 
   $sql = "INSERT INTO USERS(USERID, PASSWORD) VALUES('$id', '$pw')";
 
   $result = mysql_query($sql);
 
   if(!$result)
            die("mysql query error");
   else
        echo "insert success"
 
?>