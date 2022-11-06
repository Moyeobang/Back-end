package com.ssafy.interest.model.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.interest.model.InterestDto;
import com.ssafy.util.DBUtil;

public class InterestDaoImp {
//	private static InterestMapper interestMapper = new InterestDaoImp();
//	private DBUtil dbUtil;
//	
//	private InterestDaoImp() {
//		 dbUtil = DBUtil.getInstance();
//	}
//	public static InterestMapper getInstance() {
//		return interestMapper;
//	}
//	
//	@Override
//	public int insertInterest(InterestDto dto) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int cnt = 0;
//		int exist = -1;
//		
//		 try {
//			 conn = dbUtil.getConnection();
//			 String sql = "select count(*) from interest where user_id=? \n";
//			 pstmt = conn.prepareStatement(sql);
//			 pstmt.setString(1, dto.getUser_id());
//			 rs = pstmt.executeQuery();
//			 if(rs.next()) {
//				 exist = rs.getInt(1);
//			 }
//					 
//			 String sql2 = "insert into interest (dong, dongName,"
//			 		+ "gugun, gugunName, sido, sidoName, user_id, is_main)"
//			 		+ "values (?,?,?,?,?,?,?,?)";
//			 
//			 pstmt = conn.prepareStatement(sql2);
//			 pstmt.setInt(1, dto.getDong());
//			 pstmt.setString(2, dto.getDongName());
//			 pstmt.setInt(3, dto.getGugun());
//			 pstmt.setString(4, dto.getGugunName());
//			 pstmt.setInt(5, dto.getSido());
//			 pstmt.setString(6, dto.getSidoName());
//			 pstmt.setString(7, dto.getUser_id());
//			 pstmt.setInt(8, exist>0?0:1);
//			 cnt = pstmt.executeUpdate();
//			 
//		 } finally {
//			 dbUtil.close(rs, pstmt, conn);
//		 }
//		 
//		 return cnt;
//	}
//	
//	@Override
//	public int checkInsert(InterestDto dto) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select * from interest \n");
//			sql.append("where user_id = ? and dong = ? and gugun = ? and sido = ?");
//			
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, dto.getUser_id());
//			pstmt.setInt(2, dto.getDong());
//			pstmt.setInt(3, dto.getGugun());
//			pstmt.setInt(4, dto.getSido());
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next())
//				return true;
//			
//		} finally {
//			dbUtil.close(rs, pstmt, conn);
//		}
//		
//		return false;
//	}
//	
//	@Override
//	public int deleteInterest(int seq) throws SQLException {
//		int cnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("delete from interest \n");
//			sql.append("where seq = ?");
//			
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, seq);
//			
//			cnt = pstmt.executeUpdate();
//			
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return cnt;
//	}
//	
//	@Override
//	public List<InterestDto> selectInterest(String userId) throws SQLException {
//		List<InterestDto> list = new ArrayList<>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select seq,dong,dongName,gugun,gugunName,sido,sidoName,user_id   from interest \n");
//			sql.append("where user_id = ?");
//			
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, userId);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				InterestDto dto = new InterestDto();
//				dto.setSeq(rs.getInt(1));
//				dto.setDong(rs.getInt(2));
//				dto.setDongName(rs.getString(3));
//				dto.setGugun(rs.getInt(4));
//				dto.setGugunName(rs.getString(5));
//				dto.setSido(rs.getInt(6));
//				dto.setSidoName(rs.getString(7));
//				dto.setUser_id(rs.getString(8));
//				
//				list.add(dto);
//			}
//			
//		} finally {
//			dbUtil.close(rs, pstmt, conn);
//		}
//		
//		return list;
//	}
//	@Override
//	public String getRegion(String id) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String str = null;
//		
//		try {
//			conn = dbUtil.getConnection();
//			String sql = "select sidoName, gugunName, dongName"
//					+ " from interest "
//					+ "where user_id = ? and is_main = 1";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			
//			rs = pstmt.executeQuery();
//
//			while(rs.next()) {
//				str = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
//			}
////			System.out.println(str);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return str;
//	}
//	@Override
//	public int mainChange(int beforeSeq, int seq) {
//		int cnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			// 이전에 main인 값의 seq
//			sql.append("update interest set is_main = 0 where seq = ? ; \n");
//			// main으로 바꿀 값의 seq
//			sql.append("update interest set is_main = 1 where seq = ? ; \n");
//			
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, beforeSeq);
//			pstmt.setInt(2, seq);
//			
//			cnt = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return cnt;
//	}
//	@Override
//	public int getMainInterestSeq(String id) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int mainSeq = 0;
//		
//		try {
//			conn = dbUtil.getConnection();
//			String sql = "select seq "
//					+ "from interest "
//					+ "where user_id = ? and is_main = 1";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			
//			rs = pstmt.executeQuery();
//
//			while(rs.next()) {
//				mainSeq = rs.getInt(1);
//			}
////			System.out.println("is_main == true인 데이터의 seq는 " + mainSeq);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return mainSeq;
//	
//	}

}
