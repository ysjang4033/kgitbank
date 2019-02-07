package net.hb.crud;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	
	@Autowired
	LoginDAO loginDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	

	@RequestMapping(value="login.do",method=RequestMethod.GET)
	 public String login(Model model){
		return "login";  //views/login.jsp����
	}//end
	
	@RequestMapping(value="loginprocess.do", method=RequestMethod.POST)
	public String loginprocess(LoginDTO dto, HttpServletResponse response, HttpSession session)	throws Exception
	{
		//�α��� ���񽺸� ȣ���ϰ� ����� result�� ����
		LoginDTO result = loginDAO.login(dto);
		String a=result.getUserid();
		//�α��� ������ Model�� �������� �ʰ� HttpSession�� �����Ѵ�.
		session.setAttribute("LOGIN",a);
		//�α��ο� ������ ���� �α��� ���������̵�  LOGIN�빮��
		if(result==null){
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append("<script>"
							+ "alert('���̵� ��й�ȣ�� ��ġ�����ʽ��ϴ�.');"
				        		 + "window.history.back();"
							  + "</script>").flush();
			return "redirect:login.do";
		}
		//������ ��쿡�� ������������ �̵�
		return "redirect:list.do";
	}//end
	
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session){
		//���� �ʱ�ȭ
		session.invalidate(); 
		//������������ �����̷�Ʈ
		return "redirect:/index.jsp";
	}//end
	
}//LoginController class END