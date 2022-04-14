<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="./../common/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 폼</title>
<script type="text/javascript">
	function register() {
		location.href = "registerForm.me"; // MemberRegisterController.java get
	}
	function memberList() {
		location.href = "list.me"; // MemberListController 
	}
	function init(){
		alert(f.password.value+","+f.password.value.length);
		f.pw.value=f.password.value;
	}
</script>
</head>
<body >
	<br>
	<br> MemberLoginForm.jsp
	<br>
	<br>
	<form method="post" action="LoginForm.me" name="f"> <!-- => MemberLoginController -->
		<!-- MemberLoginController post-->
		<input type="text" name="pw">
		<table border="1" width="40%" height="120px" cellspacing="0px">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="kim"></td>
			</tr>
			<tr>
				<td>비번</td>
				<td><input type="password" name="password"  ></td> <!-- value="1234" value를 1234로 넣었는데도 1111로 넘어간다. value를 여기에 넣지말고 실행할 때 직접 넣자. -->
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="로그인"> 
					<input type="reset" value="취소"> 
					<input type="button" value="회원가입" onClick="register();"> 
					<input type="button" value="회원목록보기" onClick="memberList();">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
