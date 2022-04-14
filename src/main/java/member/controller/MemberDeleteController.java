package member.controller;

import javax.servlet.http.HttpSession;
import member.model.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//  한번 더 수정함
@Controller
public class MemberDeleteController { // MemberList.jsp에서 넘어옴
	//private static final String getPage = "MemberUpdateForm";
	private static final String gotoPage =  "redirect:/list.me";
	private static final String command = "/delete.me";

	//이줄 추가됨
	//이줄 추가됨  
	//또 한줄 이줄 추가됨  
	   
	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;
   
/*delete 아직 미완성 => 잘 되는것 같은데.. */

	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet( HttpSession session,
			@RequestParam(value="id",required=true) String id ,
			Model model){

		/*if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/update.me?id="+id );
			return "redirect:/LoginForm.me";	 // get 요청		
		}else{ //yes
*/
			int cnt =  memberDao.DeleteData( id );
			return gotoPage;
	//	}
	}
	
	/*
	@RequestMapping(value=command , method=RequestMethod.POST)
	public ModelAndView doActionPost(Member member){		
		System.out.println("post 방식 들어옴");

		ModelAndView mav = new ModelAndView();

		// 데이터 베이스에 추가하는 코드 
		Integer cnt = -1;
		cnt = memberDao.UpdateData(member);	
		mav.setViewName(gotoPage);

		return mav;

		Integer cnt = -1;
		System.out.println("투스트링메소드");
		System.out.println(product.toString());
		cnt = productDao.UpdateProduct(product);

		if (cnt != -1) {
			return new ModelAndView(gotoPage);

		} else {
			return new ModelAndView(getPage);
		}
	}
	*/
	
}
