package com.ssafy.store.service;

import com.ssafy.store.StoreDto;

public interface StoreService {
	void insertStore(StoreDto storeDto) throws Exception;
	int countStore() throws Exception;
}
