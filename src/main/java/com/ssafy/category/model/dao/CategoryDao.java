package com.ssafy.category.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.category.model.CategoryDto;

public interface CategoryDao {

	List<CategoryDto> listCategory(Map<String, String> map) throws SQLException;
	
}
