package order.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myOrderDao")
public class OrderDao {// extends SuperDao{

	// 주의 : 부모 생성자 호출시 첫번째 매개 변수인 맵 설정 파일의 경로를 명시할 때
	// 앞에 슬래시 붙이지 말고 패키지 경로 쭉~~~ 파일 이름 명시하도록 한다.
	private final String namespace = "order.model.Order"; // order.xml

	@Autowired
	// SqlSessionTemplate 객체가 sqlSession 역할을 한다.
	SqlSessionTemplate sqlSessionTemplate;

	public OrderDao() { }

	public Integer InsertData(String id) {
		Order order = new Order() ;
		order.setMid(id); 
		Integer cnt = -1;
		System.out.println("호호호");
		cnt = sqlSessionTemplate.insert(namespace + ".InsertData", order ); // order대신 id를 넘겨도 된다. 
		// 로그인한 회원의 아이디를 orders 테이블에 넣는다.
		
		return cnt;
	}
	/*
	  OID MID                  ORDERDAT
	   12 choi                 16/11/25
	   13 choi                 16/11/25
	   14 choi                 16/11/25
	   15 choi                 16/11/25
	   16 choi                 16/11/25
	*/   

	public int GetMaxOrderId() {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne( namespace + ".GetMaxOrderId"  );
		// 가장 최근의 송장번호(주문번호)를 가져온다.
		return cnt;
	}
}

