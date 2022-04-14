package member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("myMemberDao") 
public class MemberDao {// extends SuperDao{

	// ���� : �θ� ������ ȣ��� ù��° �Ű� ������ �� ���� ������ ��θ� ����� ��
	// �տ� ������ ������ ���� ��Ű�� ��� ��~~~ ���� �̸� ����ϵ��� �Ѵ�.
	private final String namespace = "member.model.Member";

	// namespace�� �������Ͽ� ����ִ� namespace�̴�.

	@Autowired
	// SqlSessionTemplate ��ü�� sqlSession ������ �Ѵ�.
	SqlSessionTemplate sqlSessionTemplate;

	public MemberDao() { } 
	/*
	public List<Member> GetDataList() {
		List<Member> lists = new ArrayList<Member>();
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList"); // ���� ������ �θ��°�
		return lists;
	}
	*/
	public int GetTotalCount( Map<String, String> map ) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace + ".GetTotalCount", map);
		return cnt;
	}
	
	public List<Member> GetDataList( Paging pageInfo,  Map<String, String> map ) {
		List<Member> lists = new ArrayList<Member>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit() );
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList", map, rowBounds);
		return lists;
	}

	public Integer InsertData(Member member) {
		/*
		int i;
		String temp="";
		for(i=0;i<member.getHobby2().length;i++){
			if(i!=member.getHobby2().length-1)
				temp += member.getHobby2()[i]+",";
			else
				temp += member.getHobby2()[i];
		}
		System.out.println("temp:"+temp);
		member.setHobby(temp);
		*/
		
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".InsertData", member);
		return cnt;
	}
 
	public int DeleteData(String pmKey) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".DeleteData", pmKey);
		return cnt;
	}

	public Member GetData(String id) {
		System.out.println("GetData�� id:"+id);
		Member bean = null; // = null ���� ���� 
		bean = sqlSessionTemplate.selectOne(namespace + ".GetData",	id);
		//System.out.println("bean�� id:"+bean.getId()); // ���� id�� �ѱ�� �ش� ���ڵ尡 �����ϱ� �� �ڸ����� NullPointerException �� ���. 
		return bean;
	}
	
 /*
	public Member GetDataMember(Member member) {
		System.out.println("GetDataMember�� id:"+member.getId());
		Member bean = null;
		bean = sqlSessionTemplate.selectOne(namespace + ".GetDataMember", member);
		System.out.println("bean�� id:"+bean.getId());
		return bean;
	}
	*/
	
	// MemberUpdateController���� �Ѿ��
	public Integer UpdateData(Member bean) {
		/*
		int i;
		String temp="";
		for(i=0;i<bean.getHobby2().length;i++){
			if(i!=bean.getHobby2().length-1)
				temp += bean.getHobby2()[i]+",";
			else
				temp += bean.getHobby2()[i];
		}
		System.out.println("temp:"+temp);
		bean.setHobby(temp);
		*/
		System.out.println("UpdateData");
		System.out.println(bean.getId());
		System.out.println(bean.getPassword());
		System.out.println(bean.getName());
		System.out.println(bean.getGender());
		System.out.println(bean.getHobby());
		System.out.println();
		
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".UpdateData", bean);
		return cnt;
		
	}

	public Integer DetailData(Member bean) {
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".DetailData", bean);
		return cnt;
	}

	// CartCalculateController.java���� ȣ��
	public Integer UpdateData(String id, int mpoint) {
		Member bean = new Member() ;
		bean.setId(id);
		bean.setMpoint(mpoint); 
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".UpdateData2", bean);
		return cnt;		
	}
}