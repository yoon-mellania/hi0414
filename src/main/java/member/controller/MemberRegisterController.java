package member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import member.model.Member;
import member.model.MemberDao;

@Controller
public class MemberRegisterController {
	
	private static final String getPage= "MemberRegisterForm"; // ȸ�� ���ȭ��
	private static final String gotoPage = "redirect:/list.me"; 
	private static final String command = "/registerForm.me";
	
	// �Ʒ� ��ü ���� �ڵ�� �ʿ���ڱ�..
	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;	
	
	//MemberLoginForm.jsp���� ȸ������ Ŭ���ؼ� ��û��
	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get ��� ����");
		return getPage;
	}
	
	
	@RequestMapping(value = command, method = RequestMethod.POST)
	public ModelAndView doActionPost(
			@ModelAttribute("member") @Valid Member member, 
			BindingResult bindingResult) {

		//System.out.println("����Ʈ�� : " + product.toString());  
		System.out.println(this.getClass() + " POST ��� ����");
		//System.out.println();
		ModelAndView mav = new ModelAndView();

		/*
		�Ʒ� �߰�
		ProductValidator validator = new ProductValidator();
		validator.setDao(productDao);
		validator.validate(product,bindingResult);
		/////������� �߰� /////
		 */
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
