package com.ssafy.interest.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.interest.model.InterestDto;
import com.ssafy.interest.model.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	private InterestMapper interestMapper;
	
	@Override
	public int insertInterest(InterestDto dto) throws Exception {
		return interestMapper.insertInterest(dto);
	}

	@Override
	public boolean checkInsert(InterestDto dto) throws Exception {
		int check = interestMapper.checkInsert(dto);
		return (check>0);
	}

	@Override
	public int deleteInterest(int seq) throws Exception {
		return interestMapper.deleteInterest(seq);
	}

	@Override
	public List<InterestDto> selectInterest(String userId) throws Exception {
		return interestMapper.selectInterest(userId);
	}

	@Override
	public String getRegion(String id) {
		return interestMapper.getRegion(id);
	}

	@Override
	public void mainChange(Map<String, Integer> seqMap) {
		interestMapper.mainChange(seqMap);
	}

	@Override
	public int getMainInterestSeq(String id) {
		return interestMapper.getMainInterestSeq(id);
	}
	
}
