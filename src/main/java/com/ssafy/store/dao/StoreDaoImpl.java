package com.ssafy.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.store.StoreDto;
import com.ssafy.util.DBUtil;

public class StoreDaoImpl implements StoreDao {

	private static StoreDao storeDao = new StoreDaoImpl();
	private DBUtil dbUtil;
	
	private StoreDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static StoreDao getMemberDao() {
		return storeDao;
	}
	
	@Override
	public void insertStore(StoreDto storeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into storeinfo (storeId, storeName, category, dongCode, address, latitude, longitude) \n");
			sql.append("values (?, ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, storeDto.getStoreId());
			pstmt.setString(++idx, storeDto.getStoreName());
			pstmt.setString(++idx, storeDto.getCategory());
			pstmt.setString(++idx, storeDto.getDongCode());
			pstmt.setString(++idx, storeDto.getAddress());
			pstmt.setString(++idx, storeDto.getLatitude());
			pstmt.setString(++idx, storeDto.getLongitude());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public int countStore() throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) \n");
			sql.append("from storeinfo \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);				
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}
}
