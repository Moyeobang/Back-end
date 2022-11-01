package com.ssafy.interest.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.interest.model.InterestDto;

public interface InterestDao {
	int getMainInterestSeq(String id);
	List<InterestDto> selectInterest(String userId) throws SQLException;
	int insertInterest(InterestDto dto) throws SQLException;
	boolean checkInsert(InterestDto dto) throws SQLException;
	int deleteInterest(int seq) throws SQLException;
	String getRegion(String id);
	int mainChange(int beforeSeq, int seq);
}
