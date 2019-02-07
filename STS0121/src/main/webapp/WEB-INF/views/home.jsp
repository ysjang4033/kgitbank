<%@ page language="java" contentType="text/html; charset=UTF-8"  %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>home.jsp</title>
  <style type="text/css">
    *{font-size:20pt; font-weight:bold;  font-family:  Segoe UI Symbol , comic Sans MS ;  }
    a{font-size:20pt; font-weight:bold; color:blue; text-decoration:none; }
    a:hover{font-size:26pt; font-weight:bold; color:green; text-decoration:underline; }            
  </style>
</head>
<body>

<img src="./resources/images/bar.gif"><br>
<img src="./resources/images/bar.gif"><br>
<img src="./resources/images/bar.gif"><br>

	<c:if test="${LOGIN == null}">
	   <a href = "login.do">[로그인] </a>
	</c:if>
	<c:if test="${LOGIN!=null}">
            ${LOGIN.userid}님 환영합니다 <br>
	    <a href = "logout.do">[로그아웃]</a>
	</c:if>
	
	<a href = "list.do">[게시물 목록보기]</a>
	<a href = "write.do">[게시물 쓰기]</a>
</body>
</html>
