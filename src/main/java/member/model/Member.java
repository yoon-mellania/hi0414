package member.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

//Bean(빈) 클래스 : 데이터의 처리 1건을 나타내기 위한 자바 클래스
public class Member {
	//getter, setter, toString() 메소드까지 구현하세요.
	
	/*private int num;*/
	
	@NotEmpty(message = "아이디는 필수입니다.") /*primary key 체크하는 어노테이션은 없나?? 없는듯.. DB에 넣기 전에 빈으로 먼저 만드니까... */
	private String id ;
	
	@NotEmpty(message = "이름은 필수입니다.")
	private String name ;
	
	@NotEmpty(message = "비번은 필수입니다.")
	private String password ;
	
	private int salary ;
	private String hiredate ; //날짜지만 문자열로 처리
	
	@NotNull(message = "성별을 선택해 주세요.")
	private String gender ;
	
	@NotNull(message="취미는 1개 이상 선택해야 합니다.")
	/*private String[] hobby2 ;*/ /*배열로 안해도 아래 hobby에 공부,운동의 형식으로 쉼표가 자동으로 붙어서 들어간다.*/
	private String hobby ;
	
	private String job ;
	private String zipcode ;
	
	@NotEmpty(message = "주소1은 필수입니다.")
	private String address1 ;
	private String address2 ;
	private int mpoint ; //적립 포인트
	
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
		//return "윤아2";
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
