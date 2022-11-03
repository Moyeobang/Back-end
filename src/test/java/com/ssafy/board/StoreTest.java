package com.ssafy.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.store.model.StoreDto;
import com.ssafy.store.model.service.StoreService;

public class StoreTest extends SpringbootRestapiApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(StoreTest.class);

	@Autowired
	StoreService storeService;

	@Test
	@Ignore
	public void nullTest() {
		assertNotNull(storeService);
		logger.debug("storeService : {}", storeService);
	}

	@Test
	@Ignore
	public void count() throws Exception {
		int cnt = storeService.countStore();
		logger.debug("cnt : {}", cnt);
		assertEquals(cnt, 11932);
	}

	@Test
	public void insert() throws Exception {
		StoreDto storeDto = new StoreDto("9999", "가짜 분식", "음식", "3611010700", null, null, null);
		storeService.insertStore(storeDto);
	}

}
