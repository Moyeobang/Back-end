package com.ssafy.board;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.category.model.CategoryDto;
import com.ssafy.category.model.service.CategoryService;

public class CategoryTest extends SpringbootRestapiApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(CategoryTest.class);

	@Autowired
	private CategoryService categoryService;
	
	// TODO : Category 생성 sql문 찾기. 없어서 테스트 불가.
	@Test
	public void listCategory() throws SQLException {
		Map<String, String> map = new HashMap<>();
		map.put("parent_id", "1");
		try {
		List<CategoryDto> c = categoryService.listCategory(map);
		logger.debug("categoryList : {}", c);
		assertNotNull(c);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}
