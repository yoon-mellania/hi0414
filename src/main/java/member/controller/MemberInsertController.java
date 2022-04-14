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

//아래 내용을 MemberRegisterController의 POST처리로 넘김
/*@Controller
public class MemberInsertController {
	private static final String getPage = "memRegisterForm"; // 회원 가입 화면 폼
	private static final String gotoPage = "redirect:/list.me"; //=>MemberListController.java
	private static final String command = "/memRegister.me"; // memRegisterForm.jsp에서 요청함 

	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;

	@RequestMapping(value = command, method = RequestMethod.GET)
	public String doActionGet(	HttpSession session ) {
		System.out.println(this.getClass() + " Get 방식 들어옴");
		System.out.println("loginfo : " + session.getAttribute("loginfo"));

		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/insert.prd" );
			return "redirect:/LoginForm.me";	 // get 요청		
		}else{ //yes
			return getPage;	
		}		
	}

	
	윗 부분으로는 넘어올 일이 없다.
	회원 등록 폼을 요청하는 MemberInsertController와 등록 처리하는 MemberRegisterController를 하나로 합쳐도 되겠다.
	 
	
	@RequestMapping(value = command, method = RequestMethod.POST)
	public ModelAndView doActionPost(
			@ModelAttribute("member") @Valid Member member, 
			BindingResult bindingResult) {

		//System.out.println("투스트링 : " + product.toString());  
		System.out.println(this.getClass() + " POST 방식 들어옴");
		//System.out.println();
		ModelAndView mav = new ModelAndView();

		
		아래 추가
		ProductValidator validator = new ProductValidator();
		validator.setDao(productDao);
		validator.validate(product,bindingResult);
		/////여기까지 추가 /////
		 
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

*/