<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

TestForm.jsp(redirect는 GET요청인가? POST요청인가? => 항상 GET요청이구나..)<br>
<form action="test.prd" method="post"> 
	<input type="submit" value="전송post">
</form>
<!-- 
method를 get으로 하던 post로 하던 Test2Controller에서는 항상 GET 요청이 들어왔다고 생각한다.
그러니까 TestController에서 하는 redirect요청은 항상 GET요청이라고 볼 수 있겠다.
 --> 
 
