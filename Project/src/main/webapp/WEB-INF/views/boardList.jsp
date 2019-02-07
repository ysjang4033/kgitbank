<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <style>
	  #a {
	    width: 70%;
	    border-collapse: collapse;
	    font-size:12px;
    	border-top: 1px solid black;
    	border-collapse: collapse;
    	background-color:white;
    	border-bottom:white;
    	
	  }
	  th{
	  	background-color:#e7e7e7;
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
	  	color:gray;
	 	font-size:12px;
	  }
	  a{font-size:10pt; font-weight:bold; color:black; text-decoration:none;}
      a:hover{font-size:10pt; font-weight:bold; color:black; text-decoration:underline; }  
      
      select {
      	font-size:15px;
      }
      
      option {
      	font-size:15px;
      }
      
      body {
        margin: 0px;
        padding: 0px;
      }
      .jbTitle {
        text-align: center;
      }
      .jbMenu {
        text-align: center;
        background-color: yellow;
        padding: 10px 0px;
        width: 100%;
      }
      .jbContent {
        height: 0px;
      }
      .jbFixed {
        position: fixed;
        top: 0px;
      }
	  
	</style>
  <script src="./js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript">
    var usrId = "<%= session.getAttribute("sessionID") %>";
  
	function changeclear( ){
//		alert("dd");
		document.getElementById(keyword).value = "";
		
	}//end
	
	function logout() {
		
		var id = document.getElementById("id").value;
		$.ajax({
			data : {
				"id" : id	
			},
			url : "./logOut.do",
			dataType : "JSON",
			success : function(data) {
				if(data.t) {
					alert("로그아웃되었습니다.");
					location.href='login.do';
				}else {
					alert("로그아웃을 실패했습니다.");
				}
			}
		})
	}
	
	function intercep() {
		
		if(usrId=="null") {
			alert("권한이 없습니다. 로그인해주세요.");
			location.href='boardWrite.do?b_id='+usrId;
		} else {
			alert("회원이 확인되었습니다.");
			location.href='boardWrite.do?b_id='+usrId;
		}
	}
	
	function intercep2(idx, pageNum, keyfield, keyword) {
		
 		if(usrId=="null") {
			alert("권한이 없습니다. 로그인해주세요.");
			location.href='boardDetail.do?idx='+idx+'&pageNum='+pageNum+'&keyfield='+keyfield+'&keyword='+keyword;
		} else {
			alert("회원이 확인되었습니다.");
			location.href='boardDetail.do?idx='+idx+'&pageNum='+pageNum+'&keyfield='+keyfield+'&keyword='+keyword;
		} 

	}
	
	
	// 스크롤바 메뉴
	$( document ).ready( function() {
        var jbOffset = $( '.jbMenu' ).offset();
        $( window ).scroll( function() {
          if ( $( document ).scrollTop() > jbOffset.top ) {
            $( '.jbMenu' ).addClass( 'jbFixed' );
          }
          else {
            $( '.jbMenu' ).removeClass( 'jbFixed' );
          }
        });
      });
	
  </script>

</head>
<body>
<br><br>
<c:if test ="${sessionScope.sessionID!=null}">
	<input type="hidden" value="${sessionScope.sessionID}" id="id">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디 : ${sessionScope.sessionID}
</c:if>
<center>
	<div class="jbTitle">
      <font style='font-size:24pt; color:gray'>B &nbsp;&nbsp; o &nbsp;&nbsp; a &nbsp;&nbsp; r &nbsp;&nbsp; d</font>
    </div>
    <br>
    <div class="jbMenu">
      <p>
        <c:if test="${sessionScope.sessionID==null}">
        <a href="login.do">로 그 인</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </c:if>
        <a href="join.do">회 원 가 입</a> &nbsp;&nbsp;&nbsp;
        <c:if test="${sessionScope.sessionID!=null}">
        <a href="javascript:void(0);" onclick="javascript:logout()">로 그 아 웃</a>
        </c:if>
      </p>
    </div>
    <div class="jbContent">
    </div>
    <br><br>
    
	<table width=900 border=0 cellspacing=0 id="a"  bgcolor="white">
