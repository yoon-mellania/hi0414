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
public class CartCalculateController { // (��ٱ��� ��Ϻ���-�ֹ� ����)MallList.jsp���� �����ϱ� Ŭ���� get���� �Ѿ��
	//private static final String getPage = "�̸����弼��";
	//private static final String gotoPage = "�̸����弼��";
	private static final String command = "/calculate.mall";
	 

	@Autowired
	@Qualifier("myMemberDao") // mall-servlet.xml�� <context:component-scan base-package="member"/> �� ������ ���ٿ��� ��������. 
	private MemberDao memberDao; // ȸ�� ����Ʈ ���� 
	
	@Autowired
	@Qualifier("myProductDao")
	private ProductDao productDao; // ������ ����
	
	@Autowired
	@Qualifier("myOrderDao")
	private OrderDao orderDao ; // orders ���̺� ����
	
	@Autowired
	@Qualifier("myOrderDetailDao")
	private OrderDetailDao orderDetailDao ; // �ֹ� �� ���̺� �߰�
	
	/*
	OrderDetailDao�� ���� �ֹ� ������ �󼼺��� Ŭ������ �� ������ �ֹ� �� ���̺�??? 
	private int odid ;
	private int oid ;
	private int pnum ;
	private int qty ;
	*/
	/*@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get ��� ����");
		return getPage;
	}*/
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session	){
		
		// System.out.println("��� ������");
		//List<ShoppingInfo> shoplists = (List<ShoppingInfo>)session.getAttribute("shoplists") ;
		
		MyCartList mycart = (MyCartList)session.getAttribute("mycart") ; // CartAddController ���� session ���� 
		
		if( mycart != null ){
			Map<Integer, Integer> maplists =  mycart.GetAllOrderLists() ;
			System.out.println("CartCalculateController�� maplists.size():"+maplists.size());
			/*
			�ֹ��ϱ��ؼ� �ֹ� ������ ��Ÿ�� ���ڵ尡 �����ϱ� Ŭ���ϸ� ���� �������� �Ѿ���� �׶��� size�� 
			�ֹ� ������ ��Ƴ��� ���ڵ� ��
			16 2016-11-25 08:29:42.0 =>16�� �ֹ���Ͽ� �ִ� ��ǰ 2���� �ֹ��� ����(���� maplists.size()�� 2)
			�߰��ֹ� �Ҷ����� ShoppingInfo��ü�� ���� �� ��ü�� ArrayList�� ����.
			������ �����ϱ� Ŭ���ϸ� Map�� ��� ���� ������ GetAllOrderLists() ���ؼ� �Ѿ�´�.
			*/
			/*
			GetAllOrderLists�� ���:
				return orderlists;
			//orderlists�� �����ǰ, � �Ǹ� �� ������ ������ �ִ�.
			*/
			Set<Integer> keylist = maplists.keySet() ;
			// orderlists�� ������ keylist�� ����. �̹��� �ֹ��� ��ǰ��ϵ�(��ǰ��ȣ�� keylist�� ����.)
			// Map<Integer, Integer> orderlists���� (��ǰ��ȣ,����)�� �� �ִµ�, 
			// keySet()�� �ϸ� ��ǰ��ȣ�� �����ͼ� Set<Integer> keylist�� ��´�. 
			// �Ʒ����� keylist�� ����ϸ� ��ǰ��ȣ�� ������ ���� �˼� �ִ�. 
			
			System.out.println("keylist:"+keylist); // keylist:[1, 4, 23]
			
			// 1. �ֹ� ���̺�(orders ���̺�)�� ������ �߰�(ShopList.jsp���� �ֹ� ���� ������ �� �ʿ���) 
			/*
			create table orders(
				  oid number primary key,
				  mid varchar2(10) references members(id) on delete set null,
				  orderdate date default sysdate 
			);
			*/
			
			Member mem = ((Member)session.getAttribute("loginfo")) ;
			
			orderDao.InsertData( mem.getId() ) ; 
			
			// �α����� ȸ���� ���̵� orders ���̺� �ִ´�. (id�� ������ oid�� orderdate�� �ڵ����� ����. )
			//	insert into orders(oid, mid, orderdate)
			//	values( seqoid.nextval, #{mid}, sysdate )
			// orders ���̺� ����  oid:�ֹ� ��ȣ, mid:ȸ�� ��ȣ, orderdate:�ֹ� ��
			
			System.out.println("mem.getId():"+mem.getId());
			
			int oid = orderDao.GetMaxOrderId() ;
			System.out.println("oid : " + oid ); // ���� �ֱ��� �����ȣ(�ֹ���ȣ)�� �����´�. ���� �ֱټ����ȣ�� ���� �ֹ��� �����̹Ƿ�..
			
			for( Integer pnum : keylist){ // ���⼭ keylist��� maplists�� ������ �ȵȴ�. 
				Integer qty = maplists.get(pnum) ; // get(Ű) : get�� Ű�� ������ ��(����)�� ���´�.

				//2. ��ǰ ��� ���� ����
				productDao.UpdateData( pnum, qty ) ;
				
				OrderDetail odBean = new OrderDetail() ;
				odBean.setOid(oid);
				odBean.setPnum(pnum); 
				odBean.setQty(qty);
				/* 
				class OrderDetail{ // �ֹ� �� ���� bean
					private int odid ; // sequence
					private int oid ; // �ֹ���ȣ
					private int pnum ; // ��ǰ ��ȣ
					private int qty ; // �ֹ� ����
				}
				*/ 
				//3. �ֹ� �� ���̺� �߰�
				/*
				create table orderdetails(
					  odid number primary key,
					  oid number references orders(oid) on delete cascade, -- ���� ��ȣ�� ������ �� �� �ִ�. for�� �ݺ� Ƚ����ŭ.. 
					  pnum number references products(num) on delete set null,
					  qty number
				); 
				*/
				
				orderDetailDao.InsertData( odBean );
				// �̹��� �ֹ��� ��ǰ�� keylist:[1, 4, 23] �̷��� 3���̸�
				// InsertData()�� ���� ȣ��ȴ�.
				// orderdetails ���̺� ���� odid:������, oid:�ֹ���ȣ,  pnum:��ǰ ��ȣ,  qty:�ֹ� ����
			}
			
			//4. ȸ�� ����Ʈ ����
			memberDao.UpdateData(mem.getId(), 100) ;
			/*
			�Ʒ� 2�� �Ӽ� ���� ���ϸ� �����ϰ����� ��ٱ����� ����� �״�� �����־
			���� ���� �����ߴµ��� ���� ��ٱ��� ��Ͽ� ��� �߰� �ȴ�. 
			*/
			session.removeAttribute("shoplists") ; // �̰� ��� �ǳ�?? ��� ���� ������? CartListController���� shoplists�� �Ź� �����Ǵ� �� ������.. 
			session.removeAttribute("mycart") ;
		}
		return "redirect:/list.prd"; // ��ǰ ��Ϻ���� ����. 
	}
}

