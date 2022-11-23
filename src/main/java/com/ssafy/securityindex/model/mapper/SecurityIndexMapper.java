package com.ssafy.securityindex.model.mapper;

import java.sql.SQLException;

import com.ssafy.securityindex.model.SecurityIndexDetailDto;

public interface SecurityIndexMapper {
	public void insertData(SecurityIndexDetailDto sIndexDto) throws SQLException;
	public SecurityIndexDetailDto getSecurityIndex(String sigugunCode);
}
