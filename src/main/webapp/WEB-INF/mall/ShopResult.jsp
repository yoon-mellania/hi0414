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
<a href="main.jsp">시작 페이지</a><br><br> <!-- 기준 폴더 위치는 webapp => webapp의 main.jsp를 뜻함  -->
ShopResult.jsp<br><br>
	<h2 align="center">주문 상세 내역4</h2>
	<table border="1" id="tableDesign" align="center">
		<tr class="header">
			<td colspan="2" align="left">
				고객 : ${sessionScope.loginfo.name}
			</td>
			<td colspan="3" align="left">
				송장 번호 : ${requestScope.pmkey} <!-- requestScope.생략가능 -->
			</td>			
		</tr>	
		<tr class="header">
			<td colspan="5" align="left">
				배송지 : ${sessionScope.loginfo.address1}&nbsp;${sessionScope.loginfo.address2}
				<!-- sessionScope. 생략 가능 -->  
			</td>			
		</tr>	
		<tr class="header">
			<th><span>순번</span></th>
			<th><span>상품명(상품번호)</span></th>
			<th><span>수량</span></th>
			<th><span>단가</span></th>
			<th><span>금액</span></th>
		</tr>
		<c:forEach items="${requestScope.shoplists}" var="result" varStatus="status" >
			<tr class="record">
				<td align="center">
					${status.count}
				</td>			
				<td align="center">
					${result.pname}(${result.pnum})
				</td>				
				<td align="center">
					${result.qty}
				</td>
				<td align="center">
					<fmt:formatNumber value="${result.price}" pattern="###,###"/>					
				</td>
				<td align="center">
					<fmt:formatNumber value="${result.amount}" pattern="###,###"/>					
				</td>			
			</tr>
		</c:forEach>		
	</table>		
	<br><br>
</body>
</html> 