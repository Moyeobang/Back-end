package com.ssafy.housedeal.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.model.HouseDto;
import com.ssafy.housedeal.model.HouseDealDto;

@Mapper
public interface HouseDealMapper {

	List<HouseDealDto> listHouseDeal(Map<String, String> map) throws SQLException;

	int totalHouseDealCount(Map<String, String> map) throws SQLException;

	HouseDealDto getHouseDeal(long no) throws SQLException;

	int insertHouseDeal(HouseDealDto houseDealDto) throws SQLException;
	
	void deleteHouseDeal(long no) throws SQLException;
	
	int updateHouseDeal(HouseDealDto houseDealDto) throws SQLException;

}
