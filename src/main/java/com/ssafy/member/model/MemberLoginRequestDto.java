package com.ssafy.member.model;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
	private String memberId;
	private String password;
}
