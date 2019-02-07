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
    <font style='font-size:24pt; color:gray'>W R I T E</font>
    <br><br>
  <br>
<form action="boardInsert.do" enctype="multipart/form-data" method="post">

<table width="600" border="0" align="center">
	<input type="hidden" value="${id}" name="b_id">
	<tr height="40" >
		<td width="450" align="center"> <input type="text" name="b_title" size="137" placeholder="T I T L E"></td>
	</tr>
	<tr height="40">
   	 	<td align="right"> 첨부파일 : <input type="file" name="b_upload_f"><p> </td>
	</tr>
	<tr height=300 >
		<td width="450" colspan="2" align="center"><textarea rows="15" cols=140 name="b_content" placeholder="C O N T E N T"></textarea></td>
	</tr>
	</table>
<br><br>
	<tr height="40">
		<td align="center" colspan="2">
			<input type="submit" value="완료" >&nbsp;&nbsp;
			<input type="reset" value="리셋">&nbsp;&nbsp;
			<input type="button" value="뒤로" onclick="location.href='boardList.do'">
		</td>
	</tr>

</form>
<br><br>
</center>
</body>

</html>