package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.jwt.TokenInfo;
import com.ssafy.jwt.util.JwtTokenProvider;
import com.ssafy.member.model.Member;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.PwdChangeRequestDto;
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
    
    // For mail
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String FROM_ADDRESS = "";

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
        // 1. Login ID/PW ??? ???????????? Authentication ?????? ??????
        // ?????? authentication ??? ?????? ????????? ???????????? authenticated ?????? false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        // 2. ?????? ?????? (????????? ???????????? ??????)??? ??????????????? ??????
        // authenticate ???????????? ????????? ??? CustomUserDetailsService ?????? ?????? loadUserByUsername ???????????? ??????
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

 
        // 3. ?????? ????????? ???????????? JWT ?????? ??????
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        
        // 4. ????????? JWT ?????? ??? refresh token ??????
        memberMapper.updateRefreshToken(memberId, tokenInfo.getRefreshToken());
        
        return tokenInfo;
    }

	@Override
	public void deleteRefreshToken(String userId) throws Exception {
		// TODO Auto-generated method stub
		memberMapper.updateRefreshToken(userId, null);
	}
	
	public String getPasswordById(String userId) throws Exception {
		return memberMapper.getPasswordById(userId);
	}

	@Override
	public MemberDto getUserInfo(String userId) throws Exception {
		MemberDto memberDto = memberMapper.getUserInfo(userId);
	    List<String> roles = memberMapper.getRoles(userId);
	    memberDto.setRoles(roles);
	    return memberDto;
	}

	@Override
	public String getRefreshToken(String userId) throws Exception {
		return memberMapper.getRefreshToken(userId);
	}

	@Override
	public String createAccessToken(String userId) throws Exception {
		String password = this.getPasswordById(userId);
        // userId - password?????? ??????????????? ????????????, DB??? ????????? ?????? ??????????????? ????????????. ??????????????? userId, password, roles??? ????????????.
		// ??????????????? ?????? `ROLE_` prefix??? ?????? Authentication ????????? ???????????? token??? ????????????. 
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return jwtTokenProvider.createAccessToken(authorities, authentication);
	}

	@Override
	public String getMemberEmail(String userId) throws Exception {
		return memberMapper.getMemberEmail(userId);
	}

	@Override
	public void sendMail(String randPwd, String memberEmail) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		System.out.println("FROM : "+FROM_ADDRESS);
		System.out.println("TO : "+memberEmail);
		message.setTo(memberEmail);
		message.setFrom(FROM_ADDRESS);
		message.setSubject("[WhereIsOurHome] Forgot Password Authentication");
		message.setText("??????????????? ????????? ???????????????.");
		message.setText("?????? ?????? ??????????????? "+randPwd+"?????????.");
		mailSender.send(message);		
	}
	
	@Override
	public boolean changePassword(String userId, String newPassword) throws SQLException {
		return memberMapper.changePassword(userId, newPassword) == 1;
	}

}
