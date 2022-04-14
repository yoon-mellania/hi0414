



//�̰ź��� DetailViewController.java�� ���ڴ�.




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
	//private static final String getPage = "�̸����弼��";
	//private static final String gotoPage = "�̸����弼��";
	private static final String command = "/detailview.mall";	 
	
	@Autowired
	@Qualifier("myCompositeDao")
	private CompositeDao compositeDao;		
	
	@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get ��� ����");
		return getPage;
	}
	
	// ShopList.jsp���� ������ �ֹ���ȣ �ϳ� ������ �Ѿ�´�.
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session,
			@RequestParam(value="pmkey",required=true) int pmkey, Model model){
		// hong ���̵�� �ֹ��� ��� �������� ������ �ϳ��� �ֹ���ȣ�� �Ѿ�ͼ� pmkey�� ����.
		
		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/list.prd" ) ; 
			return "redirect:/LoginForm.me";	
			
		}else{
			List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>() ;
			
			
			ShoppingInfo
			private int pnum ; //��ǰ ��ȣ
			private String pname ; //��ǰ ��
			private int qty ; //�ֹ� ����
			private int price ; //�ܰ�
			private int amount ; //�ݾ�
			
			
			List<HashMap<String, Object>> lists = this.compositeDao.ShowDetail( pmkey ) ;
			//ShowDetail()�� ���� selectList�� �ϸ� List�� ���ϵǹǷ� ����Ÿ���� List~�� �ؾ��Ѵ�.
			
			System.out.println("lists.size():"+lists.size());
			for(int i=0;i<lists.size();i++){
				HashMap<String, Object> hash = lists.get(i);
				System.out.println("hash:"+hash);
				//hash:{PNAME=ġŲ, PNUM=6, PRICE=5000, AMOUNT=10000, QTY=2}
				Set<String> set = hash.keySet();
				System.out.println("set:"+set);
				// set:[PNAME, PNUM, PRICE, AMOUNT, QTY]
				// �ֹ��� ������ ������ŭ �� set:~ ������ ��µȴ�.
				
				for(String s :set ){
					Object obj = hash.get(s);
					System.out.println(s+":"+obj);
				}
				System.out.println("--------------------");
			}
			
			�ֹ� �������� �ֹ���ȣ 1�� �󼼺��⸦ Ŭ������ ��
			���� ���� for���� ������ �Ʒ� ������ ��µȴ�.
			hash:{PNAME=ġŲ, PNUM=6, PRICE=5000, AMOUNT=10000, QTY=2}
			set:[PNAME, PNUM, PRICE, AMOUNT, QTY]
			PNAME:ġŲ
			PNUM:6
			PRICE:5000
			AMOUNT:10000
			QTY:2
			------------------------------
			hash:{PNAME=ȯŸ, PNUM=5, PRICE=5000, AMOUNT=15000, QTY=3}
			set:[PNAME, PNUM, PRICE, AMOUNT, QTY]
			PNAME:ȯŸ
			PNUM:5
			PRICE:5000
			AMOUNT:15000
			QTY:3
			
			�׷��ϱ�, 
			5���� �׸�(PNAME, PNUM, PRICE, AMOUNT, QTY)�� 
			HashMap���� ������ ArrayList�� ����ִ� ���̴�.
					
			
			for(HashMap<String, Object> map : lists){
				BigDecimal pnum = (BigDecimal)map.get("PNUM") ;
				String pname = (String)map.get("PNAME") ;				
				BigDecimal qty = (BigDecimal)map.get("QTY") ;
				BigDecimal price = (BigDecimal)map.get("PRICE") ;
				BigDecimal amount = (BigDecimal)map.get("AMOUNT") ;
				System.out.println( pname + "/" + qty + "/" + price + "/" + amount );
				
				���̴�/10/4000/40000
				�ݶ�/20/3000/60000
				ũ����/30/2000/60000
				
				
				
				������ PNUM,QTY,PRICE,AMOUNT���� ������ ��
				����Ŭ NUMBER �� �÷��� ������("PNUM") ���� HashMap Ÿ������ �޾�
				java���� ����Ϸ��� �ϸ�	java.math.BigDecimal cannot be cast to java.lang.String  ������ �߻��Ѵ�.
				������ Integer ����ȯ�� ����� ���� ���� BigDecimalŸ���� ����ȯ�� ��� �Ѵ�.
				
				ShoppingInfo shopinfo = new ShoppingInfo() ;
				shopinfo.setPnum( pnum.intValue() ); 
				shopinfo.setPname(pname); 
				shopinfo.setQty(qty.intValue());
				shopinfo.setPrice(price.intValue()); 
				shopinfo.setAmount(amount.intValue() ); // .intValue()�Ⱦ��� ������
				
				shoplists.add(shopinfo) ;
			}
			model.addAttribute( "pmkey", pmkey ); //���� ��ȣ(�ֹ���ȣ)			
			model.addAttribute( "shoplists", shoplists );
			return "ShopResult";
		}	
	}
}
*/