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
	// private static final String gotoPage = "�̸����弼��";
	private static final String getPage= "MemberLoginForm"; // id,pw �Է���
	private static final String command = "/LoginForm.me"; 

	@Autowired
	@Qualifier("myMemberDao")
	private MemberDao memberDao;	

	// ProductInsertController.java���� ȣ���
	@RequestMapping(value=command , method=RequestMethod.GET)
	public String doActionGet(){
		System.out.println(this.getClass() + " Get ��� ����");
		return getPage; //MemberLoginForm.jsp
	}

	// MemberLoginForm.jsp���� �α��� ��ư Ŭ���� �� ȣ��� 
	@RequestMapping(value=command , method=RequestMethod.POST) 
	public ModelAndView doActionPost(
			Member member, 
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException{

		System.out.println(this.getClass() + " POST ��� ����");
		System.out.println("���̵� : " + member.getId() + ", ");
		System.out.println("��� : " + member.getPassword() + ", ");

		ModelAndView mav = new ModelAndView();

		//������ ���̽��� ��� �ִ� �����͸� �̿��Ͽ� ȸ���� �����ϴ°�?�� üũ
		Member login =  this.memberDao.GetData( member.getId() ) ;
		// login���� DB���� ������ ������ �ְ�, member���� form���� �Է��� ������ �ִ�.

		System.out.println("login:"+login); 
		// ���� id�� ���� ��� null�� ���ͼ� �Ʒ� 2�� ����� �� ��� ������ �߰�,
		// �ִ� id��  Member�� toString�� ���� Member [id=kim, name=��ö��, password=1234,~~ �̷����� ���.

		//System.out.println(login.getId()); // ��
		//System.out.println(login.getName()); // ��


		PrintWriter writer;
		response.setContentType("text/html;charset=UTF-8"); // �Ʒ� alert�� �ѱ� ������ �� �� �ʿ���(���ο����� �ʿ������, �ܺκ��������� ��ﶧ�� �ʿ���)
		// ���� ���� ��.. �ѱ��� ���ö��� �ְ� ������ ���ö��� �����ϱ� ���� �ִ°� ���ڴ�.. 
		writer = response.getWriter(); // IOException ����ó�� �ʿ�

		if( login == null ){
			System.out.println("�������� �ʴ� ȸ��"); // ���� ���̳� �谡 ������ ���� ���� �Ʒ����� Dead code��� ����� ��� ������ �� ���.

			// response.setCharacterEncoding("EUC-KR");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('�ش� ���̵� �������� �ʽ��ϴ�.2');");
			writer.println("history.back();"); // �� ���� ������ ȸ������ Ŭ���ϰ� �ٽ� �ڷ� ���ư��� ���� �� alert()�� ���ڱ� �߹Ƿ� �� �� �� �־�� �Ѵ�. 
			writer.println("</script>");
			writer.flush(); // �� �� ������ alert�� ���� �ʴ´�. 
			//return;

			//return new ModelAndView( getPage ) ; // MemberLoginForm.jsp
			return new ModelAndView( "via.me" ) ;

		}else{
			// member���� ���� �Է��Ѱ���, login���� DB���� ������ ���� ����. 
			System.out.println(member.getId());
			System.out.println(login.getId());
			System.out.println(member.getPassword()); 
			System.out.println(login.getPassword());

			if ( member.getId().equals( login.getId() ) && member.getPassword().equals( login.getPassword() )) {	
				// ���̵� �˻�� ������ �̹� �����ϱ� ��� �˻縸 �ص� �ɵ���

				session.setAttribute("loginfo", login); 
				System.out.println( "�� �� : " + session.getAttribute("destination") ); 
				//�� �� : redirect:/insert.prd(get�����)
				//���� destination�� ProductInsertController.java���� session ������

				mav.setViewName( (String)session.getAttribute("destination") ); // get���=>ó���� insert.prd��û�� get��� ��û�̾ ���⼭�� get����ΰǰ�? 

			}else{

				System.out.println("����� �߸���");
				writer = response.getWriter(); // IOException ����ó�� �ʿ�
				writer.println("<script type='text/javascript'>");
				writer.println("alert('����� �߸��Ǿ����ϴ�.');");
				writer.println("history.back();"); // �� ���� ������ ȸ������ Ŭ���ϰ� �ٽ� �ڷ� ���ư��� ���� �� alert()�� ���ڱ� �߹Ƿ� �� �� �� �־�� �Ѵ�. 
				writer.println("</script>");
				writer.flush(); // �� �� ������ alert�� ���� �ʴ´�. 

				return new ModelAndView( getPage ) ; // getPage=MemberLoginForm.jsp
			}
		}
		return mav;
	}
}


