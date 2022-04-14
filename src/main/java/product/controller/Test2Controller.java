package product.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.util.privilegedactions.GetMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test2Controller {
	final String command = "test2.prd";
	
	@RequestMapping(command)
	public String doAction(HttpServletRequest request) {
		
		System.out.println(getClass() + request.getMethod());
		
		return "Test";
	}
}
