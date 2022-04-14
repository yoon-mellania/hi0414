이 예제는 3일 정도 걸린다.(2.7일정도)

main.jsp에서 시작한다.

main.jsp에서 시작해서 상품 목록보기 하나라도 결과가 나오게 하려면
기본적으로 아래의 화일들은 실행하기 전에 미리 만들어 놓아야 한다.
class MyCartList 를 만들어 놓는다.
SqlMapConfig.xml의 typeAlias로 설정한 패키지와 클래스들을 미리 만들어 놓는다. 
member.model.Member
product.model.Product
order.model.Order
orderdetail.model.OrderDetail

빈 클래스들을 미리 만들어 놓는다.
mybatis의 모든 매퍼 xml 화일을 미리 만들어 놓고, 
내용은 없어도 아래와 같이 namespace는 미리 설정을 해놓는다.
<mapper namespace="product.model.Composite">	  
</mapper>

webapp\images폴더의 화일이미지는 미리 아래의 폴더에 넣어놓고 하자.(폴더는 안만들고 화일만 넣어놓자.)
C:\Spring_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\51_Spring_MyBatis_Products\resources
이미지 올리는 화일을 res로 바꾸면 안된다. 
servlet-context.xml의 resources를 res로 
ProductDetailViewController.java 과
ProductInsertController.java 의 resources를 res로 모두 바꿔도 안된다.
화일은 기본 resources에 올라가도록 둬야 하는것 같다.

화일은 자동으로 만들어지는 아래 폴더에 들어가는데..
C:\Spring_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\resources
수정 작업하면서 화일 선택안하면 기존의 화일로 들어가는게 아니라 화일이 아예 안들어가고 기존에 올려놓은 화일은 지워진다.
화일이 지워지면서 위의 resource폴더도 지워지니까 아무 화일이나 위의 resource폴더에 넣어놓고 작업하자.

css, image file, javascript file은 src-main-resources에 넣어놓아야 한다.




