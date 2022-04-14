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
public class OrderMallController { // main���� �Ѿ��
	private static final String getPage = "ShopList";
	//private static final String gotoPage = "�̸����弼��";
	private static final String command = "/order.mall" ;	 // main.jsp���� �Ѿ��
	
	@Autowired
	@Qualifier("myCompositeDao")
	private CompositeDao compositeDao;	
	
	// main���� ���� �ֹ� ���� Ŭ���ϸ� �Ѿ�´�.
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session, Model model ){

		Member loginfo = (Member)session.getAttribute("loginfo") ;
		if( loginfo == null ){ //no
			session.setAttribute("destination", "redirect:/order.mall"  );
			return "redirect:/LoginForm.me" ;			
		/*}else{ 
			System.out.println("ȸ���� ���� �ֹ� �󼼸� ���� ����");
			
			List<Order> orderlists = new ArrayList<Order>() ;
			
			//lists : ���� �α��� �� ����� ���� �ֹ� �������� ��� �ִ� �÷���(�ֱ� �ֹ� ������ ���� ����)
			List<HashMap<String, Object>> lists = this.compositeDao.OrderMall( loginfo ) ;
			
			lists���� �Ʒ� ������ ����ִ�. 
			MID		OID		ORDERDATE
			hong	21		2016/6/15 08:36
			hong	20		2016/6/13  1:16
			hong	19		2016/6/07  12:12
			hong	18		2016/6/02  17:51
				:
				:
			
			
			// �Ʒ� for�� ���鼭 
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
			
			���� for�� ���鼭 List<HashMap<String, Object>> lists���� ������ ����ֳ� Ȯ���غ���.
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
			1���� ��Ÿ����.
			�׷��ϱ�,
			OID,9   MID,hong   ORDERDATE,2016/06/15 21:57
			OID,8   MID,hong   ORDERDATE,2016/06/13 21:28 �� ���� ���ٰ� ���� �ɵ���
				:
				:
			 	
				
			for(HashMap<String, Object> map : lists){
				//���� ���� �빮�ڷ� �����ȴ�.				
				String mid = (String)map.get("MID") ;
				BigDecimal oid = (BigDecimal)map.get("OID") ;
				//Integer oid = (Integer)map.get("OID") ; // ��
				String orderdate = (String)map.get("ORDERDATE") ;
				
				
				������ OID���� ������ ��
				����Ŭ NUMBER �� �÷��� ������("OID")�� HashMap Ÿ������ �޾�
				java���� ����Ϸ��� �ϸ�	java.math.BigDecimal cannot be cast to java.lang.String  ������ �߻��Ѵ�.
				������ ���� ����� ���� ���� BigDecimalŸ���� ����ȯ�� ��� �Ѵ�.
				�� �Ʒ��� �赵 �ȵǰ� �� ���� �ڵ带 ����.
				
				
				Order order = new Order() ;
				order.setMid(mid); 
				order.setOid( oid.intValue() );
				//order.setOid( oid); // ��
				order.setOrderdate(orderdate); 
				orderlists.add(order) ;
			}			
			model.addAttribute( "orderlists", orderlists );*/ 
			// orderlists���� ���� ���̺���(lists�� ����-hong ���̵�� �ֹ��� ��� ����)�� ����ִ�. 
			
			
			
			/*
			 * 
			 * ���� ���� ������� �ص� �ǰ� �� �� �����ϰ� �Ʒ��� ����� �ᵵ �ȴ�.
			 * 
			 * */
			
			
			
			
		}else{ 
			System.out.println("OrderMallController ȸ���� ���� �ֹ� �󼼸� ���� ����");
			
			//List<Order> orderlists = new ArrayList<Order>() ; // �� �� �ʿ����
			
			//lists : ���� �α��� �� ����� ���� �ֹ� �������� ��� �ִ� �÷���(�ֱ� �ֹ� ������ ���� ����)
			List<Order> lists = this.compositeDao.OrderMall2( loginfo ) ; // ���� ������ ����� �����
			//���� OrderMall2 �޼��带 OrderDao�� �ۼ��ص� �ȴ�.
			
			// for�� ���� syso ����غ���. 
			// �Ʒ� for�� �����ص� �ǰڴ�.
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
