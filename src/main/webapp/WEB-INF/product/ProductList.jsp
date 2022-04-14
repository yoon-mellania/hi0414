<%@page import="member.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="./../common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		function insert(){
			location.href='insert.prd'; // get 방식
		}
		function goUpdate( num ){
			location.href='update.prd?pmkey=' +  num ; // ProductUpdateController get
		}
		function abc(){
			location.href='list.prd'; // get 방식
		}
		</script>
		
		
		<script src="../js/jquery.js" type="text/javascript"></script>
	    <script src="../js/jquery-ui-1.8.16.custom.min.js"></script>
	    <script type="text/javascript">
	  	$(document).ready(function() {   
	  		/* $("#datepicker").datepicker(); */
	  		 $('#datepicker').datepicker({"onSelect" : function(value,date,inst){//,date,inst는 필요없음
	             alert("선택 : " + value);
	             $('.result').append(value); // html가능함, innerHTML은 왜 안되지?
	         }});
	         /* 
	         type=date로 체크하려면 body에 button을 만들고 아래 코드를 쓰자 
	        	또는 $("input[type='date']").change(function(){ 로 해도 된다.
	         */
	        /*  $("#datepicker").change(function(){ // change대신 click은 안됨
	        	 var value2 = $("input[type='date']").val();
	             $('.result').append(value2); // html가능함, innerHTML은 왜 안되지?
	             alert(value2);    
	         }); */
	  		
	 
	  	});
	         
	  	</script>
	
</head>
<body>
ProductList.jsp<br>
<%-- 아이디1 : <%=((Member)session.getAttribute("loginfo")) %> <br>  --%>
<%-- 아이디2 : <%=((Member)session.getAttribute("loginfo")).getId() %> <br> --%>
<%-- 아이디3 : 
<%
	if(((Member)session.getAttribute("loginfo")).getId() != null){
		out.println(((Member)session.getAttribute("loginfo")).getId());
	}
%> --%>

<%-- 아이디4 : ${sessionScope.loginfo.id}<br>
아이디5 : <%=session.getAttribute("loginfo")%><br> --%>

<!-- 
session으로 설정한 loginfo가 있을 때에는 
위의 아이디1~아이디5까지 모두 잘 나오지만..

session으로 설정한 loginfo가 없을 때에는 
위의 아이디2, 아이디3은 NullPointerException 이 발생한다.

언제가 가능한 아이디4가 제일 괜찮겠다.

-->
<br>
<a href="main.jsp">시작 페이지</a> 
<!-- 
윗줄에서 별도의 경로없이 main.jsp라고 했지만 그렇다고 현재 폴더밑의 main.jsp로 이동하지는 않는다. 
web-inf폴더안의 jsp에서는 web-inf폴더안의 jsp로 직접 이동할수 없고 
controller를 통해서만 web-inf폴더안의 jsp로 직접 이동할수 있기 때문이다. 
기본 폴더는, url에는 ex로 나오지만 webapp폴더가 기본이고 
그래서 그냥 main.jsp라고 하면 webapp폴더안의 main.jsp를 말하는 것이다.  
윗줄에서 main.jsp는 webapp\main.jsp를 말한다. 
 -->
<a href="logout.jsp">로그아웃</a>
	<h2 align="center">상품 리스트 화면<br>
	ProductList.jsp</h2>
	<!-- 아래 날짜는 질문 들어와서 그냥 해본것임 -->
	<!-- <form action="list.prd" method="get">
		<p>Date: <input id="datepicker" type='date' name="abc" onsubmit="abc()"/></p>
		<input type="submit" value="검색2">
	</form> -->
	<center>
	<form action="list.prd" method="get"> <!-- 왜 post는 안되지?? -->
		<select name="whatColumn">
			<option value="all">전체 검색
			<option value="name">상품명
			<option value="contents">설명
		</select>
		<input type="text" name="keyword" value="오렌지">
		<input type="submit" value="검색">
	</form>
	</center>
	<table border="1" id="tableDesign" align="center" width="60%">
		<tr class="header">
			<td colspan="6" align="right">
				<input type="button" value="추가하기" onclick="insert();">
			</td>
		</tr>
		<tr class="header">
			<th><span>상품번호</span></th>
			<th><span>상품명</span></th>
			<th><span>설명</span></th>
			<th><span>가격</span></th>
			<th><span>삭제</span></th>
			<th><span>수정</span></th>
		</tr>
		<c:forEach items="${productLists}" var="product">
			<tr class="record">
				<td align="center">
					<c:out value="${product.num}" />
				</td>
				<td align="left" class="leftStyle">
					<a href="detail.prd?pmkey=<c:out value="${product.num}" />"><c:out value="${product.name}" /></a><br>
					<!-- detail.prd=>ProductDetailViewController -->
				</td>
				<td align="left">
					<c:out value="${ product.contents }" />
				</td>				
				<td align="right">
					<c:out value="${ product.price }" />원
				</td>
				<td align="right">
					<a href="delete.prd?pmkey=<c:out value="${product.num}" />">삭제</a><br>
					<!-- delete.prd => ProductDeleteController -->
				</td>
				<td class="lastStyle"  align="center">
					<input type="button" value="수정" class="btnView" value="${product.num}" onclick="goUpdate('${product.num}')"/>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<center>
		${pageInfo.pagingHtml}
	</center>		
</body>
</html>