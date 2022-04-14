package orderdetail.model;

public class OrderDetail {
	private int odid ; // sequence
	private int oid ; // 주문번호
	private int pnum ; // 상품 번호
	private int qty ; // 주문 수량
	//private String remark ; // 필요 없는듯
	
	public int getOdid() {
		return odid;
	}
	public void setOdid(int odid) {
		this.odid = odid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	/*public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "OrderDetail [odid=" + odid + ", oid=" + oid + ", pnum=" + pnum
				+ ", qty=" + qty + ", remark=" + remark + "]";
	}
	*/
	@Override
	public String toString() {
		return "OrderDetail [odid=" + odid + ", oid=" + oid + ", pnum=" + pnum + ", qty=" + qty + "]";
	}
	
}

