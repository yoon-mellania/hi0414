<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3 align="center">예외 발생</h3>
	<table border="1"  align="center">
		<tr>
			<td align="center">발생 위치</td>
			<td>${errlocation}</td>
		</tr>
		<tr>
			<td align="center">문자열</td>
			<td>${errstring}</td>
		</tr>
		<tr>
			<td align="center">메시지</td>
			<td>${errmessage}</td>
		</tr>		
	</table>
</body>
</html>