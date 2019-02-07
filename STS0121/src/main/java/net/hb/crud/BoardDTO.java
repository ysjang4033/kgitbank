package net.hb.crud;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	private  int hobby_idx;
	private  String name;
	private  String title;
	private  String content;
	private  String gender ;
	private  String hobby;
	private  String img_file_name ;
    private  MultipartFile upload_f ;
    private  int rn;
    private  int mid;
    private  int start, end;
    private  String skey, sval;
    private  int rcnt;	//댓글개수
    
    public int getRcnt() {return rcnt;}
	public void setRcnt(int rcnt) {this.rcnt = rcnt;}
	public String getSkey() {return skey;}
	public void setSkey(String skey) {this.skey = skey;}
	public String getSval() {return sval;}
	public void setSval(String sval) {this.sval = sval;}
	public int getStart() {return start;}
	public void setStart(int start) {this.start = start;}
	public int getEnd() {return end;}
	public void setEnd(int end) {this.end = end;}
	public int getMid() {return mid;}
	public void setMid(int mid) {this.mid = mid;}
	public int getRn() {return rn;}
    public void setRn(int rn) {this.rn=rn;}
	public int getHobby_idx() {	return hobby_idx;	}
	public void setHobby_idx(int hobby_idx) {this.hobby_idx = hobby_idx;	}
	public String getName() {return name;	}
	public void setName(String name) {	this.name = name;	}
	public String getTitle() {	return title;	}
	public void setTitle(String title) {this.title = title;	}
	public String getContent() {return content;	}
	public void setContent(String content) {this.content = content;	}
	public String getGender() {	return gender;	}
	public void setGender(String gender) {	this.gender = gender;	}
	public String getHobby() {return hobby;	}
	public void setHobby(String hobby) {this.hobby = hobby;	}
	public String getImg_file_name() {return img_file_name;	}
	public void setImg_file_name(String img_file_name) {this.img_file_name = img_file_name;	}
	public MultipartFile getUpload_f() {return upload_f;}
	public void setUpload_f(MultipartFile upload_f) {this.upload_f = upload_f;	}
	
}//BoardDT end

