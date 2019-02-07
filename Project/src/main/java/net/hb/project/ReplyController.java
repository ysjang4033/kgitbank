package net.hb.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.hb.project.Reply.ReplyDAO;
import net.hb.project.Reply.ReplyDTO;
import net.sf.json.JSONObject;

@Controller
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	@Autowired
	ReplyDAO rao;
	
	@RequestMapping(value="/ReplyList.do", produces="text/plain;charset=UTF-8")
	public ModelAndView ReplyList(@RequestParam("idx") int b_idx) {
		ModelAndView mav=new ModelAndView();
		
		List<ReplyDTO> list = rao.r_list(b_idx);
		mav.addObject("b_idx", b_idx);
		mav.addObject("replyList", list);
		mav.setViewName("boardReply");
		
		return mav;
		
	}
	
	@RequestMapping(value="/replyWrite.do", produces="text/plain;charset=UTF-8")
	public String ReplyWrite(ReplyDTO rto) {
		System.out.println(rto.getB_idx());
		System.out.println(rto.getR_content());
		System.out.println(rto.getR_id());
		rao.r_insert(rto);
		return "redirect:/boardDetail.do?idx="+rto.getB_idx();
	}

	
	@RequestMapping("/replyDelete.do")
	public @ResponseBody JSONObject ReplyDelete(@RequestParam("r_idx") int r_idx, @RequestParam("b_idx") int b_idx) {
		System.out.println(r_idx + " / " + b_idx);
		
		rao.r_delete(r_idx);
		
	    JSONObject json = new JSONObject(); 
	    boolean f = true;
	    json.put("t", f);
	    System.out.println("완료됨?");
	    return json;
	}
	
	@RequestMapping("/replyPreEdit.do")
	public ModelAndView ReplyPreEdit(@RequestParam("r_idx") int r_idx) {
		ModelAndView mav=new ModelAndView();
//		System.out.println(r_idx);
		ReplyDTO rto=rao.r_detail(r_idx);
//		System.out.println(rto.getR_content());
		mav.addObject("replyDetail",rto);
		mav.setViewName("replyEdit");
		return mav;
	}
	
	@RequestMapping("/replyEdit.do")
	public @ResponseBody JSONObject ReplyUpdate(ReplyDTO rto) {

		rao.r_update(rto);
		
		JSONObject json=new JSONObject();
		boolean f=true;
		json.put("t", f);
		return json;
	}

}