package mall.cart ;
import java.util.HashMap;
import java.util.Map;

//카트 클래스 : 주문자가 상품을 장바구니에 담으면 이를 컬렉션으로 저장하기 위한 클래스



// JSP에서 장바구니를 Vector로 어떻게 만들었지?? => 아래처럼 만듦
// Vector<ProductBean> clist




public class MyCartList {
	
	//key는 상품 번호, value는 판매된 수량
	private Map<Integer, Integer> orderlists = null ;
	//orderlists는 몇번상품, 몇개 판매 의 정보를 가지고 있다.
	//이번 회차에 hong이 물건 3개를 주문하다면 3개 주문건에 대한 정보가 Map orderlists에 담긴다. 
	
	public MyCartList() { //생성자에서 카트 컬렉션 준비,생성자 한번만 호출되고 orderlists가 관리하는 HashMap 객체 하나가 만들어진다. 
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
	 장바구니에서 상품 내용을 수정한다. 
	public void EditOrder(int pmkey, int stock){
		this.AddOrder(pmkey, stock); 
	}
	
	 장바구니에서 상품 삭제 
	public void DeleteOrder(int pmkey){
		orderlists.remove( pmkey ) ;
	}
	*/
	public void AddOrder(int pmkey, int oqty){ // 상품번호, 주문수량
		// 해당 상품 번호에 대한 수량을 추가한다.
		if ( orderlists.containsKey( pmkey ) == false ) { //없으면 추가
		//if ( orderlists.get(pmkey)) { // 이 줄은 에러남, get()은 값을 가져오므로..
			orderlists.put(pmkey, oqty ) ; // orderlists가 관리하는 HashMap객체에 계속 put한다. 
		}else{ // 있으면 누적하기
			orderlists.put(pmkey, orderlists.get(pmkey) +  oqty ) ;
			// pmkey번호 상품의 판매수량을 가져와서 +stock을 한후 다시 map에 넣는다. 
		}		
	}
	
	@Override
	public String toString() {
		return "MyCartList [orderlists=" + orderlists + "]";
	}
}

