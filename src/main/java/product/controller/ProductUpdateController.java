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
public class ProductUpdateController implements ServletContextAware { // ServletContextAware ��Ӿȹް�, �� �ؿ� �޼��� �������̵� ���ϰ� ServletContext�� @Autowired�ص� �ȴ�. 
	private static final String getPage = "ProductUpdateForm";
	private static final String gotoPage =  "redirect:/list.prd";
	private static final String command = "/update.prd";

	//@Autowired
	private ServletContext servletContext;

	@Autowired
	@Qualifier("myProductDao")
	private ProductDao productDao;


	// ProductList.jsp�� goUpdate���� GET���� �Ѿ��
	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet( HttpSession session,
			@RequestParam(value="pmkey",required=true) int pmkey ,
			Model model){

		System.out.println("Get ��� ����");

		if( session.getAttribute("loginfo") == null ){ //no
			session.setAttribute("destination", "redirect:/update.prd?pmkey="+pmkey );
			return "redirect:/LoginForm.me";	 // get ��û		
		}else{ //yes

			Product product =  productDao.GetData( pmkey );
			model.addAttribute("product" , product);
			return getPage; // "ProductUpdateForm";
		}
	}



	@RequestMapping(value=command , method=RequestMethod.POST)
	public ModelAndView doActionPost(@ModelAttribute("product") @Valid Product product,
			BindingResult bindingResult/* , MultipartRequest mul */){		

		System.out.println("ProductUpdateController post ��� ����");

		ModelAndView mav = new ModelAndView();
//		product.setName("Ǫ������");
		System.out.println("product.getUpload2():"+product.getUpload2());
		System.out.println("product.getUpload():"+product.getUpload());
		
		// ���� form���� �� �̹��� �������� ������ �ٽ� ���� form���� ���ư��µ�..
		// �̶� ���� �̹����� ���� form�� �ٽ� ��Ÿ���� ���ؼ��� �Ʒ��� �ڵ带 �����ؾ� �Ѵ�. 
		
		// ���� form���� ���ο� ȭ���� �������� �ʾƵ� setUpload()�� ȣ��Ǹ鼭 
		// MultipartFile��ü�� ��������Ƿ� �Ʒ�ó�� null�� �������� ���ϸ� �ȵȴ�.
		/*
		if(product.getUpload() == null) {
			product.setImage(product.getUpload2());
		}
		*/
		System.out.println("product.getImage()����:"+product.getImage());

		// ���� form���� ���ο� ȭ���� �������� �ʾƵ� 
		// setUpload()���� this.image�� ""�� �Ѿ���� 
		// �Ʒ��ٿ��� null�� ������ ���� ""���� ���ؾ� �Ѵ�. 
		// Product.java�� setUpload()���� image�� ��¹� Ȯ���ϱ�
		if(product.getImage().equals("")) { // ���� if�� ������ ����form���� �����Ȱ� �־ �ٽ� ����form���� �� �� �ٸ� �׸��� �� �����µ� �̹����� �ȳ��´�. ������ �� �־�� �Ѵ�. 
			product.setImage(product.getUpload2());
		}
		System.out.println("product.getImage()����:"+product.getImage());
		
		if (bindingResult.hasErrors()) {
			System.out.println("��ȿ�� �˻� �����Դϴ�");
			mav.setViewName(getPage); // ProductUpdateForm.jsp 
			// => �Ʒ�ó�� ���� �ʰ� ���ٸ� �ᵵ ��ȣ�� �Ѿ��? ProductUpdateForm.jsp�� ��� ��ȣ�� ���̳�? �� �׷���?
			// => @ModelAttribute�� product�� �ؼ� �׷��� ����..
			
			// "redirect:/update.prd?pmkey="+pmkey
			//mav.setViewName("redirect:/update.prd?pmkey="+product.getNum());
			return mav;
		} 

		MultipartFile multi = product.getUpload();
		System.out.println("multi.getName():"+multi.getName());

		//MultipartRequest mulreq = new MultipartRequest(); // ���� 


		// �ϵ��ũ c����̺��� temp ������ �̹��� ���ε�
		/*String pathname = "c:\\temp\\";
		File target = new File(pathname);
		if (!target.exists()) {
			target.mkdir();
		}*/

		System.out.println("servletContext.getRealPath('/'):"+servletContext.getRealPath("/"));
		// C:\Spring_my\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\51_Spring_MyBatis_Products\

		String uploadDir=servletContext.getRealPath("/resources"); // /resources��� /abc�� �ȵǰ� /resources/abc�� �ȵȴ�. 
		System.out.println("PUCon uploadDir : "+uploadDir);

		// ������ ���̽��� �߰��ϴ� �ڵ�
		Integer cnt = -1;
		cnt = productDao.UpdateData(product);	
		if (cnt != -1) { // ����

			System.out.println("multi.getOriginalFilename():"+multi.getOriginalFilename());
			System.out.println("product.getUpload2():"+product.getUpload2());

			//File uploadFolder = new File( uploadDir + File.separator);
			File destination = new File( uploadDir + File.separator +multi.getOriginalFilename() );
			File destination2 = new File( uploadDir + File.separator +product.getUpload2() ); // ������ ȭ���� ����� ���� getUpload2()�� ���� ȭ�� ������ ���´�.
			//���� File��ü ���� �� �߰��� File.separator�� ������ �ȵȴ�. �̹��� �������´�. 

			destination2.delete(); // �ش� �������� ȭ�� �����
			//System.out.println("ProductUpdateController:"+destination);

			// System.out.println("multi.getOriginalFilename():"+multi.getOriginalFilename());
			// ȭ�� ���� ������ �� �Ʒ�ó�� equals("")�� ���ؾ� �Ѵ�. 
			// ==null�� ���ϸ� ȭ�� ���� ���ߴµ��� null�ƴ��� ���´�. 

			//if(multi.getOriginalFilename().equals("")){ //==null
				//System.out.println("null");
				//product.setUpload(product.getUpload2());
				/*try {
					multi.transferTo(destination2); // ���� ����(resources)�� �ִµ� ���پ��� �� �����Ϸ��� �ؼ� ���� ���� �޽����� ���. �׷��� �������� ������ �ϰڴٰ� ���� if���� ���.
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}*/

			//}else{
			//	System.out.println("null �ƴ�");
			//}


			try {
				//if(!uploadFolder.exists()){ // already exists and could not be deleted
					multi.transferTo(destination); // ���� ����(resources)�� �ִµ� ���پ��� �� �����Ϸ��� �ؼ� ���� ���� �޽����� ���. �׷��� �������� ������ �ϰڴٰ� ���� if���� ���.
				//}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			mav.setViewName(gotoPage); // "redirect:/list.prd";

		} else {// ����
			mav.setViewName(getPage); // "ProductUpdateForm";
		}
		return mav;

		/*Integer cnt = -1;
		System.out.println("����Ʈ���޼ҵ�");
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
