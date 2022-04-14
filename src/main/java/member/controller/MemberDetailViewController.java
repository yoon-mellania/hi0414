package member.controller;

import member.model.Member;
import member.model.MemberDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class MemberDetailViewController { // MemberList.jsp에서 넘어옴
	private static final String getPage = "MemberDetailView";
	private static final String command = "/detail.me";	
	
	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;	

	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet( @RequestParam(value="id",required=true) String id ,
			Model model){
		
		
		Member  member =  memberDao.GetData( id );
		model.addAttribute("member" , member);
		System.out.println("Get 방식 들어옴");
		return getPage; //MemberDetailView.jsp
	}
	
	//아래 코드는 사용안함
	/*	
	@RequestMapping(value=command , method=RequestMethod.POST)
	public ModelAndView doActionPost(Product product){
		Integer cnt = -1;
		System.out.println("투스트링메소드");
		System.out.println(product.toString());
		cnt = productDao.DetailData(product);
		System.out.println("ProductDetailViewController cnt:"+cnt);
		if (cnt != -1) {
			return new ModelAndView(gotoPage);

		} else {
			return new ModelAndView(getPage);
		}
	}
	*/
}
