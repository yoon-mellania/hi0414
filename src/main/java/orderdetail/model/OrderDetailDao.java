package orderdetail.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("myOrderDetailDao")
public class OrderDetailDao {// extends SuperDao{

	// 주의 : 부모 생성자 호출시 첫번째 매개 변수인 맵 설정 파일의 경로를 명시할 때
	// 앞에 슬래시 붙이지 말고 패키지 경로 쭉~~~ 파일 이름 명시하도록 한다.
	private final String namespace = "orderdetail.model.OrderDetail"; // orderdetail.xml

	// namespace는 매퍼파일에 들어있는 namespace이다.

	@Autowired
	// SqlSessionTemplate 객체가 sqlSession 역할을 한다.
	SqlSessionTemplate sqlSessionTemplate;

	public OrderDetailDao() { }

	public List<OrderDetail> GetDataList() {
		List<OrderDetail> lists = new ArrayList<OrderDetail>();
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList"); // 맵퍼 파일을 부르는것
		return lists;
	}
	
	public int GetTotalCount( Map<String, String> map ) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace + ".GetTotalCount", map);
		return cnt;
	}
	
	public List<OrderDetail> GetDataList( Paging pageInfo,  Map<String, String> map ) {
		List<OrderDetail> lists = new ArrayList<OrderDetail>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit() );
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList", map, rowBounds);
		return lists;
	}

	// CartCalculateController.java에서 호출
	public Integer InsertData(OrderDetail bean) {
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".InsertData", bean);
		// "orderdetail.model.OrderDetail.InsertData"
		return cnt;
	}
/*
	public int DeleteData(int pmKey) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".DeleteData", pmKey);
		return cnt;
	}
*/
	/*
	public OrderDetail GetData(String pmKey) {
		OrderDetail bean = null;
		bean = sqlSessionTemplate.selectOne(namespace + ".GetData",
				pmKey);
		return bean;
	}
*/
	/*
	public Integer UpdateData(OrderDetail bean) {
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".UpdateData", bean);
		return cnt;
	}
*/
	/*
	public Integer DetailData(OrderDetail bean) {
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".DetailData", bean);
		return cnt;
	}
	*/
}