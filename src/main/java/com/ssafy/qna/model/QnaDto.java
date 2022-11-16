package com.ssafy.qna.model;

public class QnaDto {
	private int articleno;
	private String userid;
	private String subject;
	private String content;
	private int hit;
	private String regtime;
	private QnaAnswerDto qnaAnswerDto;
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public QnaAnswerDto getQnaAnswerDto() {
		return qnaAnswerDto;
	}
	public void setQnaAnswerDto(QnaAnswerDto qnaAnswerDto) {
		this.qnaAnswerDto = qnaAnswerDto;
	} 
	
	

}
