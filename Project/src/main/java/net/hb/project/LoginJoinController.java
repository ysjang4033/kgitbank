package net.hb.project;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.hb.project.Member.MemberDAO;
import net.hb.project.Member.MemberDTO;
import net.sf.json.JSONObject;

@Controller
public class LoginJoinController {
	
	@Inject
	@Autowired
	MemberDAO mao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginJoinController.class);

	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String login(Model model){
		return "login";
	}
	
	@RequestMapping(value="/join.do",method=RequestMethod.GET)
	public String join(Model model){
		return "join";  
	}
	
	@RequestMapping(value="/idCheck.do")
	public @ResponseBody JSONObject idcheck(@RequestParam("m_id") String m_id) {
		boolean f = false;
		JSONObject json=new JSONObject();
		int cnt=mao.dbIdCheck(m_id);
		if(cnt>0) {
			f=true;
		}else {
			f=false;
		}
		json.put("t", f);
		
		return json;
	}
	
	@RequestMapping(value="/loginCheck.do")
	public @ResponseBody JSONObject logincheck(MemberDTO mto, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("컨트롤러");
		boolean f=mao.dbLoginCheck(mto);
		JSONObject json=new JSONObject();
		
		System.out.println(mto.getM_id());
		System.out.println(mto.getM_pwd());
		
		if(f==true) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", mto.getM_id());
			System.out.println("로그인 성공");

		}else {
			
			System.out.println("로그인 실패");

		}
		json.put("t", f);
		return json;
	}
	
	@RequestMapping(value="/logOut.do")
	public @ResponseBody JSONObject logout(@RequestParam("id") String id, HttpServletRequest request){
		boolean f=true;
		JSONObject json=new JSONObject();
		System.out.println(id);
		HttpSession session = request.getSession();
		session.invalidate();

		json.put("t", f);
		
		return json;
		
	}
	
	@RequestMapping(value="/popup.do")
	public ModelAndView popup() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("popUp");
		return mav;
	}
	
		



}