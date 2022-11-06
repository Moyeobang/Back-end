package com.ssafy.store.model.service;

import com.ssafy.store.model.StoreDto;

public interface StoreService {
	void insertStore(StoreDto storeDto) throws Exception;
	int countStore() throws Exception;
}
