package mall.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mall.cart.MyCartList;
import product.controller.ProductValidator;
import product.model.Product;
import product.model.ProductDao;

@Controller
public class CartAddController { // ProductDetailView.jsp���� �ֹ� Ŭ���ϸ� �Ѿ��

	//private static final String getPage = "�̸����弼��";
	private static final String gotoPage = "redirect:/list.mall";// list.mall => CartListController
	private static final String command = "/add.mall";
	// redirect~ �տ� /���� ��û ����� ó�� ���Ѵ�.

	@Autowired
	public ProductDao productDao = null;

	/*@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get ��� ����");
		return getPage;
	}*/

	
	///////////////////////////////////////////////////////////////
	// �Ʒ� GET����� ��� �Ѿ����?? �Ѿ���� ���� ���°� ������.. ������ ���� ������.. 
	// ������ �ǰڴ�.
	///////////////////////////////////////////////////////////////
	@RequestMapping(value=command, method=RequestMethod.GET )
	public String doActionGet( HttpSession session,
			@ModelAttribute("pro")  @Valid Product product,
			/*@RequestParam(value="num",required=true) int num,
			@RequestParam(value="orderqty",required=true) int orderqty,*/
			BindingResult result ){ 
		// ����� �Ѿ���� �����ʹ� num�� �ֹ������ε� 
		// �� �ΰ��� Product Ŀ�ǵ� ��ü�� ����� 
		// �Էµ��� �ʴ� ���� �־ ��ȿ�� �˻� ������ ���� ������? 
		// �׷��� �翬�� �� �Ʒ� result.hasErrors()�� true�ϱ� ��� detail.prd�� ��û�� ���̰�..
		// �׷���  ��ȿ�� �˻� ������ ���� �ʰ� ��� ���� �� �ǳ�?? @Valid�� ��� �׷���?

		System.out.println("CartAddController GET");
		//System.out.println( pmkey + " / "  + qty ); 
		System.out.println(product.getNum());
		System.out.println(product.getOrderqty());
		int pnum = product.getNum();
		int oqty = product.getOrderqty();


		if( session.getAttribute("loginfo") == null ){ //no
			System.out.println("loginfo null");
			//session.setAttribute("destination", "redirect:/add.mall?num=" + num + "&orderqty=" + orderqty );
			session.setAttribute("destination","redirect:/list.prd");
			// pmkey:��ǰ��ȣ

			return "redirect:/LoginForm.me"; // id, pw �Է� ������ �Ѿ��. 			
		}else{ //yes
			MyCartList mycart = (MyCartList)session.getAttribute("mycart") ;
			System.out.println("mycart : "+mycart); // ó���� �α��� ���ڸ��� mycart�� null��
			/*mycart�� ó������ null�̰�
			null�� �� �ѹ� ��ü�� ����� 
			�� �������� ���� mycart�� ��� ����Ѵ�.

			MyCartList�� �Ʒ��� ��������� ���´�.
			private Map<Integer, Integer> orderlists = null ;
				//orderlists�� �����ǰ, � �Ǹ� �� ������ ������ �ִ�.

			// JSP���� ��ٱ��ϸ� Vector�� ��� �������?? => �Ʒ�ó�� ����
			// Vector<ProductBean> clist

			 */
			if (mycart == null) {
				mycart = new MyCartList() ; 
			}

			///////// �߰�1  /////////
			/*
			Product bean = productDao.GetData(pmkey);
			int getStock = bean.getStock();
			if(getStock<qty){
				System.out.println("������ ����");
				return "ProductDetailView";
				return "redirect:/list.prd";
			}
			 */

			/////////////�߰� 2 ////////			

			System.out.println("new ProductValidator()");
			ProductValidator validator = new ProductValidator(); // ������ ��ŭ�� �ֹ��ϱ� �� �ȵ���?? 
			validator.setDao(productDao);
			/*System.out.println(product.getNum());
			System.out.println(product.getOrderqty());*/
			validator.validate(product,result); 

 
			String page = "";
			if(result.hasErrors()){
				System.out.println("result.hasErrors() true");
				page = "detail.prd?pmkey="+product.getNum();
				//mav.setViewName(page);
				return page;
			}
			/////////////////
			// ���� �߰� 2 �κ��� ��� ������ �ȴ�. 
			// �׷��� ���� �߰� 2�� �־��µ��� ������ üũ�� �ȵǴ� ������ ����? 


			mycart.AddOrder(pnum, oqty); // Map<Integer, Integer> orderlists�� put�ϴ� ����� �����ִ�. 
			// mycart���� ��ǰ��ȣ�� ������ ����.

			session.setAttribute("mycart", mycart ); // mycart���� �ֹ� ���������� ����ִ�.(hong�� �̹� ȸ���� cart�� ���� ����)
			//return "redirect:/list.prd";
			return gotoPage; // CartListController.java�� �Ѿ
			// �ֹ��������� �ֹ� ��ư Ŭ���� ������ list.mall�� ��û�Ѵ�.			
		}
	}

