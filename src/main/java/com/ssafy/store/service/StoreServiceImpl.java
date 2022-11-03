package com.ssafy.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.store.model.StoreDto;
import com.ssafy.store.model.mapper.StoreMapper;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreMapper storeMapper;
	
	@Override
	public void insertStore(StoreDto storeDto) throws Exception {
		// TODO Auto-generated method stub
		storeMapper.insertStore(storeDto);
	}

	@Override
	public int countStore() throws Exception {
		// TODO Auto-generated method stub
		return storeMapper.countStore();
	}

}
