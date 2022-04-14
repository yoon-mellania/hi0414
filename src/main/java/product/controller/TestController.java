package product.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	final String command = "test.prd";
	
	@RequestMapping(command)
	public String doAction(HttpServletRequest request) {
		
		System.out.println(getClass() + request.getMethod());
		
		return "redirect:/test2.prd"; 
	}
}
