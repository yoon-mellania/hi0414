<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 수정 화면</title>
<style type="text/css">
		.err{
		font-size: 9pt;
		color: red;
		font-weight: bold;
		}
	</style>
</head>
<body>
ProductUpdateForm.jsp<br><br>
<c:set var="theString" value="http://localhost:9090/${pageContext.request.contextPath}/resources/${product.image }"/>
<%-- <c:set var="theString" value="${pageContext.request.contextPath}/resources/${product.image }"/> --%>
<!-- 
9090뒤에 /가 있으면 ~9090//ex/~ 의 형태로 /가 2번 나오지만 실행은 잘 된다. 이렇게 / 빼도 잘 된다. 9090${pageContext.request.contextPath}
${pageContext.request.contextPath} 가 /ex의 형태로 출력된다. 
앞의 http://localhost:9090/ 생략가능, 9090 뒤에 /까지 생략해야 함, / 생략 안하면 그림 안나옴-->

theString : ${ theString}<br> 
<!-- http://localhost:9090//ex/resources/man_coat.jpg -->
image:${product.image}<br>
<h2 align="center">상품 수정 화면(ProductUpdateForm.jsp)${product.num}</h2>
<form:form commandName="product" action="update.prd" method="post" enctype="multipart/form-data">
	<input type="hidden" name="num" value="${product.num}">
<table border="1" align="center">
	<tr>
		<td width="120">*상품명</td>
		<td width="180">
			<input type="text" name="name" value="${product.name}">
			<form:errors cssClass="err" path="name" />
		</td>
	</tr>
	<tr>
		<td width="120">제조 회사</td>
		<td width="180">
			<input type="text" name="company" value="${product.company}">
			<form:errors cssClass="err" path="company" />
		</td>
	</tr>
	<tr>
		<td width="120">*가격</td>
		<td width="180">
			<input type="text" name="price" value="${product.price}">
			<form:errors cssClass="err" path="price" />
		</td>
	</tr>
	<tr>
		<td width="120">재고 수량</td>
		<td width="180">
			<input type="text" name="stock" value="${product.stock}">
			<form:errors cssClass="err" path="stock" />
		</td>
	</tr>
	
	<tr>
		<td width="120">적립 포인트</td>
		<td width="180">
			<input type="text" name="point" value="${product.point}">
			<form:errors cssClass="err" path="point" />
		</td>
	</tr>
	
	<tr>
		<td width="120">*설명</td>
		<td width="180"><input type="text" name="contents" value="${product.contents}">
		<form:errors cssClass="err" path="contents" /></td>
	</tr>
	<tr>
		<td width="120">*그림 파일</td>
		<td width="180">
		<%-- <img src = "${theString}" width="100px" height="100px"/> --%>
		<img src="<%=request.getContextPath() %>/resources/${product.image}" width="100px" height="100px"> <!-- 잘됨 -->
		<input type="file" name="upload" id="upload" value="">
		<input type="hidden" name="upload2" id="upload" value="${product.image}">
		<!-- 기존의 화일은 지우기 위해 hidden으로 넘긴다.upload2 작성하지 않으면 이미지가 지워지지 않는다.  -->
		<form:errors cssClass="err" path="image" />
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
