package net.hb.project.Member;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {
	
	//Member DB�Է�
	private int rn;
	private int m_idx;
	private String m_id;
	private String m_pwd;
	private String m_year;
	private String m_phone;
	private String m_gender;
	private String m_hobby;
    private Date m_date;
    private String m_juso1;
    private String m_juso2;
    private String m_email;
    
    //Member DB ������ ��ĥ Phone, E-mail, �������
    private String birth1;
    private String birth2;
    private String birth3;
    private String email1;
    private String email2;
    private String phone1;
    private String phone2;
    private String phone3;
    
    
	//Member DB�Է� GET, SET
	public int getRn() {return rn;}
	public void setRn(int rn) {this.rn = rn;}
	public int getM_idx() {return m_idx;}
	public void setM_idx(int m_idx) {this.m_idx = m_idx;}
	public String getM_id() {return m_id;}
	public void setM_id(String m_id) {this.m_id = m_id;}
	public String getM_pwd() {return m_pwd;}
	public void setM_pwd(String m_pwd) {this.m_pwd = m_pwd;}
	public String getM_year() {return m_year;}
	public void setM_year(String m_year) {this.m_year = m_year;}
	public String getM_phone() {return m_phone;}
	public void setM_phone(String m_phone) {this.m_phone = m_phone;}
	public String getM_gender() {return m_gender;}
	public void setM_gender(String m_gender) {this.m_gender = m_gender;}
	public String getM_hobby() {return m_hobby;}
	public void setM_hobby(String m_hobby) {this.m_hobby = m_hobby;}
	public Date getM_date() {return m_date;}
	public void setM_date(Date m_date) {this.m_date = m_date;}
	public String getM_juso1() {return m_juso1;}
	public void setM_juso1(String m_juso1) {this.m_juso1 = m_juso1;}
	public String getM_juso2() {return m_juso2;}
	public void setM_juso2(String m_juso2) {this.m_juso2 = m_juso2;}
	public String getM_email() {return m_email;}
	public void setM_email(String m_email) {this.m_email = m_email;}
	
	//Member DB ������ ��ĥ Phone, E-mail, ������� GET, SET
	public String getBirth1() {return birth1;}
	public void setBirth1(String birth1) {this.birth1 = birth1;}
	public String getBirth2() {return birth2;}
	public void setBirth2(String birth2) {this.birth2 = birth2;}
	public String getBirth3() {return birth3;}
	public void setBirth3(String birth3) {this.birth3 = birth3;}
	public String getEmail1() {return email1;}
	public void setEmail1(String email1) {this.email1 = email1;}
	public String getEmail2() {return email2;}
	public void setEmail2(String email2) {this.email2 = email2;}
	public String getPhone1() {return phone1;}
	public void setPhone1(String phone1) {this.phone1 = phone1;}
	public String getPhone2() {return phone2;}
	public void setPhone2(String phone2) {this.phone2 = phone2;}
	public String getPhone3() {return phone3;}
	public void setPhone3(String phone3) {this.phone3 = phone3;}

}