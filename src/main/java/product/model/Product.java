package product.model;

import javax.servlet.ServletContext;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.multipart.MultipartFile;

public class Product /*implements ServletConfigAware*/{
	// �Ʒ� 2�� �߰��ϸ� �� upload�� �ȵɱ�??
	//@Autowired
	//ServletContext sc ;
	
	private int num ;	
	
	@Length(min=3, max=10, message="��ǰ �̸��� �ּ� 3�ڸ� �ִ� 10�ڸ� �Դϴ�.")
	private String name ;
	
	private String company ;
	
	@NotEmpty( message="ȭ�� ���� ����") // @NotBlank�� ��, @NotNull�� �ȵ�
	private String image ;
	
	private int stock ; 
	
	@Min(value=3000, message="������ �ּ� 3000�� �̻� �̿��� �մϴ�")
	private int price ;
	
	private String category ;
	
	@Length(min=10, max=15, message="��ǰ�� ���� ������ �ּ� 10�ڸ� �ִ� 15�ڸ� �Դϴ�.")
	private String contents ;
	
	private int point ;
	private String inputdate ;
	
	//�Ʒ� ������̼��� �ֹ����� üũ�� �� �� ������ �ȵɱ�??? int���� �ȵǳ�??
	//@Min(value=1, message="�ֹ����� �ּ� 1�� �̻� �̿��� �մϴ�")
	//@NotNull(message="1�� �̻��Դϴ�.")
	private int orderqty; // �ֹ�����
	
	public int getOrderqty() {
		return orderqty;
	}
	public void setOrderqty(int orderqty) {
		this.orderqty = orderqty;
	}
	//@NotEmpty( message="ȭ�� ���� ����2") // �̰��� ���� ������
	private MultipartFile upload;	//��ü���� upload�� ���±��� name �Ӽ��� ��
	private String upload2; // ��ǰ ������ �� ����ȭ���� �̸��� upload2�� �ְ� ����ȭ���� ������, ���ο� ȭ���� ����Ѵ�.
	
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		System.out.println("setUpload upload:" + upload);//org.springframework.web.multipart.commons.CommonsMultipartFile@4175d46a
		// ���� form���� ���ο� ȭ���� �������� �ʾƵ� setUpload()�� ȣ��Ǹ鼭 MultipartFile��ü�� ���������.
		this.upload = upload;
		if( this.upload != null){ //���ε� �� �Ǿ��ٸ�
			System.out.println("upload.getName():"+upload.getName());//upload
			System.out.println("upload.getOriginalFilename():"+upload.getOriginalFilename());//Tulips.jpg(�Ȱ��� �̸��� ȭ���� �� �÷��� ��� Tulips.jpg�� ���´�.) 
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
			//���� form���� �� �̹����� �������� �ʾ��� ��.. �Ʒ� ��¹� 3�� Ȯ���ϱ�
			System.out.println(this.image == null); // false
			System.out.println(this.image != null); // true
			System.out.println(this.image.equals("")); // true
			
			//System.out.println(sc.getRealPath("/resources"));
		}
	}
	
	
	////  �߰�  ////
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

