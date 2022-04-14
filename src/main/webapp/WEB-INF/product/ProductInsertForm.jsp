<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
	<title>상품 추가 화면</title>
	<meta charset="UTF-8">
	<style type="text/css">
		.err{
		font-size: 9pt;
		color: red;
		font-weight: bold;
		}
	</style>
</head>
<body>
ProductInsertForm.jsp<br><br>
<div id="wrapper">	
	<form:form commandName="product"  method="post"  action="insert.prd"  enctype="multipart/form-data">
		<h1><span>상품 추가 화면</span></h1>
		<p>
			<label for="name">*상품명</label>
			<input type="text" name="name" id="name" value="빨간사과">
			<form:errors cssClass="err" path="name" />
		</p>
		<p>
			<label for="company">제조 회사</label>
			<input type="text" name="company" id="company" value="${product.company }">
			<form:errors cssClass="err" path="company" />
		</p>
		<p>
			<label for="price">*가격</label>
			<input type="text" name="price" id="price" value="5000">
			<form:errors cssClass="err" path="price" />
		</p>	
		<p>
			<label for="stock">재고 수량</label>
			<input type="text" name="stock" id="stock" value="30">
			<form:errors cssClass="err" path="stock" />
		</p>	
		<p>
			<label for="point">적립 포인트</label>
			<input type="text" name="point" id="point" value="10">
			<form:errors cssClass="err" path="point" />
		</p>	
		<p>
			<label for="contents">*설명</label>
			<input type="text" name="contents" id="contents" value="이건 설명입니다.맛있어">
				<form:errors cssClass="err" path="contents" />
		</p>	
		<p>
			<label for="upload">*그림 파일</label>
			<input type="file" name="upload" id="upload" value=""  multiple="multiple">
			<!-- multiple="multiple" 넣으면 다중 화일 업로드 가능=>ProductInsertController2에 하다말았다.(웹서버 폴더에 다중 업로드 까지는 했고 DB에 올리는 건 아직 안함, DB에는 a.jpg,b.jpg,c.jpg 이렇게 쉼표로 연결해서 올리면 될듯함) -->
			<form:errors cssClass="err" path="image" />
		</p>	
		<p>
			<label for="inputdate">입고 일자</label>
			<input type="text" name="inputdate" id="inputdate" value="2022/01/01">
		</p>	
		<p class="btnRow">
			<input type="submit" value="추가하기" id="btnSubmit">		
		</p>
	</form:form>
</div>
</body>
</html>

