<%@page import="member.model.Member"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="product.model.Product"%>
<%@page import="java.util.Vector"%>
<%@page import="mall.cart.MyCartList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%
	String contextPath = request.getContextPath() + "/WEB-INF";
	//out.print("컨텍스트 경로 : " + contextPath + "<br>");
	String pattern = "###,###" ;
	DecimalFormat df = new DecimalFormat( pattern ) ;
%>     --%>

<%
	/* String contextPath = application.getContextPath();
	out.print("컨텍스트 경로 : " + contextPath + "<br>"); */
	
	/* 
	String pattern = "###,###" ;
	DecimalFormat df = new DecimalFormat( pattern ) ;
	 */
%> 

<%-- <%
	MyCartList mycart = (MyCartList)session.getAttribute("mycart") ;
	if (mycart == null) {
		out.println("카트 판매 내역 없음 <br><br>") ;
	}else{
		Map<Integer, Integer> carts = mycart.GetAllOrderLists() ;
		Set<Integer> keylists = carts.keySet() ;
		for(Integer key : keylists){
			Integer value = carts.get(key) ;
			out.print( key +"/" + value + "<br>") ;
		}
	}
	out.println( "------------------------------<br>") ;			
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	접속자 아이디 : ${loginfo.id}<br>
	접속자 아이디 : ${sessionScope.loginfo.id}<br>
	<%-- 접속자 아이디 : <%=((Member)session.getAttribute("loginInfo")).getId()%> --%> <!-- 이건 에러 -->
<br>
</body>
</html>

