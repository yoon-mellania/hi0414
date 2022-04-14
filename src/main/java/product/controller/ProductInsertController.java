package product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import product.model.Product;
import product.model.ProductDao;

@Controller
public class ProductInsertController /*implements ServletContextAware*/{
	private static final String getPage = "ProductInsertForm";
	private static final String gotoPage = "redirect:/list.prd";
	private static final String command = "/insert.prd";

	@Autowired
	@Qualifier("myProductDao") 
	private ProductDao productDao;

	@Autowired //ServletContext객체를 여기서 @Autowired를 이용해서 주입해도 되고, 저 아래 setServletContext()에서 주입해도 된다. 
    ServletContext servletContext;  // 위의 @Autowired를 쓸 때 변수 이름은 상관 없다. ServletContext abc;로 해도 된다. 
	//@Autowired를 쓰면 위의 implements~는 안써도 된다. 
	
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public String doActionGet(	HttpSession session ) {
		System.out.println(this.getClass() + " Get 방식 들어옴");
		System.out.println("loginfo : " + session.getAttribute("loginfo"));
		
		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/insert.prd" );
			return "redirect:/LoginForm.me";	 // get 요청=>MemberLoginController로 이동 	
			// 어디서 요청을 하든 redirect 요청은 항상 get요청인것 같음
			
		}else{ //yes
			return getPage;	 
		}		
	}

	@RequestMapping(value = command, method = RequestMethod.POST)
	public ModelAndView doActionPost(
			@ModelAttribute("product") @Valid Product product, 
			BindingResult bindingResult /*, HttpServletRequest request, ServletConfig sc*/) { // 매개변수로 ServletContext servletContext를 쓰니 에러난다. 
		System.out.println("ProductInsertController post");
		System.out.println("product.getName():" + product.getName());
		
		//객체 만들고 setter 호출한 뒤에 유효성 검사 들어간다.
		
		//System.out.println("투스트링 : " + product.toString());  
		System.out.println(this.getClass() + " POST 방식 들어옴");
		//System.out.println();
		ModelAndView mav = new ModelAndView();
		/*
		ServletContext servletContext = sc.getServletContext();
		System.out.println("servletContext:"+servletContext);
		String scontext = servletContext.getContextPath();
		System.out.println("scontext:"+scontext); 
		*/
		
		//String uploadPath = context.getRealPath("") + File.separator + "temp";
		//String uploadPath1 = context.getRealPath("") + "temp";
		//String uploadPath2 = context.getRealPath("") + "\\temp\\";
		//String uploadPath3 = servletContext.getRealPath("") + "temp\\";
		
		System.out.println("servletContext:"+servletContext);
		// org.apache.catalina.core.ApplicationContextFacade@6a182669
		
		System.out.println("servletContext.getRealPath('/'):"+servletContext.getRealPath("/"));
		// C:\Spring_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\
		
		String uploadPath4=servletContext.getRealPath("/resources"); // 이 폴더 말고 다른 폴더로 지정해도 되나? 안됨
		//이미지 저장을 resources가 아닌 temp에 하면 업로드는 잘 되는데..
		//상세보기에서 이미지를 가져올 때 resources를 temp로 하면 이미지 안나옴
		
		//String uploadPath5=request.getContextPath()+"/resources";
		//윗줄 uploadPath5 경로로 하면 \ex\resources\유아복1.jpg (지정된 경로를 찾을 수 없습니다) 에러 뜬다.
		
		
		System.out.println("uploadPath4:"+uploadPath4);
		//uploadPath:C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\\temp
		//uploadPath1:C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\temp
		//uploadPath2:C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\\temp\
		//uploadPath3:C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\51_Spring_MyBatis_Products\temp\
		//uploadPath4:C:\Spring_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\51_Spring_MyBatis_Products\resources
		
		
		// resources 폴더에 화일이 하나도 없으면 폴더가 사라지니까 수정, 삭제 작업하기전에 아무거나 넣어놓자.
		// resources 폴더가 사라지면 화일 추가해도 폴더가 새로 만들어지지 않는다. 
		// 처음에 서버가 구동되기 시작하면 resources 폴더는 자동으로 만들어진다. 
		/*
		아래 추가
		ProductValidator validator = new ProductValidator();
		validator.setDao(productDao);
		validator.validate(product,bindingResult);
		/////여기까지 추가 /////
		*/
		
		System.out.println("product.getName():"+product.getName()); // prod-servlet.xml에 multipartResolver 설정 안해놓으면 이 부분에서 null이 나올 수 있다. 
		
		if (bindingResult.hasErrors()) {
			System.out.println("유효성 검사 오류입니다");
			mav.setViewName(getPage); // ProductInsertForm.jsp
			return mav;
		} 

		MultipartFile multi = product.getUpload();
		// multi에는 name이 upload인 곳의 정보가 들어간다.

		// 하드디스크 c드라이브의 temp 폴더에 이미지 업로드
		/*
		String pathname = "c:\\temp\\";
		//File target = new File(pathname);
		File target = new File(uploadPath4);
		
		if (!target.exists()) {// 폴더가 없으면
			target.mkdir();// 폴더 강제로 생성
		}
		*/
		System.out.println("product.getUpload():"+product.getUpload());
		// org.springframework.web.multipart.commons.CommonsMultipartFile@f1c3b56
		System.out.println("multi:"+multi); // product.getUpload()와 같은 값 들어간다. 
		// org.springframework.web.multipart.commons.CommonsMultipartFile@f1c3b56
		
		System.out.println("product.getImage():"+product.getImage());
		// womanpink.jpg
		
		// 데이터베이스에 추가하는 코드
		Integer cnt = -1;
		cnt = productDao.InsertData(product);
		
		if (cnt != -1) { // 성공
			mav.setViewName(gotoPage); // redirect:/list.prd
			
			System.out.println("1:"+multi.getOriginalFilename()+"/"+multi.getName()); // 1:child2.jpg/upload
			//File destination = new File(pathname + multi.getOriginalFilename());
			File destination = new File(uploadPath4 + File.separator + multi.getOriginalFilename());
			// 윗줄에서 multi.getOriginalFilename()대신 product.getImage()도 된다.
			
			/*
			실제 화일이 올라가는 위치
			C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp3\wtpwebapps\51_Spring_MyBatis_Products\resources
			*/
			
			try {
				multi.transferTo(destination); // transferTo:원하는 위치에 저장할 때 사용
				//File 객체를 만든 뒤 MultipartFile 객체의 transferTo 메서드를 실행시켜 실제 물리적인 파일을 만든다.
				// 업로드 한 파일 데이터를 지정한 파일에 저장한다.
				// 이 줄이 없으면 그림이 보이지 않는다.
			
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {// 실패
			mav.setViewName(getPage); //ProductInsertForm.java

		}
		return mav;
	}

	/*@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		servletContext=context;		
	}*/
}

