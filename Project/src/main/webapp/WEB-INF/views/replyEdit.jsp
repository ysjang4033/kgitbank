<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title> 댓글 답변 </title>
    
    <style type="text/css">
        #wrap {
            width: 550px;
            margin: 0 auto 0 auto;
            text-align :center;
        }
    

    </style>
    <script src="./js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
    
	    function check() {
	    	
	    	var r_idx=document.getElementById("r_idx").value;
	    	var r_content=document.getElementById("r_content").value;
	    	var b_idx=document.getElementById("b_idx").value;
	    	alert(r_idx+""+r_content+""+b_idx);
	    	
			$.ajax({  
				data : {
					"r_idx" : r_idx,
					"r_content" : r_content,
					"b_idx" : b_idx
				},
				url : "./replyEdit.do",
				dataType : "JSON",
				success : function(data) {
					if(data.t == true) {
						alert("수정완료되었습니다.");
 						window.opener.document.location.reload();
						window.close(); 
					}else {
						alert("ERROR");
					}
				}
			})
		}

    </script>
    
</head>
<body>
<center>
    <br>
    
    <b><font size="5" color="gray">댓글 수정</font></b>
    <hr size="1" width="550">
    <br>       
    		<input type="hidden" value="${replyDetail.r_idx}" id="r_idx">
    		<input type="hidden" value="${replyDetail.b_idx}" id="b_idx">
            <textarea rows="7" cols="70" name="r_content" id="r_content">${replyDetail.r_content}</textarea>
            <br><br>
            <input type="button" value="등록" onclick="check()">
            <input type="button" value="창닫기" onclick="window.close()">
<center>
</body>
</html>