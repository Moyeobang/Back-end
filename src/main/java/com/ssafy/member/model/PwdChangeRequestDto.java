package com.ssafy.member.model;

import lombok.Data;

@Data
public class PwdChangeRequestDto {
	private String currentPassword;
	private String newPassword;
}
