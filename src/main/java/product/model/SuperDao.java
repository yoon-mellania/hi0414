package product.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SuperDao {
	//SqlSession : ���� SQL�� �����ϴ� ��ü, �� ��ü�� SQL�� ó���ϱ� ���� JDBC����̹��� �����.
	public SqlSession sqlSession = null ;	
	public String namespace = null ; //���۸� ���� ���� �����̽� �̸�
	
	//SqlSessionFactory�� �������� SqlMapClient��� Ŭ���� ����.
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
		//SqlSession ��ü�� �ݾ� �ִ� �޼ҵ�
		if( this.sqlSession != null ){ this.sqlSession.close(); }
	}
}