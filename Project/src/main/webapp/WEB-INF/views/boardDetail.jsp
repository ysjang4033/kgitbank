<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qaDetail.jsp</title>
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
	  .detail {
	  	color:grey;
	 	font-size:30pt;
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
    } );
	
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
	</script>
	
	
	
	
  </head>
<body>
<input type="hidden" value="${sessionScope.sessionID}" id="id">
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
        <a href="login.do">로 그 인</a> &nbsp;&nbsp;&nbsp;
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
<div align="center">
<table width="600" height="600" border="0" background-color:white;>
	<tr>
		<th align="left">제목 : ${detail.b_title} </th>
		<th align="right">${detail.b_date}</th>
		
	</tr>
	<tr >
		<th align="left" width="100">아이디 : ${detail.b_id}</th>
		<th align="right" width="100">조회수 :  </th>
	</tr>
	<tr>
		<th></th>
		<th align="right" width="100">첨부파일 : <a href="boardDownload.do?idx=${detail.b_img_file_name}">${detail.b_img_file_name}</a></th>
		
	</tr>
	<tr width="300" height="600">
		<th align="right"> <a href="boardDownload.do?idx=${detail.b_img_file_name}"><img src="./resources/upload/${detail.b_img_file_name}" width=500 height=500></a></th>
		<th></th>
	</tr>
	<tr>
		<th>${detail.b_content}</th>
		<th></th>
	</tr>
</table>    
<br>
			<c:if test="${sessionScope.sessionID==detail.b_id || sessionScope.sessionID=='admin'}">
			<input type="button" onClick="location.href='boardpreEdit.do?idx=${detail.b_idx}&idx2=${detail.b_id}'" value="수정">
 			<input type="button" onclick="location.href='boardDelete.do?idx=${detail.b_idx}'" value="삭제">
 			</c:if>
 			<input type="button" onclick="location.href='boardList.do?pageNum=${pageNum}&keyfield=${keyfield}&keyword=${keyword}'" value="목록">
 			
<br>
<br>
<br>

 	<!-- jstl태그  import  -->
 		<c:import url="/ReplyList.do?idx=${detail.b_idx}" />

</div>
</center>
</body>
</html>