package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.jwt.TokenInfo;
import com.ssafy.jwt.util.JwtTokenProvider;
import com.ssafy.member.model.Member;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.util.ParameterCheck;
import com.ssafy.util.SizeConstant;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

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
		int pgno = ParameterCheck.notNumberToZero(map.get("pgno"));
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
	
	public Member findByMemberId(String userId) throws Exception{
		return memberMapper.findByMemberId(userId);
	}
	

//	@Transactional(readOnly = true)
    @Transactional
    public TokenInfo login(String memberId, String password) throws SQLException {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);
 
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
 
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        
        // 4. 생성된 JWT 토큰 중 refresh token 저장
        memberMapper.updateRefreshToken(memberId, tokenInfo.getRefreshToken());
        
        return tokenInfo;
    }
}
