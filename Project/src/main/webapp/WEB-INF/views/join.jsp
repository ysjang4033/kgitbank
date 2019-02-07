<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[join.jsp]</title>
<style type="text/css">
table {
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
</style>
<script src="./js/jquery-3.3.1.min.js"></script>
</head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="./js/InputJS.js?ver=1"></script>

<script type="text/javascript">

	var flag = false;

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
	    if(!document.getElementById("m_pwd2").value)
	    {
	        alert("비밀번호를 입력하세요");    
	        document.getElementById("m_pwd2").focus();
	        return false;
	    }
	    if(!document.getElementById("m_name").value)
	    {
	        alert("이름을 입력하세요");    
	        document.getElementById("m_name").focus();
	        return false;
	    }
	    if(!(document.getElementById("birth1").value && document.getElementById("birth2").value && document.getElementById("birth3").value))
	    {
	        alert("생년월일을 입력하세요");    
	        document.getElementById("birth1").focus();
	        return false;
	    }
	    if(!(document.getElementById("email1").value && document.getElementById("email2").value))
	    {
	        alert("이메일을 입력하세요");    
	        document.getElementById("email1").focus();
	        return false;
	    }
	    if(!(document.getElementById("phone1").value && document.getElementById("phone2").value && document.getElementById("phone3").value))
	    {
	        alert("전화번호를 입력하세요");    
	        document.getElementById("phone1").focus();
	        return false;
	    }
	    if(!(document.getElementById("juso1").value && document.getElementById("juso2").value))
	    {
	        alert("주소를 입력하세요");    
	        document.getElementById("juso2").focus();
	        return false;
	    }
	    if (flag == false) { // 버튼을 안눌렀으면
	         alert("아이디 중복 체크 하세요");
	         return false;
	    } else {
	    	checkid();
	    }
	}
	
	 function email() {
		  if (document.getElementById("emailin").value == '1') {
		    	  document.getElementById("emailin").readonly = false;
		    	  document.getElementById("emailin").value = '';
		    	  document.getElementById("emailin").focus();
		      } else {
		    	  document.getElementById("emailin").readonly = true;
		    	  document.getElementById("email2").value = document.getElementById("emailin").value;
		      }
		   }

   
   function idCheck() {
      var id = document.getElementById("m_id").value;
      $.ajax({
         data : {
            "m_id" : id
         },
         url : "./idCheck.do",
         dataType : "JSON",
         success : function(data) {
            if (data.t == true) {
               alert("사용 불가능한 아이디입니다.");
            } else
               alert("사용 가능한 아이디입니다.");
            flag = true;
         }
      })
   }
   
   function checkid() {

         $.ajax({
            data : {
               "m_id" : $('#m_id').val(),
               "m_pwd" : $('#m_pwd').val(),
               "m_name" : $('#m_name').val(),
               "m_gender" : $('#m_gender').val(),
               "m_hobby" : $('#m_hobby').val(),
               "birth1" : $('#birth1').val(),
               "birth2" : $('#birth2').val(),
               "birth3" : $('#birth3').val(),
               "email1" : $('#email1').val(),
               "email2" : $('#email2').val(),
               "phone1" : $('#phone1').val(),
               "phone2" : $('#phone2').val(),
               "phone3" : $('#phone3').val(),
               "m_juso1" : $('#juso1').val(),
               "m_juso2" : $('#juso2').val(),
               

            },
            type : "POST",
            url : "./MemberInsert.do",
            success : function() {
               location.href = "login.do";
            }
         })
   }
</script>

