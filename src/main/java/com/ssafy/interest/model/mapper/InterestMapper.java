package com.ssafy.interest.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.interest.model.InterestDto;

public interface InterestMapper {
	int getMainInterestSeq(String id);
	List<InterestDto> selectInterest(String userId) throws SQLException;
	int insertInterest(InterestDto dto) throws SQLException;
	int checkInsert(InterestDto dto) throws SQLException;
	int deleteInterest(int seq) throws SQLException;
	String getRegion(String id);
	void mainChange(Map<String, Integer> seqMap);
}
