package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.dao.MemberDao;
import com.ssafy.member.model.dao.MemberDaoImpl;
import com.ssafy.util.SizeConstant;

public class MemberServiceImpl implements MemberService {
	
	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;
	
	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
	}
	
	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberDao.idCheck(userId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
		// TODO validation check
		memberDao.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		return memberDao.loginMember(userId, userPwd);
	}

	@Override
	public void deleteMember(MemberDto memberDto) throws Exception {
		// TODO Auto-generated method stub
		memberDao.deleteMember(memberDto);
	}
	
	@Override
	public void updateMember(MemberDto memberDto, String userName, String userEmailId, String userEmailDomain) throws Exception {
		// TODO Auto-generated method stub
		memberDao.updateMember(memberDto, userName, userEmailId, userEmailDomain);
	}

	@Override
	public MemberDto changePassword(MemberDto memberDto, String newPassword) throws SQLException {
		// TODO Auto-generated method stub
		return memberDao.changePassword(memberDto, newPassword);
	}

	@Override
	public List<MemberDto> listMember(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		int pgno = Integer.parseInt(map.get("pgno"));
		int spl = SizeConstant.SIZE_PER_LIST;
		int start = (pgno - 1) * spl;
		map.put("start", start + "");
		map.put("spl", spl + "");
		return memberDao.listMember(map);
	}

	@Override
	public int totalMemberCount(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.totalMemberCount(map);
	}
}
