package com.ssafy.interest.service;

import java.util.List;

import com.ssafy.interest.model.InterestDto;

public interface InterestService {
	List<InterestDto> selectInterest(String userId) throws Exception;
	int insertInterest(InterestDto dto) throws Exception;
	boolean checkInsert(InterestDto dto) throws Exception;
	int deleteInterest(int seq) throws Exception;
	String getRegion(String id);
	int mainChange(int beforeSeq, int seq);
	int getMainInterestSeq(String id);
}
