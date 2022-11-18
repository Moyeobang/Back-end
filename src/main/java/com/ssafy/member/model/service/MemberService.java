package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.jwt.TokenInfo;
import com.ssafy.member.model.Member;
import com.ssafy.member.model.MemberDto;

public interface MemberService {
	int idCheck(String userId) throws Exception; // ���대�� 以�蹂듦���
	void joinMember(MemberDto memberDto) throws Exception; // ����媛���
	void deleteMember(String userId) throws Exception; // ��������
	void updateMember(MemberDto memberDto) throws Exception; // ������蹂대�寃�
	MemberDto changePassword(Map<String, String> map) throws SQLException; // 鍮�諛�踰��� 蹂�寃�
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
}
