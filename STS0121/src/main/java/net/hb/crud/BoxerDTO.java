package net.hb.crud;

public class BoxerDTO {
	private String userid;
	private String pwd;
	private String username;
	private int usergrade;
	
	public String getUserid() {	return userid;	}
	public void setUserid(String userid) {this.userid = userid;	}
	public String getPwd() {	return pwd;}
	public void setPwd(String pwd) {	this.pwd = pwd;	}
	public String getUsername() {return username;	}
	public void setUsername(String username) {this.username = username;	}
	public int getUsergrade() {	return usergrade;	}
	public void setUsergrade(int usergrade) {	this.usergrade = usergrade;	}

	@Override
	public String toString() {
		return "Boxer [userid=" + userid + ", pwd=" + pwd + ", username=" + username + ", usergrade=" + usergrade
				+ "]";
	}//end
	
}//class End
