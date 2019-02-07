package net.hb.crud;

import java.io.File;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.hb.crud.reply.ReplyDAO;


@Controller
public class BoardController {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	@Autowired
	BoardDAO dao;
	
	@Autowired
	private ServletContext  application;
	
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String board_write(Locale locale, Model model) {
		return "board";
	}//end
	
	@RequestMapping("/insert.do")
	public String board_insert(BoardDTO dto) {
		
		MultipartFile mf=dto.getUpload_f();
		
//		System.out.println("\n��Ʈ�� �Ѿ���̸�="  + dto.getName() );
//		System.out.println("��Ʈ�� �Ѿ������="  + dto.getTitle());
//		System.out.println("��Ʈ�� �Ѿ�³���="  + dto.getContent());
//		System.out.println("��Ʈ�� �Ѿ�¼���="  + dto.getGender());
//		System.out.println("��Ʈ�� �Ѿ�����="  + dto.getHobby());
//		System.out.println("��Ʈ�� �Ѿ������="  + dto.getUpload_f());
//		System.out.println("��Ʈ�� ����������="  + dto.getImg_file_name());
		
		String path=application.getRealPath("/resources/upload");
		String img=mf.getOriginalFilename();
		File file=new File(path, img);	// �ڹٱ���io��Ű�� Fileȭ
		
		try {
			mf.transferTo(file);
			//FileCopyUtils.copy(dto.getUpload_f().getBytes(), file);
			//File target=new File(path,img);
			//FileCopyUtils.copy(dto.getUpload_f().getBytes(), target);
		    //dto.getUpload_f().transferTo(target);
		}catch(Exception ex){ System.out.println("���Ͽ���:"+ex); }
		
		dto.setImg_file_name(img);
		System.out.println("��Ʈ�� �Ѿ������="  + dto.getImg_file_name());
		
		/*
		  String path=application.getRealPath("/resources/upload");
		  String img=dto.getUpload_f().getOriginalFilename();
		  File file = new File( path, img);
		  
		  
		  try{
		    dto.getUpload_f().transferTo(file);
			//FileCopyUtils.copy(dto.getUpload_f().getBytes(), file);
			// File target=new File(path,img);
			//FileCopyUtils.copy(dto.getUpload_f().getBytes(), target);
		    //dto.getUpload_f().transferTo(target);
		  }catch(Exception ex){ System.out.println("���Ͽ���:"+ex); }

		
		dto.setImg_file_name(img); 
		System.out.println("��Ʈ�� �Ѿ������="  + dto.getImg_file_name());
		
		*/
		
		dao.dbInsert(dto);
	  return "redirect:/list.do";
	}
	
	@RequestMapping("list.do")
	public ModelAndView board_Select(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		
		int pageNum=1;
		int start, end, startpage, endpage, temp, pagecount, Gtotal;
		String Pnum=request.getParameter("pageNum");
		
		String skey, sval;
		skey=request.getParameter("keyfield");
		sval=request.getParameter("keyword");
		
		if(skey=="" || skey==null || sval=="" || sval==null) {
			skey="name"; sval="";
			Gtotal=dao.dbCount();
			
			if(Gtotal%3==0) {
				pagecount=Gtotal/3;
			}else {
				pagecount=(Gtotal/3)+1;
			}
			
		}else {
			Gtotal=dao.dbCountSearch(skey, sval);
			System.out.println("Gtotal"+Gtotal);
			if(Gtotal%10==0) {
				pagecount=Gtotal/5;
			}else {
				pagecount=(Gtotal/5)+1;
			}
		}
		
		
		System.out.println(skey+sval);
		
		if(Pnum=="" || Pnum==null) {
			pageNum=1;
		}else {
			pageNum=Integer.parseInt(Pnum);
		}
		
		
		
		//리스트 뽑는거 1~3 4~6 
		start=(pageNum*3)-2;
		end=pageNum*3;
		
		//페이지 1~5까지만
		temp=(pageNum-1)%5;
		startpage=pageNum-((pageNum-1)%5);
		endpage=startpage+4;
		
		if(endpage>pagecount) {
			endpage=pagecount;
		}
		
		List<BoardDTO> list=dao.dbList(start, end, skey, sval);
		mav.addObject("list", list);
		mav.addObject("Gtotal", Gtotal);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("pageNum", pageNum);
		mav.addObject("pagecount", pagecount);
		mav.addObject("skey", skey);
		mav.addObject("sval", sval);
		mav.setViewName("boardList"); 
		return mav;
	}
	
	@RequestMapping("detail.do")
	public String board_Detail(Model m, @RequestParam("idx") int data) {
		BoardDTO dto=dao.dbDetail(data);
		m.addAttribute("detail", dto);
		return "boardDetail";
	}
	
	@RequestMapping("delete.do")
	public String board_Delete(@RequestParam("idx") int data, @RequestParam("idx2") String img) {
		dao.dbDelete(data);
		
		String path="C:\\dev\\sts-bundle\\my\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\STS0121\\resources\\upload\\"+img;
		File file=new File(path);
		file.delete();
//		System.out.println("��������!!");
		return "redirect:/list.do";
	}
	
	@RequestMapping("preedit.do")
	public String board_preedit(Model m, @RequestParam("idx") int data) {
		BoardDTO dto=dao.dbDetail(data);
		m.addAttribute("detail", dto);
		return "boardEdit";
	}
	
	@RequestMapping("edit.do")
	public String board_edit(BoardDTO dto, @RequestParam("idx") int data, @RequestParam("idx2") String img2) {
		
		String path2="C:\\dev\\sts-bundle\\my\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\STS0121\\resources\\upload\\"+img2;
		
		File file2=new File(path2);
		
		file2.delete();
		
		System.out.println(dto.getHobby_idx());
		
		MultipartFile mf=dto.getUpload_f();
		String path=application.getRealPath("/resources/upload");
		String img=mf.getOriginalFilename();
		File file=new File(path, img);	// �ڹٱ���io��Ű�� Fileȭ
		
		try {
			mf.transferTo(file);
		}catch(Exception ex){ System.out.println("���Ͽ���:"+ex); }
		
		dto.setImg_file_name(img);
		
		dao.dbEdit(dto);
//		System.out.println("��?");
		return "redirect:/detail.do?idx="+data;
	}
	
	@RequestMapping("fileDownload.do")
	public ModelAndView download(@RequestParam("idx") String data) throws Exception {
		
		String path2="C:\\dev\\sts-bundle\\my\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\STS0121\\resources\\upload\\";
		
		File downloadFile=new File(path2+data);
		
		return new ModelAndView("downloadView", "downloadFile", downloadFile);
	}
	
}//BoardController class END