	// ProductDetailView.jsp ���� �ֹ� ��ư Ŭ���ϸ� �Ѿ�� 
	@RequestMapping(value=command, method=RequestMethod.POST  )
	public String doActionPost( HttpSession session,
			/*@ModelAttribute("product")*/  Product product,HttpServletRequest request, HttpServletResponse response
			/*@RequestParam(value="pmkey",required=true) int pmkey,
			@RequestParam(value="qty",required=true) int qty,*/
			/*BindingResult result*/) throws IOException{ //BindingResult result �� �ٵ� �ʿ���� �ʳ�??
		/*
		������ @Valid���ϱ� ��Ϻ���� ��� �ȳѾ��. 
		���� �߻��Ѱɷ� �Ǽ� hasErrors()�� ��� �ɸ���.
		@Valid�� bean�� ��ȿ�� üũ �ڵ�� �����ִµ���
		���� @ModelAttribute("product")�� ��� �ȴ�. 
		�ֹ� ���������� �ֹ������� ��ȿ�� üũ �����Ÿ� @ModelAttribute("product") @Valid �� �־�� �Ѵ�. 
		 */

		System.out.println("CartAddController POST");
		//System.out.println( pmkey + " / "  + qty ); 
		System.out.println("product.getNum():"+product.getNum());
		System.out.println("product.getOrderqty():"+product.getOrderqty());

		int pnum = product.getNum();
		int oqty = product.getOrderqty();

		//ModelAndView mav = new ModelAndView();

		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/detail.prd?pmkey="+pnum);
			// pmkey:��ǰ��ȣ

			return "redirect:/LoginForm.me";	// �Ʒ���ó�� modelandview�� �����ص�,  ����ó�� String�� �����ص� �ȵǳ�.. �� �Ʒ� ��� return���� ����
			// LoginForm.me => MemberLoginController

			//mav.setViewName("redirect:/LoginForm.me");
			//return mav;

		}else{ //yes
			MyCartList mycart = (MyCartList)session.getAttribute("mycart") ; // �� �Ʒ����� mycart session �����Ѵ�. ��ٱ��Ͽ� ��� ����..
			System.out.println("mycart : "+mycart); // ó���� �α��� ���ڸ��� mycart�� null��
			/*mycart�� ó������ null�̰�
			null�� �� �ѹ� ��ü�� ����� 
			�� �������� ���� mycart�� ��� ����Ѵ�.

			MyCartList�� �Ʒ��� ��������� ���´�.
			private Map<Integer, Integer> orderlists = null ;
				//orderlists�� �����ǰ, � �Ǹ� �� ������ ������ �ִ�.
			 */
			if (mycart == null) {
				mycart = new MyCartList() ; //īƮ(��ٱ���) Ŭ����(��ǰ��ȣ, �ֹ������� Map ����)
			}

			System.out.println("mycart2 : "+mycart); // mall.cart.MyCartList@7c7e2029
			System.out.println( this.getClass() );

			///////// �߰�1  /////////
			/*
			Product bean = productDao.GetData(product.getNum());
			int getStock = bean.getStock();
			if(getStock<product.getOrderqty()){
				System.out.println("������ ����");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter writer = response.getWriter(); // IOException ����ó�� �ʿ�
				writer.println("<script type='text/javascript'>");
				writer.println("alert('������ ����');"); // �� �ѱ۷� �ȳ��ñ�..
				writer.println("history.back();"); // �� ���� ������ alert �߰� �ٽ� ��ǰ �󼼺��� form���� �Ȱ���. 
				writer.println("</script>");
				
				writer.flush();
				
				try {
					writer.flush();
				}catch(IllegalStateException e) {
					
				}
				
				//return "ProductDetailView";
				return "redirect:detail.prd?pmkey="+product.getNum();
				//return "redirect:/list.prd";
			}
			 */
			// ������  �����߻��� => java.lang.IllegalStateException: Cannot call sendRedirect() after the response has been committed
			// ��� �ذ��ؾ��ұ�?


			/*
			������ ���� üũ�� ���� �߰�1�� �ص� �ǰ�, �Ʒ��� �߰�2�� �ص��Ǵµ�..
			�߰�1�� ���� ������ alert�� �ѱ��� �ȳ��´�. �� �׷���...=> response.setContentType�� ������ �ߵȴ�. 
			�׷��� �ܺκ��������� ���� �� alert�� �ȶ��.
			 */
			/////////////�߰� 2 ////////			

			/*
			ProductValidator validator = new ProductValidator();
			validator.setDao(productDao);
			System.out.println(product.getNum());
			System.out.println(product.getOrderqty());
			validator.validate(product,result); 
			String page = "";

			if(result.hasErrors()){
				System.out.println("error");
				page = "redirect:/detail.prd?pmkey="+pnum;
				return page;
				//mav.setViewName("redirect:/detail.prd?pmkey="+pnum);
				//return mav;
			}*/
			/////////////////
			mycart.AddOrder(pnum, oqty); // Map<Integer, Integer> orderlists�� put�ϴ� ����� �����ִ�. 
			// mycart���� ��ǰ��ȣ�� ������ ����.

			session.setAttribute("mycart", mycart ); // mycart���� �ֹ� ���������� ����ִ�.(hong�� �̹� ȸ���� cart�� ���� ����)
			//return "redirect:/list.prd";
			return gotoPage; // CartListController.java�� �Ѿ
			//mav.setViewName("redirect:/list.mall"); 
			//return mav;
			// �ֹ��������� �ֹ� ��ư Ŭ���� ������ list.mall�� ��û�Ѵ�.		



		}
	}

}

