package com.ssafy.category.model.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.category.model.CategoryDto;
import com.ssafy.house.model.HouseDto;
import com.ssafy.util.DBUtil;

public class CategoryDaoImpl implements CategoryMapper{
	
	
	private static CategoryMapper categoryDao = new CategoryDaoImpl();
	private DBUtil dbUtil;
	
	private CategoryDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static CategoryMapper getCategoryDao() {
		return categoryDao;
	}
	

	@Override
	public List<CategoryDto> listCategory(Map<String, String> map) throws SQLException {
		List<CategoryDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CATEGORY_ID, PARENT_ID, CATEGORY_NAME \n");
			sql.append("FROM CATEGORY \n");
			sql.append("WHERE PARENT_ID = ?");
			
			String parentId = map.get("parent_id");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, Integer.parseInt(parentId));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryDto categoryDto = new CategoryDto();
				categoryDto.setCategoryId(rs.getInt(1));
				categoryDto.setParentId(rs.getInt(2));
				categoryDto.setCategoryName(rs.getString(3));
				
				list.add(categoryDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

}
