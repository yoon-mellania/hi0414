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
MallList.jsp<br><br>
	<h2 align="center">주문 내역</h2>
	<table border="1" id="tableDesign" align="center">
		<tr class="header">
			<td colspan="5" align="center">
				주문자 정보 : ${sessionScope.loginfo.name}(${sessionScope.loginfo.id})
				<!-- 
				sessionScope. 모두 빼도 잘 된다. loginfo.name, loginfo.id만 써도 된다. 
				scope는 생략이 가능하다. 
				${sessionScope.id}를 ${id}라고 표현할 수 있다. 
				생략하면 작은 영역순으로 찾게 된다.
				request에서 id를 찾고, 없으면 session, 없으면 application순으로...
				-->
			</td>
		</tr>	
		<tr class="header">
			<th><span>상품 번호</span></th>
			<th><span>상품명</span></th>
			<th><span>주문 수량</span></th>
			<th><span>단가</span></th>
			<th><span>금액</span></th>
		</tr>
		<!-- 위의 common.jsp가 없으면 홍길동(hong)은 잘 나오는데
		아래 상품 목록은 안나오는 이유가 뭘까?? => taglib prefix="c"가 없어서..-->
		<c:forEach items="${sessionScope.shoplists}" var="shopinfo"> <!-- session shoplists 설정 => CartListController에서 함 --> 
		<!-- sessionScope.없어도 된다. -->
			<!-- CartListController.java에서 shoplists session설정함 -->
			<tr class="record">
				<td align="center">
					${shopinfo.pnum}
				</td>				
				<td align="center">
					${shopinfo.pname}
				</td>
				<td align="center">
					${shopinfo.qty}
				</td>				
				<td align="center">
					${shopinfo.price}
				</td>
				<td align="center">
					${shopinfo.amount}
				</td>				
			</tr>
		</c:forEach>
		<tr class="header">
			<td colspan="3" align="center">
				<a href="calculate.mall">결제하기</a> <!-- calculate.mall => CartCalculateController get로 넘어감 --> 
				&nbsp;&nbsp; 
				<a href="list.prd">추가 주문</a>
			</td>
			<td colspan="2" align="center">총 금액 : ${totalAmount}</td> <!-- sessionScope. 생략 가능 -->
			<!-- 
			앞의 CartListController.java의 맨 아랫부분에
			totalAmount를 model로도 설정하고 session으로 설정한 것도 있다면..
			위 총금액 출력자리에서 
			totalAmount만 쓰면 model로 설정한 totalAmount가 나오고, 
			sessionScope.totalAmount으로 쓰면 session으로 설정한 totalAmount가 나온다.
			
			앞의 CartListController.java의 맨 아랫부분에
			totalAmount를 model로 설정안하고 session으로 설정한 것도 있다면..
			위 총금액 출력자리에서 
			sessionScope. 생략하고 totalAmount만 써도 잘된다.
			model로 설정한것만 있으면 sessionScope.을 쓰면 안된다.
			 -->
		</tr>
		
	</table>	
	<br><br><br>	
</body>
</html> 


