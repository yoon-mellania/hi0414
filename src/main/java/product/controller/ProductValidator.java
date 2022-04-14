package product.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import product.model.Product;
import product.model.ProductDao;



//import javax.xml.bind.Validator; //이거 아님

public class ProductValidator implements Validator{
/*
	@Autowired*/ 
	/*여기서 autowired해도 객체가 넘어오지 않는다.
	@controller가 없어서 그런가???
	controller에서 StudentDao객체정보를 보내주고 저 아래에서 setter로 넘어오는 StudentDao객체를 받고있다.*/ 	
	private ProductDao productDao;
	
	
	@Override
	public boolean supports(Class<?> arg0) { // 검증할 객체(Student)의 클래스 타입 정보(Student객체를 검증하겠다고 설정함)
		// TODO Auto-generated method stub
		return Product.class.isAssignableFrom(arg0);
	}

	@Override
	// Validator를 상속받으면 반드시 구현해야 하는 메서드
	public void validate(Object obj, Errors errors) {
		// Errors는 넘어오는 BindingResult의 부모임
		
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
			errors.rejectValue("name", "name.required", null, "입력해주세요");
		}
		
		// 윗줄 대신 아랫줄을 쓸 수 도 있지만 많이 쓰지 않는다. 
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
			System.out.println("null아님");
		}
		*/
		/*if(!isNumber(stock)) {
			System.out.println("studentId 문자입력됨");
			//errors.rejectValue("id", "trouble");
			errors.rejectValue("id", "id.required", null, "숫자 입력하세요");
		}
		else if(isNumber(studentId) && studentDao.GetStudent(studentId)==null){
			System.out.println("없는 id입니다.");
			errors.rejectValue("id", "id.required", null, "등록된 ID가 아닙니다.");
		}*/
		int dbStock = productDao.GetStock(product);
		System.out.println("dbStock:"+dbStock);
		
		if(dbStock < product.getOrderqty()) {
			System.out.println("재고수량보다 주문수량이 더 많음");
			//errors.rejectValue("id", "trouble");
			//errors.rejectValue("orderqty", "orderqty.required", null, "재고보다 주문이 더 많음");
			errors.rejectValue("orderqty", "orderqty.required", null, "입력해주세요");
		}
	} // validate
	
	// 아래 isNumber는 필요없음
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
