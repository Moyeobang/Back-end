package com.ssafy.store.dao;

import java.sql.SQLException;

import com.ssafy.store.StoreDto;

public interface StoreDao {
	void insertStore(StoreDto storeDto) throws SQLException;
	int countStore() throws SQLException;
}
