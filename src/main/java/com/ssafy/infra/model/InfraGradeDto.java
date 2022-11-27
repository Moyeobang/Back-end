package com.ssafy.infra.model;

import lombok.Data;

@Data
public class InfraGradeDto {
	private String entertainmentGrade; // 관광/여가/오락
	private String realEastateGrade; // 부동산
	private String livingServiceGrade; // 생활서비스
	private String retailSaleGrade; // 소매
	private String accommodationGrade; // 숙박
	private String sportsGrade; // 스포츠
	private String foodGrade; // 음식
	private String educationGrade;// 학문/교육
	private String total;
}
