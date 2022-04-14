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

@Component("myProductDao") // ������ @Component �̷��Ը� ���� @Qualifier("productDao") �ҹ��� p�� �����ϴ� Ŭ���� �̸��� ���� �ȴ�.
public class ProductDao {// extends SuperDao{

	// ���� : �θ� ������ ȣ��� ù��° �Ű� ������ �� ���� ������ ��θ� ����� ��
	// �տ� ������ ������ ���� ��Ű�� ��� ��~~~ ���� �̸� ����ϵ��� �Ѵ�.
	private final String namespace = "product.model.Product";

	// namespace�� �������Ͽ� ����ִ� namespace�̴�.

	@Autowired
	// SqlSessionTemplate ��ü�� sqlSession ������ �Ѵ�.
	SqlSessionTemplate sqlSessionTemplate;

	public ProductDao() { }

	// �Ʒ� GetDataList()�� �ʿ���µ���
	/*public List<Product> GetDataList() {
		List<Product> lists = new ArrayList<Product>();
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList"); // ���� ������ �θ��°�
		return lists;
	}*/
	
	//ProductListController.java���� ���
	public int GetTotalCount( Map<String, String> map ) {
		System.out.println("\nGetTotalCount sqlSessionTemplate : " + sqlSessionTemplate);
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace + ".GetTotalCount", map);
		return cnt;
	}
	
	//ProductListController.java���� ���
	public List<Product> GetDataList( Paging pageInfo,  Map<String, String> map ) {
		List<Product> lists = new ArrayList<Product>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit() );
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList", map, rowBounds);
		return lists;
	}

	// ProductInsertController.java���� ���
	public Integer InsertData(Product bean) {
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".InsertData", bean);
		return cnt;
	}

	//ProductDeleteController.java���� ���
	public int DeleteData(int pmKey) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".DeleteData", pmKey);
		return cnt;
	}

	// ProductUpdateController.java���� ���
	// ProductDetailViewController.java���� ���
	// CartListController.java���� ���
	public Product GetData(int id) {
		Product bean = null;
		bean = sqlSessionTemplate.selectOne(namespace + ".GetData",	id);
		return bean;
	}
	
	// CartCalculateController.java���� ���
	public Integer UpdateData(Integer pnum, Integer qty) {
		Product bean = new Product() ;
		bean.setNum( pnum );
		bean.setStock( qty );
		
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".UpdateData2", bean);
		//cnt = sqlSessionTemplate.insert(namespace + ".UpdateData2", pnum,qty); // ������ ���Ŀ� ���� �ʾƼ� ����
		return cnt;
	}
	
	// ProductUpdateController.java���� ���
	public Integer UpdateData(Product bean) { // ��ǰ����
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".UpdateData", bean);
		return cnt;
	}

	//ProductDetailViewController.java���� ���
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