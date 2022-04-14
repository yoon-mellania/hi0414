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
	//private static final String getPage = "�̸����弼��";
	//private static final String gotoPage = "�̸����弼��";
	private static final String command = "/detailview.mall";	 
	
	@Autowired
	@Qualifier("myCompositeDao")
	private CompositeDao compositeDao;		
	
	/*@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get ��� ����");
		return getPage;
	}*/
	
	// ShopList.jsp���� ������ �ֹ���ȣ �ϳ� ������ �Ѿ�´�.
	@RequestMapping(value=command )
	public String doActionPost( HttpSession session,
			@RequestParam(value="pmkey",required=true) int pmkey, Model model){
		// hong ���̵�� �ֹ��� ��� �������� ������ �ϳ��� �ֹ���ȣ�� �Ѿ�ͼ� pmkey�� ����.
		
		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/list.prd" ) ; 
			return "redirect:/LoginForm.me";	
			
		}else{
			
			/*
			ShoppingInfo
			private int pnum ; //��ǰ ��ȣ
			private String pname ; //��ǰ ��
			private int qty ; //�ֹ� ����
			private int price ; //�ܰ�
			private int amount ; //�ݾ�
			*/
			
			List<ShoppingInfo> lists = this.compositeDao.ShowDetail( pmkey ) ;
			System.out.println(lists.get(0).getPnum());
			System.out.println(lists.get(0).getPname());
			
			model.addAttribute( "pmkey", pmkey ); //���� ��ȣ(�ֹ���ȣ)			
			model.addAttribute( "shoplists", lists );
			return "ShopResult";
		}	
	}
}
