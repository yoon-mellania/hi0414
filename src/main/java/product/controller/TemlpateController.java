package product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemlpateController {
	private static final String getPage = "�̸����弼��";
	private static final String gotoPage = "�̸����弼��";
	private static final String command = "/��ġ����.prd";
	
	@ModelAttribute("")
	public List<String> someData(){
		List<String> lists = new ArrayList<String>();
		lists.add("a");
		lists.add("b");
		lists.add("c");
		
		return lists;
	}
	
	@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get ��� ����");
		return getPage;
	}
	@RequestMapping(value="/.prd" , method=RequestMethod.POST)
	public ModelAndView doActionPost(){
		System.out.println(this.getClass() + " POST ��� ����");
		ModelAndView mav = new ModelAndView();
		mav.addObject("some","");
		mav.setViewName(getPage);
		
		return mav;
	}
}