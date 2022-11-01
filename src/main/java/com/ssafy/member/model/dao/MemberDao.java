package com.ssafy.member.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberDao {

	int idCheck(String userId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(String userId, String userPwd) throws SQLException;
	void deleteMember(MemberDto memberDto) throws SQLException;
	void updateMember(MemberDto memberDto, String userName, String userEmailId, String userEmailDomain) throws SQLException;
	MemberDto changePassword(MemberDto memberDto, String newPassword) throws SQLException;
	List<MemberDto> listMember(Map<String, String> map) throws SQLException;
	int totalMemberCount(Map<String, String> map) throws SQLException;
}
