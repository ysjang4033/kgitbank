package net.hb.project;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import net.hb.project.Member.MemberDAO;
import net.hb.project.Member.MemberDTO;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	MemberDAO dao;
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping(value="/MemberInsert.do", produces="text/plain;charset=UTF-8", method=RequestMethod.POST)
	public String MemberInsert(MemberDTO mto){
		
		System.out.println("id="  + mto.getM_id());
		System.out.println("pwd=" + mto.getM_pwd());
		System.out.println("year=" + mto.getBirth1());
		System.out.println("year=" + mto.getBirth2());
		System.out.println("year=" + mto.getBirth3());
		System.out.println("email=" + mto.getEmail1());
		System.out.println("email=" + mto.getEmail2());
		System.out.println("phone=" + mto.getPhone1());
		System.out.println("phone=" + mto.getPhone2());
		System.out.println("phone=" + mto.getPhone3());
		System.out.println("gender="  + mto.getM_gender());
		System.out.println("hobby="  + mto.getM_hobby());
		System.out.println("juso1=" + mto.getM_juso1());
		System.out.println("juso2=" + mto.getM_juso2());
		
		//Member DB 합치기
		String birth = mto.getBirth1() + "년 " + mto.getBirth2() + "월 " + mto.getBirth3() + "일";
		String email = mto.getEmail1() + " @ " +  mto.getEmail2();
		String phone = mto.getPhone1() + " - " + mto.getPhone2() + " - " + mto.getPhone3();
		System.out.println(birth);
		System.out.println(email);
		System.out.println(phone);
		
		//DAO SET
		mto.setM_year(birth);
		mto.setM_email(email);
		mto.setM_phone(phone);
		
		dao.dbInsert(mto);
		
	  return "redirect:/login.do";
	  
	}//end
	
}//LoginController class END