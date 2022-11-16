package com.ssafy.qna.model;

public class QnaAnswerDto {
	private String userId;
	private String answerContent;
	private String answerRegTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getAnswerRegTime() {
		return answerRegTime;
	}
	public void setAnswerRegTime(String answerRegTime) {
		this.answerRegTime = answerRegTime;
	}
	@Override
	public String toString() {
		return "QnaAnswerDto [userId=" + userId + ", answerContent=" + answerContent + ", answerRegTime="
				+ answerRegTime + "]";
	}
	
}
