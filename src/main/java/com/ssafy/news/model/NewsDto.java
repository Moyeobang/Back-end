package com.ssafy.news.model;

import lombok.Data;

@Data
public class NewsDto {
	private String categoryName;
	private String title;
	private String url;
	private String imgURL;
	private String date;
}
