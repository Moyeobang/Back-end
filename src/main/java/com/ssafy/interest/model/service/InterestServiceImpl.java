package com.ssafy.interest.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.interest.model.InterestDto;
import com.ssafy.interest.model.mapper.InterestMapper;
import com.ssafy.interest.model.mapper.InterestDaoImp;

@Service
public class InterestServiceImpl implements InterestService {

	private static InterestService interestService = new InterestServiceImpl();
	private InterestMapper interestdao;
	
	private InterestServiceImpl() {
		interestdao = InterestDaoImp.getInstance();
	}
	
	public static InterestService getInstance() {
		return interestService;
	}
	
	@Override
	public int insertInterest(InterestDto dto) throws Exception {
		return interestdao.insertInterest(dto);
	}

	@Override
	public boolean checkInsert(InterestDto dto) throws Exception {
		int check = interestdao.checkInsert(dto);
		return (check>0);
	}

	@Override
	public int deleteInterest(int seq) throws Exception {
		return interestdao.deleteInterest(seq);
	}

	@Override
	public List<InterestDto> selectInterest(String userId) throws Exception {
		return interestdao.selectInterest(userId);
	}

	@Override
	public String getRegion(String id) {
		return interestdao.getRegion(id);
	}

	@Override
	public int mainChange(int beforeSeq, int seq) {
		return interestdao.mainChange(beforeSeq, seq);
	}

	@Override
	public int getMainInterestSeq(String id) {
		// TODO Auto-generated method stub
		return interestdao.getMainInterestSeq(id);
	}
	
}
