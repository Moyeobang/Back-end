package com.ssafy.interest.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.interest.model.InterestDto;

public interface InterestService {
	List<InterestDto> selectInterest(String userId) throws Exception;
	int insertInterest(InterestDto dto) throws Exception;
	boolean checkInsert(InterestDto dto) throws Exception;
	int deleteInterest(int seq) throws Exception;
	String getRegion(String id);
	void mainChange(Map<String, Integer> seqMap);
	int getMainInterestSeq(String id);
}
