package net.hb.project.Reply;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ReplyDTO {
	
	private int r_idx;	// 댓글 번호
	private String r_id;// 댓글 쓴 아이디
	private int b_idx;	// 부모글
	private String r_content;	//댓글 내용
	private Date r_date;// 댓글 쓴 날짜
	
	public int getR_idx() {return r_idx;}
	public void setR_idx(int r_idx) {this.r_idx = r_idx;}
	public String getR_id() {return r_id;}
	public void setR_id(String r_id) {this.r_id = r_id;}
	public int getB_idx() {return b_idx;}
	public void setB_idx(int b_idx) {this.b_idx = b_idx;}
	public String getR_content() {return r_content;}
	public void setR_content(String r_content) {this.r_content = r_content;}
	public Date getR_date() {return r_date;}
	public void setR_date(Date r_date) {this.r_date = r_date;}    
	
}