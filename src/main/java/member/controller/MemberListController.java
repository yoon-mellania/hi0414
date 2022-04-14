package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import member.model.Member;
import member.model.MemberDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import utility.Paging;

@Controller
public class MemberListController {
	private static final String getPage = "MemberList";
	//private static final String gotoPage = "ProductList";
	private static final String command = "/list.me";

	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;
 
	@RequestMapping(value = command )//GET 방식과 POST 방식 둘 다 처리하기
	public ModelAndView doActionPost(
			@RequestParam(value = "whatColumn", required = false ) String whatColumn,
			@RequestParam(value = "keyword", required = false ) String keyword,
			@RequestParam(value = "pageNumber", required = false ) String pageNumber,
			@RequestParam(value = "pageSize", required = false ) String pageSize,
			@RequestParam(value = "flag", required = false ) boolean flag, // String flag도 가능??
			@RequestParam(value = "match", required = false ) boolean match, // 넘어오는게 없으면 boolean기본값 false나온다. 
			HttpServletRequest request) {
		
		System.out.println("request.getMethod():"+request.getMethod()); // GET 
		System.out.println("검색할 컬럼(whatColumn) : " + whatColumn + ", ");
		System.out.println("검색할 단어(keyword) : " + keyword + ", ");
		System.out.println("pageNumber : " + pageNumber + ", ");
		System.out.println("pageSize : " + pageSize + ", ");
		System.out.println("flag : " + flag + ", "); // 넘어오는게 없으면 boolean기본값 false나온다. 
		System.out.println("match : " + match + ", ");
		
		//System.out.print( 3 / 0 );

		Map<String, String> map = new HashMap<String, String>() ;	
		
		if( whatColumn != null && whatColumn.equals("all")){
			whatColumn = null ; 
		}
		map.put("whatColumn", whatColumn ) ;
		map.put("keyword", "%" + keyword + "%" ) ;
		
		int totalCount = memberDao.GetTotalCount( map );
		System.out.print("전체 행수(totalCount) : " + totalCount + ", ");
		String url = request.getContextPath() + "/" + this.command ;
		
		ModelAndView mav = new ModelAndView();
		
		Paging pageInfo 
			= new Paging( pageNumber, pageSize, totalCount, url, whatColumn, keyword, null);
		
		System.out.print( "offset : " + pageInfo.getOffset() + ", " ) ; 
		System.out.print( "limit : " + pageInfo.getLimit() + ", " ) ;  
		
		List<Member> memberLists =memberDao.GetDataList( pageInfo, map );
		
		System.out.println("조회된 건수: " + memberLists.size());
		mav.addObject( "memberLists", memberLists );		
		mav.addObject( "pageInfo", pageInfo );
		/*if(flag == false && match==true){
			mav.addObject( "flag", false );
			mav.addObject( "match", true );
		}
		else{
			mav.addObject( "flag", true );
			mav.addObject( "match", false );
		}*/
		mav.addObject( "flag", flag );
		mav.addObject( "match", match );
		
		mav.setViewName(getPage); // "MemberList"
		return mav;
	}
	
	/*@ExceptionHandler(value = Exception.class)
	public ModelAndView error(Exception ex) {
		return MyUtility.getMav(ex, this.getClass().toString());
	}	*/
}
