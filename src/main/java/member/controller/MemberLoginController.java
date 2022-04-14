package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.Member;
import member.model.MemberDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberLoginController {
	// private static final String gotoPage = "이름만드세여";
	private static final String getPage= "MemberLoginForm"; // id,pw 입력폼
	private static final String command = "/LoginForm.me"; 

	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;	

	// ProductInsertController.java에서 호출됨
	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get 방식 들어옴");
		return getPage; //MemberLoginForm.jsp
	}

	// MemberLoginForm.jsp에서 로그인 버튼 클릭할 때 호출됨 
	@RequestMapping(value=command , method=RequestMethod.POST) 
	public ModelAndView doActionPost(
			Member member, 
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException{

		System.out.println(this.getClass() + " POST 방식 들어옴");
		System.out.println("아이디 : " + member.getId() + ", ");
		System.out.println("비번 : " + member.getPassword() + ", ");

		ModelAndView mav = new ModelAndView();

		//데이터 베이스에 들어 있는 데이터를 이용하여 회원이 존재하는가?를 체크
		Member login =  this.memberDao.GetData( member.getId() ) ;
		// login에는 DB에서 가져온 정보가 있고, member에는 form에서 입력한 정보가 있다.

		System.out.println("login:"+login); 
		// 없는 id면 윗줄 출력 null이 나와서 아래 2줄 출력할 수 없어서 에러가 뜨고,
		// 있는 id면  Member의 toString의 내용 Member [id=kim, name=김철수, password=1234,~~ 이런것이 뜬다.

		//System.out.println(login.getId()); // ①
		//System.out.println(login.getName()); // ②


		PrintWriter writer;
		response.setContentType("text/html;charset=UTF-8"); // 아래 alert에 한글 띄우려면 이 줄 필요함(내부에서는 필요없지만, 외부브라우저에서 띄울때는 필요함)
		// 윗줄 없을 때.. 한글이 나올때도 있고 깨져서 나올때도 있으니까 윗줄 넣는게 낫겠다.. 
		writer = response.getWriter(); // IOException 예외처리 필요

		if( login == null ){
			System.out.println("존재하지 않는 회원"); // 위의 ①이나 ②가 있으면 이줄 부터 아래까지 Dead code라고 노란색 경고 밑줄이 쭉 뜬다.

			// response.setCharacterEncoding("EUC-KR");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('해당 아이디가 존재하지 않습니다.2');");
			writer.println("history.back();"); // 이 줄이 없으면 회원가입 클릭하고 다시 뒤로 돌아가기 했을 때 alert()이 갑자기 뜨므로 이 줄 꼭 있어야 한다. 
			writer.println("</script>");
			writer.flush(); // 이 줄 없으면 alert이 뜨지 않는다. 
			//return;

			//return new ModelAndView( getPage ) ; // MemberLoginForm.jsp
			return new ModelAndView( "via.me" ) ;

		}else{
			// member에는 내가 입력한값이, login에는 DB에서 가져온 값이 들어간다. 
			System.out.println(member.getId());
			System.out.println(login.getId());
			System.out.println(member.getPassword()); 
			System.out.println(login.getPassword());

			if ( member.getId().equals( login.getId() ) && member.getPassword().equals( login.getPassword() )) {	
				// 아이디 검사는 위에서 이미 했으니까 비번 검사만 해도 될듯함

				session.setAttribute("loginfo", login); 
				System.out.println( "갈 곳 : " + session.getAttribute("destination") ); 
				//갈 곳 : redirect:/insert.prd(get방식임)
				//갈곳 destination은 ProductInsertController.java에서 session 설정함

				mav.setViewName( (String)session.getAttribute("destination") ); // get방식=>처음에 insert.prd요청이 get방식 요청이어서 여기서도 get방식인건가? 

			}else{

				System.out.println("비번이 잘못됨");
				writer = response.getWriter(); // IOException 예외처리 필요
				writer.println("<script type='text/javascript'>");
				writer.println("alert('비번이 잘못되었습니다.');");
				writer.println("history.back();"); // 이 줄이 없으면 회원가입 클릭하고 다시 뒤로 돌아가기 했을 때 alert()이 갑자기 뜨므로 이 줄 꼭 있어야 한다. 
				writer.println("</script>");
				writer.flush(); // 이 줄 없으면 alert이 뜨지 않는다. 

				return new ModelAndView( getPage ) ; // getPage=MemberLoginForm.jsp
			}
		}
		return mav;
	}
}


