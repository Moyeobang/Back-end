package com.ssafy.securityindex.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.securityindex.model.SecurityIndexDetailDto;
import com.ssafy.securityindex.model.mapper.SecurityIndexMapper;

@Service
public class SecurityIndexServiceImpl {
	
	@Autowired
	SecurityIndexMapper securityIndexMapper;

	public void insertData(SecurityIndexDetailDto sIndexDto) throws SQLException {
		try {
			securityIndexMapper.insertData(sIndexDto);
		}catch (Exception e) {
			System.out.println("사회안전지수 데이터 입력중 에러 발생");
			e.printStackTrace();
		}
	}
	
	public void sample() {
		System.out.println("안녕!");
	}

}
