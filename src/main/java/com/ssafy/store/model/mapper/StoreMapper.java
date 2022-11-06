package com.ssafy.store.model.mapper;

import java.sql.SQLException;

import com.ssafy.store.model.StoreDto;

public interface StoreMapper {
	void insertStore(StoreDto storeDto) throws SQLException;
	int countStore() throws SQLException;
}
