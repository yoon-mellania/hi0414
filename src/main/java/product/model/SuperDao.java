package product.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SuperDao {
	//SqlSession : 실제 SQL을 실행하는 객체, 이 객체는 SQL을 처리하기 위해 JDBC드라이버를 사용함.
	public SqlSession sqlSession = null ;	
	public String namespace = null ; //맵퍼를 위한 네임 스페이스 이름
	
	//SqlSessionFactory가 이전에는 SqlMapClient라는 클래스 였다.
	private SqlSessionFactory sqlSessionFactory;
	
	private  String mapConfigFile = null ;
	
	public SuperDao() { }
	
	public SuperDao(String mapConfigFile, String namespace) {
		this.mapConfigFile =mapConfigFile;
		this.namespace =namespace;
		
	}

	public SqlSession SessionOpenReader(){		
		return null ;
	}
	public SqlSession SessionOpenStream(){
		InputStream inputStream=null;
		try {
			 inputStream= Resources.getResourceAsStream(this.mapConfigFile);
			this.sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
			this.sqlSession = this.sqlSessionFactory.openSession(true);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(inputStream != null){inputStream.close();}	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			
		}
		
		return this.sqlSession ;
	}
	
	public void SessionClose(){
		//SqlSession 객체를 닫아 주는 메소드
		if( this.sqlSession != null ){ this.sqlSession.close(); }
	}
}