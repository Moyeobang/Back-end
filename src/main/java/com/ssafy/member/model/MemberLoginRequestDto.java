package com.ssafy.member.model;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
	private String userid;
	private String userpwd;
}
