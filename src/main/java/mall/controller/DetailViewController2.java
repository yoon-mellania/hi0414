



//이거보다 DetailViewController.java가 낫겠다.




/*package mall.controller;

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
public class DetailViewController2 {
	//private static final String getPage = "이름만드세여";
	//private static final String gotoPage = "이름만드세여";
	private static final String command = "/detailview.mall";	 
	
	@Autowired
	@Qualifier("myCompositeDao")
	private CompositeDao compositeDao;		
	
	@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get 방식 들어옴");
		return getPage;
	}
	
	// ShopList.jsp에서 선택한 주문번호 하나 가지고 넘어온다.
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session,
			@RequestParam(value="pmkey",required=true) int pmkey, Model model){
		// hong 아이디로 주문한 모든 내역에서 선택한 하나의 주문번호만 넘어와서 pmkey에 담긴다.
		
		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/list.prd" ) ; 
			return "redirect:/LoginForm.me";	
			
		}else{
			List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>() ;
			
			
			ShoppingInfo
			private int pnum ; //상품 번호
			private String pname ; //상품 명
			private int qty ; //주문 수량
			private int price ; //단가
			private int amount ; //금액
			
			
			List<HashMap<String, Object>> lists = this.compositeDao.ShowDetail( pmkey ) ;
			//ShowDetail()를 통해 selectList를 하면 List가 리턴되므로 리턴타입은 List~로 해야한다.
			
			System.out.println("lists.size():"+lists.size());
			for(int i=0;i<lists.size();i++){
				HashMap<String, Object> hash = lists.get(i);
				System.out.println("hash:"+hash);
				//hash:{PNAME=치킨, PNUM=6, PRICE=5000, AMOUNT=10000, QTY=2}
				Set<String> set = hash.keySet();
				System.out.println("set:"+set);
				// set:[PNAME, PNUM, PRICE, AMOUNT, QTY]
				// 주문상세 내역의 갯수만큼 위 set:~ 문장이 출력된다.
				
				for(String s :set ){
					Object obj = hash.get(s);
					System.out.println(s+":"+obj);
				}
				System.out.println("--------------------");
			}
			
			주문 내역에서 주문번호 1의 상세보기를 클릭했을 때
			위의 이중 for문을 돌리면 아래 내용이 출력된다.
			hash:{PNAME=치킨, PNUM=6, PRICE=5000, AMOUNT=10000, QTY=2}
			set:[PNAME, PNUM, PRICE, AMOUNT, QTY]
			PNAME:치킨
			PNUM:6
			PRICE:5000
			AMOUNT:10000
			QTY:2
			------------------------------
			hash:{PNAME=환타, PNUM=5, PRICE=5000, AMOUNT=15000, QTY=3}
			set:[PNAME, PNUM, PRICE, AMOUNT, QTY]
			PNAME:환타
			PNUM:5
			PRICE:5000
			AMOUNT:15000
			QTY:3
			
			그러니까, 
			5개의 항목(PNAME, PNUM, PRICE, AMOUNT, QTY)이 
			HashMap으로 묶여서 ArrayList에 들어있는 것이다.
					
			
			for(HashMap<String, Object> map : lists){
				BigDecimal pnum = (BigDecimal)map.get("PNUM") ;
				String pname = (String)map.get("PNAME") ;				
				BigDecimal qty = (BigDecimal)map.get("QTY") ;
				BigDecimal price = (BigDecimal)map.get("PRICE") ;
				BigDecimal amount = (BigDecimal)map.get("AMOUNT") ;
				System.out.println( pname + "/" + qty + "/" + price + "/" + amount );
				
				사이다/10/4000/40000
				콜라/20/3000/60000
				크림빵/30/2000/60000
				
				
				
				위에서 PNUM,QTY,PRICE,AMOUNT값을 가져올 때
				오라클 NUMBER 형 컬럼의 데이터("PNUM") 등을 HashMap 타입으로 받아
				java에서 사용하려고 하면	java.math.BigDecimal cannot be cast to java.lang.String  오류가 발생한다.
				위에서 Integer 형변환의 방법을 쓰지 말고 BigDecimal타입의 형변환을 써야 한다.
				
				ShoppingInfo shopinfo = new ShoppingInfo() ;
				shopinfo.setPnum( pnum.intValue() ); 
				shopinfo.setPname(pname); 
				shopinfo.setQty(qty.intValue());
				shopinfo.setPrice(price.intValue()); 
				shopinfo.setAmount(amount.intValue() ); // .intValue()안쓰면 에러남
				
				shoplists.add(shopinfo) ;
			}
			model.addAttribute( "pmkey", pmkey ); //송장 번호(주문번호)			
			model.addAttribute( "shoplists", shoplists );
			return "ShopResult";
		}	
	}
}
*/