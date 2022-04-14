package mall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.cart.MyCartList;
import mall.cart.ShoppingInfo;
import product.model.Product;
import product.model.ProductDao;

@Controller
public class CartListController { // CartAddController에서 넘어옴
	//private static final String getPage = "이름만드세여";
	//private static final String gotoPage = "이름만드세여";
	private static final String getPage = "MallList";
	private static final String command = "/list.mall";
	 
	/*@Autowired
	@Qualifier("myCompositeDao")
	private CompositeDao compositeDao;*/	
	
	@Autowired
	@Qualifier("myProductDao")
	private ProductDao productDao;	
	
	/*@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get 방식 들어옴");
		return getPage;
	}*/
	
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session,Model model){
		System.out.println("CartListController loginfo : " + session.getAttribute("loginfo"));
		/*Member [id=hong, name=홍길동, password=1234, salary=100, 
				hiredate=2016-06-12 13:56:36.0, gender=남자, hobby=당구, job=학생, 
				zipcode=123-456, address1=null, address2=null, mpoint=100]*/

		// MemberLoginController.java에서 loginfo session 설정함		
		/*
		if( session.getAttribute("loginfo") == null ){ //no 여기까지 오면 loginfo는 null이 될일이 없을 듯함
			System.out.println("CartListController session.getAttribute(loginfo) is null");
			//session.setAttribute("destination", "redirect:/list.prd" ) ; // 이 줄 없어도 되겠다. 
			return "redirect:/LoginForm.me";	
			
		}else{ //yes
		*/	
			MyCartList mycart = (MyCartList)session.getAttribute("mycart") ;//CartAddController에서 session 설정함 
			// 아래 if생략가능할 듯함
			/*if (mycart == null) {
				return "redirect:/list.prd" ; // ProductListController.java
			}*/	
			
			//maplists : 상품 번호와 주문 수량에 대한 정보를 담고 있는 맵 컬렉션 
			Map<Integer, Integer> maplists =  mycart.GetAllOrderLists() ;
			// mycart.GetAllOrderLists()는 return orderlists ;를 한다.
			// hong이 주문한 모든 상품이 리턴된다.
			// 3번상품 , 7개
			// 5번상품, 2개 이런식으로 모두 리턴된다.
			
			Set<Integer> keylist = maplists.keySet() ;
			System.out.println("keylist:"+keylist); // keylist:[20, 15] 상품 번호가 들어간다. keylist.toString()과 같다. 
			
			// keySet():HashMap의 키값 모두 가져오는 방법
			// 다른 방법도 있지만 keySet()을 쓸 수도 있다.
			// HashMap에는 키:상품번호, 값:주문수량이 담겨 있으므로 keySet()을 써서 키에 해당하는 상품번호들만 가져온다.
			
			System.out.println("keylist.size():"+keylist.size());
			
			//ShoppingInfo는 주문 내역을 표시할 수 있는 클래스로 아래의 멤버를 갖는다.
//			int pnum ; //상품 번호
//			String pname ; //상품 명
//			int qty ; //주문 수량
//			int price ; //단가
//			int amount ; //금액 
			/*
			 * ShoppingInfo를 또 만들지 말고.. 
			 * 기존의 ProductBean을 그대로 사용해도 괜찮을 것 같다. orderqty에 주문수량을 넣고..
			 */
			
			List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>() ;
//			주문 버튼 클릭할 때마다 이 화일로 넘어와서 ArrayList객체를 만든다.
//			1개 주문하면, 2개 주문하면, 3개 주문하면 매번 주문할 때마다 ArrayList객체를 만든다.
			
			int totalAmount = 0 ; //총 금액
			for( Integer pnum : keylist){
				Integer qty = maplists.get(pnum) ;
				System.out.println("pnum:"+pnum+", qty:"+qty); // pnum=상품 번호, qty=구매수량
				//System.out.println( this.getClass() + " " + pnum + " / " + qty );
				//해당 상품에 대한 bean 데이터 정보를 구한 다음
				Product bean = this.productDao.GetData( pnum ) ;
				
				//쇼핑 정보에 담는다.
//				주문 버튼 클릭할 때마다 이 화일로 넘어와서 ShoppingInfo객체를 만든다.
//				1개 주문하면, 2개 주문하면, 3개 주문하면 매번 주문할 때마다 ShoppingInfo객체를 만든다.
// 				ShoppingInfo는 주문 내역서를 만들기 위한 클래스
				
				ShoppingInfo shopInfo = new ShoppingInfo() ;
//				ShoppingInfo 클래스의 멤버변수들
//				private int pnum ; //상품 번호
//				private String pname ; //상품 명
//				private int qty ; //주문 수량
//				private int price ; //단가
//				private int amount ; //금액
				
				int amount = qty * bean.getPrice() ;
				totalAmount += amount ;
				shopInfo.setAmount( amount );
				shopInfo.setPname( bean.getName() ); 
				shopInfo.setPnum( pnum ); 
				shopInfo.setPrice( bean.getPrice() ); 
				shopInfo.setQty( qty ) ;
				System.out.println( shopInfo.toString() );
				shoplists.add( shopInfo ) ;
			}
			
			/*
			mycart에는 상품번호와 수량만 담겨 있는데 
			mycart의 상품번호를 이용해서 products 테이블에서 상품정보를 가져와서
			새롭게 ShoppingInfo(pnum,pname,qty,price,amount) 객체를 만든다.
			*/	
			
			// 아래 2개  대신 model 사용가능
			// model.addAttribute 를 했는데 MallList.jsp에서 sessionScope.shoplists을 쓰면 안된다. sessionScope.은 지워야 한다. 
			session.setAttribute( "shoplists" , shoplists );
			//shoplists:주문한 상품번호와 수량, 상품명, 가격 으로 만들어진 클래스
			session.setAttribute( "totalAmount" , totalAmount );
			//model.addAttribute("totalAmount" , totalAmount+11);
			
			return getPage; //"MallList";
		/*}	*/
	}
}


