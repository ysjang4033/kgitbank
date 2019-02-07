package net.hb.project.Board;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	
	private int b_idx;					// 게시글 글번호
	private String b_id;				// 게시글 작성자
	private String b_title;				// 게시글 제목
	private java.util.Date b_date;		// 게시글 작성일
	private String b_content;			// 게시글 내용
	private String b_img_file_name;		// 업로드 파일 이름 
    private MultipartFile b_upload_f;	// 업로드 파일 경로
    private int rn;						// 행번호
    private int start;					// 리스트
    private int end;					// 리스트
    private String keyfield;			// 검색필드
    private String keyword;				// 검색어
    
    // GET, SET
	public String getKeyfield() {return keyfield;}
	public void setKeyfield(String keyfield) {this.keyfield = keyfield;}
	public String getKeyword() {return keyword;}
	public void setKeyword(String keyword) {this.keyword = keyword;}
	public int getStart() {return start;}
	public void setStart(int start) {this.start = start;}
	public int getEnd() {return end;}
	public void setEnd(int end) {this.end = end;}
	public int getRn() {return rn;}
	public void setRn(int rn) {this.rn = rn;}
	public int getB_idx() {return b_idx;}
	public void setB_idx(int b_idx) {this.b_idx = b_idx;}
	public String getB_id() {return b_id;}
	public void setB_id(String b_id) {this.b_id = b_id;}
	public String getB_title() {return b_title;}
	public void setB_title(String b_title) {this.b_title = b_title;}
	public java.util.Date getB_date() {return b_date;}
	public void setB_date(java.util.Date b_date) {this.b_date = b_date;}
	public String getB_content() {return b_content;}
	public void setB_content(String b_content) {this.b_content = b_content;}
	public String getB_img_file_name() {return b_img_file_name;}
	public void setB_img_file_name(String b_img_file_name) {this.b_img_file_name = b_img_file_name;}
	public MultipartFile getB_upload_f() {return b_upload_f;}
	public void setB_upload_f(MultipartFile b_upload_f) {this.b_upload_f = b_upload_f;}
    
}
