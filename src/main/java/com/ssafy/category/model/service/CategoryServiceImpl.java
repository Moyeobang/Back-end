package com.ssafy.category.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ssafy.category.model.CategoryDto;
import com.ssafy.category.model.mapper.CategoryMapper;
import com.ssafy.category.model.mapper.CategoryDaoImpl;

public class CategoryServiceImpl implements CategoryService {

	private static CategoryService categoryService = new CategoryServiceImpl();
	private CategoryMapper categoryDao;

	private CategoryServiceImpl() {
		categoryDao = CategoryDaoImpl.getCategoryDao();
	}

	public static CategoryService getInstance() {
		return categoryService;
	}

	@Override
	public List<CategoryDto> listCategory(Map<String, String> map) throws SQLException {
		return categoryDao.listCategory(map);
	}

	@Override
	public String getCategory(String title) throws SQLException {

		Map<String, String> map = new HashMap<>();
		map.put("parent_id", "1");

		List<CategoryDto> mainCategorys = listCategory(map);
		String category = null;
		int maxScore = Integer.MIN_VALUE;

		for (CategoryDto mainCategory : mainCategorys) {
			map.put("parent_id", String.valueOf(mainCategory.getCategoryId()));
			List<CategoryDto> subCategorys = listCategory(map);
			int count = 0;
			for (CategoryDto subCategory : subCategorys) {
				if (KMP(title, subCategory.getCategoryName()))
					count++;
			}
			if (maxScore < count) {
				maxScore = count;
				category = mainCategory.getCategoryName();
			}
		}

		if (category == null || category.isEmpty()) {
			category = "기타";
		}

		return category;

	}

	private int[] makeTable(String pattern) {
		int n = pattern.length();
		int[] table = new int[n];

		int idx = 0;
		for (int i = 1; i < n; i++) {
			// 일치하는 문자가 발생했을 때(idx>0), 연속적으로 더 일치하지 않으면 idx = table[idx-1]로 돌려준다.
			while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}

			if (pattern.charAt(i) == pattern.charAt(idx)) {
				idx += 1;
				table[i] = idx;
			}
		}
		return table;
	}

	private boolean KMP(String parent, String pattern) {
		int[] table = makeTable(pattern);

		int n1 = parent.length();
		int n2 = pattern.length();

		int idx = 0; // 현재 대응되는 글자 수
		for (int i = 0; i < n1; i++) {
			// idx번 글자와 짚더미의 해당 글자가 불일치할 경우,
			// 현재 대응된 글자의 수를 table[idx-1]번으로 줄인다.
			while (idx > 0 && parent.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			// 글자가 대응될 경우
			if (parent.charAt(i) == pattern.charAt(idx)) {
				if (idx == n2 - 1) {
					return true;
				} else {
					idx += 1;
				}
			}
		}

		return false;
	}
}
