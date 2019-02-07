<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>[boardList.jsp]</title>
 	<style type="text/css">
		*{font-size:16pt; font-weight:bold; }
		a{text-decoration:none ;font-size:20pt; font-weight:bold;}
		a:hover{text-decoration:underline ;font-size:22pt; font-weight:bold;}
	</style>
</head>
<body>
   <table  width=700 border=1 cellpadding="0">
    <tr> 게시글 수 : ${Gtotal} </tr>
    <tr bgcolor=yellow height=50>
      <td>번호</td> <td>이름</td> <td>제목</td> <td>내용</td>
    </tr>
    
    <c:forEach var="list" items="${list}">
    	<tr>
    		<td>${list.rn}</td>
    		<td>${list.name}</td>
    		<td><a href="detail.do?idx=${list.hobby_idx}">${list.title} [${list.rcnt}] </a></td>
    		<td>${list.content}</td>
    	</tr>
    </c:forEach>
    
   <tr align="center">
   <td colspan="5">
    <c:if test="${startpage>4}">
 	   <a href="list.do?pageNum=${startpage-5}&keyfield=${skey}&keyword=${sval}">[이전]</a>
 	</c:if>
   
    <c:forEach var="i" begin="${startpage}" end="${endpage}">
      <c:choose>
        <c:when test="${i==pageNUM}">
        	<font style="font-size:20pt; color:red;">[${i}]</font> 
        </c:when>
        <c:otherwise>
        	<a href="list.do?pageNum=${i}&keyfield=${skey}&keyword=${sval}">[${i}]</a> 
        </c:otherwise>
      </c:choose>
	     
    </c:forEach>
    
    <c:if test="${endpage<pagecount}">
 	   <a href="list.do?pageNum=${startpage+5}&keyfield=${skey}&keyword=${sval}">[다음]</a>
 	</c:if>
   </td>
  </tr>
  
  <tr align="center">
  	<td colspan="5">
  	 <form name="myform" action="list.do">
  	 	검색 :
  	 	<select name="keyfield" onchange="changeclear();">
  	 	   <option value="" selected <c:if test="${skey==''}">selected="selected"</c:if>>전체검색</option>
  	 	   <option value="name" <c:if test="${skey=='name'}">selected="selected"</c:if>>이름조회</option>
  	 	   <option value="title" <c:if test="${skey=='title'}">selected="selected"</c:if>>제목검색</option>
  	 	   <option value="content" <c:if test="${skey=='content'}">selected="selected"</c:if>>내용검색</option>
  	 	   <option value="all" <c:if test="${skey=='all'}">selected="selected"</c:if>>이름+제목+내용</option>
  	 	</select>
  	 	<input type="text" name="keyword"  size=10  value="${sval}">
  	 	<input type="submit" value="검 색 "> 
  	 	<input type="button" value="index" onclick="location.href='index.jsp'">
  	 	<input type="button" value="login" onclick="location.href='login.do'">
  	 	<input type="button" value="list" onclick="location.href='list.do'"> 
  	 </form>
  	</td>
  </tr>
    
  </table>
	
<p>
  <a href="index.jsp">[index.jsp]</a>
  <a href="write.do">[신규등록]</a>
  <a href="home.do">[home.jsp]</a>
  <a href="login.do">[login.jsp]</a>
</body>
</html>



