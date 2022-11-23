package com.ssafy.securityindex.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.securityindex.model.SecurityIndexDetailDto;
import com.ssafy.securityindex.model.SecurityIndexDto;
import com.ssafy.securityindex.model.mapper.SecurityIndexMapper;

@Service
public class SecurityIndexServiceImpl {

	@Autowired
	SecurityIndexMapper securityIndexMapper;

	public void insertData(SecurityIndexDetailDto sIndexDto) throws SQLException {
		securityIndexMapper.insertData(sIndexDto);
	}

	public SecurityIndexDetailDto getSecurityIndex(String sigugunCode) {
		return securityIndexMapper.getSecurityIndex(sigugunCode);
	}

}
