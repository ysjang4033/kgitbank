package net.hb.order;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GuestController {
	//GuestDAO dao=new GuestDAO();//�ڹ�,MVC2=����
	
	@Autowired
	GuestDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(GuestController.class);
	
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String guest_form(Locale locale, Model model) {
		logger.info("write.do");
//		return "guest"; //.jspȮ���� ���� ������, WEB-INF/views/guestList.jsp����
		return "WEB-INF/views/guest.jsp";
	}//end
	
	@RequestMapping("/insert.do")
	public String guest_insert(GuestDTO dto) { //MVC2���� InsertController.java
		//request.getParameter("pay")
//		System.out.println("GuestController��Ʈ�� guest_insert(GuestDTO dto)");
		System.out.println("��Ʈ�� �Ѿ�»��="+dto.getSabun());
		System.out.println("��Ʈ�� �Ѿ�»��="+dto.getName());
		System.out.println("��Ʈ�� �Ѿ�»��="+dto.getTitle());
		System.out.println("��Ʈ�� �Ѿ�»��="+dto.getPay());
		dao.dbInsert(dto);
		//return "guestList";	//.jspȮ���� ���� ������
//		return "redirect:/list.do";
		return "redirect:/list.do";
	}//end
	
	@RequestMapping("/list.do")
	public ModelAndView guest_select(HttpServletRequest request) { //MVC2���� ListController.java
		ModelAndView mav=new ModelAndView();
		
		int startpage=1, endpage=10;
		String pnum="";
		int pageNUM=1, start=1, end=10;
		int pagecount=1, temp=0;
		String skey="", sval="", returnpage="";
		skey=request.getParameter("keyfield"); 
		sval=request.getParameter("keyword");
		int Gtotal=0;
		
		if(skey==null || sval==null || skey=="" || sval=="") {
			skey="title"; sval=""; 
			Gtotal=dao.dbCount();	//���ڵ���ü����
			if(Gtotal%10==0) {
				pagecount=Gtotal/10;
			}else {
				pagecount=(Gtotal/10)+1;
			}
		}else {
//			System.out.println(skey+""+sval);
			Gtotal=dao.dbCountSearch(skey, sval);
			
			if(Gtotal%10==0) {
				pagecount=Gtotal/10;
			}else {
				pagecount=(Gtotal/10)+1;
			}
		}
		System.out.println(skey+""+sval);
		pnum=request.getParameter("pageNum");	//<a href=list.do?pageNum=${i}>
		
		if(pnum=="" || pnum==null) {
			pageNUM=1;
		}else {
			pageNUM=Integer.parseInt(pnum);
		}
		
		start=((pageNUM-1)*10)+1;
		end=pageNUM*10;
		
		
		temp=(pageNUM-1)%10;
		startpage=pageNUM-temp;
		endpage=startpage+9;
		if(endpage>pagecount) {
			endpage=pagecount;
		}
		
		List<GuestDTO> list=dao.dbSelect(start, end, skey, sval);
		mav.addObject("naver", list);
		mav.addObject("Gtotal", Gtotal);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("pagecount", pagecount);
		mav.addObject("pageNUM", pageNUM);
		mav.addObject("skey", skey);
		mav.addObject("sval", sval);
//		mav.setViewName("guestList"); 
		mav.setViewName("WEB-INF/views/guestList.jsp"); 
		
		return mav;
	}// end
	
	@RequestMapping("/detail.do")
	public String guest_detail(Model model, @RequestParam("idx") int data) {
		GuestDTO dto=dao.dbDetail(data);
		model.addAttribute("bean", dto);
//		mav.setViewName("guestDetail"); sevlet-context.xml�� ���� �ּ�ó���� ��� �ؿ��� ��
		return "WEB-INF/views/guestDetail.jsp";
	}
	
	@RequestMapping("/delete.do")
	public String guest_delete(HttpServletRequest request) {	//@RequestParam("idx") String data�� �ᵵ��
		int data=Integer.parseInt(request.getParameter("idx"));
		dao.dbDelete(data);
//		return "redirect:/list.do";
		return "redirect:/list.do";
	}
	
	@RequestMapping("/preedit.do")
	public ModelAndView guest_preEdit(@RequestParam("idx") String data) {
		ModelAndView mav=new ModelAndView();
		int a=Integer.parseInt(data);
		GuestDTO dto=dao.dbDetail(a);
		mav.addObject("bean", dto);
//		mav.setViewName("guestEdit");
		mav.setViewName("WEB-INF/views/guestEdit.jsp");
		return mav;
	}
	
	@RequestMapping("/edit.do")
	public String guest_Edit(GuestDTO dto) { //MVC2���� InsertController.java
		dao.dbEdit(dto);
//		return "redirect:/list.do";
		return "redirect:/list.do";
	}//end	
	
}//GuestController class END