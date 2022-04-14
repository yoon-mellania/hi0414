package utility;

import java.util.Date;

import org.springframework.web.servlet.ModelAndView;

public class MyUtility {
	public static java.sql.Date getSqlDae(Date date){
		//자바의 Date 타입을 오라클의 Date 타입으로 컨버전 해준다.
		long day = date.getTime() ;
		java.sql.Date sqlDate = new java.sql.Date(day) ;
		return sqlDate ;
	}

	
	public static ModelAndView getMav(Exception ex, String errlocation) {
		//예외가 발생하는 경우, 이 예외에 대한 정보를 ModelAndView에 저장하여 리턴해주는 메소드이다.
		//여러 군데서 많이 쓰이는 것이라서, 별도로 작성했다. 
		String viewName = "error" ;
		ModelAndView mav = new ModelAndView(viewName) ;
		mav.addObject("errlocation", errlocation + " 클래스" ) ; 
		mav.addObject("errstring", ex.toString()) ;
		mav.addObject("errmessage", ex.getMessage()) ;
		//ex.printStackTrace() ;
		return mav ;
	}
}