package com.ssafy.jwt.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponse {
	private final String message = "fail";
	private String content;
}
