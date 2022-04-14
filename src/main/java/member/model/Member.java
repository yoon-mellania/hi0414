package member.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

//Bean(��) Ŭ���� : �������� ó�� 1���� ��Ÿ���� ���� �ڹ� Ŭ����
public class Member {
	//getter, setter, toString() �޼ҵ���� �����ϼ���.
	
	/*private int num;*/
	
	@NotEmpty(message = "���̵�� �ʼ��Դϴ�.") /*primary key üũ�ϴ� ������̼��� ����?? ���µ�.. DB�� �ֱ� ���� ������ ���� ����ϱ�... */
	private String id ;
	
	@NotEmpty(message = "�̸��� �ʼ��Դϴ�.")
	private String name ;
	
	@NotEmpty(message = "����� �ʼ��Դϴ�.")
	private String password ;
	
	private int salary ;
	private String hiredate ; //��¥���� ���ڿ��� ó��
	
	@NotNull(message = "������ ������ �ּ���.")
	private String gender ;
	
	@NotNull(message="��̴� 1�� �̻� �����ؾ� �մϴ�.")
	/*private String[] hobby2 ;*/ /*�迭�� ���ص� �Ʒ� hobby�� ����,��� �������� ��ǥ�� �ڵ����� �پ ����.*/
	private String hobby ;
	
	private String job ;
	private String zipcode ;
	
	@NotEmpty(message = "�ּ�1�� �ʼ��Դϴ�.")
	private String address1 ;
	private String address2 ;
	private int mpoint ; //���� ����Ʈ
	
	/*public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}*/
	
	/*@Embeddable*/
	public String getId() {
		//return "park";
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
		//return "����2";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("setPassword() password:"+password);
		this.password = password;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	/*public String[] getHobby2() {
		return hobby2;
	}
	public void setHobby2(String[] hobby2) {
		this.hobby2 = hobby2;
	}*/
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		System.out.println("setHobby:"+hobby);
		this.hobby = hobby;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public int getMpoint() {
		return mpoint;
	}
	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
	/*
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password
				+ ", salary=" + salary + ", hiredate=" + hiredate + ", gender="
				+ gender + ", hobby2=" + Arrays.toString(hobby2) + ", hobby="
				+ hobby + ", job=" + job + ", zipcode=" + zipcode
				+ ", address1=" + address1 + ", address2=" + address2
				+ ", mpoint=" + mpoint + "]";
	}
	*/
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", salary=" + salary + ", hiredate="
				+ hiredate + ", gender=" + gender + ", hobby=" + hobby + ", job=" + job + ", zipcode=" + zipcode
				+ ", address1=" + address1 + ", address2=" + address2 + ", mpoint=" + mpoint + "]";
	}
	
	
}
