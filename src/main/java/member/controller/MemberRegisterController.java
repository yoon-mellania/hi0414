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
	
	private static final String getPage= "MemberRegisterForm"; // 회원 등록화면
	private static final String gotoPage = "redirect:/list.me"; 
	private static final String command = "/registerForm.me";
	
	// 아래 객체 주입 코드는 필요없겠군..
	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;	
	
	//MemberLoginForm.jsp에서 회원가입 클릭해서 요청함
	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get 방식 들어옴");
		return getPage;
	}
	
	
	@RequestMapping(value = command, method = RequestMethod.POST)
	public ModelAndView doActionPost(
			@ModelAttribute("member") @Valid Member member, 
			BindingResult bindingResult) {

		//System.out.println("투스트링 : " + product.toString());  
		System.out.println(this.getClass() + " POST 방식 들어옴");
		//System.out.println();
		ModelAndView mav = new ModelAndView();

		/*
		아래 추가
		ProductValidator validator = new ProductValidator();
		validator.setDao(productDao);
		validator.validate(product,bindingResult);
		/////여기까지 추가 /////
		 */
		System.out.println("member.getName():"+member.getName());
		
		if (bindingResult.hasErrors()) {
			System.out.println("유효성 검사 오류입니다");
			mav.setViewName(getPage); // ProductInsertForm.jsp
			return mav;
		} 


		// 데이터베이스에 추가하는 코드
		Integer cnt = -1;
		cnt = memberDao.InsertData(member);

		mav.setViewName(gotoPage); //ProductInsertForm.java(X) => "redirect:/list.me" 회원 목록보기로 간다. 
		return mav;
	}
}
