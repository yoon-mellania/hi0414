package product.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

import product.model.Product;
import product.model.ProductDao;

@Controller
public class ProductDeleteController implements ServletContextAware{
	
	private static final String gotoPage = "redirect:/list.prd";
	private static final String command = "/delete.prd";
	
	private ServletContext servletContext; // 저 아래의 setServletContext에서  ServletContext 객체 주입했다. 
	
	@Autowired
	@Qualifier("myProductDao")
	private ProductDao productDao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String doActionGet(
				@RequestParam(value="pmkey",required=true) int pmkey){
		System.out.println("Get 방식 들어옴");
		int cnt = -1;
		System.out.println("pmkey:"+pmkey);
		
		Product product = productDao.GetData(pmkey);
		//System.out.println("product.getImage():"+product.getImage());
		
		String uploadDir=servletContext.getRealPath("/resources/"); 
		File delFile = new File( uploadDir + File.separator +product.getImage() );
		cnt = productDao.DeleteData( pmkey );
		delFile.delete();
		
		return gotoPage;
	}

	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		servletContext=context;
	}

}