</center>
		<tr> 
	    <td colspan=5 align="right"> 게시글 수 : ${Count}  &nbsp;&nbsp;</td>
	    </tr>
		<tr>
          <td colspan="2" width=300 align="center">제목</td>
          <td align="left">작성자</td>
          <td align="center">작성일</th>
          <td align="center">조회</th>
        </tr>
        
        <tr>  
          <th align="left"> <font style='font-size:11pt; color:red'> 공지 </font> </th>
          <th align="left"> <font style='font-size:11pt; color:red'> [이벤트] --------------알립니다. </font></th>
          <th align="left"> <font style='font-size:11pt; color:gray'>운영자</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>2019.01.10</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>1만</font> </th>
        </tr>
        
        <tr>  
          <th align="left"> <font style='font-size:11pt; color:red'> 공지 </font> </th>
          <th align="left"> <font style='font-size:11pt; color:red'> [이벤트] --------------알립니다. </font></th>
          <th align="left"> <font style='font-size:11pt; color:gray'>운영자</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>2019.01.10</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>1만</font> </th>
        </tr>
        
        <tr>  
          <th align="left"> <font style='font-size:11pt; color:red'> 공지 </font> </th>
          <th align="left"> <font style='font-size:11pt; color:red'> [이벤트] --------------알립니다. </font></th>
          <th align="left"> <font style='font-size:11pt; color:gray'>운영자</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>2019.01.10</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>1만</font> </th>
        </tr>
        
        <tr>  
          <th align="left"> <font style='font-size:11pt; color:red'> 공지 </font> </th>
          <th align="left"> <font style='font-size:11pt; color:red'> [이벤트] --------------알립니다. </font></th>
          <th align="left"> <font style='font-size:11pt; color:gray'>운영자</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>2019.01.10</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>1만</font> </th>
        </tr>
        
        <tr>  
          <th align="left"> <font style='font-size:11pt; color:red'> 공지 </font> </th>
          <th align="left"> <font style='font-size:11pt; color:red'> [이벤트] --------------알립니다. </font></th>
          <th align="left"> <font style='font-size:11pt; color:gray'>운영자</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>2019.01.10</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>1만</font> </th>
        </tr>
        
        <tr>  
          <th align="left"> <font style='font-size:11pt; color:red'> 공지 </font> </th>
          <th align="left"> <font style='font-size:11pt; color:red'> [이벤트] --------------알립니다. </font></th>
          <th align="left"> <font style='font-size:11pt; color:gray'>운영자</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>2019.01.10</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>1만</font> </th>
        </tr>
        
        <tr>  
          <th align="left"> <font style='font-size:11pt; color:red'> 공지 </font> </th>
          <th align="left"> <font style='font-size:11pt; color:red'> [이벤트] --------------알립니다. </font></th>
          <th align="left"> <font style='font-size:11pt; color:gray'>운영자</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>2019.01.10</font> </th>
          <th align="center"> <font style='font-size:11pt; color:gray'>1만</font> </th>
        </tr>
        
        
		<c:forEach var="list" items="${list}"> 
		<tr align="left">	
			<td><font style='font-size:9pt; color:gray'>${list.rn}</font></td>	
			<td> <font style='font-size:11pt; color:black'> <a href="javascript:void(0);" onclick="javascript:intercep2(${list.b_idx}, ${pageNum}, ${keyfield}, ${keyword});">   ${list.b_title}</a></font> </td> 
			<td align="left"><font style='font-size:11pt; color:gray'>${list.b_id}</font></td>
			<td align="center"><font style='font-size:11pt; color:gray'>${list.b_date}</font></td>
			<td align="center"><font style='font-size:11pt; color:gray'></font></td>
		</tr>
		</c:forEach>				
		
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td align=right> <input type="submit" style="font-size:10px" value="글쓰기" onclick="intercep()"> </td>
		</tr>
	</table>
	
	<br><br>
	
	<table width="70%" border=0 cellspacing=0 bgcolor=#e7e7e7>
		<tr align="center">
   			<td colspan="5">
 				<c:if test="${startPage>5}">
 	   				<a href="boardList.do?pageNum=${startPage-5}&keyfield=${keyfield}&keyword=${keyword}">[이전]</a>
 				</c:if>
   
 				<c:forEach var="i" begin="${startPage}" end="${endPage}">
 	   				<c:choose>
 	     				<c:when test="${i==pageNum}">
 	       					<font style='font-size:10pt; color:red'> ${i} </font> 
 	     				</c:when>
 	    		 		<c:otherwise> 
 	       					<a href="boardList.do?pageNum=${i}&keyfield=${keyfield}&keyword=${keyword}">${i}</a>
 	     				</c:otherwise>
 	   				</c:choose>	
 				</c:forEach>
     
			   <c:if test="${endPage<pageCount}">
				 <a href="boardList.do?pageNum=${startPage+5}&keyfield=${keyfield}&keyword=${keyword}">[다음]</a>
			   </c:if>
   			</td>
  		</tr>
  		
  		<tr align="center">
  			<td colspan="5">
  	 		<form name="myform" action="boardList.do">
			
  	 		<select name="keyfield">
				<option value="" onclick="changeclear();" selected="selected">전   체</option>
				<option value="id" <c:if test="${keyfield=='id'}">selected="selected"</c:if>>작성자</option>
				<option value="title" <c:if test="${keyfield=='title'}">selected="selected"</c:if>>제 목</option>
				<option value="content" <c:if test="${keyfield=='content'}">selected="selected"</c:if>>내 용</option>
				<option value="all" <c:if test="${keyfield=='all'}">selected="selected"</c:if>>이름 + 제목 + 내용</option>
			</select>
			
	  	 	<input type="text" name="keyword"  value="${keyword}" style="font-size:15px; font-color:gray" placeholder="S E A R C H">
	  	 	<input type="submit" value="검 색 " style="font-size:13px">
  	 	
  	 		</form>
  			</td>
  		</tr>
	
	</table>
</center>
</body>
</html>