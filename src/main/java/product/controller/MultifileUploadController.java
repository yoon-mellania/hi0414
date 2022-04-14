/*package product.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import product.model.ProductDao;




//다중 업로드용 컨트롤러




@Controller
public class MultifileUploadController implements ServletContextAware{
	
	private static final String gotoPage = "redirect:/list.prd";
	private static final String command = "/fileupload.prd";
	
	private ServletContext servletContext;
	
	@Autowired
	@Qualifier("myProductDao")
	private ProductDao productDao;
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String doActionGet(MultipartFile file1, Model model, HttpServletRequest request) throws IllegalStateException,IOException{
		
		System.out.println("POST 방식 들어옴");
		String fileName = file1.getOriginalFilename();
		String saveDir = request.getServletPath().getRealPath("resources");  
		int cnt = -1;
		return gotoPage;
	}

	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		servletContext=context;
	}

}


*/