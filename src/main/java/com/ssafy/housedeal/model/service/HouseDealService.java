package com.ssafy.housedeal.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.housedeal.HouseDealDto;

public interface HouseDealService {

	List<HouseDealDto> listHouseDeal(Map<String, String> map) throws SQLException;

	int totalHouseDealCount(Map<String, String> map) throws SQLException;

	HouseDealDto getHouseDeal(long no) throws SQLException;

	int insertHouseDeal(HouseDealDto houseDealDto) throws SQLException;

	void deleteHouseDeal(long no) throws SQLException;

	int updateHouseDeal(HouseDealDto houseDealDto) throws SQLException;
}
