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

	// 주의 : 부모 생성자 호출시 첫번째 매개 변수인 맵 설정 파일의 경로를 명시할 때
	// 앞에 슬래시 붙이지 말고 패키지 경로 쭉~~~ 파일 이름 명시하도록 한다.
	private final String namespace = "member.model.Member";

	// namespace는 매퍼파일에 들어있는 namespace이다.

	@Autowired
	// SqlSessionTemplate 객체가 sqlSession 역할을 한다.
	SqlSessionTemplate sqlSessionTemplate;

	public MemberDao() { } 
	/*
	public List<Member> GetDataList() {
		List<Member> lists = new ArrayList<Member>();
		lists = sqlSessionTemplate.selectList(namespace + ".GetDataList"); // 맵퍼 파일을 부르는것
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
		System.out.println("GetData의 id:"+id);
		Member bean = null; // = null 생략 가능 
		bean = sqlSessionTemplate.selectOne(namespace + ".GetData",	id);
		//System.out.println("bean의 id:"+bean.getId()); // 없는 id를 넘기면 해당 레코드가 없으니까 이 자리에서 NullPointerException 이 뜬다. 
		return bean;
	}
	
 /*
	public Member GetDataMember(Member member) {
		System.out.println("GetDataMember의 id:"+member.getId());
		Member bean = null;
		bean = sqlSessionTemplate.selectOne(namespace + ".GetDataMember", member);
		System.out.println("bean의 id:"+bean.getId());
		return bean;
	}
	*/
	
	// MemberUpdateController에서 넘어옴
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

	// CartCalculateController.java에서 호출
	public Integer UpdateData(String id, int mpoint) {
		Member bean = new Member() ;
		bean.setId(id);
		bean.setMpoint(mpoint); 
		Integer cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace + ".UpdateData2", bean);
		return cnt;		
	}
}