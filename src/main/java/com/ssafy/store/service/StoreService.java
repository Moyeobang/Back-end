package com.ssafy.store.service;

import com.ssafy.store.model.StoreDto;

public interface StoreService {
	void insertStore(StoreDto storeDto) throws Exception;
	int countStore() throws Exception;
}
