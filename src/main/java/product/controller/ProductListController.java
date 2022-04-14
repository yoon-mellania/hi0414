package product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import product.model.Product;
import product.model.ProductDao;
import utility.Paging;
import utility.MyUtility;

@Controller
public class ProductListController {
	private static final String getPage = "ProductList";
	//private static final String gotoPage = "ProductList";
	private static final String command = "/list.prd";

	@Autowired
	@Qualifier("myProductDao")
	private ProductDao productDao;
 
	@RequestMapping(value = command )//GET ��İ� POST ��� �� �� ó���ϱ�
	public ModelAndView doActionPost(
			@RequestParam(value = "whatColumn", required = false ) String whatColumn,
			@RequestParam(value = "keyword", required = false ) String keyword,
			@RequestParam(value = "pageNumber", required = false ) String pageNumber,
			@RequestParam(value = "pageSize", required = false ) String pageSize,
			@RequestParam(value = "abc", required = false ) String abc,
			HttpServletRequest request) {
		
		System.out.println("getClass():"+getClass() + "/" + request.getMethod());
		
		System.out.print("abc : " + abc + ", ");
		System.out.print("�˻��� �÷�(whatColumn) : " + whatColumn + ", ");
		System.out.print("�˻��� �ܾ�(keyword) : " + keyword + ", ");
		System.out.print("pageNumber : " + pageNumber + ", ");
		System.out.print("pageSize : " + pageSize + ", ");
		
		//System.out.print( 3 / 0 );

		Map<String, String> map = new HashMap<String, String>() ;	
		
		if( whatColumn != null && whatColumn.equals("all")){
			whatColumn = null ; 
		}
		map.put("whatColumn", whatColumn ) ;
		map.put("keyword", "%" + keyword + "%" ) ;
		
		int totalCount = productDao.GetTotalCount( map );
		System.out.print("��ü ���(totalCount) : " + totalCount + ", ");
		String url = request.getContextPath() + "/" + this.command ;
		
		ModelAndView mav = new ModelAndView();
		
		Paging pageInfo 
			= new Paging( pageNumber, pageSize, totalCount, url, whatColumn, keyword, null);
		
		System.out.print( "offset : " + pageInfo.getOffset() + ", " ) ; 
		System.out.print( "limit : " + pageInfo.getLimit() + ", " ) ;  
		
		List<Product> productLists = productDao.GetDataList( pageInfo, map );
		
		System.out.println("��ȸ�� �Ǽ�: " + productLists.size());
		mav.addObject( "productLists", productLists );		
		mav.addObject( "pageInfo", pageInfo );
		mav.setViewName(getPage); // ProductList
		return mav;
	}
	
	/*@ExceptionHandler(value = Exception.class)
	public ModelAndView error(Exception ex) {
		return MyUtility.getMav(ex, this.getClass().toString());
	}	*/
}
