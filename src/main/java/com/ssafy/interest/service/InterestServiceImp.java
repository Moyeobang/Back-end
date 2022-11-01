package com.ssafy.interest.service;

import java.util.List;

import com.ssafy.interest.dao.InterestDao;
import com.ssafy.interest.dao.InterestDaoImp;
import com.ssafy.interest.model.InterestDto;

public class InterestServiceImp implements InterestService {

	private static InterestService interestService = new InterestServiceImp();
	private InterestDao interestdao;
	
	private InterestServiceImp() {
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
		return interestdao.checkInsert(dto);
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
