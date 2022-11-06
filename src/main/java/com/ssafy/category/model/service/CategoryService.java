package com.ssafy.category.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.category.model.CategoryDto;

public interface CategoryService {
	
	List<CategoryDto> listCategory(Map<String, String> map) throws SQLException;
	String getCategory(String title) throws SQLException;
}
