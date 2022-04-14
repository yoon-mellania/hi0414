package product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import product.model.Product;
import product.model.ProductDao;

@Controller
public class ProductUpdateController implements ServletContextAware { // ServletContextAware 상속안받고, 저 밑에 메서드 오버라이드 안하고 ServletContext를 @Autowired해도 된다. 
	private static final String getPage = "ProductUpdateForm";
	private static final String gotoPage =  "redirect:/list.prd";
	private static final String command = "/update.prd";

	//@Autowired
	private ServletContext servletContext;

	@Autowired
	@Qualifier("myProductDao")
	private ProductDao productDao;


	// ProductList.jsp의 goUpdate에서 GET으로 넘어옴
	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet( HttpSession session,
			@RequestParam(value="pmkey",required=true) int pmkey ,
			Model model){

		System.out.println("Get 방식 들어옴");

		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/update.prd?pmkey="+pmkey );
			return "redirect:/LoginForm.me";	 // get 요청		
		}else{ //yes

			Product product =  productDao.GetData( pmkey );
			model.addAttribute("product" , product);
			return getPage; // "ProductUpdateForm";
		}
	}



	@RequestMapping(value=command , method=RequestMethod.POST)
	public ModelAndView doActionPost(@ModelAttribute("product") @Valid Product product,
			BindingResult bindingResult/* , MultipartRequest mul */){		

		System.out.println("ProductUpdateController post 방식 들어옴");

		ModelAndView mav = new ModelAndView();
//		product.setName("푸하하하");
		System.out.println("product.getUpload2():"+product.getUpload2());
		System.out.println("product.getUpload():"+product.getUpload());
		
		// 수정 form에서 새 이미지 선택하지 않으면 다시 수정 form으로 돌아가는데..
		// 이때 이전 이미지가 수정 form에 다시 나타나기 위해서는 아래의 코드를 따라해야 한다. 
		
		// 수정 form에서 새로운 화일을 선택하지 않아도 setUpload()가 호출되면서 
		// MultipartFile객체가 만들어지므로 아래처럼 null과 같은지로 비교하면 안된다.
		/*
		if(product.getUpload() == null) {
			product.setImage(product.getUpload2());
		}
		*/
		System.out.println("product.getImage()이전:"+product.getImage());

		// 수정 form에서 새로운 화일을 선택하지 않아도 
		// setUpload()에서 this.image로 ""이 넘어감으로 
		// 아랫줄에서 null로 비교하지 말고 ""으로 비교해야 한다. 
		// Product.java의 setUpload()에서 image비교 출력문 확인하기
		if(product.getImage().equals("")) { // 여기 if가 없으면 수정form에서 누락된게 있어서 다시 수정form으로 갈 때 다른 항목은 다 나오는데 이미지가 안나온다. 이줄이 꼭 있어야 한다. 
			product.setImage(product.getUpload2());
		}
		System.out.println("product.getImage()이후:"+product.getImage());
		
		if (bindingResult.hasErrors()) {
			System.out.println("유효성 검사 오류입니다");
			mav.setViewName(getPage); // ProductUpdateForm.jsp 
			// => 아래처럼 하지 않고 윗줄만 써도 번호가 넘어가네? ProductUpdateForm.jsp에 계속 번호가 보이네? 왜 그러지?
			// => @ModelAttribute를 product로 해서 그런것 같다..
			
			// "redirect:/update.prd?pmkey="+pmkey
			//mav.setViewName("redirect:/update.prd?pmkey="+product.getNum());
			return mav;
		} 

		MultipartFile multi = product.getUpload();
		System.out.println("multi.getName():"+multi.getName());

		//MultipartRequest mulreq = new MultipartRequest(); // 에러 


		// 하드디스크 c드라이브의 temp 폴더에 이미지 업로드
		/*String pathname = "c:\\temp\\";
		File target = new File(pathname);
		if (!target.exists()) {
			target.mkdir();
		}*/

		System.out.println("servletContext.getRealPath('/'):"+servletContext.getRealPath("/"));
		// C:\Spring_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\

		String uploadDir=servletContext.getRealPath("/resources"); // /resources대신 /abc도 안되고 /resources/abc도 안된다. 
		System.out.println("PUCon uploadDir : "+uploadDir);

		// 데이터 베이스에 추가하는 코드
		Integer cnt = -1;
		cnt = productDao.UpdateData(product);	
		if (cnt != -1) { // 성공

			System.out.println("multi.getOriginalFilename():"+multi.getOriginalFilename());
			System.out.println("product.getUpload2():"+product.getUpload2());

			//File uploadFolder = new File( uploadDir + File.separator);
			File destination = new File( uploadDir + File.separator +multi.getOriginalFilename() );
			File destination2 = new File( uploadDir + File.separator +product.getUpload2() ); // 기존의 화일을 지우기 위해 getUpload2()로 기존 화일 정보를 얻어온다.
			//위의 File객체 만들 때 중간에 File.separator가 없으면 안된다. 이미지 못가져온다. 

			destination2.delete(); // 해당 폴더에서 화일 지우기
			//System.out.println("ProductUpdateController:"+destination);

			// System.out.println("multi.getOriginalFilename():"+multi.getOriginalFilename());
			// 화일 선택 안했을 때 아래처럼 equals("")로 비교해야 한다. 
			// ==null로 비교하면 화일 선택 안했는데도 null아님이 나온다. 

			//if(multi.getOriginalFilename().equals("")){ //==null
				//System.out.println("null");
				//product.setUpload(product.getUpload2());
				/*try {
					multi.transferTo(destination2); // 기존 폴더(resources)가 있는데 이줄쓰면 또 생성하려고 해서 위의 에러 메시지가 뜬다. 그래서 존재하지 않으면 하겠다고 위의 if문을 썼다.
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}*/

			//}else{
			//	System.out.println("null 아님");
			//}


			try {
				//if(!uploadFolder.exists()){ // already exists and could not be deleted
					multi.transferTo(destination); // 기존 폴더(resources)가 있는데 이줄쓰면 또 생성하려고 해서 위의 에러 메시지가 뜬다. 그래서 존재하지 않으면 하겠다고 위의 if문을 썼다.
				//}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			mav.setViewName(gotoPage); // "redirect:/list.prd";

		} else {// 실패
			mav.setViewName(getPage); // "ProductUpdateForm";
		}
		return mav;

		/*Integer cnt = -1;
		System.out.println("투스트링메소드");
		System.out.println(product.toString());
		cnt = productDao.UpdateProduct(product);

		if (cnt != -1) {
			return new ModelAndView(gotoPage);

		} else {
			return new ModelAndView(getPage);
		}*/
	}

	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		servletContext=context;		
	}
}
