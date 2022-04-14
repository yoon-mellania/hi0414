package product.model;

import javax.servlet.ServletContext;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.multipart.MultipartFile;

public class Product /*implements ServletConfigAware*/{
	// 아래 2줄 추가하면 왜 upload가 안될까??
	//@Autowired
	//ServletContext sc ;
	
	private int num ;	
	
	@Length(min=3, max=10, message="상품 이름은 최소 3자리 최대 10자리 입니다.")
	private String name ;
	
	private String company ;
	
	@NotEmpty( message="화일 선택 안함") // @NotBlank도 됨, @NotNull은 안됨
	private String image ;
	
	private int stock ; 
	
	@Min(value=3000, message="가격은 최소 3000원 이상 이여야 합니다")
	private int price ;
	
	private String category ;
	
	@Length(min=10, max=15, message="상품에 대한 설명은 최소 10자리 최대 15자리 입니다.")
	private String contents ;
	
	private int point ;
	private String inputdate ;
	
	//아래 어노테이션은 주문수량 체크할 때 왜 적용이 안될까??? int여서 안되나??
	//@Min(value=1, message="주문수량 최소 1개 이상 이여야 합니다")
	//@NotNull(message="1개 이상입니다.")
	private int orderqty; // 주문수량
	
	public int getOrderqty() {
		return orderqty;
	}
	public void setOrderqty(int orderqty) {
		this.orderqty = orderqty;
	}
	//@NotEmpty( message="화일 선택 안함2") // 이곳에 쓰면 에러남
	private MultipartFile upload;	//객체변수 upload는 폼태그의 name 속성의 값
	private String upload2; // 상품 수정할 때 기존화일의 이름을 upload2에 넣고 기존화일을 지운후, 새로운 화일을 등록한다.
	
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		System.out.println("setUpload upload:" + upload);//org.springframework.web.multipart.commons.CommonsMultipartFile@4175d46a
		// 수정 form에서 새로운 화일을 선택하지 않아도 setUpload()가 호출되면서 MultipartFile객체가 만들어진다.
		this.upload = upload;
		if( this.upload != null){ //업로드 잘 되었다면
			System.out.println("upload.getName():"+upload.getName());//upload
			System.out.println("upload.getOriginalFilename():"+upload.getOriginalFilename());//Tulips.jpg(똑같은 이름의 화일을 또 올려도 계속 Tulips.jpg로 나온다.) 
			this.image = this.upload.getOriginalFilename();
			/*
			if(this.image == null){
				System.out.println("this.image null");
			}
			else{
				System.out.println("this.image not null");
				this.image = upload2;
			}
			*/
			System.out.println("this.image:"+this.image);
			//수정 form에서 새 이미지를 선택하지 않았을 때.. 아래 출력문 3개 확인하기
			System.out.println(this.image == null); // false
			System.out.println(this.image != null); // true
			System.out.println(this.image.equals("")); // true
			
			//System.out.println(sc.getRealPath("/resources"));
		}
	}
	
	
	////  추가  ////
	public String getUpload2() {
		return upload2;
	}
	public void setUpload2(String upload2) {
		System.out.println("setUpload2");
		this.upload2 = upload2;
		System.out.println("upload2:"+upload2);
	}
	///////////////
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("setName()");
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getImage() {
		System.out.println("getImage()");
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		System.out.println("setPrice(int price)");
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getInputdate() {
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	
	@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", company="
				+ company + ", image=" + image + ", stock=" + stock
				+ ", price=" + price + ", category=" + category + ", contents="
				+ contents + ", point=" + point + ", inputdate=" + inputdate
				+ "]";
	}	
}

