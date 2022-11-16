package com.ssafy.member.model;

import com.ssafy.jwt.TokenInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * 아이디-패스워드 로그인시 유저 정보와 accessToken, refress token을 발급하기 위한 DTO
 *
 */
@Getter
@Setter
public class MemberInfoDto {
	private String userId;
	private String userName;
	private TokenInfo tokenInfo;	
}
