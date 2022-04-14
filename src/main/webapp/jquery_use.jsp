<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script type="text/javascript" src="<c:url value="/ex/resources/js/jquery.js" />" ></script>
 --%>
 <!-- 위의 코드도 잘 되더니 안된다.
   아래 코드는 잘된다. -->
<script type="text/javascript" src="/ex/resources/js/jquery.js" ></script>

<script type="text/javascript">
	$(function(){  
		alert("3");
		
		$("div").css('background','green');
		
	});
</script>
<div>    
3
</div>            

<!-- 
https://thositeom.tistory.com/entry/jQuery-%EC%82%AC%EC%9A%A9%EB%B0%A9%EB%B2%95 참고
위의 코드 작성하고 webapp에 js폴더 넣으니 잘 됐다. => 안되네.. resources폴더에 js폴더 넣으니 잘 됐다.
실행은 외부 브라우저에서 해야 잘 된다. 내부 브라우저에서는 안된다. 
servlet-context.xml에는 설정이 필요없네??
webapp에만 js 폴더만들어 해보고
resources에만 폴더 만들어서 해보자. 둘다 되는지 확인해보자.
-->

