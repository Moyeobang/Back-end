package com.ssafy.category.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.category.model.CategoryDto;

public interface CategoryMapper {

	List<CategoryDto> listCategory(Map<String, String> map) throws SQLException;
	
}
