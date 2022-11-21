package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.jwt.TokenInfo;
import com.ssafy.member.model.Member;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.PwdChangeRequestDto;

public interface MemberService {
	int idCheck(String userId) throws Exception;
	void joinMember(MemberDto memberDto) throws Exception;
	void deleteMember(String userId) throws Exception;
	void updateMember(MemberDto memberDto) throws Exception;
	List<MemberDto> listMember(Map<String, String> map) throws Exception;
	int totalMemberCount(Map<String, String> map) throws Exception;
	MemberDto loginMember(Map<String, String> map) throws Exception;
	MemberDto getMember(String userId) throws Exception;
	TokenInfo login(String memberId, String password)  throws Exception;
	Member findByMemberId(String memberId)  throws Exception;
	void deleteRefreshToken(String userId) throws Exception;
	String getPasswordById(String userId) throws Exception ;
	MemberDto getUserInfo(String userId) throws Exception;
	String getRefreshToken(String userId) throws Exception;
	String createAccessToken(String string) throws Exception;
	String getMemberEmail(String userId) throws Exception;
	void sendMail(String randPwd, String memberEmail) throws Exception;
	boolean changePassword(String userId, String newPassword) throws SQLException;
}
