package mall.cart;

public class ShoppingInfo { // CartListController, DetailViewController에서 사용함
	private int pnum ; //상품 번호
	private String pname ; //상품 명
	private int qty ; //주문 수량
	private int price ; //단가
	private int amount ; //금액
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		System.out.println("setPname");
		this.pname = pname;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "ShoppingInfo [pnum=" + pnum + ", pname=" + pname + ", qty="
				+ qty + ", price=" + price + ", amount=" + amount + "]";
	}
	
	
}
