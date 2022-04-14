<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>
<html>
<head>
	<title>상품 상세 화면</title>

<meta charset="UTF-8">
<style type="text/css">
	.err{
	font-size: 9pt;
	color: red;
	font-weight: bold;
	}
	
	#inTable > td{
		border: 1px solid white; /* 안쪽 테이블의 테두리 없애려면 어떻게 하지? */
	}
</style> 
</head>
<body>
ProductDetailView.jsp<br><br>
1:<%=request.getContextPath() %><br>
2:<%=application.getContextPath() %><br>
3:${pageContext.request.contextPath}<br>
4:${pageContext.request}<br>
5:${request.contextPath}<br>
6:${requestScope.contextPath}<br> 
7:${applicationScope.contextPath }<br>
<%-- ${applicationScope.~~ }<br> 이 줄로는 어떻게 하지?? --%>
8:<%=config.getServletContext() %><br>
9:<%=config.getServletContext() %>/resources<br>


<%-- 
<c:set var="theString2" value="http://localhost:9090/${contextPath}/resources/${product.image }"/>
theString : ${ theString2}<br>
 --%>

<!--  
윗줄은 theString2 : http://localhost:9090//resources/Lighthouse.jpg 이렇게 나오고
아랫줄은 theString2 : http://localhost:9090//ex/resources/Lighthouse.jpg 이렇게 나온다. 아랫줄로 해야한다.
 아래 ${pageContext.request.contextPath} 앞에 / 붙여도 안붙여도 된다. 어차피 /가 포함된다. 
 http://localhost:9090 생략가능???
 --> 
 
<%-- <c:set var="theString" value="http://localhost:9090${pageContext.request.contextPath}/resources/${product.image }"/> 이줄 잘됨 --%>
<%-- <c:set var="theString" value="${applicationScope.product.image }"/>  이렇게 하면 theString에 아무것도 안들어간다. 다시 확인해보기 --%>
<%-- http://localhost:9090 생략하고 ${pageContext~~~ 부터 넣어도 잘된다. pageContext 빼면 안나온다. --%>
<%-- <c:set var="theString" value='<%=application.getContextPath()+"/resources"%>'/> --%> <!-- 이줄 잘돠니? -->
<%-- <c:set var="theString" value='<%=application.getRealPath("/resources") %>'/> --%>  <!-- 이줄 잘되나? -->
<%-- <c:set var="theString" value="${pageContext.request.contextPath}/resources/${product.image }"/> --%> <!-- 이줄 잘됨 -->

theString2 : ${ theString}<br> 
<div align="center" class="body"  id="wrapper">	
	<h2>상품 상세 화면${product.num}</h2>
	<table border="1"  cellspacing="0">
		<tr>
			<td>
			
			<%-- <img src="<%=request.getContextPath()+"\\temp\\<c:out value='${product.image}'"%>" border=0 width=40 height=40> --%>
				<%-- <img src="<%=contextPath%>/images/<c:out value="${product.image}" />"> --%>
				<%-- <img src="c:\\temp\\<c:out value='${product.image}' />" width="100px" height="100px"> --%>
				<%-- <img src="${theString}" width="100px" height="100px"> --%> <!-- 잘됨 -->
				<img src="<%=request.getContextPath() %>/resources/${product.image}" width="100px" height="100px"> <!-- 잘됨 -->
				<!-- 이미지 저장을 resources가 아닌 temp에 하면 업로드는 잘 되는데..
				윗줄에서 resources를 temp로 하면 이미지 안나옴 -->
				<%-- <img src="<%=application.getContextPath() %>/resources/${product.image}" width="100px" height="100px"> --%> <!-- 이것도 잘됨 --> 
				<%-- <img src="<%=config.getServletContext() %>/resources/${product.image}" width="150px" height="100px"> --%> <!-- 안됨 -->
				
				<!-- 아래 코드는 왜 안될까? -->
				<%-- <img src='<%=application.getRealPath("\\resources\\")%>/${bean.image}' width=50 height=50> --%>
				
			</td>
			<td align="center">
				<table border="1" cellspacing="0" id="inTable">
					<tr height="50">
						<td width="120">상품명</td>
						<td width="300"><c:out value="${product.name}" /></td>
					</tr>
					<tr height="50">
						<td width="120">가격</td>
						<td width="300"><c:out value="${product.price}" /> 원</td>
					</tr>
					<tr height="50">
						<td width="120">재고 수량</td>
						<td width="300"><c:out value="${product.stock}" /></td>
					</tr>				
					<tr height="50">
						<td width="120">설명</td>
						<td width="300"><c:out value="${product.contents}" /></td>
					</tr>
					<tr height="50">
						<td width="120">주문 수량</td>
						<td width="300">
						<%-- 	<form:form commandName="product" action="add.mall" method="post">
								<p>
								<input type="hidden" name="num" value="${product.num}">
								</p>
								<p>
								<label for="orderqty">주문수량2</label>
								<input type="text" name="orderqty" id="orderqty" value="1">
								<form:errors cssClass="err" path="orderqty" />
								</p>
								<input type="submit" value="주문">
							</form:form> --%>
							
							<!-- 위의 에러 체크는 왜 안될까... -->
							
							
							<!-- add.mall => mall.controller.CartAddController.java post-->
							<form action="add.mall" method="post">
								<!-- <p> -->
								<input type="hidden" name="num" value="${product.num}">
								<!-- </p>
								<p> -->
								<label for="orderqty">주문수량2</label>
								<input type="text" name="orderqty" id="orderqty" value="1"> 
								<!-- </p> -->
								<input type="submit" value="주문">
							</form>
							
						</td>
					</tr>		
					<%-- 	
					<tr height="50">
						<td width="120">입고일자</td>
						<td width="300">
						<c:out value="${product.inputdate}" />
						<c:set value="${product.inputdate}" var="pinputdate"/>
						<%
						
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd"); // 날짜 처리는 어떻게 해야할까?? 시간까지 안나오게 하고 싶은데.. 
							//sdf.parse(pinputdate);
						%>
						<fmt:formatDate  value="${product.inputdate}" type="date" dateStyle="full"/> 
						</td>
					</tr>
					 --%>
							
					<tr height="50">
						<td colspan="2" align="center" width="300">
							<a href="list.prd">
								상품 리스트
							</a>
						</td>						 
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<br><br><br>
</body>
</html>

<%-- <fmt:formatDate  value="${product.inputdate}" type="date" dateStyle="full"/> --%> 