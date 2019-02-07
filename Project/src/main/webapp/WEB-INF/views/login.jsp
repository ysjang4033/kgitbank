<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title> [login.jsp]</title>
  <style type="text/css">
  	.input_group {
	 height: 20px;
	 font: 1em/50px 'Helvetica';
	}
	.input_group > label {
	   font-size:18px;
	} 
	.loginlegend {
		font-size:30pt;
		font-family:italic;
		color:grey;
	}
  </style>
  <script src="./js/jquery-3.3.1.min.js"></script>
  <script src="./js/InputJS.js"></script>
  <script type="text/javascript">
  function checkValue()
  {

      if(!document.getElementById("m_id").value)
      {
          alert("아이디를 입력하세요");    
          document.getElementById("m_id").focus();
          return false;
      }
      if(!document.getElementById("m_pwd").value)
      {
          alert("비밀번호를 입력하세요");    
          document.getElementById("m_pwd").focus();
          return false;
      }
      logincheck();

  }
  
  function logincheck() {
	  
	  var m_id = document.getElementById("m_id").value;
	  var m_pwd = document.getElementById("m_pwd").value;
//	  alert(m_id+""+m_pwd);
	  $.ajax({
		data : {
			"m_id" : m_id,
			"m_pwd" : m_pwd
		},
		url : "./loginCheck.do",
		dataType : "JSON",  
		success : function(data) {		
            if (data.t == true) {
            	
               alert("로그인 성공하였습니다.");
               location.href='boardList.do';
               
            } else {
               alert("아이디와 비밀번호를 확인해주세요.");
               location.href='login.do';
            }
         }
	  })
	  
  }



  </script>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>	
<body>
<center>

        
            <!-- 이미지 추가 -->
            <br><br>
            

  	<legend class="loginlegend">LOGIN</legend>
	<br>
  
            <table align="center">
                <tr>
                    <td >ID</td>
                    <td><input type="text" name="m_id" id="m_id" maxlength="50"></td>
                </tr>
                <tr>
                    <td>PW</td>
                    <td><input type="password" name="m_pwd" id="m_pwd" maxlength="50"></td>
                </tr>
            </table>
            <br><br>
            <div align="center">
            <input type="submit" value="로그인" onclick="checkValue();"/>
            <input type="button" value="회원가입" onclick="location.href='join.do'"/>
            <input type="button" value="비회원접속" onclick="location.href='boardList.do'"/>

</center>
</body>
</html>
