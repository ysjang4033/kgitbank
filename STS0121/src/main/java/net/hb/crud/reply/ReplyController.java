package net.hb.crud.reply;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ReplyController {

	@Autowired
	ReplyDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@RequestMapping(value="/reply.do", method=RequestMethod.GET)
	public String reply_insert(ReplyDTO dto) {
	  System.out.println();
	  System.out.println("댓글컨트롤 넘어온idx="  + dto.getHobby_idx());
		System.out.println("댓글컨트롤 넘어온저자="  + dto.getRwriter() );
		System.out.println("댓글컨트롤 넘어온메모="  + dto.getRmemo());
		dao.dbInsert(dto);
		return "redirect:/detail.do?idx="+dto.getHobby_idx();
	}
	
	@RequestMapping(value="/reply_list.do", method=RequestMethod.GET)
	public ModelAndView reply_select(@RequestParam("idx") int idx) {
		ModelAndView mav = new ModelAndView();
		List<ReplyDTO> list = dao.dbSelect(idx);
		mav.addObject("list", list);
		mav.setViewName("board_reply");
		return mav;
	}
	
	@RequestMapping(value="/reply_delete.do", method=RequestMethod.GET)
	public String reply_delete(@RequestParam("idx") int idx, @RequestParam("hobby_idx") int hobby_idx) {
		dao.dbDelete(idx);
		return "redirect:/detail.do?idx="+hobby_idx;
	}
	
	@RequestMapping(value="/reply_preEdit.do", method=RequestMethod.GET)
	public ModelAndView reply_preEdit(@RequestParam("idx") int idx, @RequestParam("hobby_idx") int hobby_idx) {
		ModelAndView mav = new ModelAndView();
		ReplyDTO dto = dao.dbDetail(idx);
		/*dto.setRhobby_idx(idx);
		dto.setHobby_idx(hobby_idx);*/
		mav.addObject("dto", dto);
		mav.setViewName("board_reply_edit");
		return mav;
	}
	
	@RequestMapping(value="/reply_edit.do", method=RequestMethod.GET)
	public String reply_preEdit(ReplyDTO dto) {
		dao.dbUpdate(dto);
		return "redirect:/detail.do?idx=" + dto.getHobby_idx();
	}
	
}//ReplyBoardController class END
