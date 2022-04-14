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
		
		System.out.println(this.getClass() + "GET ���");
		
		ModelAndView mav = new ModelAndView();
		System.out.println("session.getAttribute('loginfo'):"+session.getAttribute("loginfo"));
		//mav.addObject("match" , true);
		//mav.addObject("flag", "false"); 
		// flag�� ����Ŭ���ϸ� true�� �ٲ��. 
		// �Ź� ���� Ŭ���� ������ Ŭ�� ���ѻ��¿��� �����ؾ��ؼ� MemberUpdateController�� �� ������ false�� �ʱ�ȭ���ش�.
		
		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/update.me?id="+id );
			//return "redirect:/LoginForm.me";	 // get ��û
			mav.setViewName("redirect:/LoginForm.me");
			//return mav;
			
		}else{ //yes:�α��� ������..
				
			if(((Member)session.getAttribute("loginfo")).getId().equals(id)){
				Member member =  memberDao.GetData( id ); // 1
				//model.addAttribute("member" , member); // 1
				mav.addObject("member" , member);
				
				//Member member2 =  memberDao.GetDataMember( member ); // 2(()���� member�� ���� 1�� ���� member��
				//model.addAttribute("member" , member2); // 2
				/*
				���� 1�ڵ�� id�ϳ��� Member ��ü�� �˾ƿ��� �ڵ��̰�,
				���� 2�ڵ�� Member��ü�� �ٽ� Member��ü�� �˾ƿ��� �ڵ�� 2���� ������� ����.
				�׷��� Member.xml����..
				1�� �ڵ�� ��� ���� �ϳ��� �ѱ�� id="GetData"���� where id = #{id}�� �ǰ� where id = #{name} �� �ǰ�..
				{}�ȿ� �ƹ� ���ڳ� �־ �ȴ�.
				2�� �ڵ�� �������� �ϳ��� ��ü �������� �ѱ�� �Ѿ�� ������ �����ϱ� id="GetDataMember"���� 
				where id = #{id} �� ��Ȯ�� ���� {}�ȿ� �־�� �Ѵ�. where id = #{name}�� ������
				#{id}���� getId()���� �Ѿ���� ����
				*/
				
				System.out.println("Get ��� ����");
				//return getPage; // "MemberUpdateForm"
				mav.setViewName(getPage);
				//return mav;
			}
			else{
				//model.addAttribute("flag", "false"); // 1
				//return gotoPage; // "redirect:/list.me"
				//mav.addObject("flag" , false);
				//mav.addObject("flag", "true"); // flag=true�̸� ����Ŭ���ѹ� �ߴ�. flag=true�̸� �α��� ����
				mav.addObject("match", "false"); // match=false�̸� ��� ��ġ ����=> ����� �ƴ϶� ���̵� ��ġ ���� �ΰ� ������.
				
				mav.setViewName(gotoPage); // "redirect:/list.me"
				//return mav;
			}
			
		}
		mav.addObject("flag", "true");
		return mav;
	}
	
	// MemberUpdateForm.jsp���� �Է� �� �´�. 
	@RequestMapping(value=command , method=RequestMethod.POST)
	public ModelAndView doActionPost(@ModelAttribute("member") @Valid Member member, 
			BindingResult bindingResult){		
		System.out.println("post ��� ����");
		System.out.println(member.getHobby());
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println(bindingResult.getFieldValue("id"));
		System.out.println(bindingResult.getFieldValue("password"));
		System.out.println(bindingResult.getFieldValue("name"));
		System.out.println(bindingResult.getFieldValue("hobby"));
		System.out.println(bindingResult.getFieldValue("password"));
		System.out.println(bindingResult.getFieldValue("address1"));
		
		if (bindingResult.hasErrors()) {
			System.out.println("��ȿ�� �˻� �����Դϴ�");
			mav.setViewName(getPage); // ProductInsertForm.jsp
			return mav;
		} 
		
		// ������ ���̽��� �߰��ϴ� �ڵ� 
		Integer cnt = -1;
		cnt = memberDao.UpdateData(member);	
		mav.setViewName(gotoPage);

		return mav;

		/*Integer cnt = -1;
		System.out.println("����Ʈ���޼ҵ�");
		System.out.println(product.toString());
		cnt = productDao.UpdateProduct(product);

		if (cnt != -1) {
			return new ModelAndView(gotoPage);

		} else {
			return new ModelAndView(getPage);
		}*/
	}
}

