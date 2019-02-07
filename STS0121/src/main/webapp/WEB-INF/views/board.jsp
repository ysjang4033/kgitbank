<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board.jsp</title>
	<style type="text/css">
	   *{font-size: 20pt; font-weight: bold; }
	   a{text-decoration:none;font-size: 20pt; font-weight: bold; }
	   a:hover{text-decoration:underline;font-size: 26pt; font-weight: bold; color:blue; }
	</style>
</head>
<body>
	<font color=blue>[board.jsp]</font> <p>
	 <form action="insert.do" enctype="multipart/form-data" method="post">
	 	이름: <input type=text name="name" value="GilDong"> <br>
	 	제목: <input type=text name="title" value="snow"> <br>
	 	내용: <textarea name="content" rows="4" cols="20">KGbank</textarea> <br>
	 	성별: 
	 	  <input type="radio" name="gender" value="male">남자
	 	  <input type="radio" name="gender" value="female" checked>여자<br>
	 	취미:
	 	  <input type="checkbox" name="hobby" value="game">게임
	 	  <input type="checkbox" name="hobby" value="study">공부
	 	  <input type="checkbox" name="hobby" value="ski" checked>스키
	 	  <input type="checkbox" name="hobby" value="movie" checked>영화<br>
	
	 	파일:<input type="file" name="upload_f"><p>  <!-- img_file_name이름대신 upload__f 앞에꺼 사용하면 파일전달안됨-->
	 	  <input type="submit" value="hobby스프링전송">&nbsp;&nbsp;
	 	  <input type="reset" value="입력취소">          
	 </form>

<p>
<a href="index.jsp">[index.jsp]</a>
<a href="write.do">[신규등록]</a>
<a href="home.do">[home.jsp]</a>

</body>
</html>













