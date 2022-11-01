package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberService {
	int idCheck(String userId) throws Exception; // ���대�� 以�蹂듦���
	void joinMember(MemberDto memberDto) throws Exception; // ����媛���
	void deleteMember(MemberDto memberDto) throws Exception; // ��������
	void updateMember(MemberDto memberDto, String userName, String userEmailId, String userEmailDomain) throws Exception; // ������蹂대�寃�
	MemberDto changePassword(MemberDto memberDto, String newPassword) throws SQLException; // 鍮�諛�踰��� 蹂�寃�
	List<MemberDto> listMember(Map<String, String> map) throws Exception;
	int totalMemberCount(Map<String, String> map) throws Exception;
	MemberDto loginMember(Map<String, String> map) throws Exception;
}
