<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

TestForm.jsp(redirect�� GET��û�ΰ�? POST��û�ΰ�? => �׻� GET��û�̱���..)<br>
<form action="test.prd" method="post"> 
	<input type="submit" value="����post">
</form>
<!-- 
method�� get���� �ϴ� post�� �ϴ� Test2Controller������ �׻� GET ��û�� ���Դٰ� �����Ѵ�.
�׷��ϱ� TestController���� �ϴ� redirect��û�� �׻� GET��û�̶�� �� �� �ְڴ�.
 --> 
 
