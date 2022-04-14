package member.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import member.model.Member;
import member.model.MemberDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MemberUpdateController {
	private static final String getPage = "MemberUpdateForm";
	private static final String gotoPage =  "redirect:/list.me";
	private static final String command = "/update.me";


	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;
 
	@RequestMapping(value=command , method=RequestMethod.GET)
	//public String doActionGet( HttpSession session,
	public ModelAndView doActionGet( HttpSession session,
			@RequestParam(value="id",required=true) String id ,
			Model model){
		
		System.out.println(this.getClass() + "GET 방식");
		
		ModelAndView mav = new ModelAndView();
		System.out.println("session.getAttribute('loginfo'):"+session.getAttribute("loginfo"));
		//mav.addObject("match" , true);
		//mav.addObject("flag", "false"); 
		// flag는 수정클릭하면 true로 바뀐다. 
		// 매번 수정 클릭할 때마다 클릭 안한상태에서 시작해야해서 MemberUpdateController로 올 때마다 false로 초기화해준다.
		
		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/update.me?id="+id );
			//return "redirect:/LoginForm.me";	 // get 요청
			mav.setViewName("redirect:/LoginForm.me");
			//return mav;
			
		}else{ //yes:로그인 했으면..
				
			if(((Member)session.getAttribute("loginfo")).getId().equals(id)){
				Member member =  memberDao.GetData( id ); // 1
				//model.addAttribute("member" , member); // 1
				mav.addObject("member" , member);
				
				//Member member2 =  memberDao.GetDataMember( member ); // 2(()안의 member는 위의 1의 변수 member임
				//model.addAttribute("member" , member2); // 2
				/*
				위의 1코드는 id하나로 Member 객체를 알아오는 코드이고,
				위의 2코드는 Member객체로 다시 Member객체를 알아오는 코드로 2개의 결과물은 같다.
				그런데 Member.xml에서..
				1의 코드로 멤버 변수 하나만 넘기면 id="GetData"에서 where id = #{id}도 되고 where id = #{name} 도 되고..
				{}안에 아무 글자나 넣어도 된다.
				2의 코드로 여러개를 하나의 객체 묶음으로 넘기면 넘어가는 변수가 많으니까 id="GetDataMember"에서 
				where id = #{id} 로 정확한 값을 {}안에 넣어야 한다. where id = #{name}는 에러남
				#{id}에는 getId()값이 넘어오는 듯함
				*/
				
				System.out.println("Get 방식 들어옴");
				//return getPage; // "MemberUpdateForm"
				mav.setViewName(getPage);
				//return mav;
			}
			else{
				//model.addAttribute("flag", "false"); // 1
				//return gotoPage; // "redirect:/list.me"
				//mav.addObject("flag" , false);
				//mav.addObject("flag", "true"); // flag=true이면 수정클릭한번 했다. flag=true이면 로그인 했음
				mav.addObject("match", "false"); // match=false이면 비번 일치 안함=> 비번이 아니라 아이디 일치 안함 인것 같은데.
				
				mav.setViewName(gotoPage); // "redirect:/list.me"
				//return mav;
			}
			
		}
		mav.addObject("flag", "true");
		return mav;
	}
	
	// MemberUpdateForm.jsp에서 입력 후 온다. 
	@RequestMapping(value=command , method=RequestMethod.POST)
	public ModelAndView doActionPost(@ModelAttribute("member") @Valid Member member, 
			BindingResult bindingResult){		
		System.out.println("post 방식 들어옴");
		System.out.println(member.getHobby());
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println(bindingResult.getFieldValue("id"));
		System.out.println(bindingResult.getFieldValue("password"));
		System.out.println(bindingResult.getFieldValue("name"));
		System.out.println(bindingResult.getFieldValue("hobby"));
		System.out.println(bindingResult.getFieldValue("password"));
		System.out.println(bindingResult.getFieldValue("address1"));
		
		if (bindingResult.hasErrors()) {
			System.out.println("유효성 검사 오류입니다");
			mav.setViewName(getPage); // ProductInsertForm.jsp
			return mav;
		} 
		
		// 데이터 베이스에 추가하는 코드 
		Integer cnt = -1;
		cnt = memberDao.UpdateData(member);	
		mav.setViewName(gotoPage);

		return mav;

		/*Integer cnt = -1;
		System.out.println("투스트링메소드");
		System.out.println(product.toString());
		cnt = productDao.UpdateProduct(product);

		if (cnt != -1) {
			return new ModelAndView(gotoPage);

		} else {
			return new ModelAndView(getPage);
		}*/
	}
}

