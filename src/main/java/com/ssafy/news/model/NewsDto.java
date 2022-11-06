package com.ssafy.news.model;

public class NewsDto {

	private String categoryName;
	private String title;
	private String url;
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "NewsDto [categoryName=" + categoryName + ", title=" + title + ", url=" + url + "]";
	}
	

}
