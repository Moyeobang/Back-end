package com.ssafy.infra.model;

import lombok.Data;

@Data
public class InfraDto {
	private Integer entertainment; // 관광/여가/오락
	private Integer realEastate; // 부동산
	private Integer livingService; // 생활서비스
	private Integer retailSale; // 소매
	private Integer accommodation; // 숙박
	private Integer sports; // 스포츠
	private Integer food; // 음식
	private Integer education;// 학문/교육
}
