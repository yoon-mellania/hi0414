<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="common.jsp"%>
<html>
	<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BootStrap</title>
	<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap.min.css">
	<link rel="stylesheet"
		href="<%=contextPath%>/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<%=contextPath%>/css/kfonts2.css">
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	
	<script src="<%=contextPath%>/js/jquery-1.11.3.min.js"></script>
	<script src="<%=contextPath%>/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">미니 쇼핑몰</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">회원<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">회원 가입</a></li>
							<li><a href="#">로그인</a></li>
							<li><a href="#">회원 탈퇴</a></li>
						</ul>
					</li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">게시물<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">게시물 등록</a></li>
							<li><a href="#">로그인 수정</a></li>
							<li><a href="#">게시물 삭제</a></li>
						</ul>
					</li>					

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">상품<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">상품 등록</a></li>
							<li><a href="#">상품 정보 수정</a></li>
							<li><a href="#">상품 목록 삭제</a></li>
						</ul>
					</li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">쇼핑몰<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">aaa</a></li>
							<li><a href="#">bbb</a></li>
							<li><a href="#">ccc</a></li>
						</ul>
					</li>					

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							로그인</a></li>
				</ul>
			</div>

		</div>
	</nav>
</body>
</html> --%>