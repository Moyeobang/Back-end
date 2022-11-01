package com.ssafy.member.model.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberMapper {
	
	private static MemberMapper memberDao = new MemberDaoImpl();
	private DBUtil dbUtil;
	
	private MemberDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static MemberMapper getMemberDao() {
		return memberDao;
	}

	@Override
	public int idCheck(String userId) throws SQLException {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(user_id) \n");
			sql.append("from members \n");
			sql.append("where user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public void joinMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into members (user_id, user_name, user_password, email_id, email_domain, join_date, user_class) \n");
			sql.append("values (?, ?, ?, ?, ?, now(), ?)");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, memberDto.getUserId());
			pstmt.setString(++idx, memberDto.getUserName());
			pstmt.setString(++idx, memberDto.getUserPwd());
			pstmt.setString(++idx, memberDto.getEmailId());
			pstmt.setString(++idx, memberDto.getEmailDomain());
			pstmt.setString(++idx, memberDto.getUserClass());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select user_id, user_password, user_name, user_class, email_id, email_domain\n");
			sql.append("from members \n");
			sql.append("where user_id = ? and user_password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, map.get("userid"));
			pstmt.setString(2, map.get("userpwd"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserPwd(rs.getString("user_password"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserClass(rs.getString("user_class"));
				memberDto.setEmailId(rs.getString("email_id"));
				memberDto.setEmailDomain(rs.getString("email_domain"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

	@Override
	public void deleteMember(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from members \n");
			sql.append("where user_id = ? and user_password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, memberDto.getUserId());
			pstmt.setString(++idx, memberDto.getUserPwd());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return;
	}

	@Override
	public void updateMember(MemberDto memberDto, String userName, String userEmailId, String userEmailDomain) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members \n");
			sql.append("set user_name = ?, email_id = ?, email_domain = ? \n");
			sql.append("where user_id = ? and user_password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			
			// 수정된 name input 값을 받아야한다.
			pstmt.setString(++idx, userName);
			// 수정된 email_id input 값을 받아야한다.
			pstmt.setString(++idx, userEmailId);		
			// 수정된 email_domain input 값을 받아야한다.
			pstmt.setString(++idx, userEmailDomain);
			
			pstmt.setString(++idx, memberDto.getUserId());
			pstmt.setString(++idx, memberDto.getUserPwd());			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return;
	}

	@Override
	public MemberDto changePassword(MemberDto memberDto, String newPassword) throws SQLException {
		// TODO Auto-generated method stub
		MemberDto new_memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members \n");
			sql.append("set user_password = ? \n");
			sql.append("where user_id = ? and user_password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			
			// 새로운 비밀번호 값을 받아야한다.
			pstmt.setString(++idx, newPassword);
			pstmt.setString(++idx, memberDto.getUserId());
			pstmt.setString(++idx, memberDto.getUserPwd());
						
			pstmt.executeUpdate();
			
			sql = new StringBuilder();
			
			sql.append("select user_id, user_password, user_name, user_class, email_id, email_domain\n");
			sql.append("from members \n");
			sql.append("where user_id = ? and user_password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberDto.getUserId());
			pstmt.setString(2, newPassword);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserPwd(rs.getString("user_password"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserClass(rs.getString("user_class"));
				memberDto.setEmailId(rs.getString("email_id"));
				memberDto.setEmailDomain(rs.getString("email_domain"));
			}
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return memberDto;
	}
	
	@Override
	public List<MemberDto> listMember(Map<String, String> map) throws SQLException {
		List<MemberDto> members = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from members \n");
			String key = map.get("key");
			String word = map.get("word");
			if(!word.isEmpty()) {
			 	if(key.equals("all"))
					sql.append("where user_id like ? or user_name = ? \n");
			 	else if("user_id".equals(key))
					sql.append("where user_id like ? \n");
				else
					sql.append("where user_name = ? \n");
			}
			
			sql.append("order by user_id desc limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if(!word.isEmpty()) {
				if(key.equals("all")) {
					pstmt.setString(++idx, "%" + word + "%");
					pstmt.setString(++idx, word);
				} else if("subject".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
				else 
					pstmt.setString(++idx, word);
			}
			
			 pstmt.setInt(++idx, Integer.parseInt(map.get("start")));
			 pstmt.setInt(++idx, Integer.parseInt(map.get("spl")));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserPwd(rs.getString("user_password"));
				memberDto.setEmailId(rs.getString("email_id"));
				memberDto.setEmailDomain(rs.getString("email_domain"));
				memberDto.setJoinDate(rs.getString("join_date"));
				memberDto.setUserClass(rs.getString("user_class"));
				
				members.add(memberDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return members;
	}

	@Override
	public int totalMemberCount(Map<String, String> map) throws SQLException {
		List<MemberDto> members = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from members \n");
			String key = map.get("key");
			String word = map.get("word");
			if(!word.isEmpty()) {
				if(key.equals("all"))
					sql.append("where user_id like ? or user_name = ? \n");
			 	else if("user_id".equals(key))
					sql.append("where user_id like ? \n");
				else
					sql.append("where user_name = ? \n");
			}
			
			sql.append("order by user_id desc");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if(!word.isEmpty()) {
				if(key.equals("all")) {
					pstmt.setString(++idx, "%" + word + "%");
					pstmt.setString(++idx, word);
				} else if("subject".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
				else 
					pstmt.setString(++idx, word);
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserPwd(rs.getString("user_password"));
				memberDto.setEmailId(rs.getString("email_id"));
				memberDto.setEmailDomain(rs.getString("email_domain"));
				memberDto.setJoinDate(rs.getString("join_date"));
				memberDto.setUserClass(rs.getString("user_class"));
				
				members.add(memberDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return members.size();
	}

	/*
	@Override
	public List<MemberDto> listMember() throws SQLException {
		// TODO Auto-generated method stub	
		List<MemberDto> members = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from members \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserPwd(rs.getString("user_password"));
				memberDto.setEmailId(rs.getString("email_id"));
				memberDto.setEmailDomain(rs.getString("email_domain"));
				memberDto.setJoinDate(rs.getString("join_date"));
				memberDto.setUserClass(rs.getString("user_class"));
				
				members.add(memberDto);
			}
		} catch(SQLException e){
			e.printStackTrace();
			return null;
		} finally {
			dbUtil.close(conn, pstmt);
		}
		return members;
	}

	@Override
	public int totalCount() throws SQLException {
		// TODO Auto-generated method stub	

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
				
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from members \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			count = rs.get
		} catch(SQLException e){
				e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstmt);
		}
		return members;
	}
	 */
}
