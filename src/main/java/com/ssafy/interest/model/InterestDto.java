package com.ssafy.interest.model;

public class InterestDto {
	
	private int seq;
	private int dong;
	private String dongName;
	private int gugun;
	private String gugunName;
	private int sido;
	private String sidoName;
	private String user_id;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getDong() {
		return dong;
	}
	public void setDong(int dong) {
		this.dong = dong;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public int getGugun() {
		return gugun;
	}
	public void setGugun(int gugun) {
		this.gugun = gugun;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public int getSido() {
		return sido;
	}
	public void setSido(int sido) {
		this.sido = sido;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		return "InterestDto [seq=" + seq + ", dong=" + dong + ", dongName=" + dongName + ", gugun=" + gugun
				+ ", gugunName=" + gugunName + ", sido=" + sido + ", sidoName=" + sidoName + ", user_id=" + user_id
				+ "]";
	}
}
