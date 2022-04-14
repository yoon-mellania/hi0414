package product.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("myProductDao") // 이줄을 @Component 이렇게만 쓰고 @Qualifier("productDao") 소문자 p로 시작하는 클래스 이름을 쓰면 된다.
public class ProductDao {// extends SuperDao{

	// 주의 : 부모 생성자 호출시 첫번째 매개 변수인 맵 설정 파일의 경로를 명시할 때
	// 앞에 슬래시 붙이지 말고 패키지 경로 쭉~~~ 파일 이름 명시하도록 한다.
	private final String namespace = "product.model.Product";

	// namespace는 매퍼파일에 들어있는 namespace이다.

	@Autowired
	// SqlSessionTemplate 객체가 sqlSession 역할을 한다.
	SqlSessionTemplate sqlSessionTemplate;

	public ProductDao() { }

	// 아래 GetDataList()는 필요없는듯함
	/*public List<Product> GetDataList() {
		List<Product> lists = new ArrayList<Product>();
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList"); // 맵퍼 파일을 부르는것
		return lists;
	}*/
	
	//ProductListController.java에서 사용
	public int GetTotalCount( Map<String, String> map ) {
		System.out.println("\nGetTotalCount sqlSessionTemplate : " + sqlSessionTemplate);
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace + ".GetTotalCount", map);
		return cnt;
	}
	
	//ProductListController.java에서 사용
	public List<Product> GetDataList( Paging pageInfo,  Map<String, String> map ) {
		List<Product> lists = new ArrayList<Product>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit() );
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList", map, rowBounds);
		return lists;
	}

	// ProductInsertController.java에서 사용
	public Integer InsertData(Product bean) {
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".InsertData", bean);
		return cnt;
	}

	//ProductDeleteController.java에서 사용
	public int DeleteData(int pmKey) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".DeleteData", pmKey);
		return cnt;
	}

	// ProductUpdateController.java에서 사용
	// ProductDetailViewController.java에서 사용
	// CartListController.java에서 사용
	public Product GetData(int id) {
		Product bean = null;
		bean = sqlSessionTemplate.selectOne(namespace + ".GetData",	id);
		return bean;
	}
	
	// CartCalculateController.java에서 사용
	public Integer UpdateData(Integer pnum, Integer qty) {
		Product bean = new Product() ;
		bean.setNum( pnum );
		bean.setStock( qty );
		
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".UpdateData2", bean);
		//cnt = sqlSessionTemplate.insert(namespace + ".UpdateData2", pnum,qty); // 이줄은 형식에 맞지 않아서 에러
		return cnt;
	}
	
	// ProductUpdateController.java에서 사용
	public Integer UpdateData(Product bean) { // 상품수정
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".UpdateData", bean);
		return cnt;
	}

	//ProductDetailViewController.java에서 사용
	public Integer DetailData(Product bean) {
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".DetailData", bean);
		return cnt;
	}

	public int GetStock(Product product) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace + ".GetStock", product);
		return cnt;
	}


}