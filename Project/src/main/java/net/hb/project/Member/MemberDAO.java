package net.hb.project.Member;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Component
public class MemberDAO {
	
 //org.mybatis.spring.SqlSessionTemplate

 @Autowired
 private SqlSessionTemplate sqlSession;
	
 public void dbInsert(MemberDTO mto) {
	 System.out.println("id="  + mto.getM_id());
	 System.out.println("pwd=" + mto.getM_pwd());
	 System.out.println("year=" + mto.getM_year());
	 System.out.println("phone=" + mto.getM_phone());
	 System.out.println("gender="  + mto.getM_gender());
	 System.out.println("hobby="  + mto.getM_hobby());
	 System.out.println("juso1=" + mto.getM_juso1());
	 System.out.println("juso2=" + mto.getM_juso2());
	 System.out.println("email=" + mto.getM_email());
	 
	 sqlSession.insert("member.insert", mto);
 }
 
 public int dbIdCheck(String m_id) {
	 
	 int cnt=sqlSession.selectOne("member.idcheck", m_id);
	 
	 return cnt;
 }

public boolean dbLoginCheck(MemberDTO mto) {
	boolean f=false;
	int cnt=sqlSession.selectOne("member.logincheck", mto);
	if(cnt>0) {//로그인 가능
		f=true;
	}else {//로그인불가
		f=false;
	}
	return f;
}
 
}
