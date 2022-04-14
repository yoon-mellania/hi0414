package mall.cart ;
import java.util.HashMap;
import java.util.Map;

//īƮ Ŭ���� : �ֹ��ڰ� ��ǰ�� ��ٱ��Ͽ� ������ �̸� �÷������� �����ϱ� ���� Ŭ����



// JSP���� ��ٱ��ϸ� Vector�� ��� �������?? => �Ʒ�ó�� ����
// Vector<ProductBean> clist




public class MyCartList {
	
	//key�� ��ǰ ��ȣ, value�� �Ǹŵ� ����
	private Map<Integer, Integer> orderlists = null ;
	//orderlists�� �����ǰ, � �Ǹ� �� ������ ������ �ִ�.
	//�̹� ȸ���� hong�� ���� 3���� �ֹ��ϴٸ� 3�� �ֹ��ǿ� ���� ������ Map orderlists�� ����. 
	
	public MyCartList() { //�����ڿ��� īƮ �÷��� �غ�,������ �ѹ��� ȣ��ǰ� orderlists�� �����ϴ� HashMap ��ü �ϳ��� ���������. 
		orderlists = new HashMap<Integer, Integer>(); 
	}
	/*
	public void RemoveAllProductInfo(){
		orderlists.clear(); 
	}
	*/
	public Map<Integer, Integer> GetAllOrderLists(){ 
		return orderlists ;
	}
	/*
	 ��ٱ��Ͽ��� ��ǰ ������ �����Ѵ�. 
	public void EditOrder(int pmkey, int stock){
		this.AddOrder(pmkey, stock); 
	}
	
	 ��ٱ��Ͽ��� ��ǰ ���� 
	public void DeleteOrder(int pmkey){
		orderlists.remove( pmkey ) ;
	}
	*/
	public void AddOrder(int pmkey, int oqty){ // ��ǰ��ȣ, �ֹ�����
		// �ش� ��ǰ ��ȣ�� ���� ������ �߰��Ѵ�.
		if ( orderlists.containsKey( pmkey ) == false ) { //������ �߰�
		//if ( orderlists.get(pmkey)) { // �� ���� ������, get()�� ���� �������Ƿ�..
			orderlists.put(pmkey, oqty ) ; // orderlists�� �����ϴ� HashMap��ü�� ��� put�Ѵ�. 
		}else{ // ������ �����ϱ�
			orderlists.put(pmkey, orderlists.get(pmkey) +  oqty ) ;
			// pmkey��ȣ ��ǰ�� �Ǹż����� �����ͼ� +stock�� ���� �ٽ� map�� �ִ´�. 
		}		
	}
	
	@Override
	public String toString() {
		return "MyCartList [orderlists=" + orderlists + "]";
	}
}

