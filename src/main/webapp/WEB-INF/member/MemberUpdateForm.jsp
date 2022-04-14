<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 수정 화면</title>
<style type="text/css">
		.err{
		font-size: 9pt;
		color: red;
		font-weight: bold;
		}
	</style>
</head>
<body>
<%
	String[] hobby = {"마라톤","영화감상","게임","공부"};
	pageContext.setAttribute("hobby",hobby);
	
%>
<%-- <%=System.out.println("MemberUpdateForm.jsp") %> --%>
<h2 align="center">회원 수정 화면(MemberUpdateForm.jsp)</h2>
<form:form commandName="member" action="update.me" method="post">
	<!-- update.me => MemberUpdateController -->
	<input type="hidden" name="id" value="${member.id}">
	<table border="1" align="center">
		<tr>
			<td width="120">아이디</td>
			<td width="380">
				${member.id}
				<%-- <input type="text" name="id" value="${member.id}"> --%>
			</td>
		</tr>
		<tr>
			<td width="120">비번</td>
			<td width="380">
				<input type="text" name="password" value="${member.password}">
				<form:errors cssClass="err" path="password" />
			</td>
		</tr>
		<tr>
			<td width="120">이름</td>
			<td width="380">
				<input type="text" name="name" value="${member.name}">
				<form:errors cssClass="err" path="name" />
			</td>
		</tr>
		<tr>
			<td width="120">성별</td>
			<td width="380">
				<input type="radio" name="gender" id="gender" value="남자"  <c:if test="${member.gender=='남자'}"> checked </c:if>>남자
				<input type="radio" name="gender" id="gender" value="여자"  <c:if test="${member.gender=='여자'}"> checked </c:if>>여자
				<form:errors cssClass="err" path="gender" />
		</td>
		</tr>
		<tr>
			<td width="120">취미2</td>
			<td width="380">
				<%-- 
				<c:set var="theString" value="${member.hobby }"/> 
				<input type="checkbox" name="hobby" id="hobby2" value="마라톤" <c:if test="${fn:contains(theString,'마라톤')}"> checked</c:if> >마라톤
				<input type="checkbox" name="hobby" id="hobby2" value="영화감상" <c:if test="${fn:contains(theString,'영화감상')}"> checked</c:if> >영화감상
				<input type="checkbox" name="hobby" id="hobby2" value="게임" <c:if test="${fn:contains(theString,'게임')}"> checked</c:if> >게임 
				<input type="checkbox" name="hobby" id="hobby2" value="공부" <c:if test="${fn:contains(theString,'공부')}"> checked</c:if> >공부
				<form:errors cssClass="err" path="hobby" />
				 --%>
				<c:forEach var="i" begin="0" end="${fn:length(hobby)-1 }" step="1">
					<input type="checkbox" name="hobby" id="hobby2" value="${hobby[i] }" <c:if test="${fn:contains(member.hobby,hobby[i])}"> checked</c:if> > ${hobby[i] }        
				</c:forEach>
				<form:errors cssClass="err" path="hobby" />
				
			</td>
		</tr>
		
		<tr>
			<td width="120">주소1</td>
			<td width="380">
				<input type="text" name="address1" value="${member.address1}">
				<form:errors cssClass="err" path="address1" />
			</td>
		</tr>
		<tr>
			<td width="120">주소2</td>
			<td width="380">
				<input type="text" name="address2" value="${member.address2}">
				<form:errors cssClass="err" path="address2" />
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center" width="300">
				<input type="submit" value="수정하기">
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>