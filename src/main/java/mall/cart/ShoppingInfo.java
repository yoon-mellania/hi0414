package mall.cart;

public class ShoppingInfo { // CartListController, DetailViewController���� �����
	private int pnum ; //��ǰ ��ȣ
	private String pname ; //��ǰ ��
	private int qty ; //�ֹ� ����
	private int price ; //�ܰ�
	private int amount ; //�ݾ�
	
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
