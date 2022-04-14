<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>
<html>
<head>
	<title>회원 상세 화면</title>
<meta charset="UTF-8">
</head>
<body>
MemberDetailView.jsp<br><br>
<div align="center" class="body"  id="wrapper">	
	<h2>회원 상세 화면</h2>
	<table border="1">
		<tr height="50">
			<td width="120">아이디</td>
				<td width="300"><c:out value="${member.id}" /></td>
		</tr>
		<tr height="50">
			<td width="120">이름</td>
			<td width="300"><c:out value="${member.name}" /> </td>
		</tr>
		<tr height="50">
			<td width="120">성별</td>
			<td width="300"><c:out value="${member.gender}" /></td>
		</tr>				
		<tr height="50">
			<td width="120">취미</td>
			<td width="300"><c:out value="${member.hobby}" /></td>
		</tr>
		<tr height="50">
			<td width="120">주소</td>
			<td width="300"><c:out value="${member.address1} ${member.address2}" />
			</td>
		</tr>	
		<tr height="50">
			<td width="120">적립포인트</td>
			<td width="300"><c:out value="${member.mpoint}" />
			</td>
		</tr>					
		<tr>
			<td colspan="2" align="center" width="300">
				<br>
				<!-- <a href="list.me"> --> <!-- 이줄도 되고 아랫줄도 된다. -->
				<a href="javascript:history.go(-1)">
					회원 목록 리스트
				</a>
				<br><br>
			</td>						 
		</tr>
	</table>
</div>
</body>
</html>
