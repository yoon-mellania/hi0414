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
public class CartListController { // CartAddController���� �Ѿ��
	//private static final String getPage = "�̸����弼��";
	//private static final String gotoPage = "�̸����弼��";
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
		System.out.println(this.getClass() + " Get ��� ����");
		return getPage;
	}*/
	
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session,Model model){
		System.out.println("CartListController loginfo : " + session.getAttribute("loginfo"));
		/*Member [id=hong, name=ȫ�浿, password=1234, salary=100, 
				hiredate=2016-06-12 13:56:36.0, gender=����, hobby=�籸, job=�л�, 
				zipcode=123-456, address1=null, address2=null, mpoint=100]*/

		// MemberLoginController.java���� loginfo session ������		
		/*
		if( session.getAttribute("loginfo") == null ){ //no ������� ���� loginfo�� null�� ������ ���� ����
			System.out.println("CartListController session.getAttribute(loginfo) is null");
			//session.setAttribute("destination", "redirect:/list.prd" ) ; // �� �� ��� �ǰڴ�. 
			return "redirect:/LoginForm.me";	
			
		}else{ //yes
		*/	
			MyCartList mycart = (MyCartList)session.getAttribute("mycart") ;//CartAddController���� session ������ 
			// �Ʒ� if���������� ����
			/*if (mycart == null) {
				return "redirect:/list.prd" ; // ProductListController.java
			}*/	
			
			//maplists : ��ǰ ��ȣ�� �ֹ� ������ ���� ������ ��� �ִ� �� �÷��� 
			Map<Integer, Integer> maplists =  mycart.GetAllOrderLists() ;
			// mycart.GetAllOrderLists()�� return orderlists ;�� �Ѵ�.
			// hong�� �ֹ��� ��� ��ǰ�� ���ϵȴ�.
			// 3����ǰ , 7��
			// 5����ǰ, 2�� �̷������� ��� ���ϵȴ�.
			
			Set<Integer> keylist = maplists.keySet() ;
			System.out.println("keylist:"+keylist); // keylist:[20, 15] ��ǰ ��ȣ�� ����. keylist.toString()�� ����. 
			
			// keySet():HashMap�� Ű�� ��� �������� ���
			// �ٸ� ����� ������ keySet()�� �� ���� �ִ�.
			// HashMap���� Ű:��ǰ��ȣ, ��:�ֹ������� ��� �����Ƿ� keySet()�� �Ἥ Ű�� �ش��ϴ� ��ǰ��ȣ�鸸 �����´�.
			
			System.out.println("keylist.size():"+keylist.size());
			
			//ShoppingInfo�� �ֹ� ������ ǥ���� �� �ִ� Ŭ������ �Ʒ��� ����� ���´�.
//			int pnum ; //��ǰ ��ȣ
//			String pname ; //��ǰ ��
//			int qty ; //�ֹ� ����
//			int price ; //�ܰ�
//			int amount ; //�ݾ� 
			/*
			 * ShoppingInfo�� �� ������ ����.. 
			 * ������ ProductBean�� �״�� ����ص� ������ �� ����. orderqty�� �ֹ������� �ְ�..
			 */
			
			List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>() ;
//			�ֹ� ��ư Ŭ���� ������ �� ȭ�Ϸ� �Ѿ�ͼ� ArrayList��ü�� �����.
//			1�� �ֹ��ϸ�, 2�� �ֹ��ϸ�, 3�� �ֹ��ϸ� �Ź� �ֹ��� ������ ArrayList��ü�� �����.
			
			int totalAmount = 0 ; //�� �ݾ�
			for( Integer pnum : keylist){
				Integer qty = maplists.get(pnum) ;
				System.out.println("pnum:"+pnum+", qty:"+qty); // pnum=��ǰ ��ȣ, qty=���ż���
				//System.out.println( this.getClass() + " " + pnum + " / " + qty );
				//�ش� ��ǰ�� ���� bean ������ ������ ���� ����
				Product bean = this.productDao.GetData( pnum ) ;
				
				//���� ������ ��´�.
//				�ֹ� ��ư Ŭ���� ������ �� ȭ�Ϸ� �Ѿ�ͼ� ShoppingInfo��ü�� �����.
//				1�� �ֹ��ϸ�, 2�� �ֹ��ϸ�, 3�� �ֹ��ϸ� �Ź� �ֹ��� ������ ShoppingInfo��ü�� �����.
// 				ShoppingInfo�� �ֹ� �������� ����� ���� Ŭ����
				
				ShoppingInfo shopInfo = new ShoppingInfo() ;
//				ShoppingInfo Ŭ������ ���������
//				private int pnum ; //��ǰ ��ȣ
//				private String pname ; //��ǰ ��
//				private int qty ; //�ֹ� ����
//				private int price ; //�ܰ�
//				private int amount ; //�ݾ�
				
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
			mycart���� ��ǰ��ȣ�� ������ ��� �ִµ� 
			mycart�� ��ǰ��ȣ�� �̿��ؼ� products ���̺��� ��ǰ������ �����ͼ�
			���Ӱ� ShoppingInfo(pnum,pname,qty,price,amount) ��ü�� �����.
			*/	
			
			// �Ʒ� 2��  ��� model ��밡��
			// model.addAttribute �� �ߴµ� MallList.jsp���� sessionScope.shoplists�� ���� �ȵȴ�. sessionScope.�� ������ �Ѵ�. 
			session.setAttribute( "shoplists" , shoplists );
			//shoplists:�ֹ��� ��ǰ��ȣ�� ����, ��ǰ��, ���� ���� ������� Ŭ����
			session.setAttribute( "totalAmount" , totalAmount );
			//model.addAttribute("totalAmount" , totalAmount+11);
			
			return getPage; //"MallList";
		/*}	*/
	}
}


