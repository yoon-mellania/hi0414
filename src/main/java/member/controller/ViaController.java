package member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViaController {

	private static final String getPage= "MemberLoginForm"; // id,pw �Է���
	private static final String command = "/via.me"; 

	@RequestMapping(value=command )
	public String doActionGet(){
		System.out.println(this.getClass() + " Get ��� ����");
		return getPage; //MemberLoginForm.jsp
	}
}
