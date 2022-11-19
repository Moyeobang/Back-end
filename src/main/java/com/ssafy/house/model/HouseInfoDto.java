package com.ssafy.house.model;

import java.util.List;

import com.ssafy.housedeal.model.HouseDealInfoDto;

import lombok.Data;

@Data
public class HouseInfoDto {
	private Long aptCode;
	private Double lat;
	private Double lng;
	private String apartmentName;
	private String roadNameAddress;
	private String jibunNameAddress;
	private int buildYear;
	List<HouseDealInfoDto> houseDealList;
}
