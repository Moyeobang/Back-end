package com.ssafy.infra.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.infra.model.InfraDto;
import com.ssafy.infra.model.RandHouse;

public interface InfraMapper {

	List<RandHouse> getRandHouse(String sidoCode) throws SQLException;

	InfraDto getInfraCount(Map<String, Object> map) throws SQLException;

	void insertStatistics(Map<String, Object> map) throws SQLException;
}
