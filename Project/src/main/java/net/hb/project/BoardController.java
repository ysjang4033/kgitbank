package net.hb.project;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.hb.project.Board.BoardDAO;
import net.hb.project.Board.BoardDTO;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	@Autowired
	BoardDAO bao;
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping(value="/boardWrite.do", method=RequestMethod.GET)
	public String BoardWrite(@RequestParam("b_id") String b_id, HttpServletRequest request) {
		
		request.setAttribute("id", b_id);
		return "boardWrite";
	}
	
	@RequestMapping(value="/boardInsert.do", produces="text/plain;charset=UTF-8")
	public String BoardInsert(BoardDTO bto){
		
		MultipartFile mf=bto.getB_upload_f();
		
		System.out.println("id="  + bto.getB_id());
		System.out.println("title=" + bto.getB_title());
		System.out.println("content=" + bto.getB_content());
		System.out.println("img="  + bto.getB_img_file_name());
		System.out.println("img 경로="  + bto.getB_upload_f());
		 
		String path=application.getRealPath("/resources/upload");
		String img=mf.getOriginalFilename();
		File file=new File(path, img);
		
		try {
			mf.transferTo(file);
		}catch(Exception ex){ System.out.println(ex); }
		
		bto.setB_img_file_name(img);
		bao.b_insert(bto); 
		
		return "redirect:/boardList.do";
	}
	
	@RequestMapping(value="/boardList.do", produces="text/plain;charset=UTF-8")
	public ModelAndView BoardList(HttpServletRequest request) {
		
		ModelAndView mav=new ModelAndView();
		
		// 페이징 변수
		int start, end;
		int startPage, endPage;
		int pageNum=1;
		int pageCount;
		int Count;
		String pNum;
		
		// 검색 변수
		String keyfield;
		String keyword;
		
		// 검색변수 받음
		keyfield=request.getParameter("keyfield");
		keyword=request.getParameter("keyword");
		
		// 검색변수 Null일 때 처리
		if(keyfield==null || keyfield=="" || keyword==null || keyword=="") {
			keyfield="title"; keyword="";
			
			// 페이지Count, 몪이 없으면 딱 떨어지고 있으면 다음페이지까지가야됨
			Count=bao.b_count();
			if(Count%5==0) {
				pageCount=Count/5;
			}else {
				pageCount=(Count/5) + 1;
			}
		}else {
			
			// 페이지Count, 몪이 없으면 딱 떨어지고 있으면 다음페이지까지가야됨
			Count=bao.b_Searchcount(keyfield, keyword);
			if(Count%5==0) {
				pageCount=Count/5;
			}else {
				pageCount=(Count/5) + 1;
			}
			
		}
		
		// 페이지Num받음
		pNum=request.getParameter("pageNum");
		if(pNum=="" || pNum==null) {pageNum=1;}
		else {pageNum=Integer.parseInt(pNum);}
		
		// 리스트
		end=pageNum*5;
		start=end-4;
		
		// 페이징
		startPage=pageNum - ((pageNum-1)%5);
		endPage=startPage+4;

		
		// 페이징 마지막 작업
		if(endPage > pageCount) {
			endPage=pageCount;
		}
		
		
		List<BoardDTO> list=bao.b_list(start, end, keyfield, keyword);
		
		mav.addObject("list", list);
		mav.addObject("Count", Count);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("pageCount", pageCount);
		mav.addObject("pageNum", pageNum);
		mav.addObject("keyfield", keyfield);
		mav.addObject("keyword", keyword);
		mav.setViewName("boardList"); 
		
		return mav;
	}
	
	@RequestMapping(value="/boardDetail.do", produces="text/plain;charset=UTF-8")
	public ModelAndView BoardDetail(@RequestParam("idx") int data, @RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="keyfield",defaultValue="name") String keyfield, @RequestParam(value="keyword",defaultValue="") String keyword) {
		
		ModelAndView mav=new ModelAndView();
		BoardDTO bto=bao.b_detail(data);
		mav.addObject("detail", bto);
		mav.addObject("pageNum", pageNum);
		mav.addObject("keyfield", keyfield);
		mav.addObject("keyword", keyword);
		mav.setViewName("boardDetail");
		return mav;
	}
	
	@RequestMapping(value="/boardDelete.do", produces="text/plain;charset=UTF-8")
	public String BoardDelete(@RequestParam("idx") int data) {
		
		bao.b_delete(data);
		
		return "redirect:/boardList.do";
	}
	
	@RequestMapping(value="/boardpreEdit.do", produces="text/plain;charset=UTF-8")
	public ModelAndView BoardpreEdit(@RequestParam("idx") int idx, @RequestParam("idx2") String id) {
		
		ModelAndView mav=new ModelAndView();
		
		BoardDTO bto=bao.b_detail(idx);
		mav.addObject("detail", bto);
		mav.setViewName("boardEdit");
		
		return mav;
	}
	
	@RequestMapping(value="/boardEdit.do", produces="text/plain;charset=UTF-8")
	public String BoardEdit(@RequestParam("idx") int idx, @RequestParam("idx2") String id, BoardDTO bto) {
		
		bao.b_edit(bto);
		
		return "redirect:/boardDetail.do?idx="+idx;
	}
	
	@RequestMapping(value="/boardDownload.do", produces="text/plain;charset=UTF-8")
	public ModelAndView BoardDownload(@RequestParam("idx") String data) throws Exception {
		
		String path2="C:\\dev\\sts-bundle\\my\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\Project\\resources\\upload\\";
		
		File downloadFile=new File(path2+data);

		return new ModelAndView("downloadView", "downloadFile", downloadFile);
	}
}
