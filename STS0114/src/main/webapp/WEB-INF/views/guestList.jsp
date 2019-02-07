<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> [guestList.jsp]</title>
  <style type="text/css">
    *{font-size:16pt; font-weight:bold;  font-family: Comic Sans MS ; }
    a{font-size:16pt; font-weight:bold; color:blue; text-decoration:none; }
    a:hover{font-size:20pt; font-weight:bold; color:green; text-decoration:underline; }                
  </style>

  <script type="text/javascript">
 	function changeclear( ){
 		myform.keyword.value="";
 		myform.keyword.focus();
 	}//end
  </script>
 </head>
<body>

 <table width=900 border=1 cellspacing=0>  
  <tr  align="right">
    <td colspan=5> 레코드갯수:${Gtotal} &nbsp;&nbsp;</td>
  </tr>

  <tr bgcolor="yellow">
  	<th>행번호</th> <th>사 번</th> <th>이 름</th> <th>제 목</th> <th>날 짜</th>
  </tr>

  <c:forEach var="dto" items="${naver}"> 
   <tr>
     <td> ${dto.rn} </td>
     <td> ${dto.sabun} </td>
     <td> ${dto.name} </td>
     <td> <a href="detail.do?idx=${dto.sabun}"> ${dto.title} </a> </td>
     <td align="center"> <fmt:formatDate value="${dto.nalja}" pattern="yyyy년MM월dd일  hh시mm분ss초"/>	 </td>
   </tr>   
  </c:forEach>
 
  <tr align="center">
   <td colspan="5">
    <c:if test="${startpage>10}">
 	   <a href="list.do?pageNum=${startpage-10}">[이전]</a>
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
 	   <a href="list.do?pageNum=${startpage+10}">[다음]</a>
 	</c:if>
   </td>
  </tr>
  
 <tr align="center">
  	<td colspan="5">
  	 <form name="myform" action="list.do">
  	 	검색 :
  	 	<select name="keyfield" onchange="changeclear();">
  	 	   <option value="all" selected <c:if test="${skey==''}">selected="selected"</c:if>>전체검색</option>
  	 	   <option value="name" <c:if test="${skey=='name'}">selected="selected"</c:if>>이름조회</option>
  	 	   <option value="title" <c:if test="${skey=='title'}">selected="selected"</c:if>>제목검색</option>
  	 	</select>
  	 	<input type="text" name="keyword"  size=10  value="${sval}">
  	 	<input type="submit" value="검 색 "> 
  	 	<input type="button" value="index" onclick="location.href='index.jsp'">
  	 	<input type="button" value="등록" onclick="location.href='write.do'">
  	 	<input type="button" value="list" onclick="location.href='list.do'"> 
  	 </form>
  	</td>
  </tr>
 </table> 

</body>
</html>
