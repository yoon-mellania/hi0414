�� ������ 3�� ���� �ɸ���.(2.7������)

main.jsp���� �����Ѵ�.

main.jsp���� �����ؼ� ��ǰ ��Ϻ��� �ϳ��� ����� ������ �Ϸ���
�⺻������ �Ʒ��� ȭ�ϵ��� �����ϱ� ���� �̸� ����� ���ƾ� �Ѵ�.
class MyCartList �� ����� ���´�.
SqlMapConfig.xml�� typeAlias�� ������ ��Ű���� Ŭ�������� �̸� ����� ���´�. 
member.model.Member
product.model.Product
order.model.Order
orderdetail.model.OrderDetail

�� Ŭ�������� �̸� ����� ���´�.
mybatis�� ��� ���� xml ȭ���� �̸� ����� ����, 
������ ��� �Ʒ��� ���� namespace�� �̸� ������ �س��´�.
<mapper namespace="product.model.Composite">	  
</mapper>

webapp\images������ ȭ���̹����� �̸� �Ʒ��� ������ �־���� ����.(������ �ȸ���� ȭ�ϸ� �־����.)
C:\Spring_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\51_Spring_MyBatis_Products\resources
�̹��� �ø��� ȭ���� res�� �ٲٸ� �ȵȴ�. 
servlet-context.xml�� resources�� res�� 
ProductDetailViewController.java ��
ProductInsertController.java �� resources�� res�� ��� �ٲ㵵 �ȵȴ�.
ȭ���� �⺻ resources�� �ö󰡵��� �־� �ϴ°� ����.

ȭ���� �ڵ����� ��������� �Ʒ� ������ ���µ�..
C:\Spring_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\resources
���� �۾��ϸ鼭 ȭ�� ���þ��ϸ� ������ ȭ�Ϸ� ���°� �ƴ϶� ȭ���� �ƿ� �ȵ��� ������ �÷����� ȭ���� ��������.
ȭ���� �������鼭 ���� resource������ �������ϱ� �ƹ� ȭ���̳� ���� resource������ �־���� �۾�����.

css, image file, javascript file�� src-main-resources�� �־���ƾ� �Ѵ�.




