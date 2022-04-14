<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 수정 화면</title>
</head>
<body>
ProductUpdateForm_old.jsp<br><br>
<h2 align="center">상품 수정 화면</h2>
<form action="update.prd" method="post" enctype="multipart/form-data">
	<input type="hidden" name="pmkey" value="${product.num}">
<table border="1" align="center">
	<tr>
		<td width="120">상품명</td>
		<td width="180"><input type="text" name="name" value="${product.name}"></td>
	</tr>
	<tr>
		<td width="120">가격</td>
		<td width="180"><input type="text" name="price" value="${product.price}"></td>
	</tr>
	<tr>
		<td width="120">설명</td>
		<td width="180"><input type="text" name="contents" value="${product.contents}"></td>
	</tr>
	<tr>
		<td width="120">그림 파일</td>
		<td width="180"><input type="file" name="upload" id="upload" value="${product.image}"></td>
		
	</tr>
	<tr>
		<td colspan="2" align="center" width="300">
			<input type="submit" value="수정하기">
		</td>
	</tr>
</table>
</form>
</body>
</html>