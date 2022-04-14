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
//  �ѹ� �� ������
@Controller
public class MemberDeleteController { // MemberList.jsp���� �Ѿ��
	//private static final String getPage = "MemberUpdateForm";
	private static final String gotoPage =  "redirect:/list.me";
	private static final String command = "/delete.me";

	//���� �߰���
	//���� �߰���  
	//�� ���� ���� �߰���  
	   
	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;
   
/*delete ���� �̿ϼ� => �� �Ǵ°� ������.. */

	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet( HttpSession session,
			@RequestParam(value="id",required=true) String id ,
			Model model){

		/*if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/update.me?id="+id );
			return "redirect:/LoginForm.me";	 // get ��û		
		}else{ //yes
*/
			int cnt =  memberDao.DeleteData( id );
			return gotoPage;
	//	}
	}
	
	/*
	@RequestMapping(value=command , method=RequestMethod.POST)
	public ModelAndView doActionPost(Member member){		
		System.out.println("post ��� ����");

		ModelAndView mav = new ModelAndView();

		// ������ ���̽��� �߰��ϴ� �ڵ� 
		Integer cnt = -1;
		cnt = memberDao.UpdateData(member);	
		mav.setViewName(gotoPage);

		return mav;

		Integer cnt = -1;
		System.out.println("����Ʈ���޼ҵ�");
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
