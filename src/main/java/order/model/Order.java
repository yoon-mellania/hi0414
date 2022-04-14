package order.model;

public class Order {
	private int oid ;
	private String mid ;
	private String orderdate ;
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getMid() {
		return mid;
		//return "hoho";
	}
	public void setMid(String mid) {
		this.mid = mid; 
		System.out.println("Order mid:"+this.mid);
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", mid=" + mid + ", orderdate="
				+ orderdate + "]";
	}
	
}