<body>
<center>
   <br><br>
   <font style='font-size:24pt; color:gray'>J O I N </font>
   <br><br>
   
   <table>
   	 <tr>
   	 	<td> 아이디 :</td>
   	 	<td> <input type="text" name="m_id" id="m_id"  placeholder="I  D"/> <input type="button" onclick="idCheck()" value="중복확인"> </td>
   	 </tr>
   	 
   	 <tr>
   	 	<td> 비밀번호 : </td>
   	 	<td> <input type="password" name="m_pwd" id="m_pwd" placeholder="P W D"/>
   	 </tr>
   	 <tr>
   	 	<td> 비밀번호 확인 : </td>
   	 	<td> <input type="password" name="m_pwd2"
               id="m_pwd2" onkeyup="checkPwd();" placeholder="R E P W D"> <label id="pwdlabel">&nbsp;&nbsp;비밀번호를
              	 한번 더 입력해주세요.</label>
   	 </tr>
   	 <tr>
   	 	<td> 이름 :  </td>
   	 	<td> <input type="text" name="m_name" id="m_name" placeholder="N A M E"/> </td>
   	 </tr>
   	 <tr>
   	 	<td> 생년월일 : </td>
   	 	<td> </label> <input type="text" name="birth1"
               id="birth1" size="5" placeholder="0000">년 <input type="text" name="birth2"
               id="birth2" size="5" placeholder="00">월 <input type="text" name="birth3"
               id="birth3" size="5" placeholder="00">일 </td>
   	 </tr>
   	 <tr>
   	 	<td> 성별 : </td>
   	 	<td> <input type="radio" name="m_gender" value="male" id="m_gender" checked>남자
	 	  <input type="radio" name="m_gender" value="female" id="m_gender">여자<br>
	 	</td>
   	 </tr>
   	 <tr>
   	 	<td> 취미 : </td>
   	 	<td>  <input type="checkbox" name="m_hobby" value="game" id="m_hobby" checked>게임
	 	  <input type="checkbox" name="m_hobby" value="study" id="m_hobby">공부
	 	  <input type="checkbox" name="m_hobby" value="ski" id="m_hobby">스키
	 	  <input type="checkbox" name="m_hobby" value="movie" id="m_hobby">영화<br>
	 	</td>
   	 </tr>
   	 <tr>
   	 	<td> 이메일 : </td>
   	 	<td> <input type="text" name="email1" id="email1" placeholder="E M A I L"/>
            @ <input type="text" name="email2" id="email2" /> 
            <select   name="emailin" onChange="email();" id="emailin">
               <option value="input">이메일선택</option>
               <option value="naver.com">naver.com</option>
               <option value="gmail.com">gmail.com</option>
               <option value="daum.net">daum.net</option>
               <option value="nate.com">nate.com</option>
            </select> </td>
   	 </tr>
   	 <tr>
   	 	<td> 전화번호 : </td>
   	 	<td>  <select name="phone1"
               id="phone1">
               <option value="010">010</option>
               <option value="011">011</option>
               <option value="012">012</option>
               <option value="013">013</option>
               <option value="014">014</option>
               <option value="015">015</option>
               <option value="016">016</option>
               <option value="017">017</option>
               <option value="018">018</option>
               <option value="019">019</option>
            </select> - <input type="text" name="phone2" id="phone2" size="5" placeholder="0000"> -
            <input type="text" name="phone3" id="phone3" size="5" placeholder="0000">
        </td>
   	 </tr>
   	 <tr>
   	 	<td> 주소 : </td>
   	 	<td> <input type="text" size="30" id="juso1" name="m_juso1" readonly>  <input type="text" size="10" id="code"
                  name="code" placeholder="ADDRESS" readonly> <input type="button"
                  onclick="DaumPostcode()" value="우편번호">
        </td>
   	 </tr>
   	 <tr>
   	 	<td> 상세주소 : </td>
   	 	<td> <input type="text" size="30" id="juso2" name="m_juso2"
                        onkeyup="checklen(this,30)" placeholder="DETAIL ADDRESS">
        </td>
   	 </tr>
   	 
   </table>
   <br><br>
   <input class="bt" type="button" value="가입완료" onclick="checkValue()">&nbsp;&nbsp;
   <input type="button" value="로그인" onclick="location.href='login.do'"> <br> <br>
  
</center>
</body>
</html>