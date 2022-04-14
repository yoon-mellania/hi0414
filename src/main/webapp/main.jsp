<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<%    
	//String viewPage = request.getContextPath() + "/list.prd"; 
	String viewProduct = request.getContextPath() + "/list.prd"; 
	// 윗줄에서 request.getContextPath()가 없으면..
	// http://localhost:9090/ex/list.prd 이렇게 나와야 되는데 
	// http://localhost:9090/list.prd 처럼 ex가 안나와서 404 Not Found 에러가 뜬다.
	
	//response.sendRedirect(viewPage) ;
	String viewOrder = request.getContextPath() + "/order.mall"; // => OrderMallController로 넘어감
%>
<html>          
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=viewProduct%>">상품 목록 보기</a><br><br><br>
	<a href="<%=viewOrder%>">나의 주문 내역</a><br><br><br>
	session.getAttribute("loginfo"): <%=session.getAttribute("loginfo")%>
</body>
</html>  

  