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

	@Autowired //ServletContext��ü�� ���⼭ @Autowired�� �̿��ؼ� �����ص� �ǰ�, �� �Ʒ� setServletContext()���� �����ص� �ȴ�. 
    ServletContext servletContext;  // ���� @Autowired�� �� �� ���� �̸��� ��� ����. ServletContext abc;�� �ص� �ȴ�. 
	//@Autowired�� ���� ���� implements~�� �Ƚᵵ �ȴ�. 
	
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public String doActionGet(	HttpSession session ) {
		System.out.println(this.getClass() + " Get ��� ����");
		System.out.println("loginfo : " + session.getAttribute("loginfo"));
		
		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/insert.prd" );
			return "redirect:/LoginForm.me";	 // get ��û=>MemberLoginController�� �̵� 	
			// ��� ��û�� �ϵ� redirect ��û�� �׻� get��û�ΰ� ����
			
		}else{ //yes
			return getPage;	 
		}		
	}

	@RequestMapping(value = command, method = RequestMethod.POST)
	public ModelAndView doActionPost(
			@ModelAttribute("product") @Valid Product product, 
			BindingResult bindingResult /*, HttpServletRequest request, ServletConfig sc*/) { // �Ű������� ServletContext servletContext�� ���� ��������. 
		System.out.println("ProductInsertController post");
		System.out.println("product.getName():" + product.getName());
		
		//��ü ����� setter ȣ���� �ڿ� ��ȿ�� �˻� ����.
		
		//System.out.println("����Ʈ�� : " + product.toString());  
		System.out.println(this.getClass() + " POST ��� ����");
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
		
		String uploadPath4=servletContext.getRealPath("/resources"); // �� ���� ���� �ٸ� ������ �����ص� �ǳ�? �ȵ�
		//�̹��� ������ resources�� �ƴ� temp�� �ϸ� ���ε�� �� �Ǵµ�..
		//�󼼺��⿡�� �̹����� ������ �� resources�� temp�� �ϸ� �̹��� �ȳ���
		
		//String uploadPath5=request.getContextPath()+"/resources";
		//���� uploadPath5 ��η� �ϸ� \ex\resources\���ƺ�1.jpg (������ ��θ� ã�� �� �����ϴ�) ���� ���.
		
		
		System.out.println("uploadPath4:"+uploadPath4);
		//uploadPath:C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\\temp
		//uploadPath1:C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\temp
		//uploadPath2:C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\\temp\
		//uploadPath3:C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\51_Spring_MyBatis_Products\temp\
		//uploadPath4:C:\Spring_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\51_Spring_MyBatis_Products\resources
		
		
		// resources ������ ȭ���� �ϳ��� ������ ������ ������ϱ� ����, ���� �۾��ϱ����� �ƹ��ų� �־����.
		// resources ������ ������� ȭ�� �߰��ص� ������ ���� ��������� �ʴ´�. 
		// ó���� ������ �����Ǳ� �����ϸ� resources ������ �ڵ����� ���������. 
		/*
		�Ʒ� �߰�
		ProductValidator validator = new ProductValidator();
		validator.setDao(productDao);
		validator.validate(product,bindingResult);
		/////������� �߰� /////
		*/
		
		System.out.println("product.getName():"+product.getName()); // prod-servlet.xml�� multipartResolver ���� ���س����� �� �κп��� null�� ���� �� �ִ�. 
		
		if (bindingResult.hasErrors()) {
			System.out.println("��ȿ�� �˻� �����Դϴ�");
			mav.setViewName(getPage); // ProductInsertForm.jsp
			return mav;
		} 

		MultipartFile multi = product.getUpload();
		// multi���� name�� upload�� ���� ������ ����.

		// �ϵ��ũ c����̺��� temp ������ �̹��� ���ε�
		/*
		String pathname = "c:\\temp\\";
		//File target = new File(pathname);
		File target = new File(uploadPath4);
		
		if (!target.exists()) {// ������ ������
			target.mkdir();// ���� ������ ����
		}
		*/
		System.out.println("product.getUpload():"+product.getUpload());
		// org.springframework.web.multipart.commons.CommonsMultipartFile@f1c3b56
		System.out.println("multi:"+multi); // product.getUpload()�� ���� �� ����. 
		// org.springframework.web.multipart.commons.CommonsMultipartFile@f1c3b56
		
		System.out.println("product.getImage():"+product.getImage());
		// womanpink.jpg
		
		// �����ͺ��̽��� �߰��ϴ� �ڵ�
		Integer cnt = -1;
		cnt = productDao.InsertData(product);
		
		if (cnt != -1) { // ����
			mav.setViewName(gotoPage); // redirect:/list.prd
			
			System.out.println("1:"+multi.getOriginalFilename()+"/"+multi.getName()); // 1:child2.jpg/upload
			//File destination = new File(pathname + multi.getOriginalFilename());
			File destination = new File(uploadPath4 + File.separator + multi.getOriginalFilename());
			// ���ٿ��� multi.getOriginalFilename()��� product.getImage()�� �ȴ�.
			
			/*
			���� ȭ���� �ö󰡴� ��ġ
			C:\Spring_STS_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp3\wtpwebapps\51_Spring_MyBatis_Products\resources
			*/
			
			try {
				multi.transferTo(destination); // transferTo:���ϴ� ��ġ�� ������ �� ���
				//File ��ü�� ���� �� MultipartFile ��ü�� transferTo �޼��带 ������� ���� �������� ������ �����.
				// ���ε� �� ���� �����͸� ������ ���Ͽ� �����Ѵ�.
				// �� ���� ������ �׸��� ������ �ʴ´�.
			
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {// ����
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

