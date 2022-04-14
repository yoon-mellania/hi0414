package member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViaController {

	private static final String getPage= "MemberLoginForm"; // id,pw ÀÔ·ÂÆû
	private static final String command = "/via.me"; 

	@RequestMapping(value=command )
	public String doActionGet(){
		System.out.println(this.getClass() + " Get ¹æ½Ä µé¾î¿È");
		return getPage; //MemberLoginForm.jsp
	}
}
