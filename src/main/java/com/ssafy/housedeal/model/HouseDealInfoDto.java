package com.ssafy.housedeal.model;

import lombok.Data;

@Data
public class HouseDealInfoDto {
	private Long aptCode;
	private String dealAmount;
	private String date;
	private Double area;
	private Integer floor;
}
