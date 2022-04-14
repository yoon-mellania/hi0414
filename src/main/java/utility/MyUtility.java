package utility;

import java.util.Date;

import org.springframework.web.servlet.ModelAndView;

public class MyUtility {
	public static java.sql.Date getSqlDae(Date date){
		//�ڹ��� Date Ÿ���� ����Ŭ�� Date Ÿ������ ������ ���ش�.
		long day = date.getTime() ;
		java.sql.Date sqlDate = new java.sql.Date(day) ;
		return sqlDate ;
	}

	
	public static ModelAndView getMav(Exception ex, String errlocation) {
		//���ܰ� �߻��ϴ� ���, �� ���ܿ� ���� ������ ModelAndView�� �����Ͽ� �������ִ� �޼ҵ��̴�.
		//���� ������ ���� ���̴� ���̶�, ������ �ۼ��ߴ�. 
		String viewName = "error" ;
		ModelAndView mav = new ModelAndView(viewName) ;
		mav.addObject("errlocation", errlocation + " Ŭ����" ) ; 
		mav.addObject("errstring", ex.toString()) ;
		mav.addObject("errmessage", ex.getMessage()) ;
		//ex.printStackTrace() ;
		return mav ;
	}
}