<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title> [boardReply.jsp]</title>
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
	</style>
	<script src="./js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
	
	
	function cmUpdateOpen(r_idx, b_idx) {
		 window.name = "parentForm";
	     window.open("replyPreEdit.do?r_idx="+r_idx,
	       "replyPreEdit", "width=570, height=350, resizable = no, scrollbars = no");	
	}
	
	
	function cmDeleteOpen(r_idx, b_idx){
        var msg = confirm("댓글을 삭제합니다.");    
        if(msg == true){ // 확인을 누를경우
            deleteCmt(r_idx, b_idx);
        }
        else{
            return false; // 삭제취소
        }
    }
	
	function deleteCmt(r_idx, b_idx) {
		$.ajax({  
			data : {
				"r_idx" : r_idx,
				"b_idx" : b_idx
			},
			url : "./replyDelete.do",
			dataType : "JSON",
			contentType: "application/json; charset=UTF-8",
			success : function(data) {
				if(data.t == true) {
					alert("삭제완료되었습니다.");
					location.href='boardDetail.do?idx='+b_idx;
				}else {
					alert("ERROR");
				}
			}
		})
	}

	</script>
</head>
<body>

<!-- boardReply.jsp문서  단독실행금지 -->
<br>
  	<!-- 댓글 부분 -->
<table border="1" bordercolor="lightgray">

    <!-- 댓글 목록 -->
			<c:forEach var="replyList" items="${replyList}">
            <tr>
                <!-- 아이디, 작성날짜 -->
                
                <td id="b"  width="100" align="center">
                        <font style='font-size:11pt; color:gray'>${replyList.r_id} <font style='font-size:11pt; color:gray'><br>
                        <font style='font-size:11pt; color:gray'>${replyList.r_date} ${replyList.r_idx}</font>
                </td>
                <!-- 본문내용 -->
                <td id="b"  width="550">
                       <font style='font-size:11pt; color:black'> ${replyList.r_content}  </font>
                </td>
                
                <!-- 버튼 -->
                <td id="b"  width="100" align="center">
                    <!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
						<c:if test="${sessionScope.sessionID==replyList.r_id || sessionScope.sessionID=='admin'}">
                        <input type="submit" onclick="cmUpdateOpen(${replyList.r_idx}, ${replyList.b_idx})" value="수정">
                        <input type="submit" onclick="cmDeleteOpen(${replyList.r_idx}, ${replyList.b_idx})" value="삭제"> 
						</c:if>
                </td>
               
            </tr>
             </c:forEach>

            

            <!-- 로그인 했을 경우만 댓글 작성가능 -->
			<c:if test="${sessionScope.sessionID!=null}">
            <tr bgcolor="#F5F5F5">
            <form id="writeCommentForm" action="replyWrite.do">
            	<input type="hidden" name="b_idx" value="${b_idx}">
            	<input type="hidden" name="r_id" value="${sessionScope.sessionID}">
                <!-- 아이디-->
                <td id="b" width="100" align="center">
				
                        <font style='font-size:11pt; color:gray'>${sessionScope.sessionID}</font>

                </td>
                <!-- 본문 작성-->
                <td id="b"  width="550">

                        <textarea name="r_content" rows="7" cols="100" style="font-size:12px; font-color:gray" placeholder="내용을 입력하세요."></textarea>

                </td>
                <!-- 댓글 등록 버튼 -->
                <td id="b" width="100" align="center">
                        <input type="submit" value="등록">
                </td>
            </form>
            </tr>
			</c:if>

</table>	
</body>
</html>