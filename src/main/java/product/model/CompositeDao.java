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
	// SqlSessionTemplate 객체가 sqlSession 역할을 한다.
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

	// OrderMallController에서 회원정보 넘어옴
	public List<Order> OrderMall2(Member loginfo) {
		System.out.println(loginfo.getId());
		// TODO Auto-generated method stub
		List<Order> lists = new ArrayList<Order>();
		lists = sqlSessionTemplate.selectList( namespace + ".OrderMall2", loginfo.getId()); //loginfo만쓰고 xml에서도 parameterType="String" 그대로 썼는데도 잘 되는건 뭐지??? =>parameterType은 뭘쓰든 상관 없나보다.. 
		System.out.println("OrderMall2 lists.size() : " +lists.size()); // 해당 아이디로 주문한 수(order 테이블의 mid 갯수)
		return lists;
	}
	
	// DetailViewController.java에서 호출
	// orderdetails에는 상품명과 단가는 없어서 products와 join해서 상품명과 단가를 가져오는 작업을 아래 메서드에서 해야 한다. 
	/*
	public List<HashMap<String, Object>> ShowDetail(int pmkey) {
		Map<String, Integer> map = new HashMap<String, Integer>() ; // Integer대신 Object 가능 
		map.put("oid", pmkey ) ; // oid에 주문번호 담긴다.
		//List<HashMap<String, Object>> maplists = null;
		List<HashMap<String, Object>> maplists = null;
		//maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail", map );

		//위의 ShowDetail은 3개 테이블조인한것, 아래 ShowDetail2는 2개 테이블 조인한것
		// =>아래 ShowDetail2로 하는게 낫겠다.
		
		// 아래 ShowDetail2 결과를 담을 수 있는 bean이 없어서 
		// bean대신 HashMap<String, Object>형태로 여러개를 List형태로 리턴해준다.
		maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail2", map );
		return maplists;	// DetailViewController로 리턴
	}
	*/
	// 위의 코드를 아래 코드처럼 해도된다. 매개변수로 map이 아니라 그냥 int pmkey를 넘겨도 된다.
	/*
	public List<HashMap<String, Object>> ShowDetail(int pmkey) {
		List<HashMap<String, Object>> maplists = null;
		maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail", map );

		//위의 ShowDetail은 3개 테이블조인한것, 아래 ShowDetail2는 2개 테이블 조인한것
		
		maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail3", pmkey ); // 여기 map이 아니라 pmkey가능 
		return maplists;	// DetailViewController로 리턴
	}
	*/
	
	//위의 ShowDetail보다 아래 ShowDetail이 더 간단해서 낫겠다.
	public List<ShoppingInfo> ShowDetail(int pmkey) {
		//Map<String, Integer> map = new HashMap<String, Integer>() ; // Integer대신 Object 가능 
		//map.put("oid", pmkey ) ; // oid에 주문번호 담긴다.
		//List<HashMap<String, Object>> maplists = null;
		List<ShoppingInfo> maplists = null;
		//maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail", map );

		//위의 ShowDetail은 3개 테이블조인한것, 아래 ShowDetail2는 2개 테이블 조인한것
		// =>아래 ShowDetail2로 하는게 낫겠다.
		
		// 아래 ShowDetail2 결과를 담을 수 있는 bean이 없어서 
		// bean대신 HashMap<String, Object>형태로 여러개를 List형태로 리턴해준다.
		maplists = sqlSessionTemplate.selectList( namespace + ".ShowDetail2", pmkey );
		return maplists;	// DetailViewController로 리턴
	}
	
}







