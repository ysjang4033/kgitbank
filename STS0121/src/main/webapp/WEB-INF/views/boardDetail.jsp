<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> [guestDetail.jsp]</title>
  <style type="text/css">
    *{font-size:20pt; font-weight:bold;  font-family:  Segoe UI Symbol , comic Sans MS ;  }
    a{font-size:20pt; font-weight:bold; color:blue; text-decoration:none; }
    a:hover{font-size:26pt; font-weight:bold; color:green; text-decoration:underline; }                
  </style>
  <script type="text/javascript" src="/resource/js/jquery-1.12.1.js"></script>
  <script type="text/javascript">
/*  	function like_func() {
 		var boardno=$('#boardno').val();
 		
 		$.ajax({
 			url: "../liketo/like.do"
 			type: "GET",
 			cache: false;
 			dataType: "json",
 			data: 'boardno='+boardno,
 			success: function(data) {
 				var msg='';
 				var like_img='';
 				msg += data.msg;
 				alert(msg);
 				
 				if(data.like_check==0) {
 					like_img= "./images/dislike.png"; 					
 				} else {
 					like_img= "./images/like.png";
 				}
 				$('#like_img', frm_read).attr('src',like_img);
 			}
 		});
 	}
 		 */
 	
  
  </script>
  
 </head>
<body>
 <table  width=900 border=1 cellspacing=0 cellpadding=10>
  <tr align="center" bgcolor="yellow">
    <td colspan="2"> [${detail.name}님] 상세정보출력 </td>
  <tr>
  
  <tr>
  	<td rowspan="8" align="center"> <a href="fileDownload.do?idx=${detail.img_file_name}" name="file"><img src="./resources/upload/${detail.img_file_name}" width=200 height=250></a> </td>
  	<td> 번 호:${detail.hobby_idx}</td>
  </tr>
  
  <tr><td> 이 름: ${detail.name} </td> </tr>
  <tr><td> 제 목: ${detail.title} </td>  </tr>
  <tr><td> 내 용: ${detail.content} </td>  </tr>
  <tr><td> 성 별: ${detail.gender} </td> </tr>
  <tr><td> 취 미: ${detail.hobby} </td> </tr>
  <tr><td> 첨부파일: <a href="fileDownload.do?idx=${detail.img_file_name}" name="file"> ${detail.img_file_name} 다운로드 </a></td> </tr>
  <div >
  <tr><td> <a href='javascript: like_func();'>좋아요</a> 개</td></tr>
  </div>
  <tr align="center">
   <td colspan="2">
       <a href="index.jsp">[index]</a>
       <a href="write.do">[신규등록]</a>
       <a href="preedit.do?idx=${detail.hobby_idx}">[수정]</a>
  	   <a href="list.do">[전체출력]</a>
  	   <a href="delete.do?idx=${detail.hobby_idx}&idx2=${detail.img_file_name}">[삭제]</a>
   </td>
  </tr>
 </table>
 	
 	<!-- jstl태그  import  -->
 <c:import url="/reply_list.do" />
 	
 	
</body>
</html>