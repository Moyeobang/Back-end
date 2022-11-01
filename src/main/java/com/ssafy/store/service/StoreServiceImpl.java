package com.ssafy.store.service;

import com.ssafy.store.StoreDto;
import com.ssafy.store.dao.StoreDao;
import com.ssafy.store.dao.StoreDaoImpl;

public class StoreServiceImpl implements StoreService{

	private static StoreService storeService = new StoreServiceImpl();
	private StoreDao storeDao;
	
	private StoreServiceImpl() {
		storeDao = StoreDaoImpl.getMemberDao();
	}
	
	public static StoreService getStoreService() {
		return storeService;
	}
	
	@Override
	public void insertStore(StoreDto storeDto) throws Exception {
		// TODO Auto-generated method stub
		storeDao.insertStore(storeDto);
	}

	@Override
	public int countStore() throws Exception {
		// TODO Auto-generated method stub
		return storeDao.countStore();
	}

}
