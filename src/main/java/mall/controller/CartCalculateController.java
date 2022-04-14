package mall.controller;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import mall.cart.MyCartList;
import member.model.Member;
import member.model.MemberDao;
import order.model.OrderDao;
import orderdetail.model.OrderDetail;
import orderdetail.model.OrderDetailDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import product.model.ProductDao;

@Controller
public class CartCalculateController { // (장바구니 목록보기-주문 내역)MallList.jsp에서 결재하기 클릭시 get으로 넘어옴
	//private static final String getPage = "이름만드세여";
	//private static final String gotoPage = "이름만드세여";
	private static final String command = "/calculate.mall";
	 

	@Autowired
	@Qualifier("myMemberDao") // mall-servlet.xml의 <context:component-scan base-package="member"/> 이 없으면 이줄에서 에러난다. 
	private MemberDao memberDao; // 회원 포인트 적립 
	
	@Autowired
	@Qualifier("myProductDao")
	private ProductDao productDao; // 재고수량 감소
	
	@Autowired
	@Qualifier("myOrderDao")
	private OrderDao orderDao ; // orders 테이블에 삽입
	
	@Autowired
	@Qualifier("myOrderDetailDao")
	private OrderDetailDao orderDetailDao ; // 주문 상세 테이블에 추가
	
	/*
	OrderDetailDao는 나의 주문 내역의 상세보기 클릭했을 때 나오는 주문 상세 테이블??? 
	private int odid ;
	private int oid ;
	private int pnum ;
	private int qty ;
	*/
	/*@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get 방식 들어옴");
		return getPage;
	}*/
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session	){
		
		// System.out.println("계산 때리기");
		//List<ShoppingInfo> shoplists = (List<ShoppingInfo>)session.getAttribute("shoplists") ;
		
		MyCartList mycart = (MyCartList)session.getAttribute("mycart") ; // CartAddController 에서 session 설정 
		
		if( mycart != null ){
			Map<Integer, Integer> maplists =  mycart.GetAllOrderLists() ;
			System.out.println("CartCalculateController의 maplists.size():"+maplists.size());
			/*
			주문하기해서 주문 내역에 나타난 레코드가 결재하기 클릭하면 현재 페이지로 넘어오고 그때의 size는 
			주문 내역에 담아놓은 레코드 수
			16 2016-11-25 08:29:42.0 =>16번 주문기록에 있는 상품 2개를 주문한 내용(위의 maplists.size()는 2)
			추가주문 할때마다 ShoppingInfo객체에 담기고 그 객체가 ArrayList에 담긴다.
			마지막 결재하기 클릭하면 Map에 담아 놓은 내용이 GetAllOrderLists() 통해서 넘어온다.
			*/
			/*
			GetAllOrderLists의 기능:
				return orderlists;
			//orderlists는 몇번상품, 몇개 판매 의 정보를 가지고 있다.
			*/
			Set<Integer> keylist = maplists.keySet() ;
			// orderlists의 내용이 keylist에 들어간다. 이번에 주문한 상품목록들(상품번호만 keylist에 들어간다.)
			// Map<Integer, Integer> orderlists에는 (상품번호,수량)이 들어가 있는데, 
			// keySet()을 하면 상품번호만 가져와서 Set<Integer> keylist에 담는다. 
			// 아래에서 keylist를 출력하면 상품번호만 나오는 것을 알수 있다. 
			
			System.out.println("keylist:"+keylist); // keylist:[1, 4, 23]
			
			// 1. 주문 테이블(orders 테이블)에 데이터 추가(ShopList.jsp에서 주문 내역 보기할 때 필요함) 
			/*
			create table orders(
				  oid number primary key,
				  mid varchar2(10) references members(id) on delete set null,
				  orderdate date default sysdate 
			);
			*/
			
			Member mem = ((Member)session.getAttribute("loginfo")) ;
			
			orderDao.InsertData( mem.getId() ) ; 
			
			// 로그인한 회원의 아이디를 orders 테이블에 넣는다. (id만 보내면 oid와 orderdate는 자동으로 들어간다. )
			//	insert into orders(oid, mid, orderdate)
			//	values( seqoid.nextval, #{mid}, sysdate )
			// orders 테이블에 삽입  oid:주문 번호, mid:회원 번호, orderdate:주문 일
			
			System.out.println("mem.getId():"+mem.getId());
			
			int oid = orderDao.GetMaxOrderId() ;
			System.out.println("oid : " + oid ); // 가장 최근의 송장번호(주문번호)를 가져온다. 가장 최근송장번호가 지금 주문한 내용이므로..
			
			for( Integer pnum : keylist){ // 여기서 keylist대신 maplists를 넣으면 안된다. 
				Integer qty = maplists.get(pnum) ; // get(키) : get에 키를 넣으면 값(수량)이 나온다.

				//2. 상품 재고 수량 감소
				productDao.UpdateData( pnum, qty ) ;
				
				OrderDetail odBean = new OrderDetail() ;
				odBean.setOid(oid);
				odBean.setPnum(pnum); 
				odBean.setQty(qty);
				/* 
				class OrderDetail{ // 주문 상세 정보 bean
					private int odid ; // sequence
					private int oid ; // 주문번호
					private int pnum ; // 상품 번호
					private int qty ; // 주문 수량
				}
				*/ 
				//3. 주문 상세 테이블에 추가
				/*
				create table orderdetails(
					  odid number primary key,
					  oid number references orders(oid) on delete cascade, -- 같은 번호가 여러개 들어갈 수 있다. for문 반복 횟수만큼.. 
					  pnum number references products(num) on delete set null,
					  qty number
				); 
				*/
				
				orderDetailDao.InsertData( odBean );
				// 이번에 주문한 상품이 keylist:[1, 4, 23] 이렇게 3개이면
				// InsertData()도 세번 호출된다.
				// orderdetails 테이블에 삽입 odid:시퀀스, oid:주문번호,  pnum:상품 번호,  qty:주문 수량
			}
			
			//4. 회원 포인트 적립
			memberDao.UpdateData(mem.getId(), 100) ;
			/*
			아래 2개 속성 삭제 안하면 결재하고나서도 장바구니의 목록이 그대로 남아있어서
			다음 물건 구입했는데도 이전 장바구니 목록에 계속 추가 된다. 
			*/
			session.removeAttribute("shoplists") ; // 이건 없어도 되나?? 없어도 되지 않을까? CartListController에서 shoplists가 매번 생성되는 것 같은데.. 
			session.removeAttribute("mycart") ;
		}
		return "redirect:/list.prd"; // 상품 목록보기로 간다. 
	}
}

