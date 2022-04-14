package product.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import member.model.Member;
import order.model.Order;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mall.cart.ShoppingInfo;

@Component("myCompositeDao")
public class CompositeDao {
	private final String namespace = "product.model.Composite"; // composite.xml

	@Autowired
	// SqlSessionTemplate ��ü�� sqlSession ������ �Ѵ�.
	SqlSessionTemplate sqlSessionTemplate;

	public CompositeDao() { }

	public List<Product> GetDataList() {
		List<Product> lists = new ArrayList<Product>();
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList");  
		return lists;
	} 

	/*public List<HashMap<String, Object>> OrderMall( Member member ) {
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("mid", member.getId() ) ;
		List<HashMap<String, Object>> maplists = null;
		maplists = sqlSessionTemplate.selectList( namespace + ".OrderMall", map );
		return maplists;		
	}*/

	// OrderMallController���� ȸ������ �Ѿ��
	public List<Order> OrderMall2(Member loginfo) {
		System.out.println(loginfo.getId());
		// TODO Auto-generated method stub
		List<Order> lists = new ArrayList<Order>();
		lists = sqlSessionTemplate.selectList( namespace + ".OrderMall2", loginfo.getId()); //loginfo������ xml������ parameterType="String" �״�� ��µ��� �� �Ǵ°� ����??? =>parameterType�� ������ ��� ��������.. 
		System.out.println("OrderMall2 lists.size() : " +lists.size()); // �ش� ���̵�� �ֹ��� ��(order ���̺��� mid ����)
		return lists;
	}
	
	// DetailViewController.java���� ȣ��
	// orderdetails���� ��ǰ��� �ܰ��� ��� products�� join�ؼ� ��ǰ��� �ܰ��� �������� �۾��� �Ʒ� �޼��忡�� �ؾ� �Ѵ�. 
	/*
	public List<HashMap<String, Object>> ShowDetail(int pmkey) {
		Map<String, Integer> map = new HashMap<String, Integer>() ; // Integer��� Object ���� 
		map.put("oid", pmkey ) ; // oid�� �ֹ���ȣ ����.
		//List<HashMap<String, Object>> maplists = null;
		List<HashMap<String, Object>> maplists = null;
		//maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail", map );

		//���� ShowDetail�� 3�� ���̺������Ѱ�, �Ʒ� ShowDetail2�� 2�� ���̺� �����Ѱ�
		// =>�Ʒ� ShowDetail2�� �ϴ°� ���ڴ�.
		
		// �Ʒ� ShowDetail2 ����� ���� �� �ִ� bean�� ��� 
		// bean��� HashMap<String, Object>���·� �������� List���·� �������ش�.
		maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail2", map );
		return maplists;	// DetailViewController�� ����
	}
	*/
	// ���� �ڵ带 �Ʒ� �ڵ�ó�� �ص��ȴ�. �Ű������� map�� �ƴ϶� �׳� int pmkey�� �Ѱܵ� �ȴ�.
	/*
	public List<HashMap<String, Object>> ShowDetail(int pmkey) {
		List<HashMap<String, Object>> maplists = null;
		maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail", map );

		//���� ShowDetail�� 3�� ���̺������Ѱ�, �Ʒ� ShowDetail2�� 2�� ���̺� �����Ѱ�
		
		maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail3", pmkey ); // ���� map�� �ƴ϶� pmkey���� 
		return maplists;	// DetailViewController�� ����
	}
	*/
	
	//���� ShowDetail���� �Ʒ� ShowDetail�� �� �����ؼ� ���ڴ�.
	public List<ShoppingInfo> ShowDetail(int pmkey) {
		//Map<String, Integer> map = new HashMap<String, Integer>() ; // Integer��� Object ���� 
		//map.put("oid", pmkey ) ; // oid�� �ֹ���ȣ ����.
		//List<HashMap<String, Object>> maplists = null;
		List<ShoppingInfo> maplists = null;
		//maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail", map );

		//���� ShowDetail�� 3�� ���̺������Ѱ�, �Ʒ� ShowDetail2�� 2�� ���̺� �����Ѱ�
		// =>�Ʒ� ShowDetail2�� �ϴ°� ���ڴ�.
		
		// �Ʒ� ShowDetail2 ����� ���� �� �ִ� bean�� ��� 
		// bean��� HashMap<String, Object>���·� �������� List���·� �������ش�.
		maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail2", pmkey );
		return maplists;	// DetailViewController�� ����
	}
	
}







