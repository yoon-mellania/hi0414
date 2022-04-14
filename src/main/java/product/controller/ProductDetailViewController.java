package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import product.model.Product;
import product.model.ProductDao;

@Controller
public class ProductDetailViewController { // ProductList.jsp에서 넘어옴
	private static final String getPage = "ProductDetailView";
	private static final String command = "/detail.prd";	
	
	@Autowired
	@Qualifier("myProductDao")
	private ProductDao productDao;	

	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet( @RequestParam(value="pmkey",required=true) int pmkey ,
			Model model){
		
		System.out.println(getClass() + " Get 방식 들어옴");
		
		Product product =  productDao.GetData( pmkey );
		model.addAttribute("product" , product);
		return getPage; //ProductDetailView.jsp
	}
	
	// 아래 코드는 사용안함
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

