package order.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myOrderDao")
public class OrderDao {// extends SuperDao{

	// ���� : �θ� ������ ȣ��� ù��° �Ű� ������ �� ���� ������ ��θ� ����� ��
	// �տ� ������ ������ ���� ��Ű�� ��� ��~~~ ���� �̸� ����ϵ��� �Ѵ�.
	private final String namespace = "order.model.Order"; // order.xml

	@Autowired
	// SqlSessionTemplate ��ü�� sqlSession ������ �Ѵ�.
	SqlSessionTemplate sqlSessionTemplate;

	public OrderDao() { }

	public Integer InsertData(String id) {
		Order order = new Order() ;
		order.setMid(id); 
		Integer cnt = -1;
		System.out.println("ȣȣȣ");
		cnt = sqlSessionTemplate.insert(namespace + ".InsertData", order ); // order��� id�� �Ѱܵ� �ȴ�. 
		// �α����� ȸ���� ���̵� orders ���̺� �ִ´�.
		
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
		// ���� �ֱ��� �����ȣ(�ֹ���ȣ)�� �����´�.
		return cnt;
	}
}

