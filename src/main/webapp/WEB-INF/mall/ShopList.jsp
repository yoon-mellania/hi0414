<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="./../common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>	
</head>
<body>
ShopList.jsp<br><br> <!-- OrderMallController에서 넘어옴 -->
<a href="logout.jsp">로그아웃</a> <!-- 기준 폴더 위치는 webapp => webapp의 logout.jsp를 뜻함  -->
<br><br>
	<h2 align="center">주문 내역</h2>
	<table border="1" id="tableDesign" align="center">
		<tr class="header">
			<td colspan="3" align="center">
				주문자 정보 : ${sessionScope.loginfo.name}(${sessionScope.loginfo.id})
			</td>
		</tr>	
		<tr class="header">
			<th><span>주문 번호</span></th>
			<th><span>주문 일자</span></th>
			<th><span>상세 보기</span></th>
		</tr>
		<c:forEach items="${requestScope.orderlists}" var="shopinfo">
		<!-- requestScope. 생략 가능 -->
		<!-- orderlists에는 hong 아이디로 주문한 모든 내역((mid,oid,orderdate)이 들어있다. -->
			<tr class="record">
				<td align="center">
					${shopinfo.oid}
				</td>				
				<td align="center">
					${shopinfo.orderdate}
				</td>
				<td align="center">
					<a href="detailview.mall?pmkey=${shopinfo.oid}">상세 보기</a> <!-- pmkey=주문번호 -->  
					<!-- detailview.mall => DetailViewController -->
				</td>				
			</tr>
		</c:forEach>		
	</table>		
</body>
</html> 