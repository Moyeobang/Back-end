package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.house.model.HouseDealDto2;
import com.ssafy.house.model.HouseDto;
import com.ssafy.house.model.HouseInfoDto;
public interface HouseService {

	List<HouseDto> listHouse(Map<String, String> map) throws SQLException;

	int totalHouseCount(Map<String, String> map) throws SQLException;

	HouseDto getHouse(long aptCode) throws SQLException;

	int insertHouse(HouseDto houseDto) throws SQLException;
	
	List<HouseDealDto2> listDeal(long aptCode) throws SQLException;

	List<HouseInfoDto> getHouseList(Map<String, String> houseParameter) throws SQLException;
}
