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
public class CartAddController { // ProductDetailView.jsp에서 주문 클릭하면 넘어옴

	//private static final String getPage = "이름만드세여";
	private static final String gotoPage = "redirect:/list.mall";// list.mall => CartListController
	private static final String command = "/add.mall";
	// redirect~ 앞에 /쓰면 요청 제대로 처리 못한다.

	@Autowired
	public ProductDao productDao = null;

	/*@RequestMapping(value="/.prd" , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get 방식 들어옴");
		return getPage;
	}*/

	
	///////////////////////////////////////////////////////////////
	// 아래 GET방식은 어디서 넘어오지?? 넘어오는 곳이 없는거 같은데.. 지워도 되지 않을까.. 
	// 지워도 되겠다.
	///////////////////////////////////////////////////////////////
	@RequestMapping(value=command, method=RequestMethod.GET )
	public String doActionGet( HttpSession session,
			@ModelAttribute("pro")  @Valid Product product,
			/*@RequestParam(value="num",required=true) int num,
			@RequestParam(value="orderqty",required=true) int orderqty,*/
			BindingResult result ){ 
		// 여기로 넘어오는 데이터는 num과 주문수량인데 
		// 이 두개를 Product 커맨드 객체로 만들면 
		// 입력되지 않는 값도 있어서 유효성 검사 오류가 나지 않을까? 
		// 그러면 당연히 저 아래 result.hasErrors()도 true니까 계속 detail.prd를 요청할 것이고..
		// 그런데  유효성 검사 오류가 나지 않고 계속 실행 잘 되네?? @Valid가 없어서 그런가?

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
			// pmkey:상품번호

			return "redirect:/LoginForm.me"; // id, pw 입력 폼으로 넘어간다. 			
		}else{ //yes
			MyCartList mycart = (MyCartList)session.getAttribute("mycart") ;
			System.out.println("mycart : "+mycart); // 처음에 로그인 하자마자 mycart는 null됨
			/*mycart는 처음에만 null이고
			null일 때 한번 객체를 만들고 
			그 다음에는 같은 mycart를 계속 사용한다.

			MyCartList는 아래의 멤버변수를 갖는다.
			private Map<Integer, Integer> orderlists = null ;
				//orderlists는 몇번상품, 몇개 판매 의 정보를 가지고 있다.

			// JSP에서 장바구니를 Vector로 어떻게 만들었지?? => 아래처럼 만듦
			// Vector<ProductBean> clist

			 */
			if (mycart == null) {
				mycart = new MyCartList() ; 
			}

			///////// 추가1  /////////
			/*
			Product bean = productDao.GetData(pmkey);
			int getStock = bean.getStock();
			if(getStock<qty){
				System.out.println("재고수량 부족");
				return "ProductDetailView";
				return "redirect:/list.prd";
			}
			 */

			/////////////추가 2 ////////			

			System.out.println("new ProductValidator()");
			ProductValidator validator = new ProductValidator(); // 재고수량 만큼만 주문하기 왜 안되지?? 
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
			// 위의 추가 2 부분은 없어도 실행은 된다. 
			// 그런데 위의 추가 2를 넣었는데도 재고수량 체크가 안되는 이유는 뭘까? 


			mycart.AddOrder(pnum, oqty); // Map<Integer, Integer> orderlists에 put하는 기능을 갖고있다. 
			// mycart에는 상품번호와 수량만 담긴다.

			session.setAttribute("mycart", mycart ); // mycart에는 주문 내역정보가 담겨있다.(hong이 이번 회차에 cart에 담은 내용)
			//return "redirect:/list.prd";
			return gotoPage; // CartListController.java로 넘어감
			// 주문수량옆의 주문 버튼 클릭할 때마다 list.mall을 요청한다.			
		}
	}

	// ProductDetailView.jsp 에서 주문 버튼 클릭하면 넘어옴 
	@RequestMapping(value=command, method=RequestMethod.POST  )
	public String doActionPost( HttpSession session,
			/*@ModelAttribute("product")*/  Product product,HttpServletRequest request, HttpServletResponse response
			/*@RequestParam(value="pmkey",required=true) int pmkey,
			@RequestParam(value="qty",required=true) int qty,*/
			/*BindingResult result*/) throws IOException{ //BindingResult result 이 줄도 필요없지 않나??
		/*
		위에서 @Valid쓰니까 목록보기로 계속 안넘어간다. 
		에러 발생한걸로 되서 hasErrors()에 계속 걸린다.
		@Valid는 bean의 유효성 체크 코드와 관련있는듯함
		위의 @ModelAttribute("product")은 없어도 된다. 
		주문 페이지에서 주문수량에 유효성 체크 넣을거면 @ModelAttribute("product") @Valid 가 있어야 한다. 
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
			// pmkey:상품번호

			return "redirect:/LoginForm.me";	// 아랫줄처럼 modelandview를 리턴해도,  이줄처럼 String을 리턴해도 안되네.. 저 아래 모든 return문도 같음
			// LoginForm.me => MemberLoginController

			//mav.setViewName("redirect:/LoginForm.me");
			//return mav;

		}else{ //yes
			MyCartList mycart = (MyCartList)session.getAttribute("mycart") ; // 저 아래에서 mycart session 설정한다. 장바구니에 담고 나서..
			System.out.println("mycart : "+mycart); // 처음에 로그인 하자마자 mycart는 null됨
			/*mycart는 처음에만 null이고
			null일 때 한번 객체를 만들고 
			그 다음에는 같은 mycart를 계속 사용한다.

			MyCartList는 아래의 멤버변수를 갖는다.
			private Map<Integer, Integer> orderlists = null ;
				//orderlists는 몇번상품, 몇개 판매 의 정보를 가지고 있다.
			 */
			if (mycart == null) {
				mycart = new MyCartList() ; //카트(장바구니) 클래스(상품번호, 주문수량의 Map 형태)
			}

			System.out.println("mycart2 : "+mycart); // mall.cart.MyCartList@7c7e2029
			System.out.println( this.getClass() );

			///////// 추가1  /////////
			/*
			Product bean = productDao.GetData(product.getNum());
			int getStock = bean.getStock();
			if(getStock<product.getOrderqty()){
				System.out.println("재고수량 부족");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter writer = response.getWriter(); // IOException 예외처리 필요
				writer.println("<script type='text/javascript'>");
				writer.println("alert('재고수량 부족');"); // 왜 한글로 안나올까..
				writer.println("history.back();"); // 이 줄이 없으면 alert 뜨고 다시 상품 상세보기 form으로 안간다. 
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
			// 위에서  에러발생함 => java.lang.IllegalStateException: Cannot call sendRedirect() after the response has been committed
			// 어떻게 해결해야할까?


			/*
			재고수량 부족 체크는 위의 추가1로 해도 되고, 아래의 추가2로 해도되는데..
			추가1로 했을 때에는 alert에 한글이 안나온다. 왜 그럴까...=> response.setContentType을 넣으면 잘된다. 
			그런데 외부브라우저에서 했을 때 alert이 안뜬다.
			 */
			/////////////추가 2 ////////			

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
			mycart.AddOrder(pnum, oqty); // Map<Integer, Integer> orderlists에 put하는 기능을 갖고있다. 
			// mycart에는 상품번호와 수량만 담긴다.

			session.setAttribute("mycart", mycart ); // mycart에는 주문 내역정보가 담겨있다.(hong이 이번 회차에 cart에 담은 내용)
			//return "redirect:/list.prd";
			return gotoPage; // CartListController.java로 넘어감
			//mav.setViewName("redirect:/list.mall"); 
			//return mav;
			// 주문수량옆의 주문 버튼 클릭할 때마다 list.mall을 요청한다.		



		}
	}

}

