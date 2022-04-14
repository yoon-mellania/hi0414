package mall.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import member.model.Member;
import order.model.Order;
import product.model.CompositeDao;

@Controller
public class OrderMallController { // main에서 넘어옴
	private static final String getPage = "ShopList";
	//private static final String gotoPage = "이름만드세여";
	private static final String command = "/order.mall" ;	 // main.jsp에서 넘어옴
	
	@Autowired
	@Qualifier("myCompositeDao")
	private CompositeDao compositeDao;	
	
	// main에서 나의 주문 내역 클릭하면 넘어온다.
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session, Model model ){

		Member loginfo = (Member)session.getAttribute("loginfo") ;
		if( loginfo == null ){ //no
			session.setAttribute("destination", "redirect:/order.mall"  );
			return "redirect:/LoginForm.me" ;			
		/*}else{ 
			System.out.println("회원에 대한 주문 상세를 보여 주자");
			
			List<Order> orderlists = new ArrayList<Order>() ;
			
			//lists : 현재 로그인 한 사람의 쇼핑 주문 내역들을 담고 있는 컬렉션(최근 주문 내역이 먼저 나옴)
			List<HashMap<String, Object>> lists = this.compositeDao.OrderMall( loginfo ) ;
			
			lists에는 아래 내용이 들어있다. 
			MID		OID		ORDERDATE
			hong	21		2016/6/15 08:36
			hong	20		2016/6/13  1:16
			hong	19		2016/6/07  12:12
			hong	18		2016/6/02  17:51
				:
				:
			
			
			// 아래 for문 돌면서 
			HashMap<String, Object> hash = new HashMap<String, Object>();
			for(int i=0;i<lists.size();i++){
				hash = lists.get(i);
				Set<String> set = hash.keySet();
				System.out.println("set:"+set); //set:[OID, MID, ORDERDATE]
				for(String s : set){
					Object o = hash.get(s);
					System.out.println("o:"+o);					
				}
			}
			
			위의 for문 돌면서 List<HashMap<String, Object>> lists에는 무엇이 들어있나 확인해본다.
			set:[OID, MID, ORDERDATE]
			o:9
			o:hong  
			o:2016/06/15 21:57
			----------------------
			set:[OID, MID, ORDERDATE]
			o:8
			o:hong
			o:2016/06/13 21:28
			----------------------
			set:[OID, MID, ORDERDATE]
			o:7
			o:hong
			o:2016/06/12 22:38
				:
			1까지 나타난다.
			그러니까,
			OID,9   MID,hong   ORDERDATE,2016/06/15 21:57
			OID,8   MID,hong   ORDERDATE,2016/06/13 21:28 의 값이 들어간다고 보면 될듯함
				:
				:
			 	
				
			for(HashMap<String, Object> map : lists){
				//주의 전부 대문자로 설정된다.				
				String mid = (String)map.get("MID") ;
				BigDecimal oid = (BigDecimal)map.get("OID") ;
				//Integer oid = (Integer)map.get("OID") ; // ①
				String orderdate = (String)map.get("ORDERDATE") ;
				
				
				위에서 OID값을 가져올 때
				오라클 NUMBER 형 컬럼의 데이터("OID")를 HashMap 타입으로 받아
				java에서 사용하려고 하면	java.math.BigDecimal cannot be cast to java.lang.String  오류가 발생한다.
				위에서 ①의 방법을 쓰지 말고 BigDecimal타입의 형변환을 써야 한다.
				저 아래의 ②도 안되고 그 윗줄 코드를 쓰자.
				
				
				Order order = new Order() ;
				order.setMid(mid); 
				order.setOid( oid.intValue() );
				//order.setOid( oid); // ②
				order.setOrderdate(orderdate); 
				orderlists.add(order) ;
			}			
			model.addAttribute( "orderlists", orderlists );*/ 
			// orderlists에는 위의 테이블내용(lists의 내용-hong 아이디로 주문한 모든 내역)이 들어있다. 
			
			
			
			/*
			 * 
			 * 위와 같은 방식으로 해도 되고 좀 더 간단하게 아래의 방법을 써도 된다.
			 * 
			 * */
			
			
			
			
		}else{ 
			System.out.println("OrderMallController 회원에 대한 주문 상세를 보여 주자");
			
			//List<Order> orderlists = new ArrayList<Order>() ; // 이 줄 필요없음
			
			//lists : 현재 로그인 한 사람의 쇼핑 주문 내역들을 담고 있는 컬렉션(최근 주문 내역이 먼저 나옴)
			List<Order> lists = this.compositeDao.OrderMall2( loginfo ) ; // 쇼핑 내역에 출력할 내용들
			//위의 OrderMall2 메서드를 OrderDao에 작성해도 된다.
			
			// for문 안의 syso 출력해보자. 
			// 아래 for문 생략해도 되겠다.
			for(Order od : lists){
				int oid = od.getOid();
				String mid = od.getMid();
				String orderdate = od.getOrderdate();
				System.out.println(oid+"/"+mid+"/"+orderdate);
				/*Order order = new Order() ;
				order.setMid(mid); 
				order.setOid( oid);
				order.setOrderdate(orderdate); 
				orderlists.add(order) ;*/
				
				/*
				1/kim/2017-12-01 17:13:54.0
				2/kim/2017-12-01 17:38:21.0
				5/kim/2017-12-01 17:40:11.0
				6/kim/2017-12-01 17:42:23.0
				7/kim/2017-12-01 17:46:59.0
				*/
			}			
		
			model.addAttribute( "orderlists", lists );
			
			return getPage; // ShopList
		}
	}
}
