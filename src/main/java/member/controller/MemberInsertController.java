package member.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import member.model.Member;
import member.model.MemberDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//�Ʒ� ������ MemberRegisterController�� POSTó���� �ѱ�
/*@Controller
public class MemberInsertController {
	private static final String getPage = "memRegisterForm"; // ȸ�� ���� ȭ�� ��
	private static final String gotoPage = "redirect:/list.me"; //=>MemberListController.java
	private static final String command = "/memRegister.me"; // memRegisterForm.jsp���� ��û�� 

	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;

	@RequestMapping(value = command, method = RequestMethod.GET)
	public String doActionGet(	HttpSession session ) {
		System.out.println(this.getClass() + " Get ��� ����");
		System.out.println("loginfo : " + session.getAttribute("loginfo"));

		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/insert.prd" );
			return "redirect:/LoginForm.me";	 // get ��û		
		}else{ //yes
			return getPage;	
		}		
	}

	
	�� �κ����δ� �Ѿ�� ���� ����.
	ȸ�� ��� ���� ��û�ϴ� MemberInsertController�� ��� ó���ϴ� MemberRegisterController�� �ϳ��� ���ĵ� �ǰڴ�.
	 
	
	@RequestMapping(value = command, method = RequestMethod.POST)
	public ModelAndView doActionPost(
			@ModelAttribute("member") @Valid Member member, 
			BindingResult bindingResult) {

		//System.out.println("����Ʈ�� : " + product.toString());  
		System.out.println(this.getClass() + " POST ��� ����");
		//System.out.println();
		ModelAndView mav = new ModelAndView();

		
		�Ʒ� �߰�
		ProductValidator validator = new ProductValidator();
		validator.setDao(productDao);
		validator.validate(product,bindingResult);
		/////������� �߰� /////
		 
		System.out.println("member.getName():"+member.getName());
		
		if (bindingResult.hasErrors()) {
			System.out.println("��ȿ�� �˻� �����Դϴ�");
			mav.setViewName(getPage); // ProductInsertForm.jsp
			return mav;
		} 


		// �����ͺ��̽��� �߰��ϴ� �ڵ�
		Integer cnt = -1;
		cnt = memberDao.InsertData(member);

		mav.setViewName(gotoPage); //ProductInsertForm.java(X) => "redirect:/list.me" ȸ�� ��Ϻ���� ����. 
		return mav;
	}
}

*/