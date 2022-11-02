package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.util.SizeConstant;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	

	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
		// TODO validation check
		memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) throws Exception {
		return memberMapper.loginMember(map);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		// TODO Auto-generated method stub
		memberMapper.deleteMember(userId);
	}
	
	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		// TODO Auto-generated method stub
		memberMapper.updateMember(memberDto);
	}

	@Override
	public MemberDto changePassword(Map<String, String> map) throws SQLException {
		// TODO Auto-generated method stub
		return memberMapper.changePassword(map);
	}

	@Override
	public List<MemberDto> listMember(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		int pgno = Integer.parseInt(map.get("pgno"));
		int spl = SizeConstant.SIZE_PER_LIST;
		int start = (pgno - 1) * spl;
		map.put("start", start + "");
		map.put("spl", spl + "");
		return memberMapper.listMember(map);
	}

	@Override
	public int totalMemberCount(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.totalMemberCount(map);
	}

	@Override
	public MemberDto getMember(String userId) throws Exception {
		return memberMapper.getMember(userId);
	}
}
