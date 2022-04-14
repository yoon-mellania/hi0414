package mall.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import mall.cart.ShoppingInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import product.model.CompositeDao;

@Controller
public class DetailViewController {
	//private static final String getPage = "이름만드세여";
	//private static final String gotoPage = "이름만드세여";
	private static final String command = "/detailview.mall";	 
	
	@Autowired
	@Qualifier("myCompositeDao")
	private CompositeDao compositeDao;		
	
	/*@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get 방식 들어옴");
		return getPage;
	}*/
	
	// ShopList.jsp에서 선택한 주문번호 하나 가지고 넘어온다.
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session,
			@RequestParam(value="pmkey",required=true) int pmkey, Model model){
		// hong 아이디로 주문한 모든 내역에서 선택한 하나의 주문번호만 넘어와서 pmkey에 담긴다.
		
		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/list.prd" ) ; 
			return "redirect:/LoginForm.me";	
			
		}else{
			
			/*
			ShoppingInfo
			private int pnum ; //상품 번호
			private String pname ; //상품 명
			private int qty ; //주문 수량
			private int price ; //단가
			private int amount ; //금액
			*/
			
			List<ShoppingInfo> lists = this.compositeDao.ShowDetail( pmkey ) ;
			System.out.println(lists.get(0).getPnum());
			System.out.println(lists.get(0).getPname());
			
			model.addAttribute( "pmkey", pmkey ); //송장 번호(주문번호)			
			model.addAttribute( "shoplists", lists );
			return "ShopResult";
		}	
	}
}
