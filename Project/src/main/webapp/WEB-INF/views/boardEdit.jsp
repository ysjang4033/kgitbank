<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write.jsp</title>
   <style>
	  table {
	    width: 70%;
	    border-collapse: collapse;
	    font-size:12px;
    	border-collapse: collapse;
    	background-color:white;
    	border-bottom:white;
	  }
	  th{
	  	background-color:white;
	    border-bottom: 1px solid #444444;
	    padding: 10px;
	    font-size:12px;
	    border-color: #dedede;
	    font-weight: normal
	    
	    
	  }
	  td {
	 	border-bottom: 1px solid #444444;
	    padding: 10px;
	    font-size:13px;
	    border-color: #dedede;
	    font-weight: normal
	  }
	  .write {
	  	color:grey;
	 	font-size:30pt;
	  }
	</style>
  </head>
<body>

<center>

	<br><br>
    <font style='font-size:24pt; color:gray'>W R I T E   E D I T</font>
    <br><br>
  <br>
<form action="boardEdit.do?idx=${detail.b_idx}&idx2=${detail.b_id}" enctype="multipart/form-data" method="post">

<table width="600" border="0" align="center">
	<tr height="40" >
		<input type="hidden" name="b_idx" value=${detail.b_idx}>
		<td align="right">제목</td><td width="200" > <input type="text" name="b_title" size="97" value="${detail.b_title}"></td>
	</tr>
	<tr height=400 >
		<td width="450" colspan="2"><textarea rows="15" cols=105 name="b_content">${detail.b_content}</textarea></td>
	</tr>
	</table>
<br><br>
	<tr height="40">
		<td align="center" colspan="2">
			<input type="submit" value="완료" >&nbsp;&nbsp;
			<input type="reset" value="리셋">&nbsp;&nbsp;
			<input type="button" value="뒤로" onclick="location.href='boardDetail.do?idx=${detail.b_idx}'">
		</td>
	</tr>

</form>
<br><br>
</center>
</body>
</html>