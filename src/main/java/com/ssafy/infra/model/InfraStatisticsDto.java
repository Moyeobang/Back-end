package com.ssafy.infra.model;

import lombok.Data;

@Data
public class InfraStatisticsDto {
	private String sidoCode;
	private String category;
	private Double mean;
	private Double var;
	private Double sd;
}
