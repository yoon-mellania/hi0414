<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function init(){
		/* 
		수정폼으로 오는 경우는 2가지가 있다. 
		상세보기에서 수정폼으로 넘어올 때(1번)와 수정폼에서 비번 잘못입력해서 다시 수정폼으로 올때(2번)...
		1번(상세보기)에서 올때에는 아래 비번 체크를 하면 안되고,
		2번(비번 잘못입력해서)에서 올때에는 아래 비번 체크를 하면 된다.
		2번으로 올때에는 controller에서 flag 설정해서 오기때문에
		아래 코드에서 flag 설정된것이 있는지 if문으로 비교해봐야 한다. 
		 */
		alert("init");
		var flag = "${flag}";
		alert("flag:"+flag);
		//abc = document.myform.browser_reload.value;
		//alert("abc:"+abc); 
		/* if(document.myform.browser_reload.value==false){
			return;
		} */
		 
		if(flag == "true"){ // true를 꼭 ""로 묶어야 한다. 
			//alert("${match}");
			
			var match = "${match}";
			alert("match1="+match);
			// 위 두줄 또는 아래 한줄로 controller의 ModelAndView 값을 받아올 수 있다. 
			//alert("match2="+"${match}");
			//alert("match3="+${match}); // 에러
			/*    
			flag : true => 수정 클릭했다.
			match : false => 비번 일치 안함
			아래 if문 : 수정 클릭했는데 비번 일치 안하면 권한 없음
			 */
			if(match == "false"){ 
				alert("수정 권한 없음");
				//document.myform.browser_reload.value="false"; // 화면 새로고침 했을 때 권한 없음이라고 뜨면 안되므로 이렇게 설정해봄 
			//if(${match}== false){  이건 안되네..
			}
		}
	}
	 
	function insert(){
		location.href='registerForm.me'; // get 방식
	}
	function goUpdate( id ){
		location.href='update.me?id=' +  id ;
	}
</script>
</head>
<body onLoad=init()><!--onLoad=init()가 없어도 똑같은데 왜 썼지? 아니다. 안쓰면 위의 init() 호출 안된다. 꼭 써야한다.  -->
	<%-- <c:set value="false" var="flag"></c:set> --%>
	MemberList.jsp
	<br>
	<a href="main.jsp">시작 페이지</a> <br><br> <!-- 그냥 main.jsp로 작성해도 현재 member폴더의 main.jsp가 아니라 webapp의 main.jsp로 간다. WEB-INF안의 member안의 jsp로는 그냥 올수 없고 controller를 통해서만 올수 있나보다.. -->  
	<a href="logout.jsp">로그아웃</a>
	<h2 align="center">회원 리스트 화면</h2>
	<center> 
		<form action="list.me" method="get" name="myform">
			<!-- <input type="text" name="browser_reload" id="flag"> -->
			<!-- 왜 post는 안되지?? -->
			<select name="whatColumn">
				<option value="all">전체 검색 <!-- 자바스크립트에서는 all이라는 예약어가 있을수도 있다. JS에서는 all을 변수로 쓰지 말자. http://www.java2s.com/Tutorial/JavaScript/0280__Document/documentalltags.htm 이곳 참고해서 document.all~ 사용을 해보자. --> 
				<option value="name">이름
				<option value="gender">성별
			</select> <input type="text" name="keyword" value="여"> <input
				type="submit" value="검색">
		</form>
	</center>
	<table border="1" id="tableDesign" align="center">
		<tr class="header">
			<td colspan="9" align="right"><input type="button" value="추가하기"
				onclick="insert();"></td>
		</tr>
		<tr class="header">
			<th><span>ID</span></th>
			<th><span>이름</span></th>
			<th><span>비번</span></th>
			<th><span>성별</span></th>
			<th><span>취미</span></th>
			<th><span>주소</span></th>
			<th><span>포인트</span></th>
			<th><span>삭제</span></th>
			<th><span>수정</span></th>
		</tr>
		<c:forEach items="${memberLists}" var="member">
			<tr class="record">
				<td align="center"><c:out value="${member.id}" /></td>
				<td align="left" class="leftStyle"><a
					href="detail.me?id=<c:out value="${member.id}" />"><c:out
							value="${member.name}" /></a><br> <!-- detail.me=>MemberDetailViewController -->
				</td>
				<td align="center"><c:out value="${member.password}" /></td>
				<td align="left"><c:out value="${ member.gender }" /></td>
				<td align="right"><c:out value="${ member.hobby }" /></td>
				<td align="right"><c:out
						value="${ member.address1 } ${ member.address2 }" /></td>
				<td align="right"><c:out value="${ member.mpoint }" /></td>
				<td align="right"><a
					href="delete.me?id=<c:out value="${member.id}" />">삭제</a><br>
					<!-- delete.me=>MemberDeleteController --></td>
				<td class="lastStyle"><input type="button" value="수정"
					class="btnView" onclick="goUpdate('${member.id}')" /></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<center>${pageInfo.pagingHtml}</center>

</body>
</html>