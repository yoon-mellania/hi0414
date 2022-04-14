package product.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import product.model.Product;
import product.model.ProductDao;



//import javax.xml.bind.Validator; //�̰� �ƴ�

public class ProductValidator implements Validator{
/*
	@Autowired*/ 
	/*���⼭ autowired�ص� ��ü�� �Ѿ���� �ʴ´�.
	@controller�� ��� �׷���???
	controller���� StudentDao��ü������ �����ְ� �� �Ʒ����� setter�� �Ѿ���� StudentDao��ü�� �ް��ִ�.*/ 	
	private ProductDao productDao;
	
	
	@Override
	public boolean supports(Class<?> arg0) { // ������ ��ü(Student)�� Ŭ���� Ÿ�� ����(Student��ü�� �����ϰڴٰ� ������)
		// TODO Auto-generated method stub
		return Product.class.isAssignableFrom(arg0);
	}

	@Override
	// Validator�� ��ӹ����� �ݵ�� �����ؾ� �ϴ� �޼���
	public void validate(Object obj, Errors errors) {
		// Errors�� �Ѿ���� BindingResult�� �θ���
		
		// TODO Auto-generated method stub
		
		System.out.println("validate()");
		Product product = (Product)obj;
		System.out.println("product.getNum():"+product.getNum());
		System.out.println("product.getOrderqty():"+product.getOrderqty());
		int stock = product.getStock();
		System.out.println("stock:"+stock);
		
		/*
		
		if(studentName == null || studentName.trim().isEmpty()) {
			System.out.println("studentName is null or empty");
			//errors.rejectValue("name", "trouble");
			errors.rejectValue("name", "name.required", null, "�Է����ּ���");
		}
		
		// ���� ��� �Ʒ����� �� �� �� ������ ���� ���� �ʴ´�. 
		 //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "trouble");
		
		String studentId = student.getId();
		System.out.println("studentId:"+studentId);
		System.out.println("studentDao~:"+studentDao);
		//studentDao.GetStudent(studentId);
		//Student sb = studentDao.GetStudent(studentId);
		if(sb==null){
			System.out.println("null");
		}
		else{
			System.out.println("null�ƴ�");
		}
		*/
		/*if(!isNumber(stock)) {
			System.out.println("studentId �����Էµ�");
			//errors.rejectValue("id", "trouble");
			errors.rejectValue("id", "id.required", null, "���� �Է��ϼ���");
		}
		else if(isNumber(studentId) && studentDao.GetStudent(studentId)==null){
			System.out.println("���� id�Դϴ�.");
			errors.rejectValue("id", "id.required", null, "��ϵ� ID�� �ƴմϴ�.");
		}*/
		int dbStock = productDao.GetStock(product);
		System.out.println("dbStock:"+dbStock);
		
		if(dbStock < product.getOrderqty()) {
			System.out.println("���������� �ֹ������� �� ����");
			//errors.rejectValue("id", "trouble");
			//errors.rejectValue("orderqty", "orderqty.required", null, "����� �ֹ��� �� ����");
			errors.rejectValue("orderqty", "orderqty.required", null, "�Է����ּ���");
		}
	} // validate
	
	// �Ʒ� isNumber�� �ʿ����
	/*public static boolean isNumber(String str){
        boolean result = false; 
         
        try{
            Integer.parseInt(str) ;
            result = true ;
        }catch(Exception e){}
          
        return result ;
    }
	 */
	
	public void setDao(ProductDao productDao) {
		System.out.println("setDao");
		System.out.println("productDao:"+productDao);
		this.productDao = productDao;
		
	}

}